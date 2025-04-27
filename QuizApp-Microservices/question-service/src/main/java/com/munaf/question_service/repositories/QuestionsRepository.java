package com.munaf.question_service.repositories;

import com.munaf.question_service.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByCategory(String category);

    @Query(value = "SELECT q.id FROM questions_table q WHERE q.category=?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    List<Long> getRandomQuestionsByCategoryWithSize(String category, Integer size);
}
