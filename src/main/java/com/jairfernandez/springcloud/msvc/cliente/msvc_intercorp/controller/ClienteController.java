package com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.entity.Cliente;
import com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.entity.ClienteDTO;
import com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.service.IClienteService;

@RestController
@RequestMapping("/Cliente")
public class ClienteController {
    @Autowired
    IClienteService service;

    @GetMapping("/listar")
    public List<ClienteDTO> listar(){
        return service.listar();
    }
    
    @PostMapping("/creacliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente crearCliente(@RequestBody Cliente cliente){
        return service.crear(cliente);
    }

    @GetMapping("/kpideclientes")
    public Map<String,Double> kpideclientes (){
        return service.kpideclientes();
    }
}
