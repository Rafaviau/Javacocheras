package Modelo;
public class TipoVehiculo {
    String nombre;
    float PrecioPorHora;
    int CantPlazasQueOcupa;
    int id;
    //Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecioPorHora() {
        return PrecioPorHora;
    }

    public void setPrecioPorHora(int PrecioPorHora) {
        this.PrecioPorHora = PrecioPorHora;
    }

    public int getCantPlazasQueOcupa() {
        return CantPlazasQueOcupa;
    }

    public void setCantPlazasQueOcupa(int CantPlazasQueOcupa) {
        this.CantPlazasQueOcupa = CantPlazasQueOcupa;
    }
    
    //Constructor

    public TipoVehiculo(String nombre, float PrecioPorHora, int CantPlazasQueOcupa) {
        this.nombre = nombre;
        this.PrecioPorHora = PrecioPorHora;
        this.CantPlazasQueOcupa = CantPlazasQueOcupa;
    }

    public TipoVehiculo(int id, String nombre, float PrecioPorHora, int CantPlazasQueOcupa) {
        this.id = id;
        this.nombre = nombre;
        this.PrecioPorHora = PrecioPorHora;
        this.CantPlazasQueOcupa = CantPlazasQueOcupa;
    }
    
    //To string

    @Override
    public String toString() {
        return  nombre;
    }
    
}
