package com.example.ordenesservice.Ordenes.Infrestructure.Controllers.Orden;

import com.example.ordenesservice.Ordenes.Application.UseCase.Orden.Update;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.requests.UpdateOrdenRequest;
import com.example.ordenesservice.Ordenes.Infrestructure.dtos.responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordenes")
public class UpdateController {

    @Autowired
    private Update useCase;

    @PutMapping
    public BaseResponse update(@RequestBody UpdateOrdenRequest request) {return useCase.run(request);}

}
