package com.AlgorithmService.Modes;

import com.Algorithm.Encoder;

import java.util.ArrayList;

public class CFB implements IEncryptionMode
{
    public byte[] encrypt(ArrayList<byte[]> blocksArray, Object key, byte[] iv)
    {
        byte[] outputBuffer = new byte[blocksArray.size() * blockSize];
        Encoder encoder = new Encoder();

        int step = 0;
        byte[] encBlock = iv;

        for (byte[] block : blocksArray)
        {
            encBlock = encoder.blockEncrypt(encBlock, 0, key);
            encBlock = IEncryptionMode.xor(encBlock, block);

            System.arraycopy(encBlock, 0, outputBuffer, (step++) * blockSize, blockSize);
        }

        return outputBuffer;
    }

    public byte[] decrypt(ArrayList<byte[]> blocksArray, Object key, byte[] iv)
    {
        byte[] outputBuffer = new byte[blocksArray.size() * blockSize];
        Encoder encoder = new Encoder();

        int step = 0;
        byte[] encBlock = iv;

        for (byte[] block : blocksArray)
        {
            encBlock = encoder.blockEncrypt(encBlock, 0, key);

            byte[] temp = IEncryptionMode.xor(encBlock, block);
            encBlock = block;

            System.arraycopy(temp, 0, outputBuffer, (step++) * blockSize, blockSize);
        }

        return outputBuffer;
    }
}
