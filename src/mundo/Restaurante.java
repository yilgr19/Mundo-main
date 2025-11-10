
package mundo;


public class Restaurante {
    private int numero;
    private long precio;
    private String descripcion;
    private boolean estado;
public Restaurante (int  numero,long  precio,String  descripcion,boolean  estado) { 
this.numero=numero;
this.precio=precio;
this.descripcion=descripcion; 
this.estado=estado; 
}
public int  getNumero () {
return numero;
}
public void setNumero() {
this.numero=numero;
}
public long getPrecio() {
return precio;
}
public void setPrecio() {
this.precio=precio;
}
public String getDescripcion() {
return descripcion;
}
public void setDescripcion() {
this.descripcion=descripcion;
}
public boolean getEstado() {
return estado;
}
public void setEstado() {
this.estado=estado;
}
public String ToString() {
return ("Numero: " + numero + "\nPrecio: " + precio + "\nDescripcion: " + descripcion + "\nEstado: " + estado);
}
}
