import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Child {
    public static void main(String[] args) {
            File file = new File("src/Carpeta-Integradora-Pgv");
            System.out.println("La carpeta es " + file.getName());
            String absolutePathCarpeta = file.getAbsolutePath();
            String absolutePathBat= "C:\\Users\\pc\\Desktop\\DAM2-master\\PGV\\pgbIntegradora-master\\src\\pgv.bat";
            try{
                for (int i=0; i<file.list().length; i++){
                    String nombreArchivo= file.list()[i];
                    ProcessBuilder pr = new ProcessBuilder(absolutePathBat,absolutePathCarpeta,nombreArchivo);
                    Process pb = pr.start();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
}