package com.easychat.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyUtils {

	public static String sHAEncrypt(String password) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA");
		String value = password;
		byte[] inpute = value.getBytes();
		mDigest.update(inpute);        //input可以是字节型或字节型数组
		String key = new BigInteger(mDigest.digest()).toString();
		return key;
	}
}
