package mundo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OperacionesHabitaciones {

    private static final String IP = "localhost";
    private static final String PUERTO = "3306";
    private static final String NOMBRE_BD = "mundo_main";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private OperacionesHabitaciones() {
    }

    private static ConexionBd abrirConexion() throws Exception {
        ConexionBd conexion = new ConexionBd(IP, PUERTO, NOMBRE_BD, USUARIO, PASSWORD);
        conexion.ConexionBdMySQL();
        return conexion;
    }

    public static void guardar(Habitaciones habitacion) throws Exception {
        ConexionBd conexion = null;
        try {
            conexion = abrirConexion();
            String descripcion = habitacion.getDescripcion() != null
                    ? habitacion.getDescripcion().replace("'", "''")
                    : null;
            String precio = String.format(Locale.US, "%.2f", habitacion.getPrecio());
            String sql = "INSERT INTO habitaciones (numero, precio, descripcion, disponible) VALUES ("
                    + habitacion.getNumero() + ", "
                    + precio + ", "
                    + (descripcion == null ? "NULL" : "'" + descripcion + "'") + ", "
                    + (habitacion.getEstado() ? 1 : 0) + ")";
            conexion.actualizar(sql);
        } finally {
            cerrar(conexion);
        }
    }

    public static Habitaciones buscar(int numero) throws Exception {
        ConexionBd conexion = null;
        ResultSet rs = null;
        try {
            conexion = abrirConexion();
            String sql = "SELECT numero, precio, descripcion, disponible FROM habitaciones WHERE numero = " + numero;
            rs = conexion.consultar(sql);
            if (rs.next()) {
                double precio = rs.getDouble("precio");
                String descripcion = rs.getString("descripcion");
                boolean disponible = rs.getInt("disponible") == 1;
                Habitaciones habitacion = new Habitaciones(numero, precio, descripcion, disponible);
                return habitacion;
            }
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
            cerrar(conexion);
        }
    }

    public static boolean eliminar(int numero) throws Exception {
        ConexionBd conexion = null;
        try {
            conexion = abrirConexion();
            String sql = "DELETE FROM habitaciones WHERE numero = " + numero;
            int filas = conexion.sentencia.executeUpdate(sql);
            return filas > 0;
        } finally {
            cerrar(conexion);
        }
    }

    public static List<Habitaciones> listarTodas() throws Exception {
        ConexionBd conexion = null;
        ResultSet rs = null;
        List<Habitaciones> habitaciones = new ArrayList<>();
        try {
            conexion = abrirConexion();
            String sql = "SELECT numero, precio, descripcion, disponible FROM habitaciones ORDER BY numero";
            rs = conexion.consultar(sql);
            while (rs.next()) {
                int numero = rs.getInt("numero");
                double precio = rs.getDouble("precio");
                String descripcion = rs.getString("descripcion");
                boolean disponible = rs.getInt("disponible") == 1;
                habitaciones.add(new Habitaciones(numero, precio, descripcion, disponible));
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
            cerrar(conexion);
        }
        return habitaciones;
    }

    private static void cerrar(ConexionBd conexion) {
        if (conexion != null) {
            try {
                conexion.cerrar();
            } catch (SQLException ignored) {
            }
        }
    }
}

