package com.openhabbo.api.game.items.bindings;

import com.openhabbo.api.game.items.instances.Item;

import java.util.Map;

public interface ItemBindingService {
    Map<String, Class<? extends Item>> getAllBindings();

    Class<? extends Item> getBindingByAlias(String alias);
}
