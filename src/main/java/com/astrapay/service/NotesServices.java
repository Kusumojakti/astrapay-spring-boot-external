package com.astrapay.service;

import com.astrapay.dto.request.NotesRequest;
import com.astrapay.dto.response.NotesResponse;

import java.util.List;

public interface NotesServices {
    NotesResponse addNewNote(NotesRequest request);
    List<NotesResponse> getAllNotes();
    NotesResponse updateNote(String id, NotesRequest request);
    void deleteNote(String id);
}
