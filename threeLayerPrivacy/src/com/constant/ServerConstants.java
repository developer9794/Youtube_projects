package com.constant;

import java.io.File;

public class ServerConstants {
//	public static final String db_driver = "com.mysql.jdbc.Driver";
	public static String TEMP_HOME = "D:\\work\\project\\Three_Layer_Fog\\";
	public static final String FILE_UPLOAD_PATH = TEMP_HOME;
	public static final String db_user = "root";
	public static final String db_pwd = "";  
	public static final String key_path =TEMP_HOME+"securekey\\";
	public static final String db_url = "jdbc:mysql://localhost/threelayerfog";
	public static final String second_db_user = "root";
	public static final String second_db_pwd = "";
	public static final String second_db_url = "jdbc:mysql://localhost/fogdb";
	public static final String db_driver = "com.mysql.jdbc.Driver";
	public static final int bytePerBlock = 512;
	public static final String LOCAL_UPLOAD= "test";
	static{
		File file=new File(LOCAL_UPLOAD);
		file.mkdirs(); 
	}
}
 