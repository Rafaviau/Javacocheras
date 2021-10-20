package Modelo;
public class Cochera {
    int id;
    String nombre;
    int ocupaciones;

    
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOcupaciones() {
        return ocupaciones;
    }

    public void setOcupaciones(int ocupacion) {
        this.ocupaciones = ocupacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
 //Constructor

    public Cochera(String nombre, int ocupacion) {
        this.nombre = nombre;
        this.ocupaciones = ocupacion;
    }

    public Cochera(int id, String nombre, int ocupaciones) {
        this.id = id;
        this.nombre = nombre;
        this.ocupaciones = ocupaciones;
    } 

    @Override
    public String toString() {
        return "Cochera{" + "id=" + id + ", nombre=" + nombre + ", ocupaciones=" + ocupaciones + '}';
    }
    
}
