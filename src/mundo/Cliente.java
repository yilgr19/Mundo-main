package mundo;

public class Cliente {
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String correo;

    public Cliente(String cedula, String nombre, String apellido, String telefono, String direccion, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public String getCorreo() { return correo; }

    @Override
    public String toString() {
        return "Cédula: " + cedula + ", Nombre: " + nombre + ", Apellido: " + apellido +
               ", Teléfono: " + telefono + ", Dirección: " + direccion + ", Correo: " + correo;
    }
}
