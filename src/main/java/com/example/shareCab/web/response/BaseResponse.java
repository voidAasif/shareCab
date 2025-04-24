package com.example.shareCab.web.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {
    private String status;
    private String message;
    private T data;
}