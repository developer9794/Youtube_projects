package com.Myrestaurant.Myrestaurant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	public static Map<String ,Long> mapdata=new HashMap<>();
	
	public static void setmapdata(String key,long val) {
		System.out.println(key +":"+val);
		mapdata.put(key,val);
	}
	public static Map<String ,Long> getmapdata() {
	
		return mapdata;
	}
	
}