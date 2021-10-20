/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.dao;

import Modelo.Cochera;
import Modelo.Cochera;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafa
 */
public class DAOCochera implements crud <Cochera> {
    private static final String SQL_CREATE = "INSERT INTO cochera (nombre,capacidad) VALUES(?,?)";
    private static final String SQL_DELETE = "DELETE FROM cochera WHERE idcochera = ?";
    private static final String SQL_UPDATE = "UPDATE cochera SET nombre = ?, capacidad = ? WHERE idcochera = ?";
    private static final String SQL_READ = "SELECT * FROM cochera WHERE idcochera=?";
    private static final String SQL_READALL = "SELECT * FROM cochera";
    
    private static final Conexion cn = Conexion.verificador(); 
    @Override
    public boolean create(Cochera c) {
        PreparedStatement ps;
        try {    
            ps = cn.getCnn().prepareStatement(SQL_CREATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, Integer.toString(c.getOcupaciones()));
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Agregado correctamente.");
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
/**
 * Recibe una cochera ya creada, que la utiliza para obtener los datos que necesita
 * 
 * @param c
 * @return 
 */
    @Override
    public boolean update(Cochera c) {
        PreparedStatement ps;
        try {
            ps = cn.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, Integer.toString(c.getOcupaciones()));
            ps.setString(3,Integer.toString(c.getId()));
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
    public Cochera read(Object key) { 
        PreparedStatement ps;
        ResultSet res;
        Cochera l=null;
        try {
            ps = cn.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString()); 
            res = ps.executeQuery();
            while(res.next()){
                 l = new Cochera(Integer.parseInt(res.getString(1)),res.getString(2),Integer.parseInt(res.getString(3))); 
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cerraconexion();
        }
        return l;
    }

    @Override
    public List<Cochera> readALL() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList <Cochera> cocheras = new ArrayList();
        try {    
            ps = cn.getCnn().prepareStatement(SQL_READALL);
            res = ps.executeQuery();
            while(res.next()){
                cocheras.add(new Cochera(Integer.parseInt(res.getString(1)),res.getString(2),Integer.parseInt(res.getString(3))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cerraconexion();
        }
        return cocheras;
    }
    
}
