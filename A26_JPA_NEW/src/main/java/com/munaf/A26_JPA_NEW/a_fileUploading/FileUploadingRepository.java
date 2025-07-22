package com.munaf.A26_JPA_NEW.a_fileUploading;

import com.munaf.A26_JPA_NEW.a_fileUploading.entities.FileUploading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadingRepository extends JpaRepository<FileUploading, Long> {
}
