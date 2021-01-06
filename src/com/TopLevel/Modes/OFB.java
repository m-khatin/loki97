package com.TopLevel.Modes;

import com.LowLevel.Encoder;

import java.util.ArrayList;

public class OFB implements IMode {

    public byte[] encrypt(ArrayList<byte[]> blocksArray, Object key, byte[] iv)
    {
        byte[] outputBuffer = new byte[blocksArray.size() * blockSize];
        Encoder encoder = new Encoder();

        int step = 0;
        byte[] encBlock = iv;

        for (byte[] block : blocksArray)
        {
            encBlock = encoder.blockEncrypt(encBlock, 0, key);
            byte res[] = xor(encBlock, block);

            System.arraycopy(res, 0, outputBuffer, (step++) * blockSize, blockSize);
        }

        return outputBuffer;
    }

    public byte[] decrypt(ArrayList<byte[]> blocksArray, Object key, byte[] iv)
    {
        return encrypt(blocksArray, key, iv);
    }
}
