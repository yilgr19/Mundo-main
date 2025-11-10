package mundo;

import java.sql.ResultSet;

public class TestConexion {
    public static void main(String[] args) {
        String ip = "localhost";       // o 127.0.0.1
        String puerto = "3306";
        String nombreBD = "mundo_main"; // tu base de datos en phpMyAdmin
        String usuario = "root";        // tu usuario MySQL
        String password = "";           // tu contraseña si tienes, o vacío si no

        ConexionBd conexion = new ConexionBd(ip, puerto, nombreBD, usuario, password);

        try {
            conexion.ConexionBdMySQL(); // <-- usa el método de conexión a MySQL
            System.out.println("✅ Conexión exitosa a la base de datos " + nombreBD);

            // Prueba: listar tablas
            ResultSet rs = conexion.consultar("SHOW TABLES;");
            System.out.println("📋 Tablas encontradas:");
            while (rs.next()) {
                System.out.println(" - " + rs.getString(1));
            }

            conexion.cerrar();
            System.out.println("Conexion cerrada correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Error al conectar:");
            e.printStackTrace();
        }
    }
}
