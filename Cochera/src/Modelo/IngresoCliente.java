package Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class IngresoCliente {
    TipoVehiculo tipodevehiculo;
    String patente;
    Cochera cocheraqueocupa;
    int dni;
    LocalDateTime entrada;
    LocalDateTime salida;
    
    //Getters

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

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSalida() {
        return salida;
    }
    
    //Setters

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

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }
    
    //Constructor

    public IngresoCliente(TipoVehiculo tipodevehiculo, String patente, Cochera cocheraqueocupa, int dni, LocalDateTime entrada) {
        this.tipodevehiculo = tipodevehiculo;
        this.patente = patente;
        this.cocheraqueocupa = cocheraqueocupa;
        this.dni = dni;
        this.entrada = entrada;
    }
    
    //Metodos
    int HorasOcupadas(IngresoCliente a){
        int horasocupadas;
        horasocupadas = a.getSalida().getHour()- a.getEntrada().getHour();
        if (horasocupadas == 0){
            horasocupadas +=1;
        }
        return horasocupadas;
    }
    
}
