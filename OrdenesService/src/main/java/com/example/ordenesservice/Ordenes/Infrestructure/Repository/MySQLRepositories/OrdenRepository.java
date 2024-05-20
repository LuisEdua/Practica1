package com.example.ordenesservice.Ordenes.Infrestructure.Repository.MySQLRepositories;

import com.example.ordenesservice.Ordenes.Domain.Entities.Orden;
import com.example.ordenesservice.Ordenes.Domain.Port.IOrdenPort;
import com.example.ordenesservice.Ordenes.Infrestructure.Exceptions.NotFoundException;
import com.example.ordenesservice.Ordenes.Infrestructure.Models.MySQLModels.OrdenModel;
import com.example.ordenesservice.Ordenes.Infrestructure.Repository.MySQLRepositories.JPA.IOrdenRepository;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.BaseResponse;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.OrdenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdenRepository implements IOrdenPort {

    @Autowired
    private IOrdenRepository repository;

    @Override
    public BaseResponse Listar() {
        return from(repository.findAll().stream().map(this::from).toList(),
                "ordenes listadas correctamente", true, 200);
    }

    @Override
    public OrdenResponse Crear(Orden orden) {
        OrdenModel model = new OrdenModel();
        model.setId(orden.getId());
        model.setFecha(orden.getFecha());
        model.setStatus(orden.getStatus());
        model.setTotal(orden.getTotal());
        return from(repository.save(model));
    }

    @Override
    public BaseResponse ActualizarStatus(String id, String status) {
        OrdenModel model = findAndEnsureExist(id);
        model.setStatus(status);
        return from(from(repository.save(model)),
                "Status actualizado exitosamente", true, 200);
    }

    private BaseResponse from(OrdenResponse response, String message, boolean success, int code) {
        return BaseResponse.builder()
                .data(response).message(message).success(success).httpStatus(HttpStatus.valueOf(code)).build();
    }

    public OrdenModel findAndEnsureExist(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Orden no encontrada"));
    }

    OrdenResponse from(OrdenModel ordenModel) {
        OrdenResponse ordenResponse = new OrdenResponse();
        ordenResponse.setId(ordenModel.getId());
        ordenResponse.setStatus(ordenModel.getStatus());
        ordenResponse.setFecha(ordenModel.getFecha());
        ordenResponse.setTotal(ordenModel.getTotal());
        return ordenResponse;
    }

    BaseResponse from(List<OrdenResponse> responses, String message, boolean success, int code) {
        return BaseResponse.builder()
                .data(responses)
                .message(message)
                .success(success)
                .httpStatus(HttpStatus.valueOf(code))
                .build();
    }



}
