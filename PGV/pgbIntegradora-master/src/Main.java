import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {


//        String relativePath= "./src/msconfig.bat";
        try {
            ProcessBuilder pb1= new ProcessBuilder("cmd","/c" ,"start msconfig");
            pb1.start();
//          ProcessBuilder pb1 = new ProcessBuilder(relativePath);
            ProcessBuilder pb2 = new ProcessBuilder("java", "-jar", "out/artifacts/IntegradoraPgv_jar/IntegradoraPgv.jar");
            Process proc1 = pb1.start();
            Process proc2 = pb2.start();


            InputStreamReader isr = new InputStreamReader(proc2.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            String line;


            while((line=br.readLine())!=null){
                System.out.println(line);
            }

            String integradora= "C:\\Users\\pc\\Desktop\\DAM2-master\\PGV\\pgbIntegradora-master\\src\\Carpeta-Integradora-Pgv";
            proc2.waitFor();

            ProcessBuilder pb4 = new ProcessBuilder("cmd", "/c", "dir", integradora);
            pb4.inheritIO().start().waitFor();

            proc1.destroy();




        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}