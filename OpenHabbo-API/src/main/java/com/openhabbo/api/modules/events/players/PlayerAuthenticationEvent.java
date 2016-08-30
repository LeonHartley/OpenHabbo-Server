package com.openhabbo.api.modules.events.players;

import com.openhabbo.api.modules.events.Event;
import com.openhabbo.api.modules.events.players.args.PlayerAuthenticationEventArgs;

import java.util.function.Consumer;

public class PlayerAuthenticationEvent extends Event<PlayerAuthenticationEventArgs> {
    public PlayerAuthenticationEvent(Consumer<PlayerAuthenticationEventArgs> callback) {
        super(callback);
    }
}
