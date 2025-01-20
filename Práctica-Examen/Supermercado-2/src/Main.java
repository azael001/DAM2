import java.util.ArrayList;
import java.util.concurrent.Semaphore;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1);
        Supermercado supermer = new Supermercado(s);
        for (int o = 0; o<40; o++){
            Clientes cli = new Clientes(supermer);
            cli.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}