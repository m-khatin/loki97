package com.TopLevel.Modes;

import com.LowLevel.Decoder;
import com.LowLevel.Encoder;

import java.util.ArrayList;

public class ECB implements IMode
{

    public byte[] encrypt(ArrayList<byte[]> blocksArray, Object key, byte[] iv)
    {

        byte[] outputBuffer = new byte[blocksArray.size() * blockSize];
        Encoder encoder = new Encoder();

        int step = 0;
        for (byte[] block : blocksArray)
        {
            byte[] temp = encoder.blockEncrypt(block, 0, key);

            System.arraycopy(temp, 0, outputBuffer, (step++) * blockSize, blockSize);
        }

        return outputBuffer;
    }

    public byte[] decrypt(ArrayList<byte[]> blocksArray, Object key, byte[] iv)
    {

        byte[] outputBuffer = new byte[blocksArray.size() * blockSize];
        Decoder decoder = new Decoder();

        int step = 0;
        for (byte[] block : blocksArray)
        {
            byte[] temp = decoder.blockDecrypt(block, 0, key);

            System.arraycopy(temp, 0, outputBuffer, (step++) * blockSize, blockSize);
        }

        return outputBuffer;
    }

}
