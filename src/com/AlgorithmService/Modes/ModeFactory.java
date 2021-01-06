package com.AlgorithmService.Modes;

import com.AlgorithmService.Enums.EncryptionMode;
import com.AlgorithmService.Modes.*;

public class ModeFactory
{
    public static IEncryptionMode CreateEncryptionMode(EncryptionMode encryptionMode)
    {
        switch (encryptionMode)
        {
            case ECB:
                return new ECB();
            case CBC:
                return new CBC();
            case CFB:
                return new CFB();
            case OFB:
                return new OFB();
            default:
                throw new IllegalStateException("Unexpected value: " + encryptionMode);
        }
    }
}
