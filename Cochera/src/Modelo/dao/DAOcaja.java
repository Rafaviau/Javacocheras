/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.dao;

import Modelo.Caja;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafa
 */
public class DAOcaja implements crud <Caja> {
    private static final String SQL_CREATE = "INSERT INTO caja (fecha,cajainicial,cajafinal) VALUES(?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM caja WHERE fecha = ?";
    private static final String SQL_UPDATE = "UPDATE caja SET cajainicial = ?, ingresos = ?, gastos = ?, cajafinal = ? WHERE fecha = ?";
    private static final String SQL_READ = "SELECT * FROM caja WHERE fecha=?";
    private static final String SQL_READALL = "SELECT * FROM caja";

    private static final Conexion cn = Conexion.verificador(); 


    @Override
    public boolean create(Caja c) {
        PreparedStatement ps;
        try {    
            ps = cn.getCnn().prepareStatement(SQL_CREATE);
            ps.setString(1, c.getFecha().toString());
            ps.setString(2, Float.toString(c.getCajainicial()));
            ps.setString(3, Float.toString(c.getCajafinal()));
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Creado correctamente.");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error, la base de datos no guardo los cambios");
        }finally{
            cn.cerraconexion();
        }
        
        return false;    }

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
        return false;    }

    @Override
    public boolean update(Caja c) {
        PreparedStatement ps;
        try {
            ps = cn.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1,Float.toString(c.getCajainicial()));
            ps.setString(2, Float.toString(c.getIngresos()));
            ps.setString(3,Float.toString(c.getGastos()));
            ps.setString(4,Float.toString(c.getCajafinal()));
            ps.setString(5, c.getFecha().toString());
            if (ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error, la base de datos no guardo los cambios");
        }finally{
            cn.cerraconexion();
        }  
        return false;    }

    @Override
    public Caja read(Object key) {
        PreparedStatement ps;
        ResultSet res;
        Caja l=null;
        try {
            ps = cn.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString()); 
            res = ps.executeQuery();
            while(res.next()){
                 l = new Caja(LocalDate.parse(res.getString(1)) , Float.parseFloat(res.getString(2)) , Float.parseFloat(res.getString(3)) ,
                         Float.parseFloat(res.getString(4)) , Float.parseFloat(res.getString(5))); 
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cerraconexion();
        }
        return l;    }

    @Override
    public List<Caja> readALL() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList <Caja> caja = new ArrayList();
        try {    
            ps = cn.getCnn().prepareStatement(SQL_READALL);
            res = ps.executeQuery();
            while(res.next()){
                caja.add(new Caja(LocalDate.parse(res.getString(1)) , Float.parseFloat(res.getString(2)) , Float.parseFloat(res.getString(3)) ,
                         Float.parseFloat(res.getString(4)) , Float.parseFloat(res.getString(5))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cerraconexion();
        }
        return caja;    
    }
}
   
    

