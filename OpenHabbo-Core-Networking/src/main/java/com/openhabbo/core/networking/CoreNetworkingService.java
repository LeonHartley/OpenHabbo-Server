package com.openhabbo.core.networking;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.networking.NetworkingService;
import com.openhabbo.core.networking.channels.CoreChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultMessageSizeEstimator;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;

@Singleton
public class CoreNetworkingService implements NetworkingService {
    private final CoreNetworkingServiceConfig config;
    private final CoreChannelInitializer channelInitializer;
    private Logger log = LogManager.getLogger(CoreNetworkingService.class);
    private ServerBootstrap serverBootstrap;

    @Inject
    public CoreNetworkingService(CoreNetworkingServiceConfig config, CoreChannelInitializer channelInitializer) {
        this.config = config;
        this.channelInitializer = channelInitializer;
    }

    @Override
    public void initialise() {
        final EventLoopGroup acceptGroup =
                this.config.useEpoll && Epoll.isAvailable() ? new EpollEventLoopGroup(this.config.acceptGroupThreads) :
                        new NioEventLoopGroup(this.config.acceptGroupThreads);

        final EventLoopGroup channelGroup =
                this.config.useEpoll && Epoll.isAvailable() ? new EpollEventLoopGroup(this.config.channelGroupThreads) :
                        new NioEventLoopGroup(this.config.channelGroupThreads);

        final EventLoopGroup ioGroup =
                this.config.useEpoll && Epoll.isAvailable() ? new EpollEventLoopGroup(this.config.ioGroupThreads) :
                        new NioEventLoopGroup(this.config.ioGroupThreads);

        this.channelInitializer.setExecutor(channelGroup);

        this.serverBootstrap = new ServerBootstrap()
                .group(acceptGroup, ioGroup)
                .channel(this.config.useEpoll && Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childHandler(this.channelInitializer)
                .option(ChannelOption.SO_BACKLOG, this.config.backlog)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 32 * 1024)
                .option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 64 * 1024)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.MESSAGE_SIZE_ESTIMATOR, DefaultMessageSizeEstimator.DEFAULT)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 32 * 1024)
                .childOption(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 64 * 1024);

        this.bind();

        log.info("Initialized networking service on address: tcp://{}:{}",
                this.config.networkingHost, this.config.networkingPort);
    }

    private void bind() {
        try {
            this.serverBootstrap.bind(new InetSocketAddress(this.config.networkingHost, this.config.networkingPort))
                    .addListener(future -> {
                        if (!future.isSuccess()) {
                            this.onError(null);
                        }
                    });
        } catch (Exception e) {
            this.onError(e);
        }
    }

    private void onError(Exception error) {
        log.error("Failed to initialise network service on address: tcp://{}:{}",
                this.config.networkingHost, this.config.networkingPort);

        if (error != null) {
            log.error("Networking exception caught", error);
        }

        System.exit(0);
    }

    @Override
    public void dispose() {

    }
}
