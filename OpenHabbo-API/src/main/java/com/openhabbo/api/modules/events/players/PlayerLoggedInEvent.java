package com.openhabbo.api.modules.events.players;

import com.openhabbo.api.modules.events.Event;
import com.openhabbo.api.modules.events.players.args.PlayerEventArgs;

import java.util.function.Consumer;

public class PlayerLoggedInEvent extends Event<PlayerEventArgs> {
    public PlayerLoggedInEvent(Consumer<PlayerEventArgs> callback) {
        super(callback);
    }
}
