package Controlador;

import Modelo.Caja;
import Modelo.dao.DAOcaja;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class ContorladorCaja {
    DAOcaja l = new DAOcaja();
    
    
    public boolean crear(String fecha, String cajainicial){
        if(fecha.equals("")||cajainicial.equals("")){
            JOptionPane.showMessageDialog(null,"Error\nNo pueden haber campos vacios.");
        }else if(Float.parseFloat(cajainicial)<0){
            JOptionPane.showMessageDialog(null,"Error\nLa caja inicial no puede ser menor a 0.");
        }else{
        l.create(new Caja(LocalDate.parse(fecha),Float.parseFloat(cajainicial)));
        return true;
        }
        return false;
    }
    
    
    public void ingresogasto(String fecha, String ing, String gast) {
        Object c = fecha;
        Caja f = l.read(c);
        if(ing.equals("")){
            ing = "0";
        }
        if(gast.equals("")){
            gast = "0";
        }
        f.AgregarGasto(Float.parseFloat(gast));
        f.Agregaringreso(Float.parseFloat(ing));
        l.update(f);
    }
    
   public float obtenercajafinal(String fecha){
       float value = 0;
       if(fecha.equals("")){
           JOptionPane.showMessageDialog(null,"Error\nLa fecha no puede estar vacia.");
       }else{
           Object c = fecha;
           Caja f = l.read(c);
           value = f.getCajafinal();
       }
       return value;
       }
   
   public boolean editar(String fecha,String cajainicial){
       if(fecha.equals("")){
           JOptionPane.showMessageDialog(null,"Error\nLa fecha no puede estar vacia.");
       }else if(cajainicial.equals("")){
           cajainicial = "0";
       }else{
           l.update(new Caja(LocalDate.parse(fecha),Float.parseFloat(cajainicial)));
           return true;
       }
       return false;
   }
   
}
