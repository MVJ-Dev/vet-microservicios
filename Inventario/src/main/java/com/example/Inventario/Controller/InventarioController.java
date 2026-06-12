package com.example.Inventario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Inventario.Model.Inventario;
import com.example.Inventario.Service.InventarioService;

@RestController
@RequestMapping("/productos")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public String almacenarProductos(@RequestBody Inventario inventario){
        return inventarioService.almacenarProducto(inventario);
    }

    @GetMapping
    public List<Inventario> listarInventario(){
        return inventarioService.listarProductos();
    }

    @PutMapping("/{idProducto}")
    public void actualizarInventario(@PathVariable Integer idProducto, @RequestBody Inventario inventario){
        this.inventarioService.actualizarProductos(idProducto, inventario);
    }

    @DeleteMapping("/{idProducto}")
    public void eliminarProductos(@PathVariable Integer idProducto){
        this.inventarioService.eliminarProducto(idProducto);
    }

    @GetMapping("/{idProducto}")
    public Inventario obtenerProductos (@PathVariable Integer idProducto){
        return inventarioService.obtenerProducto(idProducto);
    }

    @PutMapping("/{idProducto}/stock")
    public Inventario actualizarStock(

        @PathVariable Integer idProducto,
        @RequestParam Integer cantidad,
        @RequestParam String tipo) {

    return inventarioService.actualizarStock(
            idProducto,
            cantidad,
            tipo);
}



}
