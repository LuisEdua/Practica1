package com.example.ordenesservice.Ordenes.Domain.Port;

import com.example.ordenesservice.Ordenes.Domain.Entities.OrdenProductos;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.OrdenProductosResponse;

public interface IOrdenProductosPort {
    OrdenProductosResponse CrearOrdenProducto(OrdenProductos ordenProductos);
}
