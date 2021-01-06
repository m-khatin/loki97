package com.TopLevel.Modes;

import java.util.ArrayList;

public interface IMode {

    int blockSize = 16;

    byte[] encrypt(ArrayList<byte[]> blocksArray, Object key, byte[] iv);

    byte[] decrypt(ArrayList<byte[]> blocksArray, Object key, byte[] iv);

    default byte[] xor(byte[] a, byte[] b) {
        byte[] res = new byte[a.length];

        for (int i = 0; i < a.length; i++) {
            res[i] = (byte) (a[i] ^ b[i]);
        }
        return res;
    }
}
