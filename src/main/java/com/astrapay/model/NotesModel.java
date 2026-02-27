package com.astrapay.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class NotesModel {
    private String id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    public NotesModel(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
