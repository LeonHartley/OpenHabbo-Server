package com.openhabbo.game.players.auth;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.game.players.Player;
import com.openhabbo.api.game.players.PlayerFactory;
import com.openhabbo.api.game.players.auth.AuthenticationService;
import com.openhabbo.api.modules.events.SystemEventRegistry;
import com.openhabbo.api.modules.events.players.PlayerAuthenticationEvent;
import com.openhabbo.api.modules.events.players.PlayerLoggedInEvent;
import com.openhabbo.api.modules.events.players.args.PlayerAuthenticationEventArgs;
import com.openhabbo.api.modules.events.players.args.PlayerEventArgs;
import com.openhabbo.api.networking.sessions.Session;
import com.openhabbo.api.storage.dao.players.PlayerDao;
import com.openhabbo.api.threading.EventScheduler;
import com.openhabbo.core.protocol.composers.handshake.AuthenticationOKMessageComposer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class PlayerAuthenticationService implements AuthenticationService {
    private static final Logger log = LogManager.getLogger(PlayerAuthenticationService.class);

    private static boolean ASYNC_AUTH = true;

    private final PlayerDao playerDao;
    private final PlayerFactory playerFactory;
    private final EventScheduler eventScheduler;
    private final SystemEventRegistry eventRegistry;

    @Inject
    public PlayerAuthenticationService(PlayerDao playerDao, PlayerFactory playerFactory, EventScheduler eventScheduler,
                                       SystemEventRegistry systemEventRegistry) {
        this.playerDao = playerDao;
        this.playerFactory = playerFactory;
        this.eventScheduler = eventScheduler;
        this.eventRegistry = systemEventRegistry;
    }

    @Override
    public void authenticatePlayer(Session session, String ssoTicket) {
        if (!ASYNC_AUTH) {
            this.processAuthentication(session, ssoTicket);
        } else {
            this.eventScheduler.submit(() -> processAuthentication(session, ssoTicket));
        }
    }

    private void processAuthentication(Session session, String ssoTicket) {
        final PlayerAuthenticationEventArgs eventArgs = new PlayerAuthenticationEventArgs(session, ssoTicket);

        this.eventRegistry.invoke(PlayerAuthenticationEvent.class, eventArgs);

        if (eventArgs.isAuthenticationFailed()) {
            session.disconnect();
            return;
        }

        this.playerDao.getDataByTicket(ssoTicket, (playerData) -> {
            if (playerData == null) {
                session.disconnect();
                return;
            }

            final Player playerInstance = this.playerFactory.create(session, playerData);
            session.setPlayer(playerInstance);

            log.debug("Player {} logged in", session.getPlayer().getData().getUsername());

            session.getChannel().writeAndFlush(new AuthenticationOKMessageComposer());
            this.eventRegistry.invoke(PlayerLoggedInEvent.class, new PlayerEventArgs(session));
        });
    }
}
