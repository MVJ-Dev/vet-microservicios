package com.example.Inventario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Inventario.Model.Inventario;
import com.example.Inventario.Repository.InventarioRepository;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public String almacenarProducto(Inventario inventario){
        if(inventarioRepository.findByNombre(inventario.getNombre()) == null) {
            inventarioRepository.save(inventario);
            return "Producto: " + inventario.getNombre() + " Almacenado Correctamente!";
        }else{
            return "Producto: " + inventario.getNombre() + " Ya se encuentra Almacenado!";
        }
    }

    public List<Inventario> listarProductos(){
        return inventarioRepository.findAll();
    }

    public void actualizarProductos(Integer idProducto, Inventario inventario){
    inventarioRepository.findById(idProducto)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    inventario.setIdProducto(idProducto);
    inventarioRepository.save(inventario);
    }

    public void eliminarProducto(Integer idProducto){
        this.inventarioRepository.deleteById(idProducto);
    }

    public Inventario obtenerProducto(Integer idProducto){
        return inventarioRepository.findById(idProducto)
        .orElseThrow(() -> new RuntimeException("Producto No encontrado"));
    }



    public Inventario actualizarStock(
        Integer idProducto,
        Integer cantidad,
        String tipo) {

    Inventario producto = inventarioRepository.findById(idProducto)
        .orElseThrow(() ->
            new RuntimeException("Producto no encontrado"));

    if(tipo.equalsIgnoreCase("ENTRADA")) {

        producto.setStock(
            producto.getStock() + cantidad);

    } else if(tipo.equalsIgnoreCase("SALIDA")) {

        if(producto.getStock() < cantidad) {
            throw new RuntimeException(
                "Stock insuficiente");
        }

        producto.setStock(
            producto.getStock() - cantidad);
    }

    return inventarioRepository.save(producto);
}

    



    

}
