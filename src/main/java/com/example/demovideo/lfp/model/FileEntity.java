package com.example.demovideo.lfp.model;

import java.sql.Timestamp;

public class FileEntity {
	
    private long fileId;
    
    /*****
     * ԭ�ļ���
     ***/
    
    private String titleOrig;
 
    /*****
     * �޸ĺ��ļ���
     ***/
    
    private String titleAlter;
 
    /*****
     * �ļ���С
     ***/
    
    private String size;
 
    /*****
     * �ļ�����
     ***/
    
    private String type;
 
    /*****
     * �ļ�����·��
     ***/
    
    private String path;
 
    /*****
     * �ļ��ϴ�ʱ��
     ***/
    
    private Timestamp uploadTime;

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getTitleOrig() {
		return titleOrig;
	}

	public void setTitleOrig(String titleOrig) {
		this.titleOrig = titleOrig;
	}

	public String getTitleAlter() {
		return titleAlter;
	}

	public void setTitleAlter(String titleAlter) {
		this.titleAlter = titleAlter;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
    
    


}
