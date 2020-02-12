package com.amaropticals.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.DatatypeConverter;

import com.amaropticals.model.UserModel;

public class CacheMap {

	private static Map<String, Object> cacheMap = new ConcurrentHashMap<>();

	public static void addEntry(String key, Object value) {
		cacheMap.put(key, value);
	}

	public static synchronized void deleteEntry(String key) {
		if (cacheMap.containsKey(key)) {
			cacheMap.remove(key);

		}
	}

	public static Object readEntry(String key) {
		if (cacheMap.containsKey(key)) {
			return cacheMap.get(key);
		}
		return null;
	}

	public static String getToken(UserModel user) throws NoSuchAlgorithmException {
		
		MessageDigest md=	MessageDigest.getInstance("SHA-1");
		StringBuilder message = new StringBuilder();
		message.append(user.getUsername()).append(user.getPassword())
		.append(Long.toString(System.currentTimeMillis()));
		md.update(message.toString().getBytes());
		return DatatypeConverter
			      .printHexBinary(md.digest()).toUpperCase();
			
		}
}
