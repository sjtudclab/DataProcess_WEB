package cn.edu.sjtu.dcl.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtil {
	
	public static String getMD5Code(String str){
		StringBuffer md5StrBuff = new StringBuffer(); 
		if(str != null){
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.reset();
				md.update(str.getBytes("UTF-8"));
				byte[] byteArray = md.digest(); 
		 
		        for (int i = 0; i < byteArray.length; i++) {             
		            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) 
		                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i])); 
		            else 
		                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i])); 
		        } 
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return md5StrBuff.toString();
	}

}
