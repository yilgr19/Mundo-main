package mundo;

import java.sql.SQLException;

public class OperacionesClientes {

    public static void guardar(Cliente c) {
        ConexionBd conexion = null;
        try {
            // Cambia el usuario y contraseña si los tuyos son diferentes
            conexion = new ConexionBd("localhost", "3306", "mundo_main", "root", "");
            conexion.ConexionBdMySQL();

            String sql = "INSERT INTO clientes (cedula, nombre, apellido, telefono, direccion, correo) VALUES (" +
                    "'" + c.getCedula() + "', " +
                    "'" + c.getNombre() + "', " +
                    "'" + c.getApellido() + "', " +
                    "'" + c.getTelefono() + "', " +
                    "'" + c.getDireccion() + "', " +
                    "'" + c.getCorreo() + "')";

            conexion.actualizar(sql);
            System.out.println("✅ Cliente guardado correctamente en la base de datos.");

        } catch (Exception e) {
            System.err.println("❌ Error al guardar el cliente: " + e.getMessage());
        } finally {
            if (conexion != null) {
                try {
                    conexion.cerrar();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public static Cliente buscar(String cedulaBuscada) {
        ConexionBd conexion = null;
        try {
            conexion = new ConexionBd("localhost", "3306", "mundo_main", "root", "");
            conexion.ConexionBdMySQL();

            var rs = conexion.consultar("SELECT * FROM clientes WHERE cedula = '" + cedulaBuscada + "'");

            if (rs.next()) {
                return new Cliente(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("correo")
                );
            } else {
                System.out.println("Cliente no encontrado con cédula: " + cedulaBuscada);
                return null;
            }

        } catch (Exception e) {
            System.err.println("Error al buscar el cliente: " + e.getMessage());
            return null;
        } finally {
            if (conexion != null) {
                try {
                    conexion.cerrar();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public static boolean eliminar(String cedula) {
        ConexionBd conexion = null;
        try {
            conexion = new ConexionBd("localhost", "3306", "mundo_main", "root", "");
            conexion.ConexionBdMySQL();

            String sql = "DELETE FROM clientes WHERE cedula = '" + cedula + "'";
            conexion.actualizar(sql);
            System.out.println("Cliente con cédula " + cedula + " eliminado de la base de datos.");
            return true;

        } catch (Exception e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        } finally {
            if (conexion != null) {
                try {
                    conexion.cerrar();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }
}