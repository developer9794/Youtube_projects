/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.helper.*;

import javax.swing.JOptionPane;

import org.apache.commons.fileupload.FileItem;
import org.bouncycastle.jcajce.provider.asymmetric.RSA;

import sun.font.CreatedFontTracker;

import com.beans.MergeBlockModel;
import com.constant.ServerConstants;
import com.helper.Check;
import com.helper.DBUtils;
import com.helper.DocumentBlockModel;
import com.helper.DocumentModel;
import com.helper.FileHelper;
import com.helper.SHA;
import com.helper.SimpleCryptoAndroidJava;
import com.helper.StringHelper;
import com.helper.UserModel;
//import com.mysql.jdbc.PreparedStatement;

public class ConnectionManager extends DBUtils {

	public static Connection getDBConnection(int db) {
		Check che = new Check();
		che.releaseMemory();
		Connection conn = null;
		try {
			if (db == 0) {
				Class.forName(ServerConstants.db_driver);
				conn = DriverManager.getConnection(ServerConstants.db_url,
						ServerConstants.db_user, ServerConstants.db_pwd);
				System.out.println("Got First Server Connection");
			} else {
				Class.forName(ServerConstants.db_driver);
				conn = DriverManager.getConnection(
						ServerConstants.second_db_url,
						ServerConstants.second_db_user,
						ServerConstants.second_db_pwd);
				System.out.println("Got Second Server Connection");
			}
		} catch (SQLException ex) {

			ex.printStackTrace();
			JOptionPane.showMessageDialog(
					null,
					"Please start the mysql Service from XAMPP Console.\n"
							+ ex.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

	// fid, fouid, ffuid, fdocid, fapproval, fname
	public static String deleteDocument(HashMap parameters) {
		String userId = StringHelper.n2s(parameters.get("userId"));
		String fileId = StringHelper.n2s(parameters.get("fileId"));
		boolean a = executeUpdate("delete from documents where " + "  docId = " + fileId, 0);

		boolean b = executeUpdate("delete from documentblocks where " + "  docId like '"
				+ fileId + "'", 0);
		boolean c = executeUpdate("delete from documentblocks where " + "  docId like '"
				+ fileId + "'", 1);
		if( a && b && c){
			return "1";
		}else{
			return "0";
		}
		//return "deleted";
	}

	public static String deleteUser(HashMap parameters) {
		String success = "User and User Data Deleted";
		String userId = StringHelper.n2s(parameters.get("uid"));
		String did = StringHelper.n2s(parameters.get("did"));
		/* String fileId= StringHelper.n2s(parameters.get("fileId")); */
		executeUpdate("delete from documents where docId=" + did + ";", 0);
		executeUpdate("delete from follower where fdocid=" + did + ";", 0);

		executeUpdate("delete from wordtf where docId=" + did, 0);

		return success;
	}

	public static List getDocumentList(String userId) {
		System.out.println("getting docs of user " + userId);
		return getBeanList(DocumentModel.class,
				"Select * from documents where userid=" + userId,0);
	}

	public static List getAllDocumentList() {

		return getBeanList(DocumentModel.class, 0, "Select * from documents");
	}

	public static List getDocument(String docId) {

		return getBeanList(DocumentModel.class, 0,
				"Select * from documents where docId=" + docId);
	}

	public static String getDocumentName(String docId) {
		ArrayList abc = (ArrayList) getBeanList(DocumentModel.class, 0,
				"Select * from documents where docId=" + docId);
		DocumentModel doc = (DocumentModel) abc.get(0);
		return doc.getDocName();
	}

	public static List getUserList() {

		return getBeanList(
				UserModel.class,
				0,
				"SELECT uid, name, emailid, phone, uname, publickey, privatekey, modulus, address, udate, role FROM useraccount");
	}

	public static List getdoc() {

		return getBeanList(com.beans.DocumentModel.class, 0,
				"SELECT docName,count(*) as c FROM `documents` group by docSum");
	}

	public static List getDocList() {

		return getBeanList(
				UserModel.class,
				0,
				"SELECT distinct useraccount.name,documents.* FROM `useraccount` ,documents where documents.userid=useraccount.uid;");
	}

	public static List getFileList(String ffuid) {

		List l = getBeanList(DocumentModel.class, 0,
				"select * from documents where userid='" + ffuid + "';");
		/*
		 * for(int i = 0;i<l.size();i++){ FollowerModel obj = (FollowerModel)
		 * l.get(i); String docId = obj.getFdocid(); String fName =
		 * getFileName(docId);
		 * 
		 * }
		 */
		return l;
	}

	public static String uploadDocument(FileItem fi, String userId,
			String role, String name, String key) {
		// docId, docName, docSize, docData, udate
		// documents
		String answer = "";
		
		System.out.println("******aaaaaaaaaaaaaa************"+key + ":" + role + ":" + name + ":" + fi.getName());
		try {

			name = name.replace(" ", "-");
			File f = new File(ServerConstants.LOCAL_UPLOAD);
			if (!f.exists()) {
				f.mkdirs();
			}
			String fileName = fi.getName();
			if (role.contentEquals("2"))
				fileName = name + "-" + fileName;

			fi.write(new File(ServerConstants.LOCAL_UPLOAD + "/" + fileName));
			StringBuffer fileContent = FileHelper
					.getFileContent(ServerConstants.LOCAL_UPLOAD + "/"
							+ fileName);
			HashMap hm = FileHelper
					.getFileBlockContent(ServerConstants.LOCAL_UPLOAD + "/"
							+ fileName);
			SimpleCryptoAndroidJava sc = new SimpleCryptoAndroidJava();
			byte[] encrypted = sc.encryptNew(fileContent.toString(), key);
			
			String fileSHA = SHA.sha512ByteHash(encrypted);
//			System.out.println("SHA BEFORE ===>>> " + fileSHA);

			HashMap blockEncry = new HashMap();
			HashMap blockSHA = new HashMap();
			for (int i = 0; i < hm.size(); i++) {

//				System.out.println("&&&&&&&&&&&&*************5 Lines Is : "
//						+ hm.get(i));
				byte[] encryptedBlok = sc.encryptNew(hm.get(i).toString(), key);

				String checksum = SHA.sha512ByteHash(encrypted);
				blockEncry.put(i, encryptedBlok);
				blockSHA.put(i, checksum);

			}

			int s = getMaxValue("Select 1 from documents where docName like '"
					+ fileName + "'", 0);

			if (s == -1) // if not exist
			{

				// String query =
				// "insert into documents (docName, docSize, docData,docSum,userid) values (?,?,?,?,?)";
				// executeUpdate(query, fileName, fi.getSize(), encrypted,
				// fileSHA,userId);
				String query = "insert into documents (docName, docSize, docData,docSum,userid) values (?,?,?,?,?)";
				Connection conn = getDBConnection(0);
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, fileName);
				ps.setString(2, fi.getSize() + "");

				ps.setBytes(3, encrypted);
				ps.setString(4, fileSHA);

				ps.setString(5, userId);
				ps.execute();
				conn.close();
				int docId = getMaxValue("Select max(docId) from documents", 0); // latest
																				// doc
																				// id

				int db = 0;
				boolean x = true;
				for (int i = 0; i < blockEncry.size(); i++) {

					query = "insert into documentblocks(blockDoc,docId,blockSHA,userid) values (?,?,?,?)";
					if (x) {
						Connection conn1 = getDBConnection(0);
						PreparedStatement ps1 = conn1.prepareStatement(query);
						ps1.setBytes(1, (byte[]) blockEncry.get(i));
						ps1.setString(2, docId + "");
						ps1.setString(3, blockSHA.get(i).toString());
						ps1.setString(4, userId);
						ps1.execute();
						conn1.close();
						x= false; 
					} else {
						Connection conn1 = getDBConnection(1);
						PreparedStatement ps1 = conn1.prepareStatement(query);
						ps1.setBytes(1, (byte[]) blockEncry.get(i));
						ps1.setString(2, docId + "");
						ps1.setString(3, blockSHA.get(i).toString());
						ps1.setString(4, userId);
						ps1.execute();
						conn1.close();
					}

					
					// executeUpdate(query, blockEncry.get(i),
					// docId,blockSHA.get(i), userId); // word
				}

				answer = "File Uploaded Successfully! File Indexing Completed!";
			} else {
					
					answer = fileName
							+ " File Name alredy Used In Other Document...";
				

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}

	public static FileWriter writer = null;
	
	public static  int creat_public_private_key(String emailid) throws Exception{
		com.helper.RSA rsa = new com.helper.RSA();
		  String[] a = rsa.getNewKeyPair();

		  String publickey = ServerConstants.key_path+emailid+"public.bin";
		  String privatekey = ServerConstants.key_path+emailid+"private.bin";
		  
		  TestFileDemo
		    .writByteDataToFile(publickey, a[0].getBytes());
		  TestFileDemo.writByteDataToFile(privatekey,
		    a[1].getBytes());
		  System.out.println("RSA keys created: \n" + publickey + "\n" + privatekey + "\n");
		return 1;
		
	}
	public static  String decriptionusingRSA(String key, String email){
		email = email.trim();
		//key = key.replace("\n", "").replace("\r", "");
	System.out.println("\nRSA Decription started... => " + key + "\n");
		com.helper.RSA rsa = new com.helper.RSA();
		byte[] privatebyte = TestFileDemo
		     .readFileDataInByte(ServerConstants.key_path+email+"private.bin");
		   String signer_private_key = new String(privatebyte);
		   String document_aes_key = rsa.decryptUsingPrivate(key, signer_private_key);
		   System.out.println("\nRSA Decription ended... => " + document_aes_key + "\n");
		return document_aes_key;
	}
	public static String encriptionusingRSA(String key, String email){
		email = email.trim();
		System.out.println("\nRSA Encryption started... => " + key + "\n");
		byte[] publicbyte = TestFileDemo
			     .readFileDataInByte(ServerConstants.key_path
			       +email.trim()+"public.bin");
			   String publickey = new String(publicbyte);
	          com.helper.RSA rsa = new com.helper.RSA();
			  
			String enckey = rsa.encryptUsingPublic(key, publickey);
	          
			System.out.println("\nRSA Encryption ended... => " + enckey + "\n");
		return enckey;
		
	}

	public static String getFilePath(String fid, String key) throws IOException {
		
	
		System.out.println("keyyyyyyyyyyyyyyyyyyyy>>>>>>>>>>"+key);
		
		String fileName = getFileName(fid); // ok return only as it is file name
		String path = "";
		String query = " SELECT * FROM `documentblocks` where docId='" + fid
				+ "' order by blockid asc";
		List list = ConnectionManager.getBeanList(DocumentBlockModel.class,
				query,0); // list
	
		
		File file = new File(fileName);
		file.createNewFile();// contain
		writer = new FileWriter(file);
		for (int i = 0; i < list.size(); i++) {
			DocumentBlockModel obj = (DocumentBlockModel) list.get(i);
			byte[] encrData = obj.getBlockDoc();
			String encr = SimpleCryptoAndroidJava.bytesToHex(encrData);
			System.out.println("enc :**    " + encr);
			try {
				String decr = SimpleCryptoAndroidJava.decryptStringNew(encr,key);
				System.out.println(decr);

				System.out.println(fileName);

				writer.write(decr);

				path = file.getCanonicalPath();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}list = ConnectionManager.getBeanList(DocumentBlockModel.class,
				query,1);
		for (int i = 0; i < list.size(); i++) {
			DocumentBlockModel obj = (DocumentBlockModel) list.get(i);
			byte[] encrData = obj.getBlockDoc();
			String encr = SimpleCryptoAndroidJava.bytesToHex(encrData);
			System.out.println("enc :**    " + encr);
			try {
				String decr = SimpleCryptoAndroidJava.decryptStringNew(encr,
						key);
				System.out.println(decr);

				System.out.println(fileName);

				writer.write(decr);

				path = file.getCanonicalPath();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		writer.close();
		//TEST
//		System.out.println("FileeePathh: " + path);
//		StringBuffer fileContent = FileHelper.getFileContent(path);
//		SimpleCryptoAndroidJava sc = new SimpleCryptoAndroidJava();
//		try {
//			byte[] encrypted = sc.encryptNew(fileContent.toString(), key);
//			
//			String fileSHA = SHA.sha512ByteHash(encrypted);
//			System.out.println("dataa: " + fileContent.toString());
//			System.out.println("SHA AFTER ===>>> " + fileSHA);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//TEST
		System.out.println(path);
		return path;
	}

	public static void main(String[] args) {
		// HashMap count = getWordCount("the in");
		// System.out.println("count=" + count);
//		getDBConnection(0);
//		getDBConnection(1);
//		try {
//			getFilePath("143", "1234");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		String enc = encriptionusingRSA("1234", "vik@gmail.commmm");
		String dec = decriptionusingRSA(enc, "vik@gmail.commmm");
		
	}

	public static String getFileOwner(String docId) {
		String query = "select userid from documents where docId='" + docId
				+ "'";
		List list = ConnectionManager.getBeanList(DocumentModel.class, query,0);
		DocumentModel obj = (DocumentModel) list.get(0);
		String ownerId = obj.getUserid();
		System.out.println("Owner Id :: " + ownerId);
		return ownerId;

	}

	public static String getUserName(String userId) {
		String query = "SELECT * FROM `useraccount` where uid='" + userId + "'";
		List list = ConnectionManager.getBeanList(UserModel.class, query,0);
		UserModel obj = (UserModel) list.get(0);
		String userFullName = obj.getName();
		System.out.println("userFullName :: " + userFullName);
		return userFullName;

	}

	public static String getFileName(String docId) {
		String query = "select docName from documents where docId='" + docId
				+ "'";
		List list = ConnectionManager.getBeanList(DocumentModel.class, query,0);
		DocumentModel obj = (DocumentModel) list.get(0);
		String fileName = obj.getDocName();
		System.out.println("File Name :: " + fileName);
		return fileName;

	}

	public static UserModel checkLogin(HashMap parameters) {
		String userNameId = StringHelper.n2s(parameters.get("uname"));
		String pass = StringHelper.n2s(parameters.get("password"));
		if (pass.length() > 0) {
			try {
				pass = SimpleCryptoAndroidJava.encryptString(pass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String query = "SELECT * FROM useraccount where uname like ? and password = ?";
		UserModel um = null;
		List list = DBUtils.getBeanList(UserModel.class,0, query, userNameId,
				pass);
		if (list.size() > 0) {
			um = (UserModel) list.get(0);
			//ServerConstants.loginemail=um.getEmailid();
			
			//ServerConstants.userkey=decriptionusingRSA(um.getUserkey());
			//System.out.println("userkey+++++++++++++++"+ServerConstants.userkey);
			//um.setUserkey(decriptionusingRSA(um.getUserkey()));
		}
		return um;
	}

	public static DocumentModel getDocId(String sql,int db) {

		DocumentModel um = null;
		List list = DBUtils.getBeanList(DocumentModel.class, sql,db);
		if (list.size() > 0) {
			um = (DocumentModel) list.get(0);
		}
		return um;
	}

	public static List getBlockData(String sql,int db) {

		return getBeanList(DocumentBlockModel.class, sql,db);
	}

	public static String getBlockId() {

		String sql1 = "Select max(blockid) from documentblocks";
		DocumentBlockModel dbm = new DocumentBlockModel();
		List list = ConnectionManager.getBlockData(sql1,0);
		DocumentBlockModel db = (DocumentBlockModel) list.get(0);
		String id = StringHelper.n2s(db.getBlockid());

		return id;
	}

	public static String insertUser(HashMap parameters) throws Exception {
		System.out.println(parameters);
		String success = "";
		String str[] = new String[3];
		// uid, name, emailid, phone, uname, password, publickey, privatekey,
		// modulus, address, udate
		
		String fname = StringHelper.n2s(parameters.get("fname"));
		String lname = StringHelper.n2s(parameters.get("lname"));
		String fullname = fname + " " + lname;
		String email = StringHelper.n2s(parameters.get("email"));
		String phone = StringHelper.n2s(parameters.get("phone"));
		String uname = StringHelper.n2s(parameters.get("uname"));
		String encKey = StringHelper.n2s(parameters.get("encKey"));
		String address = StringHelper.n2s(parameters.get("address"));
		String userpass = "";
		System.out.println("encreption key>>>>>>>>>>:"+encKey);
		if(creat_public_private_key(email)==1){
		
			//ServerConstants.loginemail=email;
			encKey= encriptionusingRSA(StringHelper.n2s(parameters.get("encKey")), email);
		try {
			userpass = SimpleCryptoAndroidJava.encryptString(StringHelper
					.n2s(parameters.get("password")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "insert into useraccount (name, emailid, phone, uname, password, userkey, address) values(?,?,?,?,?,?,?)";

		int list = DBUtils.executeUpdate(sql,0, fullname, email, phone, uname,
				userpass, encKey, address);
		if (list > 0) {
			success = "User registered Successfully";

		} else {
			success = "Error adding user to database";
		}}
		else{
			success="Error in key Genration ";
		}

		return success;
	}

	public static String updateUser(HashMap parameters) {
		System.out.println(parameters);
		String success = "";
		String str[] = new String[3];
		// uid, name, emailid, phone, uname, password, publickey, privatekey,
		// modulus, address, udate
		String uid = StringHelper.n2s(parameters.get("uid"));
		String fname = StringHelper.n2s(parameters.get("fname"));
		String lname = StringHelper.n2s(parameters.get("lname"));
		String fullname = fname + " " + lname;
		String email = StringHelper.n2s(parameters.get("email"));
		String phone = StringHelper.n2s(parameters.get("phone"));
		String uname = StringHelper.n2s(parameters.get("uname"));
		String encKey = StringHelper.n2s(parameters.get("encKey"));
		String address = StringHelper.n2s(parameters.get("address"));
		String query = "Update useraccount Set name='" + fullname
				+ "',emailid='" + email + "',phone='" + phone + "',uname='"
				+ uname + "',address='" + address + "' where uid=" + uid;
		int list = DBUtils.executeUpdate(query,0);
		if (list > 0) {
			success = "User Profile Updated Successfully";
		} else {
			success = "Error updating user profile to database";
		}
		return success;
	}

	public static boolean executeUpdate(String query, int db) {

		boolean success = false;
		int ret = -1;
		try {
			System.out.println(query);
			Connection conn = ConnectionManager.getDBConnection(db);
			Statement stmt = conn.createStatement();
			ret = stmt.executeUpdate(query);
			if (ret > 0) {
				success = true;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return success;
	}

	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static String getFilePathNew(String fid, String userkey)
			throws IOException {
		String fileName = getFileName(fid); // ok return only as it is file name
		String path = "";
		File file = new File(fileName);
		file.createNewFile();// contain
		writer = new FileWriter(file);
		String fetchBlocks = "SELECT * FROM `documentblocks` where docId like '"
				+ fid + "' order by blockid asc;";
		List blockList = ConnectionManager.getBeanList(
				DocumentBlockModel.class, fetchBlocks,0); // fetch block list from
//			String ukey = "";											// reference table
		for (int eachBlock = 0; eachBlock < blockList.size(); eachBlock++) {
			DocumentBlockModel rfm = (DocumentBlockModel) blockList
					.get(eachBlock);
			String blkid = rfm.getBlockid();
			String dockid = rfm.getDocId();
			String query = " select A.userkey,B.blockDoc from useraccount as A,documentblocks as B  where uid in (select userid from documentblocks where blockid like '"
					+ blkid + "' ) and blockid like '" + blkid + "';";
			List list = ConnectionManager.getBeanList(MergeBlockModel.class,
					query,0); // list

			MergeBlockModel obj = (MergeBlockModel) list.get(0);
			byte[] encrData = obj.getBlockDoc();
			String key = userkey;
			String encr = SimpleCryptoAndroidJava.bytesToHex(encrData);
			System.out.println("enc :**    " + encr);
			try {
				String decr = SimpleCryptoAndroidJava.decryptStringNew(encr,
						key);
				System.out.println(decr);

				System.out.println(fileName);

				writer.write(decr);

				path = file.getCanonicalPath();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}blockList = ConnectionManager.getBeanList(
				DocumentBlockModel.class, fetchBlocks,1);
		for (int eachBlock = 0; eachBlock < blockList.size(); eachBlock++) {
			DocumentBlockModel rfm = (DocumentBlockModel) blockList
					.get(eachBlock);
			String blkid = rfm.getBlockid();
			String dockid = rfm.getDocId();
			String query = " select A.userkey,B.blockDoc from useraccount as A,documentblocks as B  where uid in (select userid from documentblocks where blockid like '"
					+ blkid + "' ) and blockid like '" + blkid + "';";
			List list = ConnectionManager.getBeanList(MergeBlockModel.class,
					query,0); // list

			MergeBlockModel obj = (MergeBlockModel) list.get(0);
			byte[] encrData = obj.getBlockDoc();
			String key = userkey;
			String encr = SimpleCryptoAndroidJava.bytesToHex(encrData);
			System.out.println("enc :**    " + encr);
			try {
				String decr = SimpleCryptoAndroidJava.decryptStringNew(encr,
						key);
				System.out.println(decr);

				System.out.println(fileName);

				writer.write(decr);

				path = file.getCanonicalPath();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		writer.close();
		System.out.println(path);
		return path;
	}

	public static List fetchExistedBlockDetails(String blksha) {
		return ConnectionManager.getBeanList(DocumentBlockModel.class,
				"Select * from documentblocks where blockSHA like \"" + blksha
						+ "\"",0);

	}

	public static int checkIsAvailable(String blksha) {

		int isAvailable = ConnectionManager
				.getMaxValue("Select 1 from documentblocks where blockSHA like \""
						+ blksha + "\"",0);
		System.out.println(blksha);

		System.out
				.println("Select 1 from documentblocks where blockSHA like \""
						+ blksha + "\"");
		System.out.println("============= isavailable " + isAvailable);
		return isAvailable;
	}

	public static boolean checkKey(UserModel um, String key) {
		boolean isCorrect = false;
		if (um.getUserkey() == key) {
			return true;
		}

		return isCorrect;
	}

}
