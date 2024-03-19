package com.helper;

import java.security.Key;
import javax.crypto.Cipher;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import javax.crypto.spec.SecretKeySpec;

import com.helper.Randomkey;

public class AES {

//    private static final String ALGO = "AES";
//    private static final byte[] keyValue =
//            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

    /**
     * Encrypt a string with AES algorithm.
     *
     * @param data is a string
     * @return the encrypted string
     */
    public static String encrypt(String data) throws Exception {
        //"0000" is user key
    	Key key = generateKey("0000");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return new BASE64Encoder().encode(encVal);
    }
    public static String padString(String source)
    {
      char paddingChar = ' ';
      int size = 16;
      int x = source.length() % size;
      int padLength = size - x;

      for (int i = 0; i < padLength; i++)
      {
              source += paddingChar;
      }

      return source;
    }
    public static byte[] encryptUsingKey(byte[] data,Key key) throws Exception {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data);
        return encVal;
    }
    /**
     * Decrypt a string with AES algorithm.
     *
     * @param encryptedData is a string
     * @return the decrypted string
     */
    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey("0000");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }
    public static byte[] decryptUsingKey(byte[] encryptedData,Key key ) throws Exception {
//        Key key = generateKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
       
        byte[] decValue = c.doFinal(encryptedData);
        return decValue;
    }
    /**
     * Generate a new encryption key.
     */
    public static Key generateKey(String userKey) throws Exception {
    	byte[] bytes=userKey.getBytes();
        return new SecretKeySpec(bytes, "AES");
    }
    public static void main(String[] args) throws Exception {
    	AES a=new AES();
    	String keystr= Randomkey.randomString(16);
    	Key key=a.generateKey(keystr);
    	
    	byte[] enc= encryptUsingKey("Test".getBytes(), key);
    	byte[] dec= decryptUsingKey(enc, key);
    	System.out.println(enc.length);
    	System.out.println(dec.length);
	}
}