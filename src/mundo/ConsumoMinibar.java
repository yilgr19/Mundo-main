package mundo;

import java.util.ArrayList;
import java.util.List;

public class ConsumoMinibar {
    public static List<ConsumoMinibar> listaConsumos = new ArrayList<>();
    
    private long cedulaCliente;
    private int numeroHabitacion;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private String fechaConsumo;
    private boolean facturado;
    
    public ConsumoMinibar(long cedulaCliente, int numeroHabitacion, String nombreProducto, 
                         int cantidad, double precioUnitario, String fechaConsumo) {
        this.cedulaCliente = cedulaCliente;
        this.numeroHabitacion = numeroHabitacion;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario;
        this.fechaConsumo = fechaConsumo;
        this.facturado = false;
    }
    
    public double calcularTotalConsumoCliente(long cedulaCliente) {
        double total = 0;
        for (ConsumoMinibar consumo : listaConsumos) {
            if (consumo.getCedulaCliente() == cedulaCliente && !consumo.isFacturado()) {
                total += consumo.getSubtotal();
            }
        }
        return total;
    }
    
    public void marcarConsumosComoFacturados(long cedulaCliente) {
        for (ConsumoMinibar consumo : listaConsumos) {
            if (consumo.getCedulaCliente() == cedulaCliente) {
                consumo.setFacturado(true);
            }
        }
    }
    
    public long getCedulaCliente() { return cedulaCliente; }
    public int getNumeroHabitacion() { return numeroHabitacion; }
    public String getNombreProducto() { return nombreProducto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getSubtotal() { return subtotal; }
    public String getFechaConsumo() { return fechaConsumo; }
    public boolean isFacturado() { return facturado; }
    
    public void setCedulaCliente(long cedulaCliente) { this.cedulaCliente = cedulaCliente; }
    public void setNumeroHabitacion(int numeroHabitacion) { this.numeroHabitacion = numeroHabitacion; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
        this.subtotal = cantidad * precioUnitario;
    }
    public void setPrecioUnitario(double precioUnitario) { 
        this.precioUnitario = precioUnitario; 
        this.subtotal = cantidad * precioUnitario;
    }
    public void setFechaConsumo(String fechaConsumo) { this.fechaConsumo = fechaConsumo; }
    public void setFacturado(boolean facturado) { this.facturado = facturado; }
    
    @Override
    public String toString() {
        return "Consumo{" +
                "producto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                ", subtotal=$" + subtotal +
                ", fecha=" + fechaConsumo +
                '}';
    }
}
