package mundo;

import java.util.List;

public class ListarHabitaciones {

    public static void main(String[] args) {
        try {
            List<Habitaciones> habitaciones = OperacionesHabitaciones.listarTodas();
            System.out.println("Listado de habitaciones (" + habitaciones.size() + "):");
            for (Habitaciones hab : habitaciones) {
                System.out.println(" - #" + hab.getNumero()
                        + " | precio=" + hab.getPrecio()
                        + " | estado=" + (hab.getEstado() ? "Disponible" : "Ocupado")
                        + " | descripcion=" + hab.getDescripcion());
            }
        } catch (Exception e) {
            System.err.println("Error al listar habitaciones: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

