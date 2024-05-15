package com.example.ordenesservice.Ordenes.Domain.Port;


import com.example.ordenesservice.Ordenes.Domain.Entities.Orden;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.BaseResponse;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.OrdenResponse;

public interface IOrdenPort {
    BaseResponse Listar();
    OrdenResponse Crear(Orden orden);
    BaseResponse ActualizarStatus(String id, String status);
}
