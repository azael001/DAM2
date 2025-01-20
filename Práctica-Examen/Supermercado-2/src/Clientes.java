public class Clientes extends Thread {
    private Supermercado superm;
    private String nombreCLiente;

    public Clientes(Supermercado superm) {
        this.superm = superm;
    }

    @Override
    public void run() {
        superm.salirCaja();
    }
}
