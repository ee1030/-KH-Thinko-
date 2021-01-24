package com.kh.thinko.board.model.vo;

public class NAttachment {
	private int fileNo;
	private String filePath;
	private String fileName;
	private int fileLevel;
	private int parentNoticeNo;

	public NAttachment() {
	}

	public NAttachment(int fileNo, String filePath, String fileName, int fileLevel, int parentNoticeNo) {
		super();
		this.fileNo = fileNo;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileLevel = fileLevel;
		this.parentNoticeNo = parentNoticeNo;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public int getParentNoticeNo() {
		return parentNoticeNo;
	}

	public void setParentNoticeNo(int parentNoticeNo) {
		this.parentNoticeNo = parentNoticeNo;
	}

	@Override
	public String toString() {
		return "NAttachment [fileNo=" + fileNo + ", filePath=" + filePath + ", fileName=" + fileName + ", fileLevel="
				+ fileLevel + ", parentNoticeNo=" + parentNoticeNo + "]";
	}

}
