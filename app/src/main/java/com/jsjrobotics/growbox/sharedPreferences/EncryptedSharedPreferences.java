package com.jsjrobotics.growbox.sharedPreferences;

import android.content.SharedPreferences;

import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class EncryptedSharedPreferences {

    private final SharedPreferences mSharedPreferences;
    private final SecretKeySpec mKey;
    private final IvParameterSpec mInitVector;
    private final Optional<Cipher> mCipher;

    public static EncryptedSharedPreferences wrap(SharedPreferences sharedPreferences) {
        return new EncryptedSharedPreferences(sharedPreferences);
    }

    private EncryptedSharedPreferences(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
        mKey = new SecretKeySpec(getSecretKey(), getCode());
        mInitVector = new IvParameterSpec(getInitilizationVector());
        Optional<Cipher> cipher = Optional.empty();
        try {
             cipher = Optional.ofNullable(Cipher.getInstance(getCipherParameters()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCipher = cipher;
    }

    private byte[] getSecretKey() {
        return "abcdefghijklmnopqrstuvwxyzasdfgh".getBytes();
    }

    private byte[] getInitilizationVector() {
        return "InitilizationVec".getBytes();
    }

    private String getCode() {
        return "abcdefghijklmnopqrstuvwxyzasdfgh";
    }

    private String getCipherParameters() {
        return "AES/CFB/NoPadding";
    }

    public void write(SharedPreferenceObject value) {
        Optional<EncryptedValue> valueToWrite = encrypt(value.getData());
        if (!valueToWrite.isPresent()){
            return;
        }
        EncryptedValue encryptedValue = valueToWrite.get();
        String out = encryptedValue.flatten();

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(
                value.getKey(),
                out
        );

        editor.commit();
    }



    public Optional<String> read(SharedPreferenceObject value){
        Optional<String> savedValue = Optional.ofNullable(mSharedPreferences.getString(value.getKey(), null));
        if (!savedValue.isPresent()){
            return Optional.empty();
        }
        EncryptedValue decrypted = new EncryptedValue(null).inflate(savedValue.get());
        return decrypt(decrypted.encrypted, decrypted.encrypted.length);
    }

    private Optional<String> decrypt(byte[] encrypted, int encryptedLength){
        if (!mCipher.isPresent()){
            return Optional.empty();
        }
        Cipher cipher = mCipher.get();
        try {
            cipher.init(Cipher.DECRYPT_MODE, mKey, mInitVector);
            byte[] decrypted = new byte[cipher.getOutputSize(encryptedLength)];
            int dec_len = cipher.update(encrypted, 0, encryptedLength, decrypted, 0);
            dec_len += cipher.doFinal(decrypted, dec_len);
            return Optional.of(new String(decrypted));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<EncryptedValue> encrypt(String data) {
        if (!mCipher.isPresent()){
            return Optional.empty();
        }
        Cipher cipher = mCipher.get();
        try {
            byte[] input = data.getBytes();
            cipher.init(Cipher.ENCRYPT_MODE, mKey, mInitVector);
            byte[] encrypted = new byte[cipher.getOutputSize(input.length)];
            int encryptedLength = cipher.update(input, 0, input.length, encrypted, 0);
            encryptedLength += cipher.doFinal(encrypted, encryptedLength);
            return Optional.of(new EncryptedValue(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
