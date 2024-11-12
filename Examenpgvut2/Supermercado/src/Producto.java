import java.util.logging.Level;
import java.util.logging.Logger;

public class Producto {
     private int pIniciales;
    private int productos;
    private boolean estaVacia;
    private boolean estaLlena;
    private int producotsllenos=10;
    private int nCliente=1;

    public Producto(int pIniciales){
        this.pIniciales=pIniciales;
        this.productos = pIniciales;
        this.estaVacia = true;
        this.estaLlena = false;

    }

    public synchronized void consumir(){
        while(this.estaVacia){
            try {
                wait();
            } catch (InterruptedException ex) {
               break;
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.estaLlena = false;
        productos--;
        System.out.println("Hola soy cliente " + nCliente+ " Y quedan "+ productos + " Productos");
        nCliente++;
        if(productos == 0){
            this.estaVacia = true;
        }
        notifyAll();
    }

    public synchronized void producir(){
        while(this.estaLlena){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        productos++;
        System.out.println("El reponedor " + "Ha repuesto 1 producto , entonces hay " + productos + " Productos");
        this.estaVacia = false;
        if(productos == producotsllenos){
            this.estaLlena = true;
        }
        notifyAll();
    }

    public int getProductos() {
        return productos;
    }
}
