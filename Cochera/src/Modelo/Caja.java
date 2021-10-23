package Modelo;

import java.time.LocalDate;

public class Caja {
    float cajainicial;
    float gastos;
    float ingresos;
    float cajafinal;
    LocalDate fecha;
    
    //Getters

    public float getCajainicial() {
        return cajainicial;
    }

    public float getGastos() {
        return gastos;
    }

    public float getIngresos() {
        return ingresos;
    }

    public float getCajafinal() {
        return cajafinal;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    //Setters

    public void setCajainicial(float cajainicial) {
        this.cajainicial = cajainicial;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    public void setIngresos(float ingresos) {
        this.ingresos = ingresos;
    }

    public void setCajafinal(float cajafinal) {
        this.cajafinal = cajafinal;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = LocalDate.now();
    }
    
    //Constructor
 
    public Caja(LocalDate fecha, float cajainicial) {
        this.cajainicial = cajainicial;
        this.fecha = fecha;
        this.cajafinal = cajainicial;
    }

    public Caja(LocalDate fecha, float cajainicial, float ingresos,float gastos, float cajafinal) {
        this.fecha = fecha;
        this.cajainicial = cajainicial;
        this.ingresos = ingresos;
        this.gastos = gastos;
        this.cajafinal = cajafinal;
    }
    
    
    //Metodos
    public void SumatoriaCajaFinal(){   
        this.cajafinal = this.cajafinal - this.gastos + this.ingresos;      
    }
    
    
    public void AgregarGasto(float gasto){
        this.gastos = this.gastos + gasto; 
        this.cajafinal = this.cajafinal - gasto;
    }
    
    
     public void Agregaringreso(float ingreso){
        this.ingresos = this.ingresos + ingreso;
        this.cajafinal = this.cajafinal + ingreso;  
    }
     
    //tostring

    @Override
    public String toString() {
        return "Caja:\n" + "cajainicial=" + cajainicial + ", gastos=" + gastos + ", ingresos=" + ingresos + ", cajafinal=" + cajafinal + ", fecha=" + fecha;
    }
     
}
