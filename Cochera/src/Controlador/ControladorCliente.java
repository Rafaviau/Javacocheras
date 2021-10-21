package Controlador;

import Modelo.IngresoCliente;
import Modelo.dao.DAOingresocliente;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        if(fecha.equals("----------")){
            JOptionPane.showMessageDialog(null,"Error\nNo hay una caja abierta");
        }else if( patente.equals("") || dni.equals("") || entrada.equals("")){
            JOptionPane.showMessageDialog(null,"Error\nNo pueden haber campos vacios.");
        }else if(Integer.parseInt(dni)< 0 ){
            JOptionPane.showMessageDialog(null,"Error\nEl DNI no puede ser menor a 0.");
        }else if(j.mostrar(tv).getCantPlazasQueOcupa()> f.mostrar(cocheraqueocupa).getOcdisponibles()){
            JOptionPane.showMessageDialog(null,"Error\nLa cochera no tiene suficiente capacidad para este vehiculo");
        }else{
            l.create(new IngresoCliente(j.mostrar(tv),patente,f.mostrar(cocheraqueocupa),Integer.parseInt(dni),LocalDateTime.parse(entrada),LocalDate.parse(fecha)));
            int id = f.mostrar(cocheraqueocupa).getId();
            String nombre = f.mostrar(cocheraqueocupa).getNombre();
            int capacidad = f.mostrar(cocheraqueocupa).getOcupaciones();
            int ocdisp = f.mostrar(cocheraqueocupa).getOcupaciones() + j.mostrar(tv).getCantPlazasQueOcupa();
            f.editar(Integer.toString(id),nombre,Integer.toString(capacidad),Integer.toString(ocdisp));
            }
        }
    }
