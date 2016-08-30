package com.openhabbo.module.motd;

import com.openhabbo.api.modules.Module;
import com.openhabbo.api.modules.events.players.PlayerLoggedInEvent;
import com.openhabbo.api.modules.events.players.args.PlayerEventArgs;
import com.openhabbo.core.protocol.composers.notifications.MotdNotificationMessageComposer;

import java.util.UUID;

public class MotdModule extends Module {
    public MotdModule() {
        super(UUID.randomUUID());
    }

    @Override
    public void onModuleLoad() {
        this.getEventRegistry().registerEvent(new PlayerLoggedInEvent(this::onPlayerLogin));
    }

    @Override
    public void onModuleDispose() {

    }

    private void onPlayerLogin(PlayerEventArgs args) {
        args.getSession().getChannel().writeAndFlush(new MotdNotificationMessageComposer("Hiya, " + args.getSession().getPlayer().getData().getUsername() + "!\n\nWelcome to OpenHabbo!"));
    }
}
