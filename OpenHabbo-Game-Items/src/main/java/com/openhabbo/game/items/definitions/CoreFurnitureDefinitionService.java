package com.openhabbo.game.items.definitions;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.game.items.FurnitureDefinitionService;
import com.openhabbo.api.game.items.data.FurnitureDefinition;
import com.openhabbo.api.storage.dao.items.FurnitureDefinitionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Singleton
public class CoreFurnitureDefinitionService implements FurnitureDefinitionService {
    private final Logger log = LogManager.getLogger(CoreFurnitureDefinitionService.class);

    private Map<Integer, FurnitureDefinition> furnitureDefinitions;

    @Inject
    public CoreFurnitureDefinitionService(final FurnitureDefinitionDao furnitureDefinitionDao) {
        furnitureDefinitionDao.getAllDefinitions((definitions) -> {
            this.furnitureDefinitions = definitions;

            log.info("Loaded {} furniture definitions", this.furnitureDefinitions.size());
        });
    }

    @Override
    public FurnitureDefinition getFurnitureDefinitionById(Integer id) {
        return this.furnitureDefinitions.get(id);
    }
}
