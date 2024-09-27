package Actividad;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Parent {public static void main(String[] args) {
    try {

        Process p = new ProcessBuilder("java", "-jar", "C:\\Users\\pc\\Desktop\\Cosasnuevashoy\\PGV\\out\\artifacts\\PGV_jar\\PGV.jar").start();
        InputStreamReader isr = new InputStreamReader(p.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        String line;

        while ((line = br.readLine()) != null) {
            System.out.printf(line);
        }
//
        br.close();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
}


