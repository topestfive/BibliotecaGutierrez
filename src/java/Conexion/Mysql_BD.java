package Conexion;
import java.sql.*;

public class Mysql_BD {
    private  static Mysql_BD instance;
    private String driver="com.mysql.cj.jdbc.Driver";
    private String user="root";
    private String pss="123456";
    private String nombre_bd="bd_biblioteca";
    private String url="jdbc:mysql://localhost:3306/";
    public Connection cnn;

//constructor Sigleton
    private Mysql_BD() {
        System.out.println("Creando instancia");
        try {
            Class.forName(driver);
            cnn=DriverManager.getConnection(url+nombre_bd, user, pss);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al cargar el driver");
        }      
    }

    public static synchronized Mysql_BD getInstance() {
        if(instance == null){
            Mysql_BD.instance=new Mysql_BD();
        }
        return instance;
    }
    
    public void cerrarConexion(){
        if(cnn!=null){
            try{
                cnn.close();
                System.out.println("Conexión cerrada");
            }catch(SQLException ex){
                System.out.println("Error al cerrar la conexión" + ex.getMessage());
            }
        }
        instance=null;
    }
}