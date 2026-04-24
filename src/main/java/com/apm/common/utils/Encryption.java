package com.apm.common.utils;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Using cryptography algorithm for Encryption and Decryption of password
 * 
 * @author Nitin Chopkar
 * @copyright Techior Solutions 2010
 * @date Sep 6, 2010
 */
public class Encryption {

	/** Algorithm to use in encryption */
	private static final String ALGORITHM = "SHA-1";
	
	/** Length of bytes array for encrypted bytes */
	private static final int BYTES_LENGT = 40;
	
	/** Encoding type for getting bytes from message */
	private static final String ENCODING = "iso-8859-1";
	
	/**
	 * 
	 * Encrypt string using SHA-1 Algorithm. This is one way encryption.
	 * Data encrypted by this method can not be decrypted. 
	 * 
	 * @param stringToEncrypt string to encrypt
	 * @return encrypted string
	 * @throws NoSuchAlgorithmException if given encryption algorithm does not exist
	 * @throws UnsupportedEncodingException if encoding type is not supported
	 * 
	 * @warning Do not change this method since all encrypted data  saved in database will become 
	 * unusable. We can not decrypt encrypted data, so for validation first this method will be
	 * used to encrypt data and then encrypted data will be compared to actual encrypted database value.
	 * If really need to change, make new method to overload this method.
	 */
	public static String encryptSHA(String stringToEncrypt) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		String encryptedString = null;		// it will hold encrypted string
		
		MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);		// initialize message digest
		
		byte[] sha1hash = new byte[BYTES_LENGT];	// to hold string bytes
		
		// encrypt data and save encrypted byte in byte array
		messageDigest.update(stringToEncrypt.getBytes(ENCODING), 0, stringToEncrypt.length());	
		sha1hash = messageDigest.digest();
		
		// convert encrypted bytes to hex string, since encrypted bytes always contains special characters
		// this step is necessary to convert special character into normal characters
		encryptedString = convertToHex(sha1hash);	
		
		return encryptedString;
		
	}

	/**
	 * 
	 * Converts byte to hex string. Converts special characters bytes into normal characters.
	 * So that data can be saved in database. 
	 * (There may be problem if data with special characters will be saved in database)
	 * 
	 * @param data
	 * @return converted hex string
	 * 
	 * @warning Do not change this method, since it is used by encryption method for final step.
	 * This method must be used to convert encrypted data to hex string, since we can not save
	 * special characters in database.
	 */
	 private static String convertToHex(byte[] data) { 
		 
		 // string buffer will hold updated string
		 // string buffer is more efficient for dynamic string than string object
		 StringBuffer sb = new StringBuffer();	
		 
		 // iterate over the byte of give bytes array and convert each byte 
		 for (int i = 0; i < data.length; ++i) {
			// this code converts byte to hex string and append to string buffer
			// don't change this code
            sb.append(Integer.toHexString((data[i] & 0xFF) | 0x100).substring(1,3));	
		 }
		 
		 return sb.toString();	// return string from string buffer
	 } 
}
