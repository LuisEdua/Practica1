package com.example.ordenesservice.Ordenes.Domain.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Orden {
    private String id;

    @Setter
    private float total;

    @Setter
    private Date fecha;

    @Setter
    private String status;

    public Orden() {
        this.id = UUID.randomUUID().toString();
    }



}
