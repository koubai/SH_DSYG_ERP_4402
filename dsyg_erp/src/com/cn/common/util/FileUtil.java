package com.cn.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @name FileUtil.java
 * @author Frank
 * @time 2014-12-22上午12:50:29
 * @version 1.0
 */
public class FileUtil {

	private static final Logger log = LogManager.getLogger(FileUtil.class);
	
	/**
	 * 删除文件
	 * @param filename
	 * @param path
	 */
	public static void deleteFile(String filename, String path) {
		File file = new File(path + filename);
		if(file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 上传文件到指定目录，返回新的文件名
	 * @param file 文件对象
	 * @param path 文件保存路径
	 * @param name 文件名
	 * @return
	 */
	public static String uploadFile(File file, String path, String name) {
		String filename = "";
		if(file != null) {
			File pathFile = new File(path);
			//判断目录是否存在，不存在则创建目录
			if(!pathFile.exists()) {
				pathFile.mkdir();
			}
			//新的文件名
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String d = sdf.format(date);
			String uuid = UUID.randomUUID().toString();
			uuid = uuid.substring(uuid.length() - 6, uuid.length());
			filename = d + uuid + name.substring(name.lastIndexOf("."), name.length());
			log.info(file.getName());
			
			//将文件保存到指定路径下
			InputStream is = null;
			OutputStream out = null;
			try {
				// 获取上传的图片
				is = new BufferedInputStream(new FileInputStream(file), 1024);
				// 指明上传的路径
				out = new BufferedOutputStream(
						new FileOutputStream(path + filename), 1024);
				byte[] bu = new byte[1024];
				while (is.read(bu) > 0) {
					out.write(bu);
				}
				out.close();
				is.close();
			} catch (FileNotFoundException e) {
				log.info("uploadFile error:" + e);
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filename;
	}
	
	public static void main(String[] args) {
		String name = "aaaa.jpg";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String d = sdf.format(date);
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 6, uuid.length());
		String filename = d + uuid + name.substring(name.lastIndexOf("."), name.length());
		System.out.println(filename);
	}
}
