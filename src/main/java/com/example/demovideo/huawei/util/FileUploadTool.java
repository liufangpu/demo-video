package com.example.demovideo.huawei.util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.example.demovideo.huawei.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;


//�ļ��ϴ�����
public class FileUploadTool {

// �ļ����500M
	private static long upload_maxsize = 5000 * 1024 * 1024;
// �ļ������ʽ
	private static String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".xlsx", ".gif",
			".png", ".jpg", ".jpeg", ".bmp", ".xls", ".mp4", ".flv", ".ppt", ".avi", ".mpg", ".wmv", ".3gp", ".mov",
			".asf", ".asx", ".vob", ".wmv9", ".rm", ".rmvb", ".mkv" };
// ����ת�����Ƶ��ʽ��ffmpeg��
	private static String[] allowFLV = { ".avi", ".mpg", ".wmv", ".3gp", ".mov", ".asf", ".asx", ".vob" };
// �������Ƶת���ʽ(mencoder)
	private static String[] allowAVI = { ".wmv9", ".rm", ".rmvb" };
	public FileEntity createFile(String logoPathDir, MultipartFile multipartFile, HttpServletRequest request) {
		FileEntity entity = new FileEntity();
		boolean bflag = false;
		String fileName = multipartFile.getOriginalFilename().toString();
		// �ж��ļ���Ϊ��
		if (multipartFile.getSize() != 0 && !multipartFile.isEmpty()) {
			bflag = true;
			// �ж��ļ���С
			if (multipartFile.getSize() <= upload_maxsize) {
				bflag = true;
				// �ļ������ж�
				if (this.checkFileType(fileName)) {
					bflag = true;
				} else {
					bflag = false;
				}
			} else {
				bflag = false;
			}
		} else {
			bflag = false;
		}
		if (bflag) {
			String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
			File logoSaveFile = new File(logoRealPathDir);
			if (!logoSaveFile.exists()) {
				logoSaveFile.mkdirs();
			}
			String name = fileName.substring(0, fileName.lastIndexOf("."));
			// �µ��ļ���
			String newFileName = this.getName(name);
			// �ļ���չ��
			String fileEnd = this.getFileExt(fileName);
			// ����·��
			String fileNamedirs = logoRealPathDir + File.separator + newFileName + fileEnd;
			File filedirs = new File(fileNamedirs);
			// ת���ļ�
			try {
				multipartFile.transferTo(filedirs);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// ���·��
			entity.setType(fileEnd);
			String fileDir = logoPathDir + newFileName + fileEnd;
			StringBuilder builder = new StringBuilder(fileDir);
			String finalFileDir = builder.substring(1);
			// size�洢ΪString
			String size = this.getSize(filedirs);
			// Դ�ļ�����·��
			String aviPath = filedirs.getAbsolutePath();
			if (aviPath != null) {
				entity.setSize(size);
				entity.setPath(finalFileDir);
				entity.setTitleOrig(name);
				entity.setTitleAlter(newFileName);
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				entity.setUploadTime(timestamp);
				return entity;
			}
		} else {
			return null;
		}
		return entity;

	}

	/**
	 * �ļ������ж�
	 *
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * ��Ƶ�����ж�(flv)
	 *
	 * @param fileName
	 * @return
	 */
	private boolean checkMediaType(String fileEnd) {
		Iterator<String> type = Arrays.asList(allowFLV).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileEnd.equals(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ��Ƶ�����ж�(AVI)
	 *
	 * @param fileName
	 * @return
	 */
	private boolean checkAVIType(String fileEnd) {
		Iterator<String> type = Arrays.asList(allowAVI).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileEnd.equals(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ��ȡ�ļ���չ��
	 *
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * ����ԭʼ�ļ����������ļ��� UUID��ȫ��Ψһ��ʶ������һ��ʮ��λ���������,����������ɣ���ǰ���ں�ʱ�䡢ʱ�����С�ȫ��Ψһ��IEEE����ʶ���
	 * 
	 * @return string
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return "" + random.nextInt(10000) + System.currentTimeMillis();
//return UUID.randomUUID().toString() + "_" + fileName;

	}

	/**
	 * �ļ���С������kb.mb ?
	 * 
	 * @return
	 */
	private String getSize(File file) {
		String size = "";
		long fileLength = file.length();
		DecimalFormat df = new DecimalFormat("#.00");
		if (fileLength < 1024) {
			size = df.format((double) fileLength) + "BT";
		} else if (fileLength < 1048576) {
			size = df.format((double) fileLength / 1024) + "KB";
		} else if (fileLength < 1073741824) {
			size = df.format((double) fileLength / 1048576) + "MB";
		} else {
			size = df.format((double) fileLength / 1073741824) + "GB";
		}

		return size;

	}

}
