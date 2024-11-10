import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ProductorConsumidor pc= new ProductorConsumidor();
        Thread productor = new Thread(() -> {
            try {
                pc.producir();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumidor = new Thread(() ->{
            try{
                pc.consumir();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        productor.start();
        consumidor.start();
    }
}
class ProductorConsumidor{
    private ArrayList<Integer> almacen = new ArrayList<>();
    private final int capacidad=5;
    public synchronized void producir() throws InterruptedException{
        int elemento=1;
        while(true){
            while(almacen.size()==capacidad){
                wait();
            }
            System.out.println("Productor produce " + elemento);
            almacen.add(elemento);
            elemento++;
            notifyAll();
            Thread.sleep(1000);
        }
    }
    public synchronized void consumir() throws InterruptedException{
        while(true){
            while(almacen.isEmpty()){
                wait();
            }
            int elemento = almacen.remove((almacen.size()-1));
            System.out.println("El consumidor consume: " + (almacen.size()+1));
            Thread.sleep(1000);
        }
    }
}