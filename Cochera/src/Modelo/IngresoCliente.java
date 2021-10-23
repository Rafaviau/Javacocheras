package Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class IngresoCliente {
    TipoVehiculo tipodevehiculo;
    String patente;
    Cochera cocheraqueocupa;
    int dni;
    LocalTime entrada;
    LocalTime salida;
    LocalDate fecha;
    
    //Getters
    
    public LocalDate getFecha() {
        return fecha;
    }

    public TipoVehiculo getTipodevehiculo() {
        return tipodevehiculo;
    }

    public String getPatente() {
        return patente;
    }

    public Cochera getCocheraqueocupa() {
        return cocheraqueocupa;
    }

    public int getDni() {
        return dni;
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public LocalTime getSalida() {
        return salida;
    }
    
    //Setters

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public void setTipodevehiculo(TipoVehiculo tipodevehiculo) {
        this.tipodevehiculo = tipodevehiculo;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setCocheraqueocupa(Cochera cocheraqueocupa) {
        this.cocheraqueocupa = cocheraqueocupa;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }

    public void setSalida(LocalTime salida) {
        this.salida = salida;
    }
    
    //Constructor

    public IngresoCliente(TipoVehiculo tipodevehiculo, String patente, Cochera cocheraqueocupa, int dni, LocalTime entrada, LocalDate fecha) {
        this.tipodevehiculo = tipodevehiculo;
        this.patente = patente;
        this.cocheraqueocupa = cocheraqueocupa;
        this.dni = dni;
        this.entrada = entrada;
        this.fecha = fecha;
    }

    
}
