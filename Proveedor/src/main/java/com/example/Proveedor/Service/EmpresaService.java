package com.example.Proveedor.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proveedor.Model.Empresa;
import com.example.Proveedor.Repository.EmpresaRepository;





@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public String almacenarEmpresa(Empresa empresa){
        if(empresaRepository.findByNombre(empresa.getNombre()) == null) {
            empresaRepository.save(empresa);
            return "Empresa " + empresa.getNombre() + " Almacenado Correctamente!";
        }else{
            return "Empresa " + empresa.getNombre() + " Ya se encuentra Almacenado!";
        }
    }

    public List<Empresa> listarEmpresa(){
        return empresaRepository.findAll();
    }



    

}
