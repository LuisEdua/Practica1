package com.example.ordenesservice.Ordenes.Infrestructure.Models.MySQLModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordenes")
@Getter @Setter
public class OrdenModel {
    @Id
    private String id;

    @Column(nullable = false)
    private float total;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "orden")
    private List<OrdenProductosModel> productos;
}
