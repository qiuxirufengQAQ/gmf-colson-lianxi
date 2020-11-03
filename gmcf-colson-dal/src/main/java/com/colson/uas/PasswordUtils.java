package com.colson.uas;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtils {

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private static String algorithmName = "md5";

	private static int hashIterations = 2;

	public static void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
		PasswordUtils.randomNumberGenerator = randomNumberGenerator;
	}

	public static void setAlgorithmName(String algorithmName) {
		PasswordUtils.algorithmName = algorithmName;
	}

	public static void setHashIterations(int hashIterations) {
		PasswordUtils.hashIterations = hashIterations;
	}

	public static String encryptPassword(String password, String salt) {
		return new SimpleHash(algorithmName, password,getSaltBytes(salt), hashIterations).toHex();
	}

	public static String genSalt(){
		return randomNumberGenerator.nextBytes().toHex();
	}

	public static ByteSource getSaltBytes(String salt) {
		return ByteSource.Util.bytes(salt);
	}

}
