package com.munaf.A13_SPRING_SECURITY_1.repositories;


import com.munaf.A13_SPRING_SECURITY_1.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
