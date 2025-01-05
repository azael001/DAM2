package Procces;

import java.io.File;
import java.io.IOException;

public class SegundoConBat {
    public static void main(String[] args) {
        //Esto de aquí es para abrir  el notepad y el calc
        File fl = new File("src/Procces/a.bat");
        String absolutePath = fl.getAbsolutePath();
//        ProcessBuilder pb1 = new ProcessBuilder(absolutePath);
        Runtime rt1 = Runtime.getRuntime();
        try{
//            Process pro1 = pb1.start();

            Process pro2 = rt1.exec(absolutePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
