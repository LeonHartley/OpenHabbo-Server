package com.openhabbo.core.modules.injection;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.openhabbo.api.game.items.FurnitureDefinitionService;
import com.openhabbo.api.game.navigator.categories.NavigatorCategoryService;
import com.openhabbo.api.game.players.Player;
import com.openhabbo.api.game.players.PlayerFactory;
import com.openhabbo.api.game.players.auth.AuthenticationService;
import com.openhabbo.api.game.players.inventory.PlayerInventory;
import com.openhabbo.api.game.players.inventory.PlayerInventoryFactory;
import com.openhabbo.api.modules.events.SystemEventRegistry;
import com.openhabbo.api.game.groups.GroupFactory;
import com.openhabbo.api.game.groups.GroupService;
import com.openhabbo.api.game.groups.types.Group;
import com.openhabbo.api.networking.NetworkingService;
import com.openhabbo.api.networking.communication.protocol.encryption.KeyStore;
import com.openhabbo.api.networking.communication.protocol.encryption.RSAClient;
import com.openhabbo.api.networking.communication.protocol.headers.IncomingHeaderProvider;
import com.openhabbo.api.networking.sessions.Session;
import com.openhabbo.api.networking.sessions.SessionFactory;
import com.openhabbo.api.networking.sessions.SessionMessageHandler;
import com.openhabbo.api.networking.sessions.SessionService;
import com.openhabbo.api.storage.dao.groups.GroupDao;
import com.openhabbo.api.storage.dao.items.FurnitureDefinitionDao;
import com.openhabbo.api.storage.dao.navigator.NavigatorCategoryDao;
import com.openhabbo.api.storage.dao.players.PlayerDao;
import com.openhabbo.api.storage.dao.players.inventory.PlayerInventoryDao;
import com.openhabbo.api.threading.EventScheduler;
import com.openhabbo.core.modules.events.CoreEventRegistry;
import com.openhabbo.core.networking.CoreNetworkingService;
import com.openhabbo.core.protocol.encryption.DiffieHellman;
import com.openhabbo.core.protocol.encryption.RSA;
import com.openhabbo.core.protocol.headers.EventHeaderProvider;
import com.openhabbo.core.sessions.CoreSession;
import com.openhabbo.core.sessions.CoreSessionService;
import com.openhabbo.core.sessions.messaging.CoreMessageHandler;
import com.openhabbo.core.storage.dao.groups.CoreGroupDao;
import com.openhabbo.core.storage.dao.items.CoreFurnitureDefinitionDao;
import com.openhabbo.core.storage.dao.navigator.CoreNavigatorCategoryDao;
import com.openhabbo.core.storage.dao.players.CorePlayerDao;
import com.openhabbo.core.storage.dao.players.CorePlayerInventoryDao;
import com.openhabbo.core.threading.CoreEventScheduler;
import com.openhabbo.game.groups.CoreGroupService;
import com.openhabbo.game.groups.types.GroupInstance;
import com.openhabbo.game.items.definitions.CoreFurnitureDefinitionService;
import com.openhabbo.game.navigator.CoreNavigatorCategoryService;
import com.openhabbo.game.players.PlayerInstance;
import com.openhabbo.game.players.auth.PlayerAuthenticationService;
import com.openhabbo.game.players.inventory.CorePlayerInventory;


public class OpenHabboModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bindCore();
        this.bindSession();
        this.bindNetwork();
        this.bindDatabase();
        this.bindGroups();
        this.bindPlayers();
        this.bindNavigator();
        this.bindItems();
    }

    private void bindCore() {
        bind(EventScheduler.class).to(CoreEventScheduler.class);
        bind(SystemEventRegistry.class).to(CoreEventRegistry.class);
    }

    private void bindSession() {
        bind(RSAClient.class).to(RSA.class);

        install(new FactoryModuleBuilder()
                .implement(Session.class, CoreSession.class)
                .build(SessionFactory.class));

        bind(SessionService.class).to(CoreSessionService.class);
        bind(SessionMessageHandler.class).to(CoreMessageHandler.class);
    }

    private void bindNetwork() {
        bind(IncomingHeaderProvider.class).to(EventHeaderProvider.class);
        bind(NetworkingService.class).to(CoreNetworkingService.class);
    }

    private void bindDatabase() {
        bind(PlayerDao.class).to(CorePlayerDao.class);
        bind(GroupDao.class).to(CoreGroupDao.class);
        bind(NavigatorCategoryDao.class).to(CoreNavigatorCategoryDao.class);
        bind(FurnitureDefinitionDao.class).to(CoreFurnitureDefinitionDao.class);
        bind(PlayerInventoryDao.class).to(CorePlayerInventoryDao.class);
    }

    private void bindGroups() {
        bind(GroupService.class).to(CoreGroupService.class);

        install(new FactoryModuleBuilder()
                .implement(Group.class, GroupInstance.class)
                .build(GroupFactory.class));
    }

    private void bindPlayers() {
        install(new FactoryModuleBuilder()
                .implement(Player.class, PlayerInstance.class)
                .build(PlayerFactory.class));

        install(new FactoryModuleBuilder()
                .implement(PlayerInventory.class, CorePlayerInventory.class)
                .build(PlayerInventoryFactory.class));

        bind(AuthenticationService.class).to(PlayerAuthenticationService.class);
    }

    private void bindNavigator() {
        bind(NavigatorCategoryService.class).to(CoreNavigatorCategoryService.class);
    }

    private void bindItems() {
        bind(FurnitureDefinitionService.class).to(CoreFurnitureDefinitionService.class);
    }
}
