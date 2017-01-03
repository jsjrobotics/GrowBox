package com.jsjrobotics.growbox.sharedPreferences;

public class EncryptedValue implements Flattenable<EncryptedValue> {
    public final byte[] encrypted;
    private final int encryptedLength;

    public EncryptedValue(byte[] encrypted) {
        this.encryptedLength = encrypted == null ? -1 : encrypted.length;
        this.encrypted = encrypted;
    }

    @Override
    public String flatten() {
        String result = String.valueOf(encryptedLength) + ":";
        for (int index = 0; index < encrypted.length; index++){
            byte current = encrypted[index];
            result += String.valueOf(current);
            if (index+1 < encrypted.length){
                result += ":";
            }
        }
        return result;
    }

    @Override
    public EncryptedValue inflate(String flattened) {
        String[] split = flattened.split(":");
        int dataLength = split.length-1;
        byte[] value = new byte[dataLength];
        for (int index = 0; index < value.length; index++) {
            String current = split[index+1];
            value[index] = Byte.decode(current);
        }
        return new EncryptedValue(value);
    }
}
