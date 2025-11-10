package mundo;

import java.util.ArrayList;
import java.util.List;

public class SistemaLogin {
    public static List<Usuario> listaUsuarios = new ArrayList<>();
    public static Usuario usuarioActual = null;
    
    static {
        inicializarUsuarios();
    }
    
    private static void inicializarUsuarios() {
        listaUsuarios.add(new Usuario("admin", "admin123", "Administrador", "Administrador del Sistema"));
        listaUsuarios.add(new Usuario("recepcion", "recepcion123", "Recepcion", "Recepcionista Principal"));
        listaUsuarios.add(new Usuario("camilo", "camilo123", "Administrador", "Camilo Ramirez"));
        listaUsuarios.add(new Usuario("melanny", "melanny123", "Administrador", "Melanny Guate"));
    }
    
    public static boolean autenticarUsuario(String username, String password) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equals(username) && usuario.verificarPassword(password)) {
                usuarioActual = usuario;
                return true;
            }
        }
        usuarioActual = null;
        return false;
    }
    
    public static void cerrarSesion() {
        usuarioActual = null;
    }
    
    public static boolean esAdministrador() {
        return usuarioActual != null && usuarioActual.getRol().equals("Administrador");
    }
    
    public static boolean esRecepcionista() {
        return usuarioActual != null && usuarioActual.getRol().equals("Recepcion");
    }
    
    public static boolean tienePermiso(String permiso) {
        if (usuarioActual == null) return false;
        
        switch (permiso) {
            case "GESTION_HABITACIONES":
                return esAdministrador();
            case "GESTION_USUARIOS":
                return esAdministrador();
            case "CHECKIN_CHECKOUT":
                return esRecepcionista() || esAdministrador();
            case "RESERVAS":
                return esRecepcionista() || esAdministrador();
            case "MINIBAR":
                return esRecepcionista() || esAdministrador();
            case "REPORTES":
                return esAdministrador();
            default:
                return false;
        }
    }
    
    public static String obtenerNombreUsuario() {
        return usuarioActual != null ? usuarioActual.getNombre() : "No autenticado";
    }
    
    public static String obtenerRolUsuario() {
        return usuarioActual != null ? usuarioActual.getRol() : "Sin rol";
    }
    
    public static void agregarUsuario(Usuario nuevoUsuario) {
        listaUsuarios.add(nuevoUsuario);
    }
    
    public static boolean existeUsuario(String username) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    
    public static List<Usuario> obtenerUsuariosPorRol(String rol) {
        List<Usuario> usuariosRol = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getRol().equals(rol)) {
                usuariosRol.add(usuario);
            }
        }
        return usuariosRol;
    }
}
