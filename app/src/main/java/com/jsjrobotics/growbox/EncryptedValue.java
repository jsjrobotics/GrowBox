package com.jsjrobotics.growbox;

public class EncryptedValue implements Flattenable<EncryptedValue>{
    public final int encryptedLength;
    public final byte[] encrypted;

    public EncryptedValue(int encryptedLength, byte[] encrypted) {
        this.encryptedLength = encryptedLength;
        this.encrypted = encrypted;
    }

    @Override
    public String flatten() {
        String result = String.valueOf(encrypted) + ":";
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
        return new EncryptedValue(dataLength, value);
    }
}
