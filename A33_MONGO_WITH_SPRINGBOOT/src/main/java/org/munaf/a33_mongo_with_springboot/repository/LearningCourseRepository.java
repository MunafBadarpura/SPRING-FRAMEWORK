package org.munaf.a33_mongo_with_springboot.repository;

import org.munaf.a33_mongo_with_springboot.entity.LearningCourse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LearningCourseRepository extends MongoRepository<LearningCourse, String> {
}
