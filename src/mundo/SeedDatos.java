package mundo;

public class SeedDatos {

    public static void main(String[] args) {
        try {
            limpiarTablas();
            insertarHabitaciones();
            insertarClientes();
            System.out.println("✅ Datos de prueba insertados correctamente.");
        } catch (Exception e) {
            System.err.println("❌ Error durante la inserción de datos de prueba: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void limpiarTablas() throws Exception {
        ConexionBd conexion = null;
        try {
            conexion = new ConexionBd("localhost", "3306", "mundo_main", "root", "");
            conexion.ConexionBdMySQL();
            conexion.actualizar("DELETE FROM habitaciones");
            conexion.actualizar("DELETE FROM clientes");
        } finally {
            if (conexion != null) {
                conexion.cerrar();
            }
        }
    }

    private static void insertarHabitaciones() throws Exception {
        Habitaciones[] habitaciones = new Habitaciones[] {
            new Habitaciones(101, 85000.00, "Habitación doble con vista al jardín", true),
            new Habitaciones(102, 65000.00, "Habitación individual cerca del lobby", true),
            new Habitaciones(201, 120000.00, "Suite ejecutiva con sala y minibar completo", false),
            new Habitaciones(202, 95000.00, "Habitación doble con balcón privado", true),
            new Habitaciones(301, 150000.00, "Suite presidencial con jacuzzi", false)
        };

        for (Habitaciones habitacion : habitaciones) {
            OperacionesHabitaciones.guardar(habitacion);
        }
    }

    private static void insertarClientes() {
        Cliente[] clientes = new Cliente[] {
            new Cliente("1012345678", "Laura", "Gómez", "3214567890", "Calle 12 #45-67", "laura.gomez@example.com"),
            new Cliente("1098765432", "Carlos", "Ramírez", "3109876543", "Av. Central 123", "carlos.ramirez@example.com"),
            new Cliente("1022334455", "Ana", "Pérez", "3001234567", "Cra. 80 #10-20", "ana.perez@example.com"),
            new Cliente("1045678901", "Julián", "Martínez", "3207654321", "Barrio Jardines", "julian.martinez@example.com")
        };

        for (Cliente cliente : clientes) {
            OperacionesClientes.guardar(cliente);
        }
    }
}

