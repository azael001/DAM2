import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Productor extends Thread {

    String fichero;
    static int caracterNumerico = 0;


    private Monitor monitor;

    Productor(String fichero,Monitor monitor) {
        this.fichero = fichero;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        FileReader fr = null;
        try {
            File fichero = new File("Fichero.txt");
            fr = new FileReader(fichero);
            BufferedReader in = new BufferedReader(fr);

            while (caracterNumerico != -1) {

                caracterNumerico = in.read();
                char letra = (char)caracterNumerico;
                monitor.producir(letra);

                sleep(1);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }


}
