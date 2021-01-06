package com.mkhatin;

public class Display
{

    public static void sBox(byte[] S)
    {
        for (int i = 0; i < S.length / 16; i++)
        {
            System.out.print(HexConverter.shortToString(i) + ": ");
            for (int j = 0; j < 16; j++) System.out.print(HexConverter.byteToString(S[i * 16 + j]) + " ");
            System.out.println();
        }
    }

    public static void permutation(long[] P)
    {
        for (int i = 0; i < 256; i++)
            System.out.println(HexConverter.byteToString(i) + ": " + HexConverter.longToString(P[i]));
    }
}
