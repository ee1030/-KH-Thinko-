package com.kh.thinko.common;

import java.security.MessageDigest;

public class SHA256 {

	public static String getSHA256(String input) {
		// getSHA256 이메일 인증 ! 해시 값을 이용했음.
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// 실제로 사용자가 입력한 값을 SHA-256으로 알고리즘을 적용할 수 있도록 만들어줌.
			byte[] salt = "Hello! This is Salt.".getBytes();
			// 단순하게 SHA-256을 이용해서 쓰면 해커한테 해킹당할 위험이 있기 때문에
			// salt값을 이용해줘야됨.
			digest.reset(); // 리셋
			digest.update(salt); // salt값 적용
			byte[] chars = digest.digest(input.getBytes("UTF-8"));
			// 배열 변수를 만들어서 UTF-8으로 해시를 적용한 값을 캐랙터 변수에 담아준다
			for (int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				// 이것을 문자열 형태로 만들어줌 헥스 값 해쉬 값
				if (hex.length() == 1)
					result.append("0");
				// 16 진수 값으로 만들어줌
				result.append(hex);

				// 특정한 입력 값(이메일)
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
		// 그 결과를 반환할 수 있도록
	}

}
