
public class Main {
    public static void main(String[] args) {
        Producto m = new Producto(7);


        for (int i=0; i<2; i++){
            Thread t = new Thread(new Productor(m));
            t.start();
        }


        for (int o=1; o<60; o++){
            Thread t = new Thread(new Consumidor(m,o));
            t.start();
        }

    }

}









