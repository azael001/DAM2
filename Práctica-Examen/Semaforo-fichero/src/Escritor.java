import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Escritor extends Thread {
    Acceso a;
    public Escritor(Acceso a) {
        this.a =a;
    }
    @Override
    public void run( ) {
        a.escritura();
    }
}
