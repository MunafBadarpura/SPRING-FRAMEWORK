package com.munaf.A14_SPRING_REDIS.services;

import com.munaf.A14_SPRING_REDIS.entities.Notes;

import java.util.List;

public interface NotesService {

    //create, getall, getbyid, update, elete

    public Notes createNewNote(Notes notes);
    public List<Notes> getAllNotes();
    public Notes getNoteById(Long noteId);
    public Notes updateNoteById(Long noteId, Notes notes);
    public String deleteNoteById(Long noteId);




}
