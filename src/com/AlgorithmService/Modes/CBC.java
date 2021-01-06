package com.AlgorithmService.Modes;

import com.Algorithm.Decoder;
import com.Algorithm.Encoder;

import java.util.ArrayList;

public class CBC implements IEncryptionMode
{
    public byte[] encrypt(ArrayList<byte[]> blocksArray, Object key, byte[] iv)
    {
        byte[] outputBuffer = new byte[blocksArray.size() * blockSize];
        Encoder encoder = new Encoder();

        int step = 0;
        byte[] encBlock = iv;

        for (byte[] block : blocksArray)
        {
            byte[] temp = IEncryptionMode.xor(encBlock, block);

            encBlock = encoder.blockEncrypt(temp, 0, key);

            System.arraycopy(encBlock, 0, outputBuffer, (step++) * blockSize, blockSize);
        }

        return outputBuffer;
    }

    public byte[] decrypt(ArrayList<byte[]> blocksArray, Object key, byte[] iv)
    {
        byte[] outputBuffer = new byte[blocksArray.size() * blockSize];
        Decoder decoder = new Decoder();

        int step = 0;
        byte[] decBlock;
        byte[] encBlock = iv;

        for (byte[] block : blocksArray)
        {
            decBlock = decoder.blockDecrypt(block, 0, key);

            byte[] temp = IEncryptionMode.xor(decBlock, encBlock);
            encBlock = block; // !

            System.arraycopy(temp, 0, outputBuffer, (step++) * blockSize, blockSize);
        }

        return outputBuffer;
    }
}
