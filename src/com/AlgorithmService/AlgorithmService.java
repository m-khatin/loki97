package com.AlgorithmService;

import com.Algorithm.KeyGeneration;
import com.AlgorithmService.Enums.EncryptionMode;
import com.AlgorithmService.Modes.ModeFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgorithmService
{
    final static int blockSize = 16;

    public static byte[] launchAlgorithm(byte[] inputBuffer, byte[] keyBuffer, byte[] initializationVector, EncryptionMode encryptionMode, boolean doEncrypt) throws IllegalStateException
    {
        KeyGeneration keyGeneration = new KeyGeneration();
        int blocksQuantity;
        ArrayList<byte[]> blocksArray;

        if ((inputBuffer.length % blockSize) == 0)
            blocksQuantity = inputBuffer.length / 16;
        else
            blocksQuantity = inputBuffer.length / 16 + 1;

        blocksArray = new ArrayList<>(blocksQuantity);

        for (int i = 0; i < blocksQuantity; i++)
        {
            int step = i * blockSize;
            blocksArray.add(Arrays.copyOfRange(inputBuffer, step, blockSize + step));
        }

        Object key = keyGeneration.makeKey(keyBuffer);

        var mode = ModeFactory.CreateEncryptionMode(encryptionMode);

        if (doEncrypt)
        {
            return mode.encrypt(blocksArray, key, initializationVector);
        } else
        {
            var badCharCount = 0;
            byte[] outputBuffer = mode.decrypt(blocksArray, key, initializationVector);

            for (int i = 0; i < 15; i++)
            {
                if (outputBuffer[outputBuffer.length - i - 1] == 0)
                    badCharCount++;
                else
                    break;
            }

            return Arrays.copyOfRange(outputBuffer, 0, outputBuffer.length - badCharCount);
        }
    }
}