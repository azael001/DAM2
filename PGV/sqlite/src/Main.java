import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try{
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\pc\\Desktop\\eje\\ejemplo.db");
            Statement sentencia= conexion.createStatement();
            ResultSet  resul = sentencia.executeQuery("Select * from emple");
            while(resul.next()){
                System.out.println(resul.getInt(1) + " " + resul.getString(2));
            }
            resul.close();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}