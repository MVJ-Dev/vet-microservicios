package com.example.mascota.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mascota.Model.Informacion;
import com.example.mascota.Service.InformacionService;

@RestController
@RequestMapping("/informaciones")
public class InformacionController {
    @Autowired
    private InformacionService informacionService;

    @PostMapping
    public String almacenarInfo(@RequestBody Informacion informacion){
        return informacionService.almacenarInfo(informacion);
    }

    @GetMapping
    public List<Informacion> listarInfo(){
        return informacionService.listarInfo();
    }

}
