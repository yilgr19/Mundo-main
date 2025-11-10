package mundo;

import java.util.ArrayList;
import java.util.List;

public class Habitaciones {
    public static List<Habitaciones> listaHabitaciones = new ArrayList<>();
    
    private int numero;
    private double precio;
    private String descripcion;
    private boolean estado;
    private String disponibilidad;

    public Habitaciones(int numero, double precio, String descripcion, boolean estado) { 
        this.numero = numero;
        this.precio = precio;
        this.descripcion = descripcion; 
        this.estado = estado;
        actualizarDisponibilidad();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
        actualizarDisponibilidad();
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    private void actualizarDisponibilidad() {
        if (this.estado) {
            this.disponibilidad = "Disponible";
        } else {
            this.disponibilidad = "Ocupado";
        }
    }

    @Override
    public String toString() {
        return "Numero: " + numero + "\nPrecio: " + precio + "\nDescripcion: " + descripcion + "\nEstado: " + disponibilidad;
    }
}