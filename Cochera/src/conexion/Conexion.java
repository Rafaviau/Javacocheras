package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    public static Conexion instance;
    private Connection cnn;
    
    private Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                cnn = DriverManager.getConnection("jdbc:mysql://localhost/estacionamiento?user=root"); //Revisar nombre de la base de datos
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static synchronized Conexion verificador(){
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }
        public void cerraconexion(){
        instance = null;
    }

    public Connection getCnn() {
        return cnn;
    }   
}
