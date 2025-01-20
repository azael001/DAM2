import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static int nHilos=100;
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1);
        Semaphore lec = new Semaphore(1);
        Acceso a = new Acceso(s,lec);
        for (int i = 0; i<10; i++){
            Escritor e = new Escritor(a);
            Lector l = new Lector(a);
            Random r = new Random();
            int randomNumber = r.nextInt(100);
            if (randomNumber<10){
                e.start();
                try {
                    e.join();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                l.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        for (int o = 0; o<5; o++){
            Escritor es = new Escritor(a);
            es.start();
            try{
                es.join();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}