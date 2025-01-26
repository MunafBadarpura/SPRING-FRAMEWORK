package com.munaf.A14_SPRING_REDIS.services.impl;

import com.munaf.A14_SPRING_REDIS.entities.Notes;
import com.munaf.A14_SPRING_REDIS.repositories.NotesRepo;
import com.munaf.A14_SPRING_REDIS.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NotesRepo notesRepo;

    @Override
    public Notes createNewNote(Notes notes) {
        return notesRepo.save(notes);
    }

    @Override
    public List<Notes> getAllNotes() {
        return notesRepo.findAll();
    }

    @Override
    public Notes getNoteById(Long noteId) {
        return notesRepo.findById(noteId).orElseThrow(() -> new NoSuchElementException("Note Not Exists"));
    }

    @Override
    public Notes updateNoteById(Long noteId, Notes notes) {
        notes.setId(noteId);
        return notesRepo.save(notes);
    }

    @Override
    public String deleteNoteById(Long noteId) {
        notesRepo.deleteById(noteId);
        return "DELETED";
    }
}
