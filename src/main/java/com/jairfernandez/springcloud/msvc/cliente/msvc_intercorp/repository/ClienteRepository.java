package com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.repository;

import org.springframework.data.repository.CrudRepository;

import com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {
    
}
