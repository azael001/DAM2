import java.util.ArrayList;

public class Parking {
    private int nMaxCoche;
    private int numPlazas=5;
    private int plazasLibres=5;
    private boolean vacio = true;
    private boolean lleno = false;
    public Parking() {
    }

    public synchronized void aparcar(){
        if(this.lleno){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        plazasLibres--;
        System.out.println("N plaza " + plazasLibres);
        this.vacio=false;
        if(plazasLibres<=0){
            this.lleno=true;
        }
        notifyAll();
    }
    public synchronized void salir(){
        if(this.vacio){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Se ha ido el coche n " );
        plazasLibres++;
        this.lleno=false;
        if(plazasLibres>=5){
            this.vacio=true;
        }
        notifyAll();
    }
}
