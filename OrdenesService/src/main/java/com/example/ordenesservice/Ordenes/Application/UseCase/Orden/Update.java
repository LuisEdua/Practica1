package com.example.ordenesservice.Ordenes.Application.UseCase.Orden;

import com.example.ordenesservice.Ordenes.Domain.Port.IOrdenPort;
import com.example.ordenesservice.Ordenes.Domain.Port.IOrdenProductosPort;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.requests.UpdateOrdenRequest;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.BaseResponse;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.OrdenProductosResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Update {

    @Autowired
    private IOrdenPort repository;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private IOrdenProductosPort productosRepository;


    public BaseResponse run(UpdateOrdenRequest request){
        String id = request.getId();
        String status = request.getStatus();
        BaseResponse response = repository.ActualizarStatus(id, status);
        if (status.equalsIgnoreCase("Enviado")){
            List<OrdenProductosResponse> listaProductos = productosRepository.findProductosByOrdenId(id);
            for (OrdenProductosResponse producto : listaProductos) {
                updateProductos(from(producto));
            }
        }
        return response;
    }

    private void updateProductos(String request){
        template.convertAndSend("queue.change_orden_status", request);
    }

    private String from(OrdenProductosResponse producto) {
        String request = "{\"id\": \"" + producto.getProductoId() + "\", \"cantidad\": " + producto.getCantidad() + "}";
        return request;
    }
}
