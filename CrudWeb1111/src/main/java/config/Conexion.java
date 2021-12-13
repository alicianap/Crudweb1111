
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion 
{
    public String driver="com.mysql.cj.jdbc.Driver";
    
    public Connection getConnection()
    {
        Connection conexion=null;
        try
        {
           Class.forName(driver);
           conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/crudweb1111","root","");
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.toString());
        }
        return conexion;
    }
    
    public static void main(String[] args) throws SQLException                      
    {
        Connection conexion=null;
        Conexion con=new Conexion();
        conexion=con.getConnection();
        
        PreparedStatement ps;
        ResultSet rs;
        
        ps=conexion.prepareStatement("SELECT id,nombre,apellido,mail FROM alumnos");
        rs=ps.executeQuery();
        
        while(rs.next())
        {
            int id=rs.getInt("id");
            String nombre=rs.getString("nombre");
            String apellido=rs.getString("apellido");
            String mail=rs.getString("mail");
            
            System.out.println("Id: "+id+" Apellido: "+apellido+" Nombre; "+nombre+" Email: "+mail);
            
        }
    }        
}
