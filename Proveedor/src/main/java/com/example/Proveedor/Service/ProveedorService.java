package com.example.Proveedor.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proveedor.Model.Empresa;
import com.example.Proveedor.Model.Proveedor;
import com.example.Proveedor.Repository.EmpresaRepository;
import com.example.Proveedor.Repository.ProveedorRepository;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    public String almacenarProveedor(Proveedor proveedor){
        if(proveedorRepository.findByNombre(proveedor.getNombre()) == null) {
            proveedorRepository.save(proveedor);
            return "Proveedor " + proveedor.getNombre() + " Almacenado Correctamente!";
        }else{
            return "Proveedor " + proveedor.getNombre() + " Ya se encuentra Almacenado!";
        }
    }

    public List<Proveedor> listarProveedor(){
        return proveedorRepository.findAll();
    }

    public void actualizarProveedor(Integer id, Proveedor proveedor){
    proveedorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    proveedor.setIdProveedor(id);
    proveedorRepository.save(proveedor);
    }

    public void eliminarProveedor(Integer id){
        this.proveedorRepository.deleteById(id);
    }

    public Proveedor obtenerProveedor(Integer id){
        return proveedorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Proveedor No encontrado"));
    }

    public String asignarEmpresa(int idProveedor, int idEmpresa){
        if(!proveedorRepository.existsById(idProveedor)){
            return "El proveedor ingresado no existe"; 
        }else if(!empresaRepository.existsById(idEmpresa)){
            return "la empresa ingresada no existe";
        }else{
            Proveedor proveedor = proveedorRepository.findById(idProveedor).get();
            Empresa empresa = empresaRepository.findById(idEmpresa).get();
            
            proveedor.setEmpresa(empresa);
            proveedorRepository.save(proveedor);
            return "Empresa asignada correctamente";

        }
    }









}
