package com.Algorithm;

public class PermutationGeneration
{
    private static final int PERMUTATION_SIZE = 0x100;
    private static final long[] P = new long[PERMUTATION_SIZE];

    public static void init()
    {
        long pval;

        for (int i = 0; i < PERMUTATION_SIZE; i++)
        {
            pval = 0L;

            for (int j = 0, k = 7; j < 8; j++, k += 8)
                pval |= (long) ((i >>> j) & 0x1) << k;
            P[i] = pval;
        }
    }

    public static final long[] getPermutation()
    {
        return P;
    }
}
