package Controlador;

import Modelo.IngresoCliente;
import Modelo.dao.DAOingresocliente;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;

public class ControladorCliente {
    DAOingresocliente l = new DAOingresocliente();
    Controladornuevovehiculo j = new Controladornuevovehiculo();
    ContorladorCochera f = new ContorladorCochera();
    
    /***
     * 
     * @param tv recibe el indice seleccionado en el combobox, luego utiliza este indice para llamar al metodo crear de la controladora tipovehiculo que devuelve un tipo de vehiculo, este resutado es el que se guarda
     * @param patente
     * @param cocheraqueocupa similar al tipo de vehiculo recibe el indice seleccionado en el combo box pero esta vez utiliza la controladora de cochera
     * @param dni
     * @param entrada
     * @param fecha 
     */
    public void crear(int tv, String patente, int cocheraqueocupa, String dni, String entrada, String fecha){
        /***
         * j.mostrartodo().get(tv).getCantPlazasQueOcupa(); COMO FUNCIONA busca entre toda la lista que devuelve el metodo "buscartodo", el objeto que esta en la posicion que se le pasa
        como el combobox muestra la lista en orden puedo utilizar el indice seleccionado para volver a buscar el mismo objeto
         */
        int z =j.mostrartodo().get(tv).getCantPlazasQueOcupa() + f.mostrartodo().get(cocheraqueocupa).getOcdisponibles();
        
        if(fecha.equals("----------")){
            JOptionPane.showMessageDialog(null,"Error\nNo hay una caja abierta");
        }
        else if( patente.equals("") || dni.equals("") || entrada.equals("")){
            JOptionPane.showMessageDialog(null,"Error\nNo pueden haber campos vacios.");
        }
        else if(Integer.parseInt(dni)< 0 ){
            JOptionPane.showMessageDialog(null,"Error\nEl DNI no puede ser menor a 0.");
        }
        else if(z > f.mostrartodo().get(cocheraqueocupa).getOcupaciones()){
            //si la suma de la cantidad de plazas que ocupa el tipo de vehiculo con la cantidad de plazas ocupadas es mayor que la cantidad de plazas totales de la cochera lanza un error
            
            JOptionPane.showMessageDialog(null,"Error\nLa cochera no tiene suficiente capacidad para este vehiculo");
        }else{
            //Crea el nuevo ingreso de cliente
            IngresoCliente nc = new IngresoCliente(j.mostrartodo().get(tv), patente, f.mostrartodo().get(cocheraqueocupa) ,Integer.parseInt(dni), LocalTime.parse(entrada), LocalDate.parse(fecha));
            l.create(nc);
            
            int id = f.mostrartodo().get(cocheraqueocupa).getId();
            int ocdisp = f.mostrartodo().get(cocheraqueocupa).getOcdisponibles()+ j.mostrartodo().get(tv).getCantPlazasQueOcupa();
            //actualiza la cochera para guardar cuantas plazas ocupadas tiene
            f.editarocupacion(ocdisp, id);

            }
        }
    
    
   public String[][] mostrartodo(){
        return l.readALLstring();
    }
   
   public void retirarvehiculo(String entr, String pat){
       LocalTime salida = LocalTime.now();
       LocalTime entrada = LocalTime.parse(entr);
       l.updateSALIDA(salida,entrada,pat);
   }
   
   public float APagar(LocalTime hentrada, int key){
       float ht = LocalTime.now().getHour()- hentrada.getHour(); //hora de salida - hora de entrada
       if (ht == 0){
            ht +=1;
        }
        float pagar = j.mostrar(key).getPrecioPorHora() * ht;
        
        return pagar;
    }
}
