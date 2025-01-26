package com.munaf.A14_SPRING_REDIS.controllers;

import com.munaf.A14_SPRING_REDIS.entities.Notes;
import com.munaf.A14_SPRING_REDIS.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotesController {

    private final NotesService notesService;


    @PostMapping
    public ResponseEntity<Notes> createNewNote(@RequestBody Notes notes) {
        return new ResponseEntity<>(notesService.createNewNote(notes), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Notes>> getALlNotes() {
        return new ResponseEntity<>(notesService.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Notes> getNoteById(@PathVariable Long noteId) {
        return new ResponseEntity<>(notesService.getNoteById(noteId), HttpStatus.OK);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<Notes> update(@RequestBody Notes notes, @PathVariable Long noteId) {
        return new ResponseEntity<>(notesService.updateNoteById(noteId, notes), HttpStatus.OK);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> delete(@PathVariable Long noteId) {
        return new ResponseEntity<>(notesService.deleteNoteById(noteId), HttpStatus.OK);
    }

}
