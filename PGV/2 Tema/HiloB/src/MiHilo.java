public class MiHilo extends Thread{
    @Override
    public void run() {
        for(int i=0; i<10; i++){
            try {
                Thread.sleep(1000);
                System.out.println("Se está ejecutando " + Thread.currentThread().getName() + " Con prioridad " + Thread.currentThread().getPriority());
                if ((i == 4) && (Thread.currentThread().getPriority() == MIN_PRIORITY)) {
                    Thread.currentThread().setPriority(MAX_PRIORITY);
                } else if ((i == 4) && (Thread.currentThread().getPriority() == MAX_PRIORITY)) {
                    Thread.currentThread().setPriority(MIN_PRIORITY);
                }
                if (i == 9) {
                    System.out.println("El hilo " + Thread.currentThread().getName() + " Se ha acabado");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
