package com.jsjrobotics.growbox;

import android.content.SharedPreferences;

import com.jsjrobotics.growbox.display.detail.WateringSchedule;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class EncryptedSharedPreferences {

    private static final String LENGTH_KEY = "_CIPHER_LENGTH";
    private final SharedPreferences mSharedPreferences;
    private final SecretKeySpec mKey;
    private final IvParameterSpec mInitVector;
    private final Optional<Cipher> mCipher;

    public static EncryptedSharedPreferences wrap(SharedPreferences sharedPreferences) {
        return new EncryptedSharedPreferences(sharedPreferences);
    }

    private EncryptedSharedPreferences(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
        byte[] secretKey = getKey();
        byte[] initilizationVector = getInitilizationVector();
        mKey = new SecretKeySpec(secretKey, getCode());
        mInitVector = new IvParameterSpec(initilizationVector);
        Optional<Cipher> cipher = Optional.empty();
        try {
             cipher = Optional.ofNullable(Cipher.getInstance(getCipherCode()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCipher = cipher;
    }

    private byte[] getKey() {
        return "TheKey".getBytes();
    }

    private byte[] getInitilizationVector() {
        return "InitilizationVector".getBytes();
    }

    private String getCode() {
        String[] in = {"a", "b", "c", "d", "e", "s"};
        return in[0] + in[4] + in[5];
    }

    private String getCipherCode() {
        return "AES/OFB/ISO10126Padding";
    }

    public void write(SharedPreferenceObject value) {
        Optional<EncryptedValue> valueToWrite = encrypt(value.getData());
        if (!valueToWrite.isPresent()){
            return;
        }
        EncryptedValue encryptedValue = valueToWrite.get();
        String out = "";
        for (byte b : encryptedValue.encrypted){
            out += (char) b;
        }

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(
                value.getCipherKey(),
                encryptedValue.encryptedLength
        );

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
        Cipher cipher = mCipher.get();
        try {
            byte[] encrypted = savedValue.get().getBytes();
            int encryptedLength = mSharedPreferences.getInt(value.getCipherKey(), -1);
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
            return Optional.of(new EncryptedValue(encryptedLength, encrypted));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
