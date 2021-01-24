package com.kh.thinko.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author jeonga
 *
 */
public class EncryptWrapper extends HttpServletRequestWrapper{

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		
		String encPwd = null;
		
		switch(name) {
		case "memberPwd" :
		case "pwd" :
		case "currentPwd" :
		case "newPwd" :
		case "newPw" :
		case "userPwd" :
			
			encPwd = getSha512(super.getParameter(name));
			break;
			
		default : encPwd = super.getParameter(name);
		}
		
		return encPwd;
	}
	
	public static String getSha512(String pwd) {
		String encPwd = null;
		
		MessageDigest md = null;
		
		try{
			md = MessageDigest.getInstance("SHA-512");
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = pwd.getBytes(Charset.forName("UTF-8"));
		
		md.update(bytes);
		
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		
		return encPwd;
	}
	
	
	

}
