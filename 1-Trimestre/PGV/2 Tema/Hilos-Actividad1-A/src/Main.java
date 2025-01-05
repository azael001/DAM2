
public class Main {
    public static void main(String[] args) {

            new Thread(new MiHilo(), "hilo1").start();

            new Thread(new MiHilo(), "hilo2").start();

            new Thread(new MiHilo(), "hilo3").start();

            new Thread(new MiHilo(), "hilo4").start();





    }

}
class  MiHilo implements Runnable{

    @Override
    public void run() {
        for (int i= 1; i<=5; i++) {
            System.out.println(Thread.currentThread().getName());
        }
        System.out.println(Thread.currentThread().getName() + " ha terminado su ejecución");
    }
   
}