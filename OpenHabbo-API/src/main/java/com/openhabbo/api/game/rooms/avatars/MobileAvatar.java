package com.openhabbo.api.game.rooms.avatars;

import com.openhabbo.api.game.rooms.util.Position;

public interface MobileAvatar {
    void moveToPosition(Position position);

    boolean isMoving();

    boolean canMove();
}
