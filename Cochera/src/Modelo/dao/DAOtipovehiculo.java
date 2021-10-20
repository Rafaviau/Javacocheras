package Modelo.dao;

import Modelo.Cochera;
import Modelo.TipoVehiculo;
import Modelo.TipoVehiculo;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DAOtipovehiculo implements crud <TipoVehiculo>{
    private static final String SQL_CREATE = "INSERT INTO tipovehiculo (nombre,plazasocupadas,importe) VALUES(?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM tipovehiculo WHERE idtipovehiculo = ?";
    private static final String SQL_UPDATE = "UPDATE tipovehiculo SET nombre = ?, plazasocupadas = ?,importe = ? WHERE 	idtipovehiculo = ?";
    private static final String SQL_READ = "SELECT * FROM tipovehiculo WHERE 	idtipovehiculo = ?";
    private static final String SQL_READALL = "SELECT * FROM tipovehiculo";
    private static final Conexion cn = Conexion.verificador();
    @Override
    public boolean create(TipoVehiculo c) {
        PreparedStatement ps;
        try {
            ps = cn.getCnn().prepareStatement(SQL_CREATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, Integer.toString(c.getCantPlazasQueOcupa()));
            ps.setString(3, Float.toString(c.getPrecioPorHora()));
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Agregado correctamente.");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOtipovehiculo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error, la base de datos no guardo los cambios");
        }finally{
            cn.cerraconexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = cn.getCnn().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Eliminado CORRECTAMENTE");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error, la base de datos no guardo los cambios");
        }finally{
            cn.cerraconexion();
        }
        return false;
    }

    @Override
    public boolean update(TipoVehiculo c) {
        PreparedStatement ps;
        try {
            ps = cn.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, Integer.toString(c.getCantPlazasQueOcupa()));
            ps.setString(3,Float.toString(c.getPrecioPorHora()));
            ps.setString(4,Integer.toString(c.getId()));
            if (ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Actualizado CORRECTAMENTE.");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error, la base de datos no guardo los cambios");
        }finally{
            cn.cerraconexion();
        }  
        return false;
    }

    @Override
    public TipoVehiculo read(Object key) {
        PreparedStatement ps;
        ResultSet res;
        TipoVehiculo l=null;
        try {
            ps = cn.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString()); 
            res = ps.executeQuery();
            while(res.next()){
                 l = new TipoVehiculo(Integer.parseInt(res.getString(1)),res.getString(2),Integer.parseInt(res.getString(3)),Integer.parseInt(res.getString(4))); 
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error, la base de datos no guardo los cambios");
        }finally{
            cn.cerraconexion();
        }
        return l;
    }

    @Override
    public List<TipoVehiculo> readALL() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList <TipoVehiculo> tvehiculos = new ArrayList();
        try {    
            ps = cn.getCnn().prepareStatement(SQL_READALL);
            res = ps.executeQuery();
            while(res.next()){
                tvehiculos.add(new TipoVehiculo(Integer.parseInt(res.getString(1)),res.getString(2),Integer.parseInt(res.getString(3)),Integer.parseInt(res.getString(4)))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error, la base de datos no guardo los cambios");
        }finally{
            cn.cerraconexion();
        }
        return tvehiculos;
    }
    
}
