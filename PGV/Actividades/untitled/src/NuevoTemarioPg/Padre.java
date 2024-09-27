package NuevoTemarioPg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Padre {public static void main(String[] args) {
        try {

            Process p = new ProcessBuilder("java", "-jar", "./out/artifacts/PGV_jar2/PGV.jar").start();
            String st = "hola soy el padre \n";
            OutputStreamWriter osr = new OutputStreamWriter(p.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osr);
            bw.write(st);
            bw.flush();

            InputStreamReader isr= new InputStreamReader(p.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line=br.readLine()) !=null){
                System.out.printf(line);
            }
//            osr.close();
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Hay que arreglar las entradas y salidas de texto solo en los padres, los demás no li
        //tienen, esto es un ejemplo para enviar un texto al hijo

    }

}

