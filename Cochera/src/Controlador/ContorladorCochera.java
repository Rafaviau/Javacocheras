package Controlador;

import Modelo.Cochera;
import Modelo.dao.DAOCochera;
import java.util.List;
import javax.swing.JOptionPane;


public class ContorladorCochera{
    DAOCochera l = new DAOCochera();
    /**
     * primero verifica que los datos sean correctos
     * luego llama al daococheras para crear una nueva cochera con los datos de los aprametros
     * @param nombre
     * @param capacidad 
     */
    public void crear(String nombre, String capacidad){
        if(nombre.equals("") || capacidad.equals("")){
            JOptionPane.showMessageDialog(null,"Error\nNo pueden haber campos vacios.");
        }else if(Integer.parseInt(capacidad)<1){
            JOptionPane.showMessageDialog(null,"Error\nLa capacidad no puede ser menor a 1.");
        }else{
            l.create(new Cochera(nombre,Integer.parseInt(capacidad)));
        }
    }
    /**
     * recibe un objeto kay
     * verifica que el dato sea correcto
     * llama al metodo delete del daococheras
     * @param key 
     */
    public void eliminar(Object key){
        if (!key.equals("")){
            l.delete(key);
        }else{
            JOptionPane.showMessageDialog(null,"Error\nSeleccione un campo.");
        }
    }
    /**
     * verifica si el id esta vacio
     * despues verifica si el nombre o la capacidad estan vacios
     * si los datos son correctos llama al metodo update de dao cocheras enviandole una cochera ya creada con los datos recibidos
     * @param id
     * @param nombre
     * @param capacidad 
     * @param ocactual ocupacion actual de la cochera, se utiliza
     */
    public void editar(String id, String nombre, String capacidad, String ocactual){
        int f;
        if(id.equals("")){
            JOptionPane.showMessageDialog(null,"Error\nSeleccione el objeto que desea editar.");
        }else if(nombre.equals("") || capacidad.equals("")){
            JOptionPane.showMessageDialog(null,"Error\nNo pueden haber campos vacios.");
        }else if(Integer.parseInt(capacidad)<1){
            JOptionPane.showMessageDialog(null,"Error\nLa capacidad no puede ser menor a 1.");
        }else if(Integer.parseInt(capacidad) < l.read(id).getOcdisponibles()){
            JOptionPane.showMessageDialog(null,"Error\nLa capacidad no puede ser menor a las ocupaciones actuales.");
        }else if(ocactual == null){
            f = l.read(id).getOcdisponibles();
            l.update(new Cochera(Integer.parseInt(id),nombre,Integer.parseInt(capacidad),f));
        }
        else{
            l.update(new Cochera(Integer.parseInt(id),nombre,Integer.parseInt(capacidad),Integer.parseInt(ocactual)));
        }
    }
    /**
     * devuelve el listado completo de las cocheras
     * se utiliza para la jtable
     * @return 
     */
    public List<Cochera> mostrartodo(){
        return l.readALL();
    }
    
    public Cochera mostrar(int key){
    return l.read(key);
    }
}
