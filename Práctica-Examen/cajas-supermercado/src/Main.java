import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        Supermercado supermercado = new Supermercado(semaphore);
        for (int i =1; i<40; i++){
            Cliente c = new Cliente(i,supermercado);
            c.start();
        
        }


    }
}