package Actividad;
import java.io.IOException;
import java.util.Scanner;

public class Child {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c","dir");

      try{
          pb.start();
      } catch (IOException e) {

          throw new RuntimeException(e);
      }

//Aprender a exportar en jar

    }
}
