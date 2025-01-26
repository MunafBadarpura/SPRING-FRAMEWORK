package com.munaf.A14_SPRING_REDIS.repositories;

import com.munaf.A14_SPRING_REDIS.entities.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepo extends JpaRepository<Notes, Long> {
}
