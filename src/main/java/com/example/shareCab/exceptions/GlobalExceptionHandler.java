package com.example.shareCab.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.shareCab.web.response.BaseResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //MethodArgumentNotValidException: this exception throws when some error occurs while validation(@Valid), so its auto execute this handleValidationExceptions method;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<List<Map<String, String>>>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        //now we map all the errors coming form exception into Map<String, String> | Map<Key, Value>;
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> err = new HashMap<>();
                    err.put("field", error.getField());
                    err.put("message", error.getDefaultMessage());
                    return err;
                })
                .collect(Collectors.toList()); //after that we make List of Maps<String, String>;

        //use BaseResponse and set errors into data attribute of response;
        BaseResponse<List<Map<String, String>>> response = BaseResponse.<List<Map<String, String>>>builder()
                .status("fail")
                .message("Validation failed")
                .data(errors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //and return the exception | status code: 400;
    }
}
