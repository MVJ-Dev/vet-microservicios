package com.example.mascota.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mascota.Model.Informacion;
import com.example.mascota.Repository.InformacionRepository;

@Service
public class InformacionService {
    @Autowired
    private InformacionRepository informacionRepository;

    public String almacenarInfo(Informacion informacion){
        if(informacionRepository.findByMicroChip(informacion.getMicroChip()) == null) {
            informacionRepository.save(informacion);
            return "Informacion " + informacion.getMicroChip() + " Almacenado Correctamente!";
        }else{
            return "Informacion " + informacion.getMicroChip() + " Ya se encuentra Almacenado!";
        }
    }

    public List<Informacion> listarInfo(){
        return this.informacionRepository.findAll();
    }
}