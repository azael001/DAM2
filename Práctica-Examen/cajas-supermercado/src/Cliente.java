import java.util.concurrent.Semaphore;

public class Cliente  extends Thread{
    private int nCliente;
    private Supermercado supermercado;
    public Cliente(int x, Supermercado supermercado) {
        this.nCliente=x;
        this.supermercado = supermercado;
    }




    @Override
    public void run() {
       supermercado.ingresarCaja(nCliente);
    }
}
