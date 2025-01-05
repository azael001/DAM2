import java.util.logging.Level;
import java.util.logging.Logger;

public class Monitor {
    private char[] buffer;
    private int siguiente;
    private boolean estaVacia;
    private boolean estaLlena;

    public Monitor(){
        this.buffer = new char[5];
        this.siguiente = 0;
        this.estaVacia = true;
        this.estaLlena = false;

    }

    public synchronized char consumir(){
        while(this.estaVacia){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.estaLlena = false;
        siguiente--;
        if(siguiente == 0){
            this.estaVacia = true;
        }

        notifyAll();

        return this.buffer[siguiente];

    }

    public synchronized void producir(char c){
        while(this.estaLlena){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        buffer[siguiente] = c;
        siguiente++;
        this.estaVacia = false;
        if(siguiente == this.buffer.length){
            this.estaLlena = true;
        }
        notifyAll();
    }
}
