package com.jsjrobotics.growbox;

public class EncryptedValue {
    public final int encryptedLength;
    public final byte[] encrypted;

    public EncryptedValue(int encryptedLength, byte[] encrypted) {
        this.encryptedLength = encryptedLength;
        this.encrypted = encrypted;
    }
}
