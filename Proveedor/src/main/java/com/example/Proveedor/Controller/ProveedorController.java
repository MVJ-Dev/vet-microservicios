package com.example.Proveedor.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Proveedor.Model.Proveedor;
import com.example.Proveedor.Service.ProveedorService;


@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;


    @PostMapping
    public String almacenar(@RequestBody Proveedor proveedor){
        return proveedorService.almacenarProveedor(proveedor);
    }


    @GetMapping
    public List<Proveedor> listarProveedor(){
        return proveedorService.listarProveedor();
    }

    @PutMapping("/{id}")
    public void actualizarProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor){
        this.proveedorService.actualizarProveedor(id, proveedor);
    }

    @DeleteMapping("/{id}")
    public void eliminarProvedor(@PathVariable Integer id){
        this.proveedorService.eliminarProveedor(id);
    }

    @GetMapping("/{id}")
    public Proveedor obtenerProveedor(@PathVariable Integer id){
        return proveedorService.obtenerProveedor(id);
    }

    @PostMapping("/asignar/{idProveedor}/{idEmpresa}")
        public String asignarEmpresa(@PathVariable Integer idProveedor, @PathVariable Integer idEmpresa){
            return proveedorService.asignarEmpresa(idProveedor,idEmpresa);

    }





}
