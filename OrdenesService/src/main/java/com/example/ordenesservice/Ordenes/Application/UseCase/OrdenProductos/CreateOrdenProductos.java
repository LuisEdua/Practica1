package com.example.ordenesservice.Ordenes.Application.UseCase.OrdenProductos;

import com.example.ordenesservice.Ordenes.Domain.Entities.OrdenProductos;
import com.example.ordenesservice.Ordenes.Domain.Port.IOrdenProductosPort;
import com.example.ordenesservice.Ordenes.Infrestructure.Repository.MySQLRepositories.OrdenProductosRepository;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.requests.CreateOrdenProductosRequest;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.OrdenProductosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrdenProductos {

    @Autowired
    IOrdenProductosPort repository;
    
    public List<OrdenProductosResponse> run(String id, List<CreateOrdenProductosRequest> productos){
        return productos.stream().map(producto -> from(id, producto)).toList();
    }

    private OrdenProductosResponse from(String id, CreateOrdenProductosRequest createOrdenProductosRequest) {
        OrdenProductos ordenProductos = new OrdenProductos();
        ordenProductos.setProductoId(createOrdenProductosRequest.getProductoId());
        ordenProductos.setCantidad(createOrdenProductosRequest.getCantidad());
        ordenProductos.setTotal(createOrdenProductosRequest.getTotal());
        ordenProductos.setOrdenId(id);
        return repository.CrearOrdenProducto(ordenProductos);
    }
}
