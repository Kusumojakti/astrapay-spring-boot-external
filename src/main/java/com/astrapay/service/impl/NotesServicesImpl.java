package com.astrapay.service.impl;

import com.astrapay.dto.request.NotesRequest;
import com.astrapay.dto.response.NotesResponse;
import com.astrapay.entity.NotesEntity;
import com.astrapay.exception.NotesException;
import com.astrapay.service.NotesServices;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class NotesServicesImpl implements NotesServices {

    private final Map<String, NotesEntity> notes = new ConcurrentHashMap<>();
    @Override
    public NotesResponse addNewNote(NotesRequest request) {
        NotesEntity note = new NotesEntity(
                request.getTitle(),
                request.getContent()
        );
        notes.put(note.getId(), note);
        return new NotesResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }

    @Override
    public List<NotesResponse> getAllNotes() {
        return notes.values().stream().map(
                note -> new NotesResponse(
                        note.getId(),
                        note.getTitle(),
                        note.getContent(),
                        note.getCreatedAt(),
                        note.getUpdatedAt()
                )
        )
                .collect(Collectors.toList());
    }

    @Override
    public NotesResponse updateNote(String id, NotesRequest request) {
        if (!notes.containsKey(id)) {
            throw new NotesException(id);
        }

        NotesEntity note = notes.get(id);
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setUpdatedAt(new Date());

        return new NotesResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }

    @Override
    public void deleteNote(String id) {
        if (!notes.containsKey(id)) throw new NotesException(id);
        notes.remove(id);
    }
}

