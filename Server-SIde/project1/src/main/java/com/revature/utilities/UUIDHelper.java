package com.revature.utilities;
import java.util.UUID;


public class UUIDHelper {
	public static UUIDHelper uuidHelper = new UUIDHelper();
	
	private UUIDHelper() {};
	
	public Boolean uuidChecker(String uu) {
		try {
			UUID uuid = UUID.fromString(uu);
			return true;
		}catch(Exception error){
			return false;
		}
		
	}
}
