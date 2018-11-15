package com.revature.utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class passwordHasher {
	public static passwordHasher Hasher = new passwordHasher();
	private passwordHasher() {}
	public String passHasher(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			byte[] digestedMessage = md.digest(password.getBytes());
			BigInteger bi = new BigInteger(1, digestedMessage);
			String hashedPassword = bi.toString(16);
			return hashedPassword;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
