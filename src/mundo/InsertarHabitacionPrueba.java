package mundo;

public class InsertarHabitacionPrueba {

    public static void main(String[] args) {
        try {
            Habitaciones habitacion = new Habitaciones(302, 500000.00, "doble", true);
            OperacionesHabitaciones.guardar(habitacion);
            System.out.println("Habitación 302 insertada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar la habitación de prueba: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

