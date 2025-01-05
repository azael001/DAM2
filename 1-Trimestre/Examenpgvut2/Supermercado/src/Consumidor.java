import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements Runnable{
    Producto producto;
    private int nCliente;

    public Consumidor(Producto producto,int numCliente){
        this.producto = producto;
        this.nCliente=numCliente;
    }

    @Override
    public void run() {
            producto.consumir();

    }

}
