package com.openhabbo.core.protocol.encryption;

import com.openhabbo.api.networking.communication.protocol.encryption.KeyStore;

import java.math.BigInteger;
import java.util.Random;

public class DiffieHellman implements KeyStore {
    public int BITLENGTH = 30;

    private BigInteger prime;
    private BigInteger generator;

    private BigInteger privateKey;
    private BigInteger publicKey;

    private BigInteger publicClientKey;

    private BigInteger sharedKey;

    public DiffieHellman() {
        this.publicKey = BigInteger.valueOf(0);
        Random random = new Random();
        while (this.publicKey.intValue() == 0) {
            this.prime = BigInteger.probablePrime(BITLENGTH, random);
            this.generator = BigInteger.probablePrime(BITLENGTH, random);

            this.privateKey = new BigInteger(generateRandomHexString(BITLENGTH), 16);

            if (this.privateKey.intValue() < 1) {
                continue;
            }

            if (this.generator.intValue() > this.prime.intValue()) {
                BigInteger temp = this.prime;
                this.prime = this.generator;
                this.generator = temp;
            }

            this.publicKey = this.generator.modPow(this.privateKey, this.prime);
        }
    }

    @Override
    public void generateSharedKey(String ckey) {
        this.publicClientKey = new BigInteger(ckey);

        this.sharedKey = this.publicClientKey.modPow(this.privateKey, this.prime);
    }

    @Override
    public BigInteger getPrime() {
        return this.prime;
    }

    @Override
    public BigInteger getGenerator() {
        return this.generator;
    }

    @Override
    public BigInteger getPrivateKey() {
        return this.privateKey;
    }

    @Override
    public BigInteger getPublicKey() {
        return this.publicKey;
    }

    @Override
    public BigInteger getPublicClientKey() {
        return this.publicClientKey;
    }

    @Override
    public BigInteger getSharedKey() {
        return this.sharedKey;
    }

    private String generateRandomHexString(int len) {
        int rand = 0;
        String result = "";

        Random rnd = new Random();

        for (int i = 0; i < len; i++) {
            rand = 1 + (int) (rnd.nextDouble() * 254); // 1 - 255
            result += Integer.toString(rand, 16);
        }
        return result;
    }
}
