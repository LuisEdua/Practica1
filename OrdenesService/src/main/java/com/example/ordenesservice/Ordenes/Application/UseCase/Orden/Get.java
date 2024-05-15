package com.example.ordenesservice.Ordenes.Application.UseCase.Orden;

import com.example.ordenesservice.Ordenes.Domain.Port.IOrdenPort;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Get {

    @Autowired
    IOrdenPort repository;

    public BaseResponse run(){
        return repository.Listar();
    }
}
