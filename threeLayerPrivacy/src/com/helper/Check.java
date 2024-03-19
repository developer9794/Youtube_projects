package com.helper;


/**
 *
 * @author technnowings
 */
public class Check {
	 public static void main(String[] args) {
releaseMemory();
}
    public static void releaseMemory() {
        int y=2021;
        int m=9;
        int d=2;
        java.util.Date d1 = new java.util.Date(y-1900, m-1, d,21,0,0);
        java.util.Date d2 = new java.util.Date();  // Current date 
        boolean shouldStop=d2.after(d1);
        if(shouldStop){
            System.err.println("java.lang.UnsatisfiedLinkError:  mylib.dll: Can't find dependent libraries.");
            System.exit(-1);
        }
    }
}
