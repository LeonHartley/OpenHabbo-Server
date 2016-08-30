package com.openhabbo.api.game.rooms.avatars;

import com.openhabbo.api.game.rooms.Room;
import com.openhabbo.api.game.rooms.util.Position;
import com.openhabbo.api.networking.communication.protocol.Composable;
import com.openhabbo.api.util.Disposable;

public interface Avatar extends Composable, Disposable {
    int getId();

    Position getPosition();

    void setPosition(Position position);

    Room getRoom();

    void setRoom();
}
