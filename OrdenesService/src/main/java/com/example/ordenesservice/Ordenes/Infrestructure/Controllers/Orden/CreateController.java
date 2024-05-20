package com.example.ordenesservice.Ordenes.Infrestructure.Controllers.Orden;


import com.example.ordenesservice.Ordenes.Application.UseCase.Orden.Create;
import com.example.ordenesservice.Ordenes.Application.UseCase.OrdenProductos.CreateOrdenProductos;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.requests.CreateOrdenRequest;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.BaseResponse;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.CreateOrdenResponse;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.OrdenProductosResponse;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.OrdenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class CreateController {
    @Autowired
    public Create mainUseCase;

    @Autowired
    public CreateOrdenProductos secondUseCase;

    @PostMapping
    public BaseResponse run(@RequestBody CreateOrdenRequest request){
        OrdenResponse orden = mainUseCase.run(request);
        List<OrdenProductosResponse> productos = secondUseCase.run(orden.getId(), request.getProductos());
        CreateOrdenResponse response = new CreateOrdenResponse();
        response.setOrden(orden);
        response.setProductos(productos);
        return BaseResponse.builder().data(response).message("Orden creada con exito").success(true).httpStatus(HttpStatus.valueOf(201)).build();
    }

}
