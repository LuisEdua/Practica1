package com.example.ordenesservice.Ordenes.Infrestructure.dtos.requests;

import com.example.ordenesservice.Ordenes.Domain.Entities.Orden;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class CreateOrdenRequest {

    private float total;

    private Date fecha;

    private String status;

    private List<CreateOrdenProductosRequest> productos;
}
