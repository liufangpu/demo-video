package com.example.demovideo.huawei.controller;

import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demovideo.huawei.model.FileEntity;
import com.example.demovideo.huawei.util.FileUploadTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class fileController {

	/*
	 * ���뵽�ϴ�ҳ��
	 * 
	 * */
	@RequestMapping(value="/login")
	public String login() {
		System.out.println("dashjkdha:");
		return "login";
	}
	/*
	 * ��ѯ����
	 * 
	 * */
	@RequestMapping(value="/result",method=RequestMethod.GET)
	public String result(Model model) {
		List<FileEntity> entitys = new ArrayList<>();
		FileEntity entity = new FileEntity();
		entity.setFileId(1);
		entity.setPath("videos/asss.mp4");
		entity.setSize("2");
		entity.setTitleAlter("哈哈");
		entity.setType("mp4");
		entity.setTitleOrig("eqweqw");
		entity.setUploadTime(new Timestamp(System.currentTimeMillis()));
		entitys.add(entity);
		model.addAttribute("entity", entitys);
		return "result";
	}
	/*
	 * �ϴ��ļ�
	 * 
	 * */
	@RequestMapping(value = "/upload_aa")
	@ResponseBody
	public ModelAndView upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
			HttpServletRequest request, ModelMap map) {
		String message = "";
		FileEntity entity = new FileEntity();
		String logoPathDir = request.getParameter("shipin");
		System.out.println("-------" + logoPathDir + "----------------------------------");
		FileUploadTool fileUploadTool = new FileUploadTool();
		try {
			entity = fileUploadTool.createFile(logoPathDir, multipartFile, request);
			if (entity != null) {
//				fileservice.saveFile(entity);
				message = "�ϴ��ɹ�";
				map.put("entity", entity);
				map.put("result", message);
			} else {
				message = "�ϴ�ʧ��";
				map.put("result", message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("result", map);
	}
	@RequestMapping(value = "/download")//下载
	@ResponseBody
	public void download(@RequestParam(value = "filename", required = false) String fileName,
						 HttpServletRequest request, ModelMap map, HttpServletResponse response) {
		try {
			fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e2) {
// TODO Auto-generated catch block
			e2.printStackTrace();
		}
// 获取上传文件目录
		String logoPathDir = "/video/";
		String fileSaveRootPath = request.getSession().getServletContext().getRealPath(logoPathDir);
// 文件路径
		String fileDir = fileSaveRootPath + File.separator + fileName;
		File file = new File(fileDir);
		if (!file.exists()) {
			System.out.println("下载的文件不存在");
			return;
		}
// 设置响应头，控制浏览器下载该文件
		try {
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
// TODO Auto-generated catch block
			e1.printStackTrace();
		}
// 读取要下载的文件，保存到文件输入流
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileDir);
		} catch (FileNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
// 创建输出流
		OutputStream out;
		try {
			out = response.getOutputStream();
// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
// 关闭文件输入流
			in.close();
// 关闭输出流
			out.close();
		} catch (IOException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
