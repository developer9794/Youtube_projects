//package com.test;
//
//import java.util.List;
//
//import com.helper.DBUtils;
//import com.helper.DocumentBlockModel;
//import com.helper.SimpleCryptoAndroidJava;
//
//public class Demo {
//	public static void main(String[] args) {
//		try {
//			String sql = " SELECT * FROM `documentblocks` where docId='56' order by blockid asc";
////			String sql=" SELECT * FROM `documents` where docId='56'";
//
//			List list = DBUtils.getBeanList(DocumentBlockModel.class, sql);
////			List list = DBUtils.getBeanList(DocumentModel.class, sql);
//			for (int i = 0; i < list.size(); i++) {
//				DocumentBlockModel model = (DocumentBlockModel) list.get(i);
//				byte [] block = model.getBlockDoc();
//				System.out.println(""+block);
//				String encr = SimpleCryptoAndroidJava.bytesToHex(block);
//				String blockData=SimpleCryptoAndroidJava.decryptStringNew(encr, "0000");
//				System.out.println(blockData);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//}
