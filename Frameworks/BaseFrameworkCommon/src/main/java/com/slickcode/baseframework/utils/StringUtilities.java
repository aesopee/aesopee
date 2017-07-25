package com.slickcode.baseframework.utils;

public class StringUtilities {
	
	public static boolean isEmpty(String value) {
		if(null == value || (value.trim().length() == 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean containsMandatoryAlphabates(String value) {
//		return value.matches("[a-zA-Z]+");
		return value.matches("[a-zA-Z][a-zA-Z/s]+");
	}
	
	public static boolean containsOptionalAlphaNumber(String value) {
		return value.matches("[A-Za-z0-9]+");
	}
	
	public static boolean containsMandatoryAlphaNumber(String value) {
		return value.matches("^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$");
	}
	
	public static boolean containsMandatoryAlphaNumericSpecialChar(String value) {
		return value.matches("/^[a-zA-Z0-9?=.*!@#$%^&*_\\-\\s]+$/");
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	
	public static void main(String[] args) {
		System.out.println(containsMandatoryAlphabates("abads das"));
		
		System.out.println(containsMandatoryAlphabates("abcd122#3"));
		
		System.out.println(containsMandatoryAlphabates("1223ab$cd"));
		
		System.out.println(containsMandatoryAlphabates("1234123%12"));
		
		System.out.println(containsMandatoryAlphabates("Sabcd"));
		
		System.out.println(containsMandatoryAlphabates("aab45cd123"));
	}
}
