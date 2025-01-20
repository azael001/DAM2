import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Supermercado {
   Semaphore s;
   private static int cajaNumUno=0;
   private static int cajaNumDos=0;
   private static int cajaNumTres=0;
   private static int nCliente=0;
   private int nClientePropio;
   String cajas[]={"Caja 1","Caja 2","Caja 3"};


    public Supermercado(Semaphore s) {
        this.s = s;
    }


    public void salirCaja(){

        try {
            String x = escogerCaja();
            Thread.sleep((long) (Math.random() * 8000 + 2000));
            s.acquire();
            if(x.equals(cajas[0])){
                System.out.println("El cliente " + nClientePropio+ " Ha salido de la" + x+ " y la ha dejado libre");
                cajaNumUno--;
                s.release();
            }
            else if(x.equals(cajas[1])){
                System.out.println("El cliente Ha salido de la caja 2 dejándola libre");
                cajaNumDos--;
                s.release();
            }
            else{
                System.out.println("El cliente Ha salido de la caja 3 dejándola libre");
                cajaNumTres--;
                s.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public String escogerCaja(){
        try {
            s.acquire();
            if((cajaNumUno<=cajaNumDos) && (cajaNumUno<=cajaNumTres)){
                cajaNumUno++;
                nCliente++;
                System.out.println("El cliente n " + nCliente + " Ha ingresado en la " + cajas[0] + " y es el " + cajaNumUno + " En cola");
                nClientePropio=nCliente;
                Thread.sleep((long) (Math.random() * 3000 + 1000));
                s.release();
                return cajas[0];
            }
            else if(cajaNumDos<=cajaNumTres){
                cajaNumDos++;
                nCliente++;
                System.out.println("El cliente n " + nCliente + " Ha ingresado en la " + cajas[1] + " y es el " + cajaNumDos + " En cola");
                nClientePropio=nCliente;
                Thread.sleep((long) (Math.random() * 3000 + 1000));
                s.release();
                return cajas[1];
            }
            else{
                cajaNumTres++;
                nCliente++;
                System.out.println("El cliente n " + nCliente + " Ha ingresado en la " + cajas[2] + " y es el " + cajaNumTres + " En cola");
                nClientePropio=nCliente;
                Thread.sleep((long) (Math.random() * 3000 + 1000));
                s.release();
                return cajas[2];
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
