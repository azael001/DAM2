import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        //El semáforo lo ponemos a 50 , para que el máximo núemero en la cola sea 50
        Semaphore semaforo = new Semaphore(50);
        //Aquí tenemos el string de array posibles para introducir en nuestro hilo
            String[] nAero={"Iberia","Air Berlin","Binter","Ryanair","Vueling","Spanair","Lufthansa","Condor","Swissair","Canaryfly"};
            Random r = new Random();
            //Con este for introducimos los datos de forma automática.
        for (int i = 1; i<100; i++){
            int nRandom = r.nextInt(nAero.length);
            Thread t = new Thread(new PistaDespeje(semaforo,nAero[nRandom]));
            t.start();
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
//Esta será nuestra única clase
class PistaDespeje implements Runnable{
    private final Semaphore semaforo;
    private String aerolinea;
    private static final Object lock = new Object();
    private static int binterCola = 0;
    private static int airBerlinCola = 0;
    private static int ryanairCola = 0;
    private static int vuelingCola = 0;
    private static int spanairCola = 0;
    private static int lufthansaCola = 0;
    private static int condorCola = 0;
    private static int swissairCola = 0;
    private static int canaryflyCola = 0;

    public PistaDespeje(Semaphore semaforo, String aerolinea) {
        this.semaforo = semaforo;
        this.aerolinea = aerolinea;
    }

    @Override
    public void run() {
        try {
            Random r = new Random();
            //Antes de pedir despegar haremos un número aleatorio entre los parámetros predefinidos.
            Thread.sleep(r.nextInt(3000)+1000);
            System.out.println(aerolinea + " Pide despegar");
            //Esta es la función para hacer esperar en caso de que una misma aerolínea tenga más de 10 vuelos en cola
            synchronized (lock) {
                while (getVuelosEnCola() >= 10) {
                    System.out.println(aerolinea + " no puede despegar, ya tiene 10 vuelos en cola. Esperando...");
                    lock.wait();
                }
                incrementaVuelosEnCola();
            }
            semaforo.acquire();
            //Con esta variable, sacamos el número de cola
            int posicion = (50-semaforo.availablePermits());
            System.out.println("El vuelo " + aerolinea + " Está en pista en la posición número " +  (posicion));
            Thread.sleep(r.nextInt(8000)+1000);
            System.out.println("Hilo "+ aerolinea + "Esta despegando");
            semaforo.release();
            synchronized (lock) {
                decrementaVuelosEnCola();
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private synchronized int getVuelosEnCola() {
        switch (aerolinea) {
            case "Binter":
                return binterCola;
            case "Air Berlin":
                return airBerlinCola;
            case "Ryanair":
                return ryanairCola;
            case "Vueling":
                return vuelingCola;
            case "Spanair":
                return spanairCola;
            case "Lufthansa":
                return lufthansaCola;
            case "Condor":
                return condorCola;
            case "Swissair":
                return swissairCola;
            case "Canaryfly":
                return canaryflyCola;
            default:
                return 0;
        }
    }
    private synchronized void incrementaVuelosEnCola() {
        switch (aerolinea) {
            case "Binter":
                binterCola++;
                break;
            case "Air Berlin":
                airBerlinCola++;
                break;
            case "Ryanair":
                ryanairCola++;
                break;
            case "Vueling":
                vuelingCola++;
                break;
            case "Spanair":
                spanairCola++;
                break;
            case "Lufthansa":
                lufthansaCola++;
                break;
            case "Condor":
                condorCola++;
                break;
            case "Swissair":
                swissairCola++;
                break;
            case "Canaryfly":
                canaryflyCola++;
                break;
        }

    }
    private synchronized void decrementaVuelosEnCola() {
        switch (aerolinea) {
            case "Binter":
                binterCola--;
                break;
            case "Air Berlin":
                airBerlinCola--;
                break;
            case "Ryanair":
                ryanairCola--;
                break;
            case "Vueling":
                vuelingCola--;
                break;
            case "Spanair":
                spanairCola--;
                break;
            case "Lufthansa":
                lufthansaCola--;
                break;
            case "Condor":
                condorCola--;
                break;
            case "Swissair":
                swissairCola--;
                break;
            case "Canaryfly":
                canaryflyCola--;
                break;
        }
    }

}