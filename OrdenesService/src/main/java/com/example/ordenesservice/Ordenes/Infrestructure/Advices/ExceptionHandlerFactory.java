package com.example.ordenesservice.Ordenes.Infrestructure.Advices;

import com.example.ordenesservice.Ordenes.Infrestructure.Exceptions.NotFoundException;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerFactory {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<BaseResponse> handleChangeSetNotFoundException(NotFoundException e) {
        BaseResponse errorResponse = BaseResponse.builder()
                .message(e.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
