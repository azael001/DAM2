import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaforoPista = new Semaphore(50);
        String[] nAero = {"Iberia", "Air Berlin", "Binter", "Ryanair", "Vueling", "Spanair", "Lufthansa", "Condor", "Swissair", "Canaryfly"};
        Semaphore[] semaforosAero = new Semaphore[nAero.length];
        int[] numeroCola = new int[nAero.length];
        Random r = new Random();

        // Inicializamos un semáforo para cada aerolínea con máximo de 10 vuelos en cola.
        for (int i = 0; i < nAero.length; i++) {
            semaforosAero[i] = new Semaphore(10);
            numeroCola[i] = 0;
        }

        for (int i = 1; i < 100; i++) {
            int nRandom = r.nextInt(nAero.length);
            String aerolinea = nAero[nRandom];
            Semaphore semaforoAero = semaforosAero[nRandom];

            Thread t = new Thread(new PistaDespeje(semaforoPista, semaforoAero, aerolinea, numeroCola, nRandom));
            t.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PistaDespeje implements Runnable {
    private final Semaphore semaforoPista;
    private final Semaphore semaforoAero;
    private final String aerolinea;
    private final int[] numeroCola;
    private final int index;

    public PistaDespeje(Semaphore semaforoPista, Semaphore semaforoAero, String aerolinea, int[] numeroCola, int index) {
        this.semaforoPista = semaforoPista;
        this.semaforoAero = semaforoAero;
        this.aerolinea = aerolinea;
        this.numeroCola = numeroCola;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            Random r = new Random();
            Thread.sleep(r.nextInt(5000) + 1000);

            // Intentamos obtener permiso para añadir un vuelo a la cola de la aerolínea.
            if (!semaforoAero.tryAcquire()) {
                System.out.println(aerolinea + " no puede despegar, ya tiene 10 vuelos en cola. Esperando...");
                semaforoAero.acquire();
            }
            semaforoPista.acquire();
            Thread.sleep(r.nextInt(2000) + 1000);
            System.out.println("El vuelo " + aerolinea + " está en cola para despegar.");
            Thread.sleep(r.nextInt(7000) + 1000);

            System.out.println("Hilo " + aerolinea + " está despegando ------------------");
            semaforoPista.release();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
