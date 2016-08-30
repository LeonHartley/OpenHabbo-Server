package com.openhabbo.core.networking.channels;

import com.google.inject.Inject;
import com.openhabbo.api.networking.communication.protocol.payload.IncomingMessagePayload;
import com.openhabbo.api.networking.sessions.Session;
import com.openhabbo.api.networking.sessions.SessionService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.UUID;

@ChannelHandler.Sharable
public class CoreChannelHandler extends SimpleChannelInboundHandler<IncomingMessagePayload> {

    private final Logger log = LogManager.getLogger(CoreChannelHandler.class);
    private final SessionService sessionService;
    private final AttributeKey<Session> SESSION_ATTRIBUTE = AttributeKey.valueOf("Session.attr");

    @Inject
    public CoreChannelHandler(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final Session session = this.sessionService.createSession(UUID.randomUUID(), new CoreChannelProxy(ctx));

        if(session == null) {
            ctx.disconnect();
            return;
        }

        ctx.attr(SESSION_ATTRIBUTE).set(session);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final Session session = ctx.attr(SESSION_ATTRIBUTE).get();

        if(session == null) {
            return;
        }

        this.sessionService.disposeSession(session);
        ctx.attr(SESSION_ATTRIBUTE).remove();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(ctx.channel().isActive()) {
            ctx.close();
        }

        if(cause instanceof IOException) {
            return;
        }

        log.error("Channel exception caught", cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IncomingMessagePayload incomingMessagePayload) throws Exception {
        final Session session = ctx.attr(SESSION_ATTRIBUTE).get();

        if(session == null) {
            return;
        }

        if(!session.isInitialised()) {
            session.onConnectionInitialised();
        }

        log.debug("Message received with ID: " + incomingMessagePayload.getMessageId());
        session.getMessageHandler().handleEvent(incomingMessagePayload);

    }
}
