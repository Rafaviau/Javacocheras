package Modelo.dao;

import Modelo.IngresoCliente;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DAOingresocliente implements crud <IngresoCliente>{
    private static final String SQL_CREATE = "INSERT INTO ingresocliente (patente, idtipovehiculo, idcochera, fecha, dni, horaentrada) VALUES(?,?,?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM ingresocliente WHERE patente = ? AND horaentrada = ?";
    private static final String SQL_UPDATE = "UPDATE ingresocliente SET idtipovehiculo = ?, idcochera = ?, fecha = ?, dni = ? WHERE patente = ? AND horaentrada = ?";
    private static final String SQL_UPDATESALIDA = "UPDATE ingresocliente SET horasalida = ? WHERE patente = ? AND horaentrada = ?";
    private static final String SQL_READ   = "SELECT * FROM ingresocliente WHERE patente = ? AND horaentrada = ?";
    private static final String SQL_READALL= "SELECT * FROM ingresocliente";
    private static final String SQL_READALLSTRING="SELECT i.patente,i.fecha,i.dni,i.horaentrada,i.horasalida,c.nombre,t.nombre FROM ingresocliente as i INNER JOIN cochera as c "
            + "ON i.idcochera=c.idcochera INNER JOIN tipovehiculo AS t ON i.idtipovehiculo=t.idtipovehiculo ORDER BY i.horaentrada";

    private static final Conexion cn = Conexion.verificador();

    @Override
    public boolean create(IngresoCliente c) {
        PreparedStatement ps;
        try {    
            ps = cn.getCnn().prepareStatement(SQL_CREATE);
            ps.setString(1, c.getPatente());
            ps.setInt(2, c.getTipodevehiculo().getId());
            ps.setInt(3, c.getCocheraqueocupa().getId());
            ps.setString(4, c.getFecha().toString());
            ps.setInt(5, c.getDni());
            ps.setString(6, c.getEntrada().toString());
            
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
        
        return false;
    }

    //@Override
    public boolean delet(String patente,LocalDateTime entrada) {
        PreparedStatement ps;
        try {
            ps = cn.getCnn().prepareStatement(SQL_DELETE);
            ps.setString(1, patente);
            ps.setString(2, entrada.toString());
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
        return false;        }

    @Override
    public boolean update(IngresoCliente c) {
        PreparedStatement ps;
        try {
            ps = cn.getCnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, c.getTipodevehiculo().getId());
            ps.setInt(2, c.getCocheraqueocupa().getId());
            ps.setString(3, c.getFecha().toString());
            ps.setInt(4, c.getDni());
            ps.setString(5, c.getPatente());
            ps.setString(6, c.getEntrada().toString());
            if (ps.executeUpdate() > 0){
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
    
    public boolean updateSALIDA(LocalTime hsalida, LocalTime hentrada, String pat) {
        PreparedStatement ps;
        try {
            ps = cn.getCnn().prepareStatement(SQL_UPDATESALIDA);
            ps.setString(1, hsalida.toString());
            ps.setString(2, pat);
            ps.setString(3, hentrada.toString());
            
            if (ps.executeUpdate() > 0){
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
    public IngresoCliente read(Object key) {
        PreparedStatement ps;
        ResultSet res;
        IngresoCliente l=null;
        try {
            ps = cn.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString()); 
            res = ps.executeQuery();
            while(res.next()){
                 //l = new IngresoCliente(res.getString(1) , Float.parseFloat(res.getString(2)) , Float.parseFloat(res.getString(3)) ,
                   //      Float.parseFloat(res.getString(4)) , Float.parseFloat(res.getString(5))); 
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
    public List<IngresoCliente> readALL() {
       PreparedStatement ps;
        ResultSet res;
        ArrayList <IngresoCliente> cliente = new ArrayList();
        try {    
            ps = cn.getCnn().prepareStatement(SQL_READALL);
            res = ps.executeQuery();
            while(res.next()){
                //cliente.add(new IngresoCliente()); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cerraconexion();
        }
        return cliente;
    }

    @Override
    public boolean delete(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public String[][] readALLstring() {
        int size =0;
        int i = 0;
        PreparedStatement ps;
        ResultSet res;

        try {    
            ps = cn.getCnn().prepareStatement(SQL_READALLSTRING);
            res = ps.executeQuery(); 
            while(res.next()){
                size++;
            }
            String[][] a = new String[size][7];
            ps = cn.getCnn().prepareStatement(SQL_READALLSTRING);
            res = ps.executeQuery(); 
            while(res.next()){
                a[i][0]=res.getString(1);
                a[i][1]=res.getString(2);
                a[i][2]=res.getString(3);
                a[i][3]=res.getString(4);
                a[i][4]=res.getString(5);
                a[i][5]=res.getString(6);
                a[i][6]=res.getString(7);
                i++;
            }
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCochera.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cerraconexion();
        }
        return null;
    }
    
}
