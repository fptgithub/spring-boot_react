package com.java6.ass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.java6.ass.utils.FileStorageService;
@Controller
@CrossOrigin("*")
public class FileHanderController {
	
	@Autowired
	FileStorageService storageService;
	
	@PostMapping("/file-upload")
	public ResponseEntity<String> handleFileUpload(@RequestBody MultipartFile file
			) {
		storageService.store(file);
		return ResponseEntity.ok("ok");
	}
}
