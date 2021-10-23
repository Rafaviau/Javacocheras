package Controlador;
import Modelo.TipoVehiculo;
import Modelo.dao.DAOtipovehiculo;
import java.util.List;
import javax.swing.JOptionPane;
public class Controladornuevovehiculo {
    DAOtipovehiculo l = new DAOtipovehiculo();
    
    public void crear(String nombre, String precio, String ocup){
        if(nombre.equals("") || precio.equals("")|| ocup.equals("")){
            JOptionPane.showMessageDialog(null,"Error\nNo pueden haber campos vacios.");
        }else if(Integer.parseInt(ocup)<1){
            JOptionPane.showMessageDialog(null,"Error\nLa ocupacion no puede ser menor a 1.");
        }else if(Float.parseFloat(precio)< 0){
            JOptionPane.showMessageDialog(null,"Error\nEl precio no puede ser menor a 0.");
        }else{
            l.create(new TipoVehiculo(nombre,Float.parseFloat(precio),Integer.parseInt(ocup)));
        }
    }
    
    
    public void eliminar(Object key){
        if (!key.equals("")){
            l.delete(key);
        }else{
            JOptionPane.showMessageDialog(null,"Error\nSeleccione un campo.");
        }
    }
    
    public void editar(String id, String nombre, String precio, String ocup){
        if(id.equals("")){
            JOptionPane.showMessageDialog(null,"Error\nSeleccione el objeto que desea editar.");
        }else if(nombre.equals("") || precio.equals("")|| ocup.equals("")){
            JOptionPane.showMessageDialog(null,"Error\nNo pueden haber campos vacios.");
        }else if(Integer.parseInt(ocup)<1){
            JOptionPane.showMessageDialog(null,"Error\nLa ocupacion no puede ser menor a 1.");
        }else if(Float.parseFloat(precio)< 0){
            JOptionPane.showMessageDialog(null,"Error\nEl precio no puede ser menor a 0.");
        }else{
            l.update(new TipoVehiculo(nombre,Float.parseFloat(precio),Integer.parseInt(ocup)));
        }
    }
    
    
    public List<TipoVehiculo>mostrartodo(){
        return l.readALL();
    }
    
    public TipoVehiculo mostrar(int key){
        return l.read(key);
    }

    public int indice(String nomb){
        if(nomb.equals("")){
            JOptionPane.showMessageDialog(null,"Error\nNo pueden haber campos vacios.");
          return -1;
        }
        return l.indice(nomb);
    }
}
