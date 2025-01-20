import java.io.*;
import java.util.concurrent.Semaphore;

public class Lector extends Thread {
    Acceso a ;
    public Lector(Acceso a) {
        this.a =a;
    }


    @Override
    public void run() {
        a.lectura();
    }

}
