import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Supermercado {
    Boolean cajasAbiertas = true;
    Semaphore s = new Semaphore(5);
    public static String cajas []={"| X | ","| X | ","| X | ","| X | ","| X | "};

    public Supermercado(Semaphore s) {
        this.s = s;
    }

    public void ingresarCaja(int x){
        int nCaja=0;
        try {
            s.acquire();
            Thread.sleep((long) (Math.random()*2000 + 1000));
            System.out.println("Cliente n " + x + " Ha ingresado en la caja");
            for (int i =0; i<cajas.length; i++){
                if(cajas[i].equals("| X | ")){
                    cajas[i]="| | ";
                    nCaja=i;
                    break;
                }
            }
            mostarCajas();
            Thread.sleep((long) (Math.random()*9000 + 5000));
            cajas[nCaja]="| X | ";
            System.out.println("El cliente "+ x + "Ha salido de la caja");
            mostarCajas();
            s.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
  public void mostarCajas(){
        for (int i =0; i<cajas.length; i++){
            System.out.print(cajas[i]);
        }
      System.out.println("\n");
  }

}
