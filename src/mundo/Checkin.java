package mundo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Checkin {
    public static List<Checkin> listaCheckins = new ArrayList<>();
    
    private String nombre;
    private String apellido;
    private long cedula;
    private long telefono;
    private String fechaIngreso;
    private String fechaSalida;
    private int diasHospedaje;
    private String alergias;
    private int numeroHabitacion;
    private String estado;
    private String observaciones;
    
    public Checkin(String nombre, String apellido, long cedula, long telefono,
                   String fechaIngreso, String fechaSalida, String alergias,
                   int numeroHabitacion, String observaciones) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.alergias = alergias;
        this.numeroHabitacion = numeroHabitacion;
        this.observaciones = observaciones;
        this.estado = "Activo";
        this.diasHospedaje = calcularDiasHospedaje();
    }
    
    private int calcularDiasHospedaje() {
        try {
            String[] partesIngreso = fechaIngreso.split("-");
            String[] partesSalida = fechaSalida.split("-");
            
            if (partesIngreso.length < 3) {
                partesIngreso = fechaIngreso.split("  -  ");
                partesSalida = fechaSalida.split("  -  ");
            }
            
            if (partesIngreso.length >= 3 && partesSalida.length >= 3) {
                int diaIngreso = Integer.parseInt(partesIngreso[0].trim());
                int mesIngreso = Integer.parseInt(partesIngreso[1].trim());
                int añoIngreso = Integer.parseInt(partesIngreso[2].trim());
                
                int diaSalida = Integer.parseInt(partesSalida[0].trim());
                int mesSalida = Integer.parseInt(partesSalida[1].trim());
                int añoSalida = Integer.parseInt(partesSalida[2].trim());
                
                LocalDate fechaInicio = LocalDate.of(añoIngreso, mesIngreso, diaIngreso);
                LocalDate fechaFin = LocalDate.of(añoSalida, mesSalida, diaSalida);
                
                long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
                return Math.max(1, (int) dias);
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }
    
    public void finalizarCheckin() {
        this.estado = "Finalizado";
    }
    
    public void actualizarDiasHospedaje() {
        this.diasHospedaje = calcularDiasHospedaje();
    }
    
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public long getCedula() { return cedula; }
    public long getTelefono() { return telefono; }
    public String getFechaIngreso() { return fechaIngreso; }
    public String getFechaSalida() { return fechaSalida; }
    public int getDiasHospedaje() { return diasHospedaje; }
    public String getAlergias() { return alergias; }
    public int getNumeroHabitacion() { return numeroHabitacion; }
    public String getEstado() { return estado; }
    public String getObservaciones() { return observaciones; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setCedula(long cedula) { this.cedula = cedula; }
    public void setTelefono(long telefono) { this.telefono = telefono; }
    public void setFechaIngreso(String fechaIngreso) { 
        this.fechaIngreso = fechaIngreso; 
        actualizarDiasHospedaje();
    }
    public void setFechaSalida(String fechaSalida) { 
        this.fechaSalida = fechaSalida; 
        actualizarDiasHospedaje();
    }
    public void setAlergias(String alergias) { this.alergias = alergias; }
    public void setNumeroHabitacion(int numeroHabitacion) { this.numeroHabitacion = numeroHabitacion; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    @Override
    public String toString() {
        return "Checkin{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula=" + cedula +
                ", habitacion=" + numeroHabitacion +
                ", estado='" + estado + '\'' +
                ", dias=" + diasHospedaje +
                '}';
    }
}
