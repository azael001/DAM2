public class MiHilo implements Runnable{
    @Override
    public void run() {
        for (int i=0; i<5; i++){
            System.out.println("Se está ejecutando el Hilo " +Thread.currentThread().getName());
        }
    }
}
