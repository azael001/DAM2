import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int nCoches = 80;
        Parking parking = new Parking(5); // Capacidad de 5 plazas
        for (int i = 1; i <= nCoches; i++) {
            Thread t = new Thread(new Coches(parking, i));
            t.start();
        }
    }
}

class Parking {
    private final boolean[] plazas;
    private int cochesDentro = 0;

    public Parking(int capacidad) {
        this.plazas = new boolean[capacidad]; // false significa que la plaza está libre
    }

    public synchronized int entrar(int nCoche) throws InterruptedException {
        while (cochesDentro >= plazas.length) {
            System.out.println("El coche " + nCoche + " está esperando");
            wait();
        }
        // Buscar la primera plaza libre
        int plazaOcupada = -1;
        for (int i = 0; i < plazas.length; i++) {
            if (!plazas[i]) {
                plazas[i] = true;
                cochesDentro++;
                plazaOcupada = i;
                System.out.println("El coche " + nCoche + " ha ocupado la plaza " + (i + 1));
                break;
            }
        }
        return plazaOcupada;
    }

    public synchronized void salir(int nCoche, int plaza) {
        plazas[plaza] = false;
        cochesDentro--;
        System.out.println("El coche " + nCoche + " ha dejado libre la plaza " + (plaza + 1));
        notifyAll();
    }
}

class Coches implements Runnable {
    private final Parking parking;
    private final int nCoche;

    public Coches(Parking parking, int nCoche) {
        this.parking = parking;
        this.nCoche = nCoche;
    }

    public void run() {
        try {
            int plazaOcupada = parking.entrar(nCoche);
            Thread.sleep(8000); // Simula el tiempo de aparcamiento
            parking.salir(nCoche, plazaOcupada);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
