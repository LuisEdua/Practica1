package com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrdenProductosResponse {
    private String id;

    private String ordenId;

    private String productoId;

    private float total;

    private int cantidad;
}
