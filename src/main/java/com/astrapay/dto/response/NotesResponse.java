package com.astrapay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor

public class NotesResponse {
    private String id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updateAt;
}
