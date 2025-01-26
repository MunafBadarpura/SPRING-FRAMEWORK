package com.munaf.A14_SPRING_REDIS.services.impl;

import com.munaf.A14_SPRING_REDIS.entities.Notes;
import com.munaf.A14_SPRING_REDIS.repositories.NotesRepo;
import com.munaf.A14_SPRING_REDIS.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {
    private final NotesRepo notesRepo;

    @Caching(
            put = @CachePut(value = "notes", key = "#notes.id"),
            evict = @CacheEvict(value = "notes", allEntries = true) // we clear because of get All api
    )
    public Notes createNewNote(Notes notes) {
        return notesRepo.save(notes);
    }

    // don't apply cache on getAll Because it will store memory on ram
    @Cacheable(value = "notes")
    public List<Notes> getAllNotes() {
        return notesRepo.findAll();
    }

    @Cacheable(value = "notes", key = "#noteId")
    public Notes getNoteById(Long noteId) {
        return notesRepo.findById(noteId).orElseThrow(() -> new NoSuchElementException("Note Not Exists"));
    }

    @Caching(
            put = @CachePut(value = "notes", key = "#noteId"),
            evict = @CacheEvict(value = "notes", allEntries = true)
    )
    public Notes updateNoteById(Long noteId, Notes notes) {
        notes.setId(noteId);
        return notesRepo.save(notes);
    }

    @CacheEvict(value = "notes", allEntries = true)  // allEntries = true can be used to clear the entire cache.
    public String deleteNoteById(Long noteId) {
        notesRepo.deleteById(noteId);
        return "DELETED";
    }
}
