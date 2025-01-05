import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try{
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:src/eje/sqlite.db");
            Statement sentencia2= conexion.createStatement();
            Statement sentencia= conexion.createStatement();

            ResultSet  resul = sentencia2.executeQuery("Select * from Empleados");
            ResultSet result = sentencia.executeQuery("select * from Departamentos");
            while(result.next()){
                System.out.println(result.getInt(1) + " " + result.getString(2) + " " + result.getString(3));
            }
            result.close();
            sentencia.close();

            while(resul.next()){
                System.out.println(resul.getInt(1) + " "+ resul.getString(2) +" "+ resul.getString(3) + " "+ resul.getString(4)+" "+
                         resul.getDouble(5) + " " + resul.getDouble(6) + " " + resul.getInt(7));
            }
            resul.close();
            sentencia2.close();
            conexion.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}