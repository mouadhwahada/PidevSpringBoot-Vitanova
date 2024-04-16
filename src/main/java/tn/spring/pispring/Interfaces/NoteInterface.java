package tn.spring.pispring.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.Note;


import java.util.List;

public interface NoteInterface{
    Note addNote(Note note);
    Note UpdateNote(Long id, Note updatedNote);
    void deleteNote(long id);
    List<Note> findAllNotes();
    Note findNoteById(long id);
}
