import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //Con esto abrimos la calculadora
//
//        String comando ="calc.exe";
//
//        ProcessBuilder pb = new ProcessBuilder(comando);
//        try{
//            Process pro = pb.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        ProcessBuilder pb = new ProcessBuilder("cmd","/c","start C:\\Users\\Alumnadomañana\\Desktop\\Clase\\a.bat");
        try{
            Process pro = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }
}