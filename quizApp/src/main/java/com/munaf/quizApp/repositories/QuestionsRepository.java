package com.munaf.quizApp.repositories;

import com.munaf.quizApp.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByCategory(String category);

    @Query(value = "SELECT * FROM questions_table q WHERE q.category=?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    List<QuestionEntity> getRandomQuestionsByCategoryWithSize(String category, Integer size);
}
