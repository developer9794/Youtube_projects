package com.helper;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSA {
	
	public String[] getNewKeyPair() throws Exception {
		String [] returnArray = new String[2];
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	    keyPairGenerator.initialize(512);
	    KeyPair keyPair = keyPairGenerator.genKeyPair();
	    BASE64Encoder b64 = new BASE64Encoder();
	    byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
	    returnArray[0]=b64.encode(publicKeyBytes);
	    //System.out.println("Public Size "+publicKeyBytes.length);
	    //System.out.println("Public:\n"+b64.encode(publicKeyBytes));
	    byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
	    returnArray[1]=b64.encode(privateKeyBytes);
	    //System.out.println("Private Size "+privateKeyBytes.length);         
	    //System.out.println("Private:\n"+b64.encode(privateKeyBytes));
		return returnArray;
	}
	
	private static String baseDecoder(byte[] array){
		BASE64Encoder b64 = new BASE64Encoder();
		return b64.encode(array);
	}
	
	public static void main(String[] args) {
		RSA obj = new RSA();
		try {
			String [] a = obj.getNewKeyPair();
//			String [] b = obj.getNewKeyPair();
			System.out.println("Public:"+a[0]);
			System.out.println("Private:"+a[1]);
			String EncResult1 = encryptUsingPublic("any message", a[0]);
//			AES.encrypt("Hello", EncResult1);
			String DecResult2 = decryptUsingPrivate(EncResult1, a[1]);
			System.out.println(DecResult2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String encryptUsingPublic(String toEncrypt, String publicKey){
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String result = "";
		System.out.println("PlainText : " + toEncrypt);
		BASE64Decoder b64 = new BASE64Decoder();
		byte[] publicBytes;
		try {
			Cipher cipher = Cipher.getInstance("RSA/None/NoPadding");
			publicBytes = b64.decodeBuffer(publicKey);
			// System.out.println("Public key : "+publicBytes.length);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
			// System.out.println("Public Key : \nmodulus : "+pubKey.getModulus()+"\nprivate exponent :"+pubKey.getPublicExponent());
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);

			byte[] cipherText = cipher.doFinal(toEncrypt.getBytes());
			// System.out.println("cipher: " + new String(cipherText));
			BASE64Encoder b64encoder = new BASE64Encoder();
			result = b64encoder.encode(cipherText);
			 System.out.println("Encryption: " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
  }
	
	public static String encryptUsingPrivate(String toEncrypt, String privateKey){
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String result = "";
		System.out.println("PlainText : " + toEncrypt);
		BASE64Decoder b64 = new BASE64Decoder();
		byte[] privateBytes;
		try {
			Cipher cipher = Cipher.getInstance("RSA/None/NoPadding");
			privateBytes = b64.decodeBuffer(privateKey);
			// System.out.println("Public key : "+publicBytes.length);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
			// System.out.println("Public Key : \nmodulus : "+pubKey.getModulus()+"\nprivate exponent :"+pubKey.getPublicExponent());
			cipher.init(Cipher.ENCRYPT_MODE, privKey);

			byte[] cipherText = cipher.doFinal(toEncrypt.getBytes());
			// System.out.println("cipher: " + new String(cipherText));
			BASE64Encoder b64encoder = new BASE64Encoder();
			result = b64encoder.encode(cipherText);
			 System.out.println("Encryption: " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
  }
  
  public static String decryptUsingPrivate(String encryptedText, String privateKey){
	    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String result = "";
		System.out.println("Encryption:\n" + encryptedText);
		BASE64Decoder b64 = new BASE64Decoder();
		try {
			Cipher cipher = Cipher.getInstance("RSA/None/NoPadding");
			byte[] privateBytes = b64.decodeBuffer(privateKey);
			// System.out.println("Private key : "+privateBytes.length);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateKey privKey = (RSAPrivateKey) keyFactory
					.generatePrivate(keySpec);
			// System.out.println("Private Key : \nmodulus : "+privKey.getModulus()+"\nprivate exponent :"+privKey.getPrivateExponent());
			cipher.init(Cipher.DECRYPT_MODE, privKey);
			BASE64Decoder b64decoder = new BASE64Decoder();
			byte[] plainText = cipher.doFinal(b64decoder.decodeBuffer(encryptedText));
			result = new String(plainText);
			System.out.println("PlainText : " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
  }
  
  public static String decryptUsingPublic(String encryptedText, String publicKey){
	    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String result = "";
		System.out.println("Encryption:\n" + encryptedText);
		BASE64Decoder b64 = new BASE64Decoder();
		try {
			Cipher cipher = Cipher.getInstance("RSA/None/NoPadding");
			byte[] publicBytes = b64.decodeBuffer(publicKey);
			// System.out.println("Private key : "+privateBytes.length);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
			// System.out.println("Private Key : \nmodulus : "+privKey.getModulus()+"\nprivate exponent :"+privKey.getPrivateExponent());
			cipher.init(Cipher.DECRYPT_MODE, pubKey);
			BASE64Decoder b64decoder = new BASE64Decoder();
			byte[] plainText = cipher.doFinal(b64decoder.decodeBuffer(encryptedText));
			result = new String(plainText);
			System.out.println("PlainText : " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
}

}
