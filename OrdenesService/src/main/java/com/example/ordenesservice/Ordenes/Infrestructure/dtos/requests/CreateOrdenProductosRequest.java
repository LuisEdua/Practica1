package com.example.ordenesservice.Ordenes.Infrestructure.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateOrdenProductosRequest {

    private String productoId;

    private float total;

    private int cantidad;
}
