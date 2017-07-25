package com.slickcode.basevalidatorframework;

import com.slickcode.baseframework.utils.StringUtilities;

public class NumericUtilities {
	
	public static boolean isInteger(String value) {
		if(StringUtilities.isEmpty(value)){
			return false;
		}
		try {
			Integer.parseInt(value);
			return true;
		} catch(Exception exception) {
			return false;
		}
	}
	
	public static boolean isAmount(String value) {
		if(StringUtilities.isEmpty(value)){
			return false;
		}
		return value.matches("[0-9]+([,.][0-9]{1,2})?");
	}
	
	public static void main(String[] args) {
		System.out.println(isAmount("1.0"));
		
		System.out.println(isAmount("1000"));
		
		System.out.println(isAmount("12356.52"));
		
		System.out.println(isAmount("45,36,523"));
		
		System.out.println(isAmount("Sabcd"));
		
		System.out.println(isAmount("aab45cd123"));
	}
}
