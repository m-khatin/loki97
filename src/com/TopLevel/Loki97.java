package com.TopLevel;

import com.LowLevel.KeyGeneration;
import com.TopLevel.Modes.*;
import com.company.EncryptionMode;

import java.util.ArrayList;
import java.util.Arrays;

public class Loki97 {

    final static int blockSize = 16;

    public static byte[] encrypt(byte[] inputBuffer, byte[] keyX, byte[] iv, EncryptionMode modeName) throws IllegalStateException {
        KeyGeneration keyGeneration = new KeyGeneration();
        int blocksQuantity;
        ArrayList<byte[]> blocksArray;

        if ((inputBuffer.length % blockSize) == 0)
            blocksQuantity = inputBuffer.length / 16;
        else
            blocksQuantity = inputBuffer.length / 16 + 1;

        blocksArray = new ArrayList<>(blocksQuantity);

        for (int i = 0; i < blocksQuantity; i++) {
            int step = i * blockSize;
            blocksArray.add(Arrays.copyOfRange(inputBuffer, step, blockSize + step));
        }

        Object key = keyGeneration.makeKey(keyX);

        IMode mode;
        switch (modeName) {
            case ECB:
                mode = new ECB();
                break;
            case CBC:
                mode = new CBC();
                break;
            case CFB:
                mode = new CFB();
                break;
            case OFB:
                mode = new OFB();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + modeName);
        }

        return mode.encrypt(blocksArray, key, iv);
    }

    public static byte[] decrypt(byte[] inputBuffer, byte[] keyX, byte[] iv, EncryptionMode modeName) throws IllegalStateException {
        KeyGeneration keyGeneration = new KeyGeneration();
        int blocksQuantity;
        int badCharCount = 0;
        ArrayList<byte[]> blocksArray;

        if ((inputBuffer.length % blockSize) == 0)
            blocksQuantity = inputBuffer.length / 16;
        else
            blocksQuantity = inputBuffer.length / 16 + 1;

        blocksArray = new ArrayList<>(blocksQuantity);

        for (int i = 0; i < blocksQuantity; i++) {
            int step = i * blockSize;
            blocksArray.add(Arrays.copyOfRange(inputBuffer, step, blockSize + step));
        }

        Object key = keyGeneration.makeKey(keyX);
        IMode mode;

        switch (modeName) {
            case ECB:
                mode = new ECB();
                break;
            case CBC:
                mode = new CBC();
                break;
            case CFB:
                mode = new CFB();
                break;
            case OFB:
                mode = new OFB();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + modeName);
        }

        byte[] outputBuffer = mode.decrypt(blocksArray, key, iv);

        for (int i = 0; i < 15; i++) {
            if (outputBuffer[outputBuffer.length - i - 1] == 0)
                badCharCount++;
            else
                break;
        }

        return Arrays.copyOfRange(outputBuffer, 0, outputBuffer.length - badCharCount);
    }
}
