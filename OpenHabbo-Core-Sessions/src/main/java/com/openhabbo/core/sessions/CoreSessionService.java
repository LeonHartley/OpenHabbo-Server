package com.openhabbo.core.sessions;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.networking.communication.ChannelProxy;
import com.openhabbo.api.networking.sessions.Session;
import com.openhabbo.api.networking.sessions.SessionFactory;
import com.openhabbo.api.networking.sessions.SessionService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class CoreSessionService implements SessionService {

    private final Map<UUID, Session> sessions;

    private final SessionFactory sessionFactory;

    @Inject
    public CoreSessionService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

        this.sessions = new ConcurrentHashMap<>();
    }

    public Session getSessionById(UUID id) {
        return this.sessions.get(id);
    }

    public Session createSession(UUID id, ChannelProxy channel) {
        Session session = sessionFactory.create(id, channel);

        return session;
    }

    public void disposeSession(Session session) {
        if (this.sessions.containsKey(session.getId())) {
            this.sessions.remove(session.getId());
        }

        session.dispose();
    }
}
