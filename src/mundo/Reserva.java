package mundo;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
    public static List<Reserva> listaReservas = new ArrayList<>();

    private String nombre;
    private String apellido;
    private long cedula;
    private long telefono;
    private int numHuespedes;
    private String metodoPago;

    private String fechaEntrada;
    private String horaEntrada;
    private String fechaSalida;
    private String horaSalida;
    private String tipoHabitacion;
    private int numeroHabitacion;

    public Reserva(String nombre, String apellido, long cedula, long telefono,
                   String fechaEntrada, String horaEntrada,
                   String fechaSalida, String horaSalida,
                   String tipoHabitacion, int numHuespedes, String metodoPago, int numeroHabitacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.tipoHabitacion = tipoHabitacion;
        this.numHuespedes = numHuespedes;
        this.metodoPago = metodoPago;
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public long getCedula() {
        return cedula;
    }

    public long getTelefono() {
        return telefono;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public int getNumHuespedes() {
        return numHuespedes;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public void setNumHuespedes(int numHuespedes) {
        this.numHuespedes = numHuespedes;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }
}