package com.openhabbo.api.game.rooms;

import com.openhabbo.api.util.Disposable;

public interface RoomComponent extends Disposable {
    Room getRoom();
}
