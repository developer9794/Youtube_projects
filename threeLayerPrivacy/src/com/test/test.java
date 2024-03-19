//package com.test;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.mail.Session;
//
//import org.apache.commons.fileupload.FileItem;
//
//import com.beans.MergeBlockModel;
//import com.beans.UserModel;
//import com.constant.ServerConstants;
//import com.database.ConnectionManager;
//import com.helper.DocumentBlockModel;
//import com.helper.DocumentModel;
//import com.helper.FileHelper;
//import com.helper.SHA;
//import com.helper.SimpleCryptoAndroidJava;
//import com.helper.StringHelper;
//
//public class test {
//	private static String answer;
//	public static void main(String[] args) throws IOException {
//		
//		uploadDocumentNew(null,"11", "2", "user-admin", "1234");
////		String path =getFilePathNew("114", "1234");
////		System.out.println(path);
//		
//	}
//	public static String uploadDocumentNew(FileItem fi, String userId,
//			String role, String name, String key) {
//		// docId, docName, docSize, docData, udate
//		// documents
//		String answer="";
//		try{
//			String text = "modi2.txt";
//			StringBuffer fileContent = FileHelper.getFileContent("C:/Users/technowings/Desktop/"+text);
//			String fileName = name + "-"+text;
////			System.out.println(key + ":" + role + ":" + name + ":" + fi.getName());
//			
//
////				name = name.replace(" ", "-");
////				File f = new File(ServerConstants.LOCAL_UPLOAD);
////				if (!f.exists()) {
////					f.mkdirs();
////				}
////				String fileName = fi.getName();
////				if (role.contentEquals("2"))
////
////					fileName = name + "-" + fileName;
////
////				fi.write(new File(ServerConstants.LOCAL_UPLOAD + "/" + fileName));
////				StringBuffer fileContent = FileHelper
////						.getFileContent(ServerConstants.LOCAL_UPLOAD + "/"
////								+ fileName);
//			
//			
//			
//			
//				HashMap hm = FileHelper.getByteFileBlockContent("C:/Users/technowings/Desktop/"+text,512);
////				HashMap hm = FileHelper.getFileBlockContent(ServerConstants.LOCAL_UPLOAD + "/"+ fileName);
//
//				SimpleCryptoAndroidJava sc = new SimpleCryptoAndroidJava();
//			byte[] encrypted = sc.encryptNew(fileContent.toString(), key);
//			String fileSHA = SHA.sha512ByteHash(encrypted);
//			HashMap blockEncry = new HashMap();
//			HashMap blockSHA=new HashMap();
//			for (int i = 0; i < hm.size(); i++) {
//
//				System.out.println("&&&&&&&&&&&&*************5 Lines Is : "
//						+ hm.get(i));
//				byte[] encryptedBlok = sc.encryptNew(hm.get(i).toString(), key);
//				
//				String checksum = SHA.sha512ByteHash(hm.get(i).toString().getBytes());
//				blockEncry.put(i, encryptedBlok);
//				blockSHA.put(i, checksum);
//				
//			}
//			
//			int s = ConnectionManager.getMaxValue("Select 1 from documents where docName like '"+ fileName + "'");
//			System.out.println("************* s "+s);
////			String answer;
//			if (s == -1) // if not exist
//			{
//				
////				String query = "insert into documents (docName, docSize, docData,docSum,userid) values (?,?,?,?,?)";
////				executeUpdate(query, fileName, fi.getSize(), encrypted, fileSHA,userId);
//				String query = "insert into documents (docName, docSize, docData,docSum,userid) values (?,?,?,?,?)";
//				Connection conn=ConnectionManager.getDBConnection();
//				PreparedStatement ps=conn.prepareStatement(query);
//				ps.setString(1, fileName);
//				ps.setString(2, fileContent.length()+"");
//				
//				ps.setBytes(3, encrypted);
//				ps.setString(4, fileSHA);
//				
//				ps.setString(5, userId);
//				ps.execute();
//				conn.close();
//				int docId = ConnectionManager.getMaxValue("Select max(docId) from documents"); // latest
//																				// doc
//																				// id
//				query = "insert into wordtf (word, count, docId) values (?,?,?)";
//				HashMap map = FileHelper.calculateWordWiseCount(fileContent); // plane
//																				// text
//				
//				// count
//				// hm(word,cnt);
//				Iterator it = map.entrySet().iterator();
//				while (it.hasNext()) {
//					Map.Entry pair = (Map.Entry) it.next(); // key value
//					System.out.println(pair.getKey() + " = " + pair.getValue());
//					ConnectionManager.executeUpdate(query,
//							sc.encrypt(pair.getKey().toString().trim()),
//							pair.getValue(), docId);
//				}
//				System.out.println("blockEncry.size()"+blockEncry.size());
//				for (int i = 0; i < blockEncry.size(); i++) {
//					
//					String blksha = (String)blockSHA.get(i);
//					byte[] encry = (byte[])blockEncry.get(i);
//					
//					//Check sha stored or not.
//					int isAvailable = ConnectionManager.checkIsAvailable(blksha);
//					
//					
//					if(isAvailable == -1)//not exist
//					{
////						
//						String query1 = "insert into documentblocks(blockDoc,docId,blockSHA,userid) values (?,?,?,?)";
//						Connection conn1=ConnectionManager.getDBConnection();
//						PreparedStatement ps1=conn1.prepareStatement(query1);
//						ps1.setBytes(1, (byte[]) blockEncry.get(i));
//						ps1.setString(2, docId+"");
//						ps1.setString(3, blockSHA.get(i).toString());
//						ps1.setString(4, userId);
//						ps1.execute();
//						conn1.close();
//						
//						
//						int blockId = ConnectionManager.getMaxValue("Select max(blockid) from documentblocks"); // latest
//						
//						query1 = "insert into reference(uid, docid, blockid, blockno, userDocId) values (?,?,?,?,?)";
//						ConnectionManager.executeUpdate(query1, userId,docId+"",blockId+"",i+1,docId );
//					}
//					else{
//						
//						
//						List sameBlock = ConnectionManager.fetchExistedBlockDetails(blksha);
//						DocumentBlockModel dn = (DocumentBlockModel) sameBlock.get(0);
//								
//						
//						String query1 = "insert into reference(uid, docid, blockid, blockno, userDocId) values (?,?,?,?,?)";
//						ConnectionManager.executeUpdate(query1, userId,dn.getDocId(),dn.getBlockid(),i+1, docId );
//						
//						
////						executeUpdate(updateBlock);
//					}
////					executeUpdate(query, blockEncry.get(i), docId,blockSHA.get(i), userId); // word
//				}
//				
//				
//				System.out.println("File Uploaded Successfully! File Indexing Completed!"); 
//			} else {
//				//return "File already Exists!";
//				
//				String sql = "Select docId from documents where docName like '"+ fileName + "' and userid like '"+ userId + "'";
//				DocumentModel dm=null;
//				dm=ConnectionManager.getDocId(sql);
//				if(dm!=null)
//				{
//					String docId=StringHelper.n2s(dm.getDocId());
//					System.out.println("*******&*&*&*&*&*Document Id Is: "+docId);
//					String sql1 = "Select * from documentblocks where docid like '"+ docId + "' and userid like "+ userId;
//					DocumentBlockModel dbm=new DocumentBlockModel();
//					List list=ConnectionManager.getBlockData(sql1);
//					System.out.println("************************list.size"+list.size());
//					
//					for (int i = 0; i < list.size(); i++) {
//						dbm=(DocumentBlockModel) list.get(i);
//						String blksha = (String )blockSHA.get(i);
//						byte[] encry = (byte[]) blockEncry.get(i);
//						
//						//check for existing block
//						int isAvailable = ConnectionManager.checkIsAvailable(blksha);
//						
//						
//						if(isAvailable == -1)//not exist
//						{
////							String blockid=dbm.getBlockid();
////							String updateBlock="update documentblocks set blockDoc=?, blockSHA='"+blockSHA.get(i)+"'where blockid="+blockid;
////							Connection conn1=ConnectionManager.getDBConnection();
////							PreparedStatement ps1=conn1.prepareStatement(updateBlock);
////							ps1.setBytes(1, (byte[]) blockEncry.get(i));
////							ps1.execute();
////							conn1.close();
//							
//							
//							String query = "insert into documentblocks(blockDoc,docId,blockSHA,userid) values (?,?,?,?)";
//							Connection conn1=ConnectionManager.getDBConnection();
//							PreparedStatement ps1=conn1.prepareStatement(query);
//							ps1.setBytes(1, (byte[]) blockEncry.get(i));
//							ps1.setString(2, docId+"");
//							ps1.setString(3, blockSHA.get(i).toString());
//							ps1.setString(4, userId);
//							ps1.execute();
//							conn1.close();
//
//							int blockId = ConnectionManager.getMaxValue("Select max(blockid) from documentblocks"); // latest
//							
//							String query1 = "insert into reference(uid, docid, blockid, blockno, userDocId) values (?,?,?,?,?)";
//							ConnectionManager.executeUpdate(query1, userId,docId+"",blockId+"",i+1, docId );
//						}
//						else{
//							
//							List sameBlock = ConnectionManager.fetchExistedBlockDetails(blksha);
//							DocumentBlockModel dn = (DocumentBlockModel) sameBlock.get(0);
//									
//							
//							String query1 = "insert into reference(uid, docid, blockid, blockno, userDocId) values (?,?,?,?,?)";
//							ConnectionManager.executeUpdate(query1, userId,dn.getDocId(),dn.getBlockid(),i+1,docId );
//							
////							String blockid=dbm.getBlockid();
////							String updateBlock="update documentblocks set blockDoc=?, blockSHA='"+blockSHA.get(i)+"'where blockid="+blockid;
////							Connection conn1=ConnectionManager.getDBConnection();
////							PreparedStatement ps1=conn1.prepareStatement(updateBlock);
////							ps1.setBytes(1, (byte[]) blockEncry.get(i));
////							ps1.execute();
////							conn1.close();
////							executeUpdate(updateBlock);
//						}
//						}				
//					}
//					
//					System.out.println("File Replace Successfully! File Indexing Completed!");
//				
//				
//				
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return answer;
//	}
//	
//
////	query = "insert into documentblocks(blockDoc,docId,blockSHA,userid) values (?,?,?,?)";
////	Connection conn1=ConnectionManager.getDBConnection();
////	PreparedStatement ps1=conn1.prepareStatement(query);
////	ps1.setBytes(1, (byte[]) blockEncry.get(i));
////	ps1.setString(2, docId+"");
////	ps1.setString(3, blockSHA.get(i).toString());
////	ps1.setString(4, userId);
////	ps1.execute();
////	conn1.close();
////	
////	String sql1 = "Select max(blockid) from documentblocks";
////	DocumentBlockModel dbm=new DocumentBlockModel();
////	List list=ConnectionManager.getBlockData(sql1);
////	DocumentBlockModel db = (DocumentBlockModel)list.get(0);
////	String blk = StringHelper.n2s(db.getBlockid());
////	
////	
////	
////	query = "insert into reference(uid, docid, blockid, blockno) values (?,?,?,?)";
////	ConnectionManager.executeUpdate(query, userId,docId+"",blk,i+1 );
//	public static FileWriter writer = null;
//
////	public static String getFilePathNew(String fid, String userkey) throws IOException {
////		String fileName = getFileName(fid); // ok return only as it is file name
////		String path = "";
////		File file = new File(fileName);	
////		file.createNewFile();// contain
////		writer = new FileWriter(file);
////		String fetchBlocks = "SELECT * FROM `reference` where userDocId like '"+fid+"' order by rid asc;";
////		List blockList = ConnectionManager.getBeanList(ReferenceModel.class, fetchBlocks); // fetch block list from reference table 
////		for (int eachBlock = 0;eachBlock < blockList.size();eachBlock++) {
////		ReferenceModel rfm = (ReferenceModel) blockList.get(eachBlock);
////		String blkid = rfm.getBlockid();
////		String query = " select A.userkey,B.blockDoc from useraccount as A,documentblocks as B  where uid in (select userid from documentblocks where blockId like '"+blkid+"' ) and blockId like '"+blkid+"';";
////		List list = ConnectionManager.getBeanList(MergeBlockModel.class, query); // list
////		
////		
////		MergeBlockModel obj = (MergeBlockModel) list.get(0);
////		byte[] encrData = obj.getBlockDoc();
////		String key = obj.getUserkey();
////		String encr = SimpleCryptoAndroidJava.bytesToHex(encrData);
////		System.out.println("enc :**    "+encr);
////		try {
////			String decr = SimpleCryptoAndroidJava.decryptStringNew(encr, key);
////			System.out.println(decr);
////			
////			System.out.println(fileName);
////			
////			
////			writer.write(decr);
////			
////			path = file.getCanonicalPath();
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		
////		}	
////		writer.close();
////		System.out.println(path);
////		return path;
////	}
////	
//	public static String getFileName(String docId) {
//		String query = "select docName from documents where docId='" + docId
//				+ "'";
//		List list = ConnectionManager.getBeanList(DocumentModel.class, query);
//		DocumentModel obj = (DocumentModel) list.get(0);
//		String fileName = obj.getDocName();
//		System.out.println("File Name :: " + fileName);
//		return fileName;
//
//	}
//}
