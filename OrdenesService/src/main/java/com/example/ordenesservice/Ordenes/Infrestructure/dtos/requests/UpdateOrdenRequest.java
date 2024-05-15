package com.example.ordenesservice.Ordenes.Infrestructure.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateOrdenRequest {
    private String id;
    private String status;
}
