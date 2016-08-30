package com.openhabbo.module.basicitems;

import com.openhabbo.api.game.items.data.FurnitureDefinition;
import com.openhabbo.api.game.items.data.stuff.types.LegacyStuffData;
import com.openhabbo.api.game.rooms.Room;
import com.openhabbo.api.game.rooms.avatars.types.PlayerAvatar;
import com.openhabbo.api.game.rooms.util.Position;
import com.openhabbo.game.items.RoomItem;

import java.util.UUID;

public class DiceItem extends RoomItem<LegacyStuffData> {
    public DiceItem(UUID id, Integer baseItem, Room room, Position position, FurnitureDefinition furnitureDefinition, LegacyStuffData stuffData, UUID playerId) {
        super(id, baseItem, room, position, furnitureDefinition, stuffData, playerId);
    }

    @Override
    public void onPlayerUse(PlayerAvatar avatar, Integer data) {
        // handle dice logic.
    }
}