package com.kh.thinko.board.model.exception;

// 파일 정보 삽입 실패시 발생할 사용자 정의 예외
// 예외를 상속받으면 예외 클래스가 된다.
public class FileInsertFailedException extends Exception{
	
	public FileInsertFailedException() {
		super();
	}
	
	public FileInsertFailedException(String message) {
		super(message);
	}

}
