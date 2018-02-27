package org.flybit.crypto;

import org.flybit.util.Converter;
import org.junit.Assert;
import org.junit.Test;

public class CryptoTest {

    @Test
    public void testSign(){
        String secretPhrase = "my secretPhrase";
        byte[] message = Converter.toBytes("test message");
        final byte[] signature = Crypto.sign(message, secretPhrase);
        Assert.assertEquals(64,signature.length);


        final byte[] publicKey = Crypto.getPublicKey(secretPhrase);
        boolean valid = Crypto.verify(signature,message,publicKey,true);
        Assert.assertTrue(valid);

        final byte[] wrongPublicKey = Crypto.getPublicKey("wrong secretPhrase");
        boolean invalid = Crypto.verify(signature,message,wrongPublicKey,true);
        Assert.assertFalse(invalid);
    }
}
