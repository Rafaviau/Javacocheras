/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ContorladorCochera;
import Controlador.Controladornuevovehiculo;
import Modelo.Cochera;
import Modelo.IngresoCliente;
import Modelo.dao.DAOingresocliente;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafa
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controladornuevovehiculo j = new Controladornuevovehiculo();
        float a = j.mostrar(12).getPrecioPorHora();
        System.out.print(a);
    }
    
}
