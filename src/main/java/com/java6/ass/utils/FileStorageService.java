package com.java6.ass.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

	public void store( MultipartFile file){
		FileOutputStream output = null;
		try {
			String fileLocation = new File("src\\main\\resources\\static\\images").getAbsolutePath() + "\\" + file.getOriginalFilename();
			output = new FileOutputStream(fileLocation);

			output.write(file.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				output.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
