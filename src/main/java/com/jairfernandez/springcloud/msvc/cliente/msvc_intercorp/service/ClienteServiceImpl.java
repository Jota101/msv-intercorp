package com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.entity.Cliente;
import com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.entity.ClienteDTO;
import com.jairfernandez.springcloud.msvc.cliente.msvc_intercorp.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService{
    @Autowired
    private ClienteRepository customerRepo;

    @Override
    @Transactional
    public Cliente crear(Cliente cl) {
        return customerRepo.save(cl);
    }

    @Override
    @Transactional
    public double promedioEdad() {
        List<Cliente> listaClientes = (List<Cliente>) customerRepo.findAll();
        double promedio = listaClientes.stream()
            .mapToInt(customer -> Period.between(customer.getFechaNac(), LocalDate.now()).getYears())
            .average().orElse(0.0);
        return promedio;
    }

    

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> listar() {
        List<Cliente> listaClientes=(List<Cliente>)customerRepo.findAll();
        List<ClienteDTO> datos = listaClientes.stream().map(cliente -> {
            return new ClienteDTO(cliente.getCodigo(), cliente.getNombre(), cliente.getApellido(), cliente.getEdad(), cliente.getFechaNac(),fechaProbableMuerte(cliente));
        }).collect(Collectors.toList());
        return datos;
    }

    @Override
    public String fechaProbableMuerte(Cliente c){
        Random random = new Random();
        String fechaMuerte="";
        int anioNacimiento = c.getFechaNac().getYear();
        String mesMuerte =String.valueOf(random.nextInt(0,12));

        //Como la esperanza de vida en el Peru es de 73 años ese sera el limite por cada anio de nacimiento por cliente
        int anioMuerte = random.nextInt(anioNacimiento,anioNacimiento+73);
        
        if (anioMuerte%4==0) {//Anio Bisiesto
            
            switch (mesMuerte) {
                case "1":
                case "3":
                case "5":
                case "7":
                case "8":
                case "10":
                case "12":
                    fechaMuerte=String.valueOf(random.nextInt(0,32)).concat(mesMuerte).concat(String.valueOf(anioMuerte));
                    break;
                case "2":
                    fechaMuerte=String.valueOf(random.nextInt(0,30)).concat(mesMuerte).concat(String.valueOf(anioMuerte));
                    break;
                default:
                    fechaMuerte=String.valueOf(random.nextInt(0,31)).concat(mesMuerte).concat(String.valueOf(anioMuerte));
                    break;
            }
        }
        else{//Para evitar el codigo repetitivo se puede usar el patron de diseño factory method
            switch (mesMuerte) {
                case "1":
                case "3":
                case "5":
                case "7":
                case "8":
                case "10":
                case "12":
                    fechaMuerte=String.valueOf(random.nextInt(0,32)).concat("-"+mesMuerte).concat(String.valueOf("-"+anioMuerte));
                    break;
                case "2":
                    fechaMuerte=String.valueOf(random.nextInt(0,29)).concat("-"+mesMuerte).concat(String.valueOf("-"+anioMuerte));
                    break;
                default:
                    fechaMuerte=String.valueOf(random.nextInt(0,31)).concat("-"+mesMuerte).concat(String.valueOf("-"+anioMuerte));
                    break;
            }
        }
        
        
        return fechaMuerte;
    }

    @Override
    public double desviacionEstandar() {
        double promedio = promedioEdad();
        List<Cliente> listaClientes=(List<Cliente>)customerRepo.findAll();
        double sumatoria = listaClientes.stream().mapToDouble(cliente -> {
            return Math.pow(cliente.getEdad()-promedio, 2);
        }).sum();
        return sumatoria/listaClientes.size();
    }

    @Override
    @Transactional
    public Map<String,Double> kpideclientes(){
        Map<String, Double> response= new HashMap<>();
        response.put("Promedio de edades: ",promedioEdad() );
        response.put("Desviacion Estandar", desviacionEstandar());
        return response;
    }
}
