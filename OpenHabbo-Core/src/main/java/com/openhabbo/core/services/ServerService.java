package com.openhabbo.core.services;

import com.google.inject.Inject;
import com.openhabbo.api.networking.NetworkingService;
import com.openhabbo.api.services.Service;

public class ServerService implements Service {

    private final NetworkingService networkingService;

    @Inject
    public ServerService(NetworkingService networkingService) {
        this.networkingService = networkingService;
    }

    @Override
    public void onServiceStarted() {
        this.networkingService.initialise();
    }
}
