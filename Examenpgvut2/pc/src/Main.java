public class Main {
    public static void main(String[] args) {
        Monitor m = new Monitor();
        Productor p = new Productor("Fichero.txt",m);
        Consumidor c = new Consumidor(m);
        p.start();
        c.start();

    }

}









