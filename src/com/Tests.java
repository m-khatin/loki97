package com;

import com.Algorithm.PermutationGeneration;
import com.Algorithm.SBoxesGeneration;
import com.AlgorithmService.AlgorithmService;
import com.AlgorithmService.Enums.EncryptionMode;
import com.mkhatin.FileWorker;
import com.mkhatin.HexConverter;

public class Tests
{
    private static byte[] key = {                // Standard LOKI97 Single Triple
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 69, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};

    private static byte[] initializationVector = {                // Standard LOKI97 Single Triple
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 69, 11, 12, 13, 14, 15};

    public static void doTests()
    {
//        SBoxesGeneration.init();
//        PermutationGeneration.init();
        fileTest();
        byteArrayTest();
    }

    public static void fileTest()
    {
        String fileName = "test.pdf";
        System.out.println("Trying to encrypt/decrypt file " + fileName);


        String inputFile = "/Users/mkhatin/study/crypto/loki97/temp/" + fileName;
        String encryptedFileName = "/Users/mkhatin/study/crypto/loki97/temp/" + "Encrypted" + fileName;
        String decryptedFileName = "/Users/mkhatin/study/crypto/loki97/temp/" + "Decrypted" + fileName;

        byte[] inputRawDataFromFile = FileWorker.read(inputFile);

        byte[] encryptedFile = AlgorithmService.launchAlgorithm(inputRawDataFromFile, key, initializationVector, EncryptionMode.OFB, true);
        byte[] decryptedFile = AlgorithmService.launchAlgorithm(encryptedFile, key, initializationVector, EncryptionMode.OFB, false);

        FileWorker.write(encryptedFileName, encryptedFile);
        FileWorker.write(decryptedFileName, decryptedFile);
    }

    public static void byteArrayTest()
    {
        byte[] input = {'a', 'p', 'g', 'e', 'r', 't', 'b', '0', '0', '0', '0', '0', '0', '0', '0', '0', ' '}; // apgertb

        byte[] encryptedByteArray = AlgorithmService.launchAlgorithm(input, key, initializationVector, EncryptionMode.OFB, true);
        byte[] decryptedByteArray = AlgorithmService.launchAlgorithm(encryptedByteArray, key, initializationVector, EncryptionMode.OFB, false);

        System.out.println(HexConverter.toString(input));
        System.out.println(HexConverter.toString(encryptedByteArray));
        System.out.println(HexConverter.toString(decryptedByteArray));
    }
}
