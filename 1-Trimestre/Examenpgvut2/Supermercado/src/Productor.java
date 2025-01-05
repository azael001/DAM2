import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Productor implements Runnable {


    private Producto producto;

    Productor(Producto producto) {
        this.producto = producto;
    }

    @Override
    public void run() {
        producto.producir();

    }

}



