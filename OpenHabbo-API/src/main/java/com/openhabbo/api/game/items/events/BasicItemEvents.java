package com.openhabbo.api.game.items.events;

import com.openhabbo.api.game.rooms.avatars.Avatar;
import com.openhabbo.api.game.rooms.avatars.types.PlayerAvatar;

public interface BasicItemEvents {
    void onPlayerUse(PlayerAvatar avatar, Integer data);

    void onAvatarStepOn(Avatar avatar);

    void onAvatarStepOff(Avatar avatar);
}
