package com.example.Proveedor.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Proveedor.Model.Empresa;
import com.example.Proveedor.Service.EmpresaService;

@RestController
@RequestMapping("/empresas")

public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;


    @PostMapping
    public String almacenar(@RequestBody Empresa empresa){
        return empresaService.almacenarEmpresa(empresa);
    }

    @GetMapping
    public List<Empresa> listar(){
        return empresaService.listarEmpresa();
    }

}
