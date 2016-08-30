package com.openhabbo.core.protocol.composers;

import com.google.common.collect.Maps;
import com.openhabbo.api.networking.communication.protocol.MessageComposer;
import com.openhabbo.api.networking.communication.protocol.payload.OutgoingMessagePayload;
import com.openhabbo.core.protocol.composers.handshake.AuthenticationOKMessageComposer;
import com.openhabbo.core.protocol.composers.handshake.InitCryptoMessageComposer;
import com.openhabbo.core.protocol.composers.handshake.SecretKeyMessageComposer;
import com.openhabbo.core.protocol.composers.notifications.MotdNotificationMessageComposer;

import java.util.Map;

public abstract class AbstractMessageComposer implements MessageComposer {
    private static final Map<Class<? extends AbstractMessageComposer>, Short> headers;

    static {
        headers = Maps.newConcurrentMap();

        // Load from a file or something ?
        headers.put(InitCryptoMessageComposer.class, (short) 1621);
        headers.put(SecretKeyMessageComposer.class, (short) 3963);
        headers.put(AuthenticationOKMessageComposer.class, (short) 456);
        headers.put(MotdNotificationMessageComposer.class, (short) 1952);
    }

    public abstract void onCompose(OutgoingMessagePayload message);

    @Override
    public short getMessageId() {
        if (!headers.containsKey(this.getClass())) {
            return -1;
        }

        return headers.get(this.getClass());
    }
}
