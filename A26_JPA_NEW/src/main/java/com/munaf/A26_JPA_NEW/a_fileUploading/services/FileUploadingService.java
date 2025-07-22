package com.munaf.A26_JPA_NEW.a_fileUploading.services;

import com.munaf.A26_JPA_NEW.a_fileUploading.FileUploadingRepository;
import com.munaf.A26_JPA_NEW.a_fileUploading.dto.FileUploadRequest;
import com.munaf.A26_JPA_NEW.a_fileUploading.entities.FileUploading;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadingService {

    private final FileUploadingRepository fileUploadingRepository;

    public FileUploadingService(FileUploadingRepository fileUploadingRepository) {
        this.fileUploadingRepository = fileUploadingRepository;
    }

    public FileUploading uploadImage(FileUploadRequest fileUploadRequest) {
        // save image to s3
        MultipartFile image = fileUploadRequest.getImage();
        String fileName = image.getOriginalFilename();

        // Simulate uploading to S3 (you can replace this with actual S3 logic)
        String imageUrl = "https://amazon/images/url/" + fileName;

        FileUploading fileUploading = new FileUploading();
        fileUploading.setName(fileUploadRequest.getName());
        fileUploading.setType(fileUploadRequest.getType());
        fileUploading.setImageURL(imageUrl);

        fileUploadingRepository.save(fileUploading);

        return fileUploadingRepository.save(fileUploading);
    }
}
