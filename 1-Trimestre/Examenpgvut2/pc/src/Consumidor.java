import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Thread{
    Monitor monitor;

    public Consumidor(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){
            char letraConsumir = this.monitor.consumir();
            System.out.print(letraConsumir);
            try {
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
