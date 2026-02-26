package com.astrapay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesRequest {

    @NotBlank(message = "Title cannot be empty!")
    @Size(max = 250, message = "Title must not exceed 250 characters")
    private String title;

    @NotBlank(message = "Content cannot be empty!")
    @Size(max = 2500, message = "Content must not exceed 2500 characters")
    private String content;
}
