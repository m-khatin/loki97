package com.company;

import com.LowLevel.*;
import com.TopLevel.Loki97;


public class Main {

    public static void main(String[] args) throws IllegalStateException {

        SBoxesGeneration.init();
        PermutationGeneration.init();


        byte[] keyX = {                // Standard LOKI97 Single Triple
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 69, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};

        byte[] iv = {                // Standard LOKI97 Single Triple
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 69, 11, 12, 13, 14, 15};


//TODO
        String fileName = "test.pdf";

        String inputFile = "/Users/mkhatin/study/crypto/loki97/temp/" + fileName;
        String encryptedFile = "/Users/mkhatin/study/crypto/loki97/temp/" + "Encrypted" + fileName;
        String decryptedFile = "/Users/mkhatin/study/crypto/loki97/temp/" + "Decrypted" + fileName;

        byte[] input = FileWorker.read(inputFile);

        byte[] encryptedRawData = Loki97.encrypt(input, keyX, iv, EncryptionMode.OFB);
        byte[] decryptedRawData = Loki97.decrypt(encryptedRawData, keyX, iv, EncryptionMode.OFB);

        FileWorker.write(encryptedFile, encryptedRawData);
        FileWorker.write(decryptedFile, decryptedRawData);

        System.out.println(Convertation.toString(input));
        System.out.println(Convertation.toString(encryptedRawData));
        System.out.println(Convertation.toString(decryptedRawData));


//TODO
//        byte[] input = {'a', 'p', 'g', 'e', 'r', 't', 'b', '0', '0', '0', '0', '0', '0', '0', '0', '0', ' '}; // apgertb

//        byte[] enc = Loki97.encrypt(input, keyX, iv, EncryptionMode.OFB);
//        byte[] dec = Loki97.decrypt(enc, keyX, iv, EncryptionMode.OFB);
//
//        System.out.println(Convertation.toString(input));
//        System.out.println(Convertation.toString(enc));
//        System.out.println(Convertation.toString(dec));


//TODO
//        byte[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
//
//        var keyGeneration = new KeyGeneration();
//        var key = keyGeneration.makeKey(keyX);
//
//        var decoder = new Decoder();
//
//        var textDec = decoder.blockDecrypt(input, 0, key);
//
//        System.out.println(Convertation.toString(input));
//        System.out.println(Convertation.toString(textDec));


//TODO
        byte[] a = {1, 8, 2, 'a', 6};
        byte[] b = {'k', 'z', 18, 8, '6'};
        byte[] temp = xor(a, b);

        System.out.println("a = " + Convertation.toString(a));
        System.out.println("b = " + Convertation.toString(b));
        System.out.println(Convertation.toString(temp));
        System.out.println("a = " + Convertation.toString(xor(temp, b)));
        System.out.println("b = " + Convertation.toString(xor(temp, a)));

    }

    private static byte[] xor(byte[] a, byte[] b) {
        byte[] res = new byte[a.length];

        for (int i = 0; i < a.length; i++) {
            res[i] = (byte) (a[i] ^ b[i]);
        }
        return res;
    }

    private static byte[] checkBlock(byte[] input) {
        if (input.length < 16) {
            return addAll(input, new byte[16 - input.length]);
        }

        return input;

    }

    private static byte[] addAll(byte[] a, byte[] b) {
        byte[] res = new byte[a.length + b.length];
        System.arraycopy(a, 0, res, 0, a.length);
        System.arraycopy(b, 0, res, a.length, b.length);

        return res;
    }

    private static void test() {
        Encoder encoder = new Encoder();
        KeyGeneration keyGeneration = new KeyGeneration();
        Decoder decoder = new Decoder();

        byte[] keyx = {                // Standard LOKI97 Single Triple
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        byte[] plain = {
                62, 2, 25, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        //byte[] text = fromString("02222222222A2B2922222222222222222");
        byte[] cipher = fromString("75080E359F10FE640144B35C57128DAD");
        byte[] temp;
        byte[] text = addAll(plain, new byte[16 - plain.length]);
//        System.out.println("   key: " + Convertation.toString(keyx));
//        System.out.println(" plain: " + Convertation.toString(plain));
//        System.out.println("cipher: " + Convertation.toString(cipher));

        Object key = keyGeneration.makeKey(keyx);
        temp = encoder.blockEncrypt(text, 0, key);
        byte[] res = decoder.blockDecrypt(temp, 0, key);

        System.out.println(" plain: " + Convertation.toString(text));
        System.out.println("encrypt block: " + Convertation.toString(temp));
        System.out.println(" plain: " + Convertation.toString(res));


//        if (Convertation.toString(plain).equals(Convertation.toString(res)))
//            System.out.println("УРА!");
    }

    private static byte[] fromString(String hex) {
        int len = hex.length();
        byte[] buf = new byte[((len + 1) / 2)];
        int i = 0, j = 0;
        if ((len % 2) == 1)
            buf[j++] = (byte) fromDigit(hex.charAt(i++));
        while (i < len) {
            buf[j++] = (byte) (
                    (fromDigit(hex.charAt(i++)) << 4) |
                            fromDigit(hex.charAt(i++))
            );
        }
        return buf;
    }

    public static int fromDigit(char ch) {
        if (ch >= '0' && ch <= '9')
            return ch - '0';
        if (ch >= 'A' && ch <= 'F')
            return ch - 'A' + 10;
        if (ch >= 'a' && ch <= 'f')
            return ch - 'a' + 10;
        throw new IllegalArgumentException("Invalid hex digit '" + ch + "'");
    }
}
