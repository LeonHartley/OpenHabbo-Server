package com.openhabbo.api.networking.communication.protocol.encryption;

import java.math.BigInteger;

public interface KeyStore {
    void generateSharedKey(String ckey);

    BigInteger getPrime();
    BigInteger getGenerator();

    BigInteger getPrivateKey();
    BigInteger getPublicKey();
    BigInteger getPublicClientKey();
    BigInteger getSharedKey();
}
