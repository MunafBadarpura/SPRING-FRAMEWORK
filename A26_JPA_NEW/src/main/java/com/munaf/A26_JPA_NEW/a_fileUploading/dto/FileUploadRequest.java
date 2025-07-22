package com.munaf.A26_JPA_NEW.a_fileUploading.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadRequest {

    private String name;
    private String type;
    private MultipartFile image;

}
