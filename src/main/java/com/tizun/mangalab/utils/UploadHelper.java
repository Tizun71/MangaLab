package com.tizun.mangalab.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadHelper {
    
    public String GenerateFileName(MultipartFile file) {
    	String fileName = file.getOriginalFilename();
    	String extension = "";
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex >= 0) {
            extension = fileName.substring(dotIndex + 1);
        }
        
    	String randomFileName = UUID.randomUUID().toString() + "." + extension;
    	return randomFileName;
    }
	
	public void uploadImage(MultipartFile file, String fileName, String uploadPath) throws IOException {
		StringBuilder fileNames = new StringBuilder();
		Path path = Paths.get(uploadPath);
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
		 String randomFileName = fileName;
		 Path fileNameAndPath = Paths.get(uploadPath, randomFileName);
         fileNames.append(randomFileName).append(" ");
         
         Files.write(fileNameAndPath, file.getBytes(), StandardOpenOption.CREATE);
	}
}
