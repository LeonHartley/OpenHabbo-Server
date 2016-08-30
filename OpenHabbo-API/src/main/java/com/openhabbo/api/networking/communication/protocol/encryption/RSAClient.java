package com.openhabbo.api.networking.communication.protocol.encryption;

import com.openhabbo.api.util.Initialisable;

import java.math.BigInteger;

public interface RSAClient {
    String encrypt(String text);

    String decrypt(String text);

    String sign(String text);
}
