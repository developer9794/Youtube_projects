package com.helper;

import java.io.BufferedReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

import com.database.ConnectionManager;

public class SimpleCryptoAndroidJava {

	private String iv = "fedcba9876543210";// Dummy iv (CHANGE IT!)
	private IvParameterSpec ivspec;
	private SecretKeySpec keyspec;
	private Cipher cipher;

	private String SecretKey = "0123456789abcdef";// Dummy secretKey (CHANGE
													// IT!)

	public SimpleCryptoAndroidJava() {
		ivspec = new IvParameterSpec(iv.getBytes());

		keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");

		try {
			cipher = Cipher.getInstance("AES/CBC/NoPadding");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte[] encrypt(String text) throws Exception {
		if (text == null || text.length() == 0)
			throw new Exception("Empty string");
		byte[] encrypted = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			encrypted = cipher.doFinal(padString(text).getBytes());
		} catch (Exception e) {
			throw new Exception("[encrypt] " + e.getMessage());
		}

		return encrypted;
	}

	public byte[] encryptNew(String text, String key) throws Exception {
		if (text == null || text.length() == 0)
			throw new Exception("Empty string");

		byte[] encrypted = null;
		SecretKeySpec keyspecNew = new SecretKeySpec(padString(key).getBytes(),
				"AES");
		try {
			cipher.init(Cipher.ENCRYPT_MODE, keyspecNew, ivspec);

			encrypted = cipher.doFinal(padString(text).getBytes());
		} catch (Exception e) {
			throw new Exception("[encrypt] " + e.getMessage());
		}

		return encrypted;
	}

	

	public byte[] decryptNew(String code, String key) throws Exception {
		if (code == null || code.length() == 0)
			throw new Exception("Empty string");

		byte[] decrypted = null;
		SecretKeySpec keyspecNew = new SecretKeySpec(padString(key).getBytes(),
				"AES");
		try {
			cipher.init(Cipher.DECRYPT_MODE, keyspecNew, ivspec);

			decrypted = cipher.doFinal(hexToBytes(code));
		} catch (Exception e) {
			throw new Exception("[decrypt] " + e.getMessage());
		}
		return decrypted;
	}

	public byte[] decrypt(String code) throws Exception {
		if (code == null || code.length() == 0)
			throw new Exception("Empty string");

		byte[] decrypted = null;

		try {
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

			decrypted = cipher.doFinal(hexToBytes(code));
		} catch (Exception e) {
			throw new Exception("[decrypt] " + e.getMessage());
		}
		return decrypted;
	}

	public static String bytesToHex(byte[] data) {
		if (data == null) {
			return null;
		}

		int len = data.length;
		String str = "";
		for (int i = 0; i < len; i++) {
			if ((data[i] & 0xFF) < 16)
				str = str + "0" + java.lang.Integer.toHexString(data[i] & 0xFF);
			else
				str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
		}
		return str;
	}

	public static byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(
						str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}
	}

	private static String padString(String source) {
		char paddingChar = ' ';
		int size = 16;
		int x = source.length() % size;
		int padLength = size - x;

		for (int i = 0; i < padLength; i++) {
			source += paddingChar;
		}

		return source;
	}

	public static String encryptString(String data) throws Exception {
		SimpleCryptoAndroidJava mc = new SimpleCryptoAndroidJava();
		byte encrypted[];

		encrypted = mc.encrypt(data);
		String str = mc.bytesToHex(encrypted);

		return str;
	}

	public static String decryptString(String enc) throws Exception {
		SimpleCryptoAndroidJava mc = new SimpleCryptoAndroidJava();
		byte decrypted[];
		decrypted = mc.decrypt(enc);
		String str1 = new String(decrypted);

		return str1;
	}

	public static String decryptStringNew(String enc, String key)
			throws Exception {
		SimpleCryptoAndroidJava mc = new SimpleCryptoAndroidJava();
		byte decrypted[];
		decrypted = mc.decryptNew(enc, key);
		String str1 = new String(decrypted);

		return str1;
	}

	public static void main(String[] args) {
		byte[] encrypted = null;
		SimpleCryptoAndroidJava mc = new SimpleCryptoAndroidJava();
		try {
//			KeyPairGenerator keyPairGenerator = KeyPairGenerator
//					.getInstance("RSA");
//			keyPairGenerator.initialize(1024);
//			KeyPair keyPair = keyPairGenerator.genKeyPair();
//
//			byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
//			BASE64Encoder b64 = new BASE64Encoder();
//			// System.out.println("Public:\n"+b64.encode(publicKeyBytes));
//
//			encrypted = mc.encrypt(b64.encode(publicKeyBytes));
//			String str = mc.bytesToHex(encrypted);
//			System.out.println(str);
			//System.out.println(decryptStringNew("5b424031623537396339", "0000"));
			/*
			 * byte decrypted[]; decrypted=mc.decrypt(
			 * "b3be17fea3dd85831ae5ae9af842b96e7ca33de04147c5750c33f8a3159dc2937e498b2e84bc694a60f15cc933e81d6755012820e9ac7ae802ef9c8ab720f0cf3402cf1363b51030d26dfc139dbdf8dbb942a10b993fe85466ed9704842cf232782750b7aca34369cb2075e85403bec4e896c015f80290e9a563ff7fb63d1b6775c3256b754a29fb062e10bb889a259e7e0cfd520aae32a3a366f0780c4ede74f34af555e6b81c8d5e916dc4bd46c368447a32c8bfba05e9f7c304d9baeeb88ea3f764db0bb94c21f24ede858da4556813ccaae19e8eaf1df2d4e239224728c4"
			 * ); String str1=new String(decrypted); System.out.println(str1);
			 */

			// System.out.println(encryptString("DEBUG"));
			 System.out.println(decryptString("55c8be001e485ba3aa0a4e739b24a70c"));
			// String keyword = "android:layout_height=\"-2\"";
			// String keyword = "load";
			// List l = ConnectionManager.getResults(keyword);
			// System.out.println(l.size());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
