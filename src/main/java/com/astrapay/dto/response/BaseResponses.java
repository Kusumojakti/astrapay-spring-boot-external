package com.astrapay.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponses<T> {
    private boolean success;
    private String message;
    private T data;

    public BaseResponses(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public BaseResponses(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
