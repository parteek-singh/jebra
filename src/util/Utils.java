package util;

import java.util.Collection;
import java.util.Random;

public class Utils {

	public static boolean containedBasicFunction(String type) {
		
		if(type == null)
			return false;
		type = type.toLowerCase();
		if(type.contains("contain") || 
				type.contains("equals") ||
				type.contains("ignorecase") ||
				type.contains("concat") ||
				type.contains("substring") ||
				type.contains("endswith")||
				type.contains("startwith")||
				type.contains("tochar")||
				type.contains("case")) {
			return true;
		}
		
		return false;
	}
	public static boolean isCollectionType(String type) {
		
		if(type == null)
			return false;
		type = type.toLowerCase();
		if(type.contains("arraylist") || 
				type.contains("list") ||
				type.contains("collection") ||
				type.contains("map") ||
				type.contains("set") ||
				type.contains("linklist")) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isPrimitveType(String type) {
		
		if(type == null)
			return false;
		type = type.toLowerCase();
		if(type.equals("int") || 
				type.equals("double") ||
				type.equals("char") ||
				type.equals("string") ||
				type.equals("long") ||
				type.equals("short")||
				type.equals("float")||
				type.equals("integer") ||
				type.equals("character")) {
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Collection collection) {
		
		if(collection != null && collection.size() > 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isBlank(String type) {
	
		if(type != null && type.length() >0) {
			return false;
		}
		return true;
	}
	
	public static boolean isNotBlank(String type) {
		
		if(type != null && type.length() >0) {
			return true;
		}
		return false;
	}
	
	public static Integer getRandomNumber() {
		Random random = new Random();
		Integer number= random.nextInt(50) + 1;
		return number;
		
	}
	
	public static boolean convertStringToBoolean(String str) {
		if(str != null && "true".equalsIgnoreCase(str)) {
			return true;
		}
		else 
			return false;
	}
	public static boolean NeedToMatchOrNot(String str) {

		boolean checkFlag = false;
		if (str == null)
			return checkFlag;

		if ("notequal".equalsIgnoreCase(str)) {
			checkFlag = false;
		} else if ("equals".equalsIgnoreCase(str)) {
			checkFlag = true;
		} else if ("contains".equalsIgnoreCase(str)) {
			checkFlag = true;
		} else if ("==".equalsIgnoreCase(str)) {
			checkFlag = true;
		} else if ("!=".equalsIgnoreCase(str)) {
			checkFlag = false;
		}
		return checkFlag;
	}
}
