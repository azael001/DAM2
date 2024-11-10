
public class Main {
    public static void main(String[] args) {
            MiHilo Tarea1 = new MiHilo();
            MiHilo Tarea2 = new MiHilo();
            MiHilo Tarea3 = new MiHilo();

            new Thread(Tarea1);
            new Thread(Tarea2);
            new Thread(Tarea3);
            Tarea1.setName("Tarea 1");
            Tarea2.setName("Tarea 2");
            Tarea3.setName("Tarea 3");


            Tarea1.setPriority(Thread.MIN_PRIORITY);
            Tarea2.setPriority(Thread.NORM_PRIORITY);
            Tarea3.setPriority(Thread.MAX_PRIORITY);
            Tarea1.start();
            Tarea2.start();
            Tarea3.start();
            try{
                    Tarea1.join();
                    Tarea2.join();
                    Tarea3.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Ha acabado el hilo principal");
    }
}