package com.openhabbo.game.items;

import com.openhabbo.api.game.items.data.FurnitureDefinition;
import com.openhabbo.api.game.items.data.stuff.StuffData;
import com.openhabbo.api.game.items.instances.Item;
import com.openhabbo.api.game.rooms.Room;
import com.openhabbo.api.game.rooms.avatars.Avatar;
import com.openhabbo.api.game.rooms.util.Position;

import java.util.UUID;

public abstract class RoomItem<T extends StuffData> implements Item<T> {
    private final UUID id;
    private final UUID playerId;

    private final Integer baseItem;
    private final T stuffData;
    private final Room room;
    private final FurnitureDefinition furnitureDefinition;

    private Position position;

    public RoomItem(UUID id, Integer baseItem, Room room, Position position, FurnitureDefinition furnitureDefinition, T stuffData, UUID playerId) {
        this.id = id;
        this.baseItem = baseItem;
        this.room = room;
        this.position = position;
        this.furnitureDefinition = furnitureDefinition;
        this.stuffData = stuffData;
        this.playerId = playerId;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public FurnitureDefinition getDefinition() {
        return this.furnitureDefinition;
    }

    @Override
    public T getData() {
        return this.stuffData;
    }

    @Override
    public UUID getPlayerId() {
        return this.playerId;
    }

    @Override
    public boolean canAvatarStepOn(Avatar avatar) {
        return true;
    }

    @Override
    public boolean canAvatarStepOff(Avatar avatar) {
        return true;
    }

    @Override
    public void onAvatarStepOn(Avatar avatar) {

    }

    @Override
    public void onAvatarStepOff(Avatar avatar) {

    }
}
