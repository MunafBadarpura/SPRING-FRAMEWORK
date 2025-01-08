package com.munaf.A12_PROD_READY_FEATURE.repositories;

import com.munaf.A12_PROD_READY_FEATURE.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
