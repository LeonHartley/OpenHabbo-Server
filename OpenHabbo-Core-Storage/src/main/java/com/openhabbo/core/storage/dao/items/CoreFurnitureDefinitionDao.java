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

//        try (Transaction transaction = this.createTransaction("SELECT * FROM furniture;")) {
//            while (transaction.getResults().hasResults()) {
//                final int id = transaction.getResults().getInteger("id");
//                final String publicName = transaction.getResults().getString("public_name");
//                final String itemName = transaction.getResults().getString("item_name");
//                final String type = transaction.getResults().getString("type");
//                final int width = transaction.getResults().getInteger("width");
//                final int length = transaction.getResults().getInteger("length");
//                final int spriteId = transaction.getResults().getInteger("sprite_id");
//
//                final boolean canStack = transaction.getResults().getString("can_stack").equals("1");
//                final boolean canSit = transaction.getResults().getString("can_sit").equals("1");
//                final boolean canWalk = transaction.getResults().getString("is_walkable").equals("1");
//                final boolean canTrade = transaction.getResults().getString("allow_trade").equals("1");
//                final boolean canInventoryStack = transaction.getResults()
//                        .getString("allow_inventory_stack").equals("1");
//
//                final int offerId = transaction.getResults().getInteger("flat_id");
//
//                final boolean canRecycle = false;
//                final boolean canMarket = false;
//                final boolean canGift = transaction.getResults().getString("allow_gift").equals("1");
//
//                final int effectId = transaction.getResults().getInteger("effectid");
//                final String interaction = transaction.getResults().getString("interaction_type");
//                final int interactionCycleCount = transaction.getResults().getInteger("interaction_modes_count");
//                final String vendingIdsData = transaction.getResults().getString("vending_ids");
//                final Integer[] vendingIds;
//
//                if (vendingIdsData != null & !vendingIdsData.isEmpty()) {
//                    String[] splitData = vendingIdsData.replace(" ", "").split(",");
//
//                    vendingIds = new Integer[splitData.length];
//
//                    for (int i = 0; i < splitData.length; i++) {
//                        vendingIds[i] = Integer.parseInt(splitData[i]);
//                    }
//                } else {
//                    vendingIds = null;
//                }
//
//                final boolean requiresRights = transaction.getResults().getString("requires_rights").equals("1");
//
//                final int songId = transaction.getResults().getInteger("song_id");
//
//                final String variableHeightData = transaction.getResults().getString("variable_heights");
//
//                final double stackHeight = transaction.getResults().getDouble("stack_height");
//                double itemHeight = 0;
//
//                Double[] variableHeights = null;
//
//                if (!variableHeightData.isEmpty() && variableHeightData.contains(",")) {
//                    String[] variableHeightArray = variableHeightData.split(",");
//                    variableHeights = new Double[variableHeightArray.length];
//
//                    for (int i = 0; i < variableHeightArray.length; i++) {
//                        try {
//                            variableHeights[i] = Double.parseDouble(variableHeightArray[i]);
//                        } catch (Exception ignored) {
//
//                        }
//                    }
//                } else {
//                    variableHeights = null;
//                }
//
//                if (stackHeight == 0.0) {
//                    itemHeight = 0.001;
//                } else {
//                    itemHeight = stackHeight;
//                }
//
//                furnitureDefinitions.put(id, new FurnitureDefinitionBean(id, publicName, itemName, type, width, length,
//                        itemHeight, spriteId, canStack, canSit, canWalk, canTrade, canRecycle, canMarket, canGift,
//                        canInventoryStack, effectId, offerId, interaction, interactionCycleCount, vendingIds,
//                        requiresRights, songId, variableHeights));
//            }
//        } catch (Exception e) {
//            log.error("Error while loading furniture definitions", e);
//        }

        return furnitureDefinitions;
    }
}
