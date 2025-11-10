package mundo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OperacionesReservas {

    private static final String IP = "localhost";
    private static final String PUERTO = "3306";
    private static final String NOMBRE_BD = "mundo_main";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private OperacionesReservas() {
    }

    private static ConexionBd abrirConexion() throws Exception {
        ConexionBd conexion = new ConexionBd(IP, PUERTO, NOMBRE_BD, USUARIO, PASSWORD);
        conexion.ConexionBdMySQL();
        return conexion;
    }

    private static void cerrar(ConexionBd conexion) {
        if (conexion != null) {
            try {
                conexion.cerrar();
            } catch (SQLException ignored) {
            }
        }
    }

    private static String limpiarTexto(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.replace("'", "''");
    }

    private static String normalizarFechaParaDb(String fecha) {
        if (fecha == null) {
            return null;
        }
        String valor = fecha.trim();
        if (valor.isEmpty() || valor.equals("0-0-0") || valor.equals("0  -  0  -  0")) {
            return null;
        }
        valor = valor.replace("  -  ", "-").replace("/", "-");
        try {
            LocalDate date = LocalDate.parse(valor, DateTimeFormatter.ofPattern("d-M-uuuu"));
            return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static String formatearFechaParaUi(String fecha) {
        if (fecha == null) {
            return "";
        }
        try {
            LocalDate date = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
            return date.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        } catch (DateTimeParseException e) {
            return fecha;
        }
    }

    private static String normalizarHoraParaDb(String hora) {
        if (hora == null) {
            return null;
        }
        String valor = hora.trim();
        if (valor.isEmpty()) {
            return null;
        }
        valor = valor.replace("  ", " ");
        String periodo = "";
        String valorMayus = valor.toUpperCase(Locale.US);
        if (valorMayus.endsWith("AM") || valorMayus.endsWith("PM")) {
            int idx = valor.lastIndexOf(' ');
            if (idx != -1) {
                periodo = valor.substring(idx + 1).toUpperCase(Locale.US);
                valor = valor.substring(0, idx);
            }
        }
        valor = valor.trim();
        if (!valor.contains(":")) {
            valor = valor + ":00";
        } else {
            String[] partes = valor.split(":");
            if (partes.length == 2 && partes[1].length() == 1) {
                valor = partes[0] + ":" + partes[1] + "0";
            }
        }
        try {
            LocalTime time;
            if (!periodo.isEmpty()) {
                DateTimeFormatter parser = DateTimeFormatter.ofPattern("h:mm a", Locale.US);
                time = LocalTime.parse(valor + " " + periodo, parser);
            } else {
                DateTimeFormatter parser = DateTimeFormatter.ofPattern("H:mm");
                time = LocalTime.parse(valor, parser);
            }
            return time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static String formatearHoraParaUi(String hora) {
        if (hora == null) {
            return "";
        }
        try {
            LocalTime time = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm:ss"));
            return time.format(DateTimeFormatter.ofPattern("h:mm a", Locale.US));
        } catch (DateTimeParseException e) {
            try {
                LocalTime time = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
                return time.format(DateTimeFormatter.ofPattern("h:mm a", Locale.US));
            } catch (DateTimeParseException ex) {
                return hora;
            }
        }
    }

    private static String construirDescripcionHabitacion(int numero) {
        for (Habitaciones hab : Habitaciones.listaHabitaciones) {
            if (hab.getNumero() == numero) {
                return "Hab. " + numero + " - $" + hab.getPrecio();
            }
        }
        return "Hab. " + numero;
    }

    public static void guardar(Reserva reserva) throws Exception {
        ConexionBd conexion = null;
        ResultSet rs = null;
        try {
            conexion = abrirConexion();
            String fechaEntrada = normalizarFechaParaDb(reserva.getFechaEntrada());
            String fechaSalida = normalizarFechaParaDb(reserva.getFechaSalida());
            String horaEntrada = normalizarHoraParaDb(reserva.getHoraEntrada());
            String horaSalida = normalizarHoraParaDb(reserva.getHoraSalida());
            String metodoPago = limpiarTexto(reserva.getMetodoPago());

            String sql = "INSERT INTO reservas (cedula_cliente, numero_habitacion, fecha_entrada, hora_entrada, fecha_salida, hora_salida, num_huespedes, metodo_pago) VALUES (" +
                    "'" + reserva.getCedula() + "', " +
                    reserva.getNumeroHabitacion() + ", " +
                    (fechaEntrada == null ? "NULL" : "'" + fechaEntrada + "'") + ", " +
                    (horaEntrada == null ? "NULL" : "'" + horaEntrada + "'") + ", " +
                    (fechaSalida == null ? "NULL" : "'" + fechaSalida + "'") + ", " +
                    (horaSalida == null ? "NULL" : "'" + horaSalida + "'") + ", " +
                    reserva.getNumHuespedes() + ", " +
                    "'" + metodoPago + "')";

            conexion.actualizar(sql);
            rs = conexion.consultar("SELECT LAST_INSERT_ID() AS id");
            if (rs.next()) {
                reserva.setIdReserva(rs.getInt("id"));
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
    }

    public static List<Reserva> listarTodas() throws Exception {
        ConexionBd conexion = null;
        ResultSet rs = null;
        List<Reserva> reservas = new ArrayList<>();
        try {
            conexion = abrirConexion();
            String sql = "SELECT r.id_reserva, r.cedula_cliente, r.numero_habitacion, r.fecha_entrada, r.hora_entrada, " +
                    "r.fecha_salida, r.hora_salida, r.num_huespedes, r.metodo_pago, " +
                    "c.nombre AS nombre_cliente, c.apellido AS apellido_cliente, c.telefono AS telefono_cliente " +
                    "FROM reservas r LEFT JOIN clientes c ON c.cedula = r.cedula_cliente " +
                    "ORDER BY r.id_reserva";
            rs = conexion.consultar(sql);
            while (rs.next()) {
                String nombre = rs.getString("nombre_cliente");
                String apellido = rs.getString("apellido_cliente");
                String telefonoStr = rs.getString("telefono_cliente");
                long telefono = 0;
                if (telefonoStr != null && !telefonoStr.isBlank()) {
                    try {
                        telefono = Long.parseLong(telefonoStr.replaceAll("\\D", ""));
                    } catch (NumberFormatException ignored) {
                    }
                }
                String cedulaStr = rs.getString("cedula_cliente");
                long cedula = 0;
                if (cedulaStr != null && !cedulaStr.isBlank()) {
                    try {
                        cedula = Long.parseLong(cedulaStr.replaceAll("\\D", ""));
                    } catch (NumberFormatException ignored) {
                    }
                }
                int numeroHabitacion = rs.getInt("numero_habitacion");
                Reserva reserva = new Reserva(
                        nombre != null ? nombre : "",
                        apellido != null ? apellido : "",
                        cedula,
                        telefono,
                        formatearFechaParaUi(rs.getString("fecha_entrada")),
                        formatearHoraParaUi(rs.getString("hora_entrada")),
                        formatearFechaParaUi(rs.getString("fecha_salida")),
                        formatearHoraParaUi(rs.getString("hora_salida")),
                        construirDescripcionHabitacion(numeroHabitacion),
                        rs.getInt("num_huespedes"),
                        rs.getString("metodo_pago") != null ? rs.getString("metodo_pago") : "",
                        numeroHabitacion
                );
                reserva.setIdReserva(rs.getInt("id_reserva"));
                reservas.add(reserva);
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
        return reservas;
    }

    public static Reserva buscarPorCedula(long cedula) throws Exception {
        ConexionBd conexion = null;
        ResultSet rs = null;
        try {
            conexion = abrirConexion();
            String sql = "SELECT r.id_reserva, r.cedula_cliente, r.numero_habitacion, r.fecha_entrada, r.hora_entrada, " +
                    "r.fecha_salida, r.hora_salida, r.num_huespedes, r.metodo_pago, " +
                    "c.nombre AS nombre_cliente, c.apellido AS apellido_cliente, c.telefono AS telefono_cliente " +
                    "FROM reservas r LEFT JOIN clientes c ON c.cedula = r.cedula_cliente " +
                    "WHERE r.cedula_cliente = '" + cedula + "' ORDER BY r.id_reserva DESC LIMIT 1";
            rs = conexion.consultar(sql);
            if (rs.next()) {
                String nombre = rs.getString("nombre_cliente");
                String apellido = rs.getString("apellido_cliente");
                String telefonoStr = rs.getString("telefono_cliente");
                long telefono = 0;
                if (telefonoStr != null && !telefonoStr.isBlank()) {
                    try {
                        telefono = Long.parseLong(telefonoStr.replaceAll("\\D", ""));
                    } catch (NumberFormatException ignored) {
                    }
                }
                int numeroHabitacion = rs.getInt("numero_habitacion");
                Reserva reserva = new Reserva(
                        nombre != null ? nombre : "",
                        apellido != null ? apellido : "",
                        cedula,
                        telefono,
                        formatearFechaParaUi(rs.getString("fecha_entrada")),
                        formatearHoraParaUi(rs.getString("hora_entrada")),
                        formatearFechaParaUi(rs.getString("fecha_salida")),
                        formatearHoraParaUi(rs.getString("hora_salida")),
                        construirDescripcionHabitacion(numeroHabitacion),
                        rs.getInt("num_huespedes"),
                        rs.getString("metodo_pago") != null ? rs.getString("metodo_pago") : "",
                        numeroHabitacion
                );
                reserva.setIdReserva(rs.getInt("id_reserva"));
                return reserva;
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

    public static void actualizar(Reserva reserva) throws Exception {
        if (reserva.getIdReserva() == null) {
            Reserva existente = buscarPorCedula(reserva.getCedula());
            if (existente != null) {
                reserva.setIdReserva(existente.getIdReserva());
            } else {
                throw new Exception("La reserva no existe en la base de datos.");
            }
        }

        ConexionBd conexion = null;
        try {
            conexion = abrirConexion();
            String fechaEntrada = normalizarFechaParaDb(reserva.getFechaEntrada());
            String fechaSalida = normalizarFechaParaDb(reserva.getFechaSalida());
            String horaEntrada = normalizarHoraParaDb(reserva.getHoraEntrada());
            String horaSalida = normalizarHoraParaDb(reserva.getHoraSalida());
            String metodoPago = limpiarTexto(reserva.getMetodoPago());

            String sql = "UPDATE reservas SET " +
                    "numero_habitacion = " + reserva.getNumeroHabitacion() + ", " +
                    "fecha_entrada = " + (fechaEntrada == null ? "NULL" : "'" + fechaEntrada + "'") + ", " +
                    "hora_entrada = " + (horaEntrada == null ? "NULL" : "'" + horaEntrada + "'") + ", " +
                    "fecha_salida = " + (fechaSalida == null ? "NULL" : "'" + fechaSalida + "'") + ", " +
                    "hora_salida = " + (horaSalida == null ? "NULL" : "'" + horaSalida + "'") + ", " +
                    "num_huespedes = " + reserva.getNumHuespedes() + ", " +
                    "metodo_pago = '" + metodoPago + "' " +
                    "WHERE id_reserva = " + reserva.getIdReserva();

            conexion.actualizar(sql);
        } finally {
            cerrar(conexion);
        }
    }

    public static boolean eliminarPorId(int idReserva) throws Exception {
        ConexionBd conexion = null;
        try {
            conexion = abrirConexion();
            String sql = "DELETE FROM reservas WHERE id_reserva = " + idReserva;
            int filas = conexion.sentencia.executeUpdate(sql);
            return filas > 0;
        } finally {
            cerrar(conexion);
        }
    }
}

