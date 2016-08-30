package com.openhabbo.core.sessions.messaging;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.openhabbo.api.modules.events.EventArgs;
import com.openhabbo.api.networking.communication.protocol.MessageEvent;
import com.openhabbo.api.networking.communication.protocol.MessageEventConsumer;
import com.openhabbo.api.networking.communication.protocol.MessageParser;
import com.openhabbo.api.networking.communication.protocol.headers.IncomingHeaderProvider;
import com.openhabbo.api.networking.communication.protocol.payload.IncomingMessagePayload;
import com.openhabbo.api.networking.sessions.Session;
import com.openhabbo.api.networking.sessions.SessionMessageHandler;
import com.openhabbo.core.protocol.headers.Events;
import org.apache.commons.lang.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class CoreMessageHandler implements SessionMessageHandler {
    private static final Logger log = LogManager.getLogger(CoreMessageHandler.class);

    private final Map<Class<? extends MessageEvent>, List<MessageEvent>> messageListeners;
    private final IncomingHeaderProvider headerProvider;

    private final Injector systemInjector;
    private Injector injector;

    private Session session;

    @Inject
    public CoreMessageHandler(IncomingHeaderProvider headerProvider, Injector systemInjector) {
        this.headerProvider = headerProvider;
        this.systemInjector = systemInjector;
        this.messageListeners = Maps.newConcurrentMap();
    }

    @Override
    public void registerEvent(MessageEvent event) {
        if (!this.messageListeners.containsKey(event.getClass())) {
            this.messageListeners.put(event.getClass(), Lists.newCopyOnWriteArrayList());
        }

        this.messageListeners.get(event.getClass()).add(event);
        log.debug("Event registered {}", event.getClass().getName());
    }

    @Override
    public void handleEvent(IncomingMessagePayload payload) {
        final long timeStart = System.currentTimeMillis();

        final Class<? extends MessageEvent> eventClass = this.headerProvider.getEventClass(payload.getMessageId());

        if (eventClass == null) {
            log.debug("Unknown event ID {}, / {}", payload.getMessageId(), Events.valueOfId(payload.getMessageId()));
            return;
        }

        if (!this.messageListeners.containsKey(eventClass)) {
            log.debug("No message listeners registered for {}", eventClass.getName());
            return;
        }

        MessageParser messageParser = null;

        for (MessageEvent event : this.messageListeners.get(eventClass)) {
            try {
                if (messageParser == null) {
                    messageParser = this.injector.getInstance(event.getParserClass());
                    messageParser.parse(payload);
                }

                final MessageEventConsumer consumer = this.injector.getInstance(event.getConsumer());

                consumer.consume(messageParser);
            } catch (Exception e) {
                log.error("Error while handing event {}", eventClass.getName(), e);
            }

            payload.reset();
        }

        long timeTaken = System.currentTimeMillis() - timeStart;

        log.debug("Took {}ms to handle message event {}", timeTaken, eventClass);
    }

    @Override
    public void unregisterEvent(Class<? extends MessageEvent> eventClass) {
        if (this.messageListeners.containsKey(eventClass)) {
            this.messageListeners.get(eventClass).clear();
            this.messageListeners.remove(eventClass);

            log.debug("Event unregistered {}", eventClass.getName());
        }
    }

    @Override
    public <A extends EventArgs> void invoke(Class<? extends MessageEvent> eventClass, A args) {
        throw new NotImplementedException();
    }

    @Override
    public Session getSession() {
        return this.session;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;

        this.injector = this.systemInjector.createChildInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Session.class).toInstance(session);
            }
        });
    }

    @Override
    public void dispose() {
        this.messageListeners.values().forEach(List<MessageEvent>::clear);
        this.messageListeners.clear();
    }
}
