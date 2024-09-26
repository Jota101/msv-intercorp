package com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.service;

import java.util.List;
import java.util.Map;

import com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.entity.Cliente;
import com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.entity.ClienteDTO;

public interface IClienteService {
    public Cliente crear(Cliente cl);
    public double promedioEdad();
    public List<ClienteDTO> listar();
    public String fechaProbableMuerte (Cliente c);
    public double desviacionEstandar();
    public Map<String,Double> kpideclientes();
}
