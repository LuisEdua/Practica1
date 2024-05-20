package com.example.ordenesservice.Ordenes.Infrestructure.Repository.MySQLRepositories;

import com.example.ordenesservice.Ordenes.Domain.Entities.OrdenProductos;
import com.example.ordenesservice.Ordenes.Domain.Port.IOrdenProductosPort;
import com.example.ordenesservice.Ordenes.Infrestructure.Exceptions.NotFoundException;
import com.example.ordenesservice.Ordenes.Infrestructure.Models.MySQLModels.OrdenProductosModel;
import com.example.ordenesservice.Ordenes.Infrestructure.Repository.MySQLRepositories.JPA.IOrdenProductosRepository;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.OrdenProductosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdenProductosRepository implements IOrdenProductosPort {

    @Autowired
    OrdenRepository ordenRepository;

    @Autowired
    IOrdenProductosRepository repository;

    @Override
    public OrdenProductosResponse CrearOrdenProducto(OrdenProductos ordenProductos) {
        OrdenProductosModel model = new OrdenProductosModel();
        model.setId(ordenProductos.getId());
        model.setProducto_id(ordenProductos.getProductoId());
        model.setOrden(ordenRepository.findAndEnsureExist(ordenProductos.getOrdenId()));
        model.setCantidad(ordenProductos.getCantidad());
        model.setTotal(ordenProductos.getTotal());
        return from(repository.save(model));
    }

    @Override
    public List<OrdenProductosResponse> findProductosByOrdenId(String ordenId){
        List<OrdenProductosResponse> productos = repository.findByOrdenId(ordenId).stream().map(this::from).toList();
        if (productos.isEmpty()) {
            throw new NotFoundException("Productos no encontrados");
        }
        return productos;
    }

    OrdenProductosResponse from(OrdenProductosModel ordenProductos) {
        OrdenProductosResponse resp = new OrdenProductosResponse();
        resp.setId(ordenProductos.getId());
        resp.setProductoId(ordenProductos.getProducto_id());
        resp.setOrdenId(ordenProductos.getOrden().getId());
        resp.setCantidad(ordenProductos.getCantidad());
        resp.setTotal(ordenProductos.getTotal());
        return resp;
    }
}
