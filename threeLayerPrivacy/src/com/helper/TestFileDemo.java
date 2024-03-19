package com.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.Key;

import com.helper.AES;
import com.constant.ServerConstants;

public class TestFileDemo {
	public static void main(String[] args) {

		
		try {
			// create FileInputStream object
			byte fileContent[] =readFileDataInByte("D:/aaa.pdf");

			// create string from byte array
//			System.out.println(fileContent.length);
			AES a = new AES();
			Key key = a.generateKey("1234123412341234");
			byte[] enc = a.encryptUsingKey(fileContent, key);
			System.out.println(enc.length);
			try (FileOutputStream fos = new FileOutputStream(
					ServerConstants.FILE_UPLOAD_PATH + "/tempenc.pdf")) {
				fos.write(enc);
				 fos.close(); 

			}
			byte fileContent1[] =readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH + "/tempenc.pdf");
			System.out.println(fileContent1.length);
//			System.out.println(key);
			byte[] dec1 = a.decryptUsingKey(fileContent1, key);
			System.out.println(dec1.length);
			try (FileOutputStream fos = new FileOutputStream(
					ServerConstants.FILE_UPLOAD_PATH + "/tempdec.pdf")) {
				fos.write(dec1);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writByteDataToFile(String writeFilePath, byte[] data) {
		try (FileOutputStream fos = new FileOutputStream(writeFilePath)) {
			fos.write(data);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] readFileDataInByte(String readFilePath) {
		File file = new File(readFilePath);
		FileInputStream fin = null;
		try {
			
			// create FileInputStream object
			System.out.println(file.getCanonicalPath());
			fin = new FileInputStream(file);
			byte fileContent[] = new byte[(int) file.length()];
			fin.read(fileContent);
			return fileContent;
		} catch (Exception e) {
			System.out.println("File not found" + e);
		} finally {
			// close the streams using close method
			try {
				if (fin != null) {
					fin.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error while closing stream: " + ioe);
			}
		}
		return null;
	}

}
