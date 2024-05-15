package com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter @Getter
public class BaseResponse {

    private Object data;
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;

}
