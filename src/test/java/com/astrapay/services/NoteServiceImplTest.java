package com.astrapay.services;

import com.astrapay.dto.request.NotesRequest;
import com.astrapay.dto.response.NotesResponse;
import com.astrapay.exception.NotesException;
import com.astrapay.service.NotesServices;
import com.astrapay.service.impl.NotesServicesImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NoteServiceImplTest {

    private final NotesServices noteService = new NotesServicesImpl();

    @Test
    void testGetAllNotes() {
        noteService.addNewNote(new NotesRequest("Note 1", "Tested for note 1"));
        noteService.addNewNote(new NotesRequest("Note 2", "Tested for note 2"));

        List<NotesResponse> notes = noteService.getAllNotes();
        assertTrue(notes.size() >= 2);
    }

    @Test
    void testAddNewNote() {
        NotesResponse response = noteService.addNewNote(new NotesRequest("Test Note", "This is a test note"));
        assertNotNull(response.getId());
        assertEquals("Test Note", response.getTitle());
        assertEquals("This is a test note", response.getContent());
    }

    @Test
    void testUpdateNote() {
        String newTitle = "New Note";
        String newContent = "New Notes content";

        NotesResponse response = noteService.addNewNote(
                new NotesRequest("Old Title", "Old Content")
        );

        NotesResponse updated = noteService.updateNote(
                response.getId(),
                new NotesRequest(newTitle, newContent)
        );

        assertAll(
                () -> assertEquals(response.getId(), updated.getId()),
                () -> assertEquals(newTitle, updated.getTitle()),
                () -> assertEquals(newContent, updated.getContent())
        );
    }

    @Test
    void testDeleteNote() {
        NotesResponse response = noteService.addNewNote(new NotesRequest("Notes Deleted Test","Test Deleted Data"));
        noteService.deleteNote(response.getId());

        List<NotesResponse> notes = noteService.getAllNotes();
        assertTrue(notes.stream().noneMatch(
                n -> n.getId().equals(response.getId())
        ));
    }

    @Test
    void testNotesException(){
        NotesRequest updateRequest = new NotesRequest("Title", "Content");
        assertThrows(NotesException.class, () -> {
            noteService.updateNote("no-id", updateRequest);
        });
    }

    @Test
    void testDeleteNotesException() {
        assertThrows(NotesException.class, () -> noteService.deleteNote("invalid-id"));
    }
}
