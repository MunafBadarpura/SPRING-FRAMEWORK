package com.munaf.A26_JPA_NEW.a_fileUploading.contollers;

import com.munaf.A26_JPA_NEW.a_fileUploading.dto.FileUploadRequest;
import com.munaf.A26_JPA_NEW.a_fileUploading.entities.FileUploading;
import com.munaf.A26_JPA_NEW.a_fileUploading.services.FileUploadingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@RestController
@RequestMapping("/file-uploading")
public class FileUploadingController {

    private final FileUploadingService fileUploadingService;


    public FileUploadingController(FileUploadingService fileUploadingService) {
        this.fileUploadingService = fileUploadingService;
    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileUploading uploadImage(@ModelAttribute FileUploadRequest fileUploadRequest) {
        return fileUploadingService.uploadImage(fileUploadRequest);
    }


}
