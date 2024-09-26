package com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.entity;

import java.time.LocalDate;

public class ClienteDTO {
    private Long codigo;
    private String nombre;
    private String apellido;
    private int edad;
    private LocalDate fechaNacimiento;
    private String fechaMuerte;

    public ClienteDTO(Long codigo, String nombre, String apellido, int edad, LocalDate fechaNacimiento,String fechaMuerte){
        this.codigo=codigo;
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
        this.fechaNacimiento=fechaNacimiento;
        this.fechaMuerte=fechaMuerte;
    }

    // Getters
    public Long getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaMuerte(){
        return fechaMuerte;
    }
}
