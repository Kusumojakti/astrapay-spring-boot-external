package com.astrapay.controller;

import com.astrapay.dto.request.NotesRequest;
import com.astrapay.dto.response.BaseResponses;
import com.astrapay.dto.response.NotesResponse;
import com.astrapay.service.NotesServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "${app.cors.allowed-origins")
@RequestMapping("api/v1/notes")
public class NotesControllers {
    private final NotesServices notesServices;

    public NotesControllers(NotesServices notesServices) {
        this.notesServices = notesServices;
    }

    @GetMapping
    public ResponseEntity<BaseResponses<List<NotesResponse>>> getAllNotes() {
        return ResponseEntity.ok(
                new BaseResponses<>(
                        true,
                        "All notes successfully retrieved",
                        notesServices.getAllNotes()
                )
        );
    }

    @PostMapping
    public ResponseEntity<BaseResponses<NotesResponse>> addNewNote(@Valid @RequestBody NotesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponses<>(
                true,
                "Note successfully created",
                notesServices.addNewNote(request)
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponses<NotesResponse>> updateNote(@PathVariable String id, @Valid @RequestBody NotesRequest request) {
        return ResponseEntity.ok(new BaseResponses<>(
                true,
                "Note successfully updated",
                notesServices.updateNote(id, request)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponses<Void>> deleteNote(@PathVariable String id) {
        notesServices.deleteNote(id);
        return ResponseEntity.ok(new BaseResponses<>(
                true,
                "Note successfully deleted"
        ));
    }
}
