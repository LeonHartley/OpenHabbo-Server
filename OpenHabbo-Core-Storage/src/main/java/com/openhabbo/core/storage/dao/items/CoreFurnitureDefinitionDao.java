package com.openhabbo.core.storage.dao.items;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.game.items.data.FurnitureDefinition;
import com.openhabbo.api.storage.dao.items.FurnitureDefinitionDao;
import com.openhabbo.api.threading.EventScheduler;
import com.openhabbo.core.storage.ConnectionProvider;
import com.openhabbo.core.storage.Transaction;
import com.openhabbo.core.storage.dao.AbstractDao;
import com.openhabbo.core.storage.dao.DaoEventScheduler;
import com.openhabbo.core.storage.dao.items.beans.FurnitureDefinitionBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Singleton
public class CoreFurnitureDefinitionDao extends AbstractDao implements FurnitureDefinitionDao {
    private static final Logger log = LogManager.getLogger(CoreFurnitureDefinitionDao.class);

    @Inject
    public CoreFurnitureDefinitionDao(EventScheduler eventScheduler, DaoEventScheduler daoEventScheduler,
                                      ConnectionProvider connectionProvider) {
        super(eventScheduler, daoEventScheduler, connectionProvider);
    }

    @Override
    public Map<Integer, FurnitureDefinition> getAllDefinitions() {
        Map<Integer, FurnitureDefinition> furnitureDefinitions = Maps.newHashMap();

        final Transaction transaction = this.createTransaction(Transaction.Type.SELECT, "SELECT * FROM furniture;");

        this.queueTransaction(transaction, (results) -> {
            try {
                while (results.hasResults()) {
                    final int id = results.getInteger("id");
                    final String publicName = results.getString("public_name");
                    final String itemName = results.getString("item_name");
                    final String type = results.getString("type");
                    final int width = results.getInteger("width");
                    final int length = results.getInteger("length");
                    final int spriteId = results.getInteger("sprite_id");

                    final boolean canStack = results.getString("can_stack").equals("1");
                    final boolean canSit = results.getString("can_sit").equals("1");
                    final boolean canWalk = results.getString("is_walkable").equals("1");
                    final boolean canTrade = results.getString("allow_trade").equals("1");
                    final boolean canInventoryStack = results
                            .getString("allow_inventory_stack").equals("1");

                    final int offerId = results.getInteger("flat_id");

                    final boolean canRecycle = false;
                    final boolean canMarket = false;
                    final boolean canGift = results.getString("allow_gift").equals("1");

                    final int effectId = results.getInteger("effect_id");
                    final String interaction = results.getString("interaction_type");
                    final int interactionCycleCount = results.getInteger("interaction_modes_count");
                    final String vendingIdsData = results.getString("vending_ids");
                    final Integer[] vendingIds;

                    if (vendingIdsData != null & !vendingIdsData.isEmpty()) {
                        String[] splitData = vendingIdsData.replace(" ", "").split(",");

                        vendingIds = new Integer[splitData.length];

                        for (int i = 0; i < splitData.length; i++) {
                            vendingIds[i] = Integer.parseInt(splitData[i]);
                        }
                    } else {
                        vendingIds = null;
                    }

                    final boolean requiresRights = results.getString("requires_rights").equals("1");

                    final int songId = results.getInteger("song_id");

                    final String variableHeightData = results.getString("variable_heights");

                    final double stackHeight = results.getDouble("stack_height");
                    double itemHeight = 0;

                    Double[] variableHeights = null;

                    if (!variableHeightData.isEmpty() && variableHeightData.contains(",")) {
                        String[] variableHeightArray = variableHeightData.split(",");
                        variableHeights = new Double[variableHeightArray.length];

                        for (int i = 0; i < variableHeightArray.length; i++) {
                            try {
                                variableHeights[i] = Double.parseDouble(variableHeightArray[i]);
                            } catch (Exception ignored) {

                            }
                        }
                    } else {
                        variableHeights = null;
                    }

                    if (stackHeight == 0.0) {
                        itemHeight = 0.001;
                    } else {
                        itemHeight = stackHeight;
                    }

                    furnitureDefinitions.put(id, new FurnitureDefinitionBean(id, publicName, itemName, type, width, length,
                            itemHeight, spriteId, canStack, canSit, canWalk, canTrade, canRecycle, canMarket, canGift,
                            canInventoryStack, effectId, offerId, interaction, interactionCycleCount, vendingIds,
                            requiresRights, songId, variableHeights));
                }
            } catch (Exception e) {
                log.error("Exception while loading furniture definitions", e);
            }
        });
        
        return furnitureDefinitions;
    }
}
