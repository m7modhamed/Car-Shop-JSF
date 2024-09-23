package com.carshop.utility;

public class Utility {

	public static String getPureText(String str) {
		
		return str.substring(str.indexOf("=")+1);
	}
}
