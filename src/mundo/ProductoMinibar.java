package mundo;

import java.util.ArrayList;
import java.util.List;

public class ProductoMinibar {
    public static List<ProductoMinibar> listaProductos = new ArrayList<>();
    
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
    private String descripcion;
    
    public ProductoMinibar(String nombre, double precio, int stock, String categoria, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }
    
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getCategoria() { return categoria; }
    public String getDescripcion() { return descripcion; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public void reducirStock(int cantidad) {
        if (this.stock >= cantidad) {
            this.stock -= cantidad;
        }
    }
    
    public void aumentarStock(int cantidad) {
        this.stock += cantidad;
    }
    
    @Override
    public String toString() {
        return nombre + " - $" + precio + " (Stock: " + stock + ")";
    }
}
