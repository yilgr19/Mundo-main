package mundo;

public class Usuario {
    private String username;
    private String password;
    private String rol;
    private String nombre;
    private boolean activo;
    
    public Usuario(String username, String password, String rol, String nombre) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.nombre = nombre;
        this.activo = true;
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }
    public String getNombre() { return nombre; }
    public boolean isActivo() { return activo; }
    
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRol(String rol) { this.rol = rol; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
    public boolean verificarPassword(String password) {
        return this.password.equals(password) && this.activo;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", rol='" + rol + '\'' +
                ", nombre='" + nombre + '\'' +
                ", activo=" + activo +
                '}';
    }
}
