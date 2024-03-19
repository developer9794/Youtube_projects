package com.helper;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

/**
 *
 * @author Administrator
 */
public class SHA {
	
	public static String sha512ByteHash(byte[] data) throws Exception {
        byte[] b = MessageDigest.getInstance("SHA-512").digest(data);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("Hex format : " + sb.toString() + "   Length : " + sb.length());
        return sb.toString();
    }

    public static String sha512FileHash(String filePath) throws Exception {
        byte[] b = MessageDigest.getInstance("SHA-512").digest(Files.readAllBytes(Paths.get(filePath)));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("Hex format : " + sb.toString() + "   Length : " + sb.length());
        return sb.toString();
    }

    public static String sha512StringHash(String plaintext) throws Exception {
        byte[] b = MessageDigest.getInstance("SHA-512").digest(plaintext.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("Hex format : " + sb.toString() + "   Length : " + sb.length());
        return sb.toString();
    }

    public static String sha512GenericHash(Object anyObject) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(anyObject);
        byte[] b = MessageDigest.getInstance("SHA-512").digest(out.toByteArray());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("Hex format : " + sb.toString() + "   Length : " + sb.length());
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            sha512GenericHash("c");
            sha512ByteHash("c".getBytes());
            sha512StringHash("c");
        } catch (Exception e) {
        }
    }
}
