package com.openhabbo.api.game.items.events;

import com.openhabbo.api.game.rooms.avatars.Avatar;

public interface BasicItemConstraints {
    boolean canAvatarStepOn(Avatar avatar);

    boolean canAvatarStepOff(Avatar avatar);
}
