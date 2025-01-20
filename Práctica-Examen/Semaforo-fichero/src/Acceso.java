import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Acceso {
    Semaphore s;
    public static int nLector = 1;
    private static int numeroDeLector = 0;
    Boolean faltaEscritura = true;
    Semaphore lect ;

    public Acceso(Semaphore s, Semaphore lec) {
        this.s = s;
        this.lect = lec;
    }

    File f = new File("src/prueba.txt");

    public void lectura( ){
            try {
                lect.acquire();
                numeroDeLector++;
                int lector = numeroDeLector;
                comprobacionLinea();
                if(faltaEscritura) {
                    System.out.println("El Lector número " + lector + "Está intentando leer, pero no hay nada escrito.");
                    while (faltaEscritura) {
                        Thread.sleep(5000);
                        comprobacionLinea();
                    }
                }
                s.acquire();
                nLector++;
                lect.release();
                System.out.println("ha ingresado el lector " + lector);
                int nsig=0;
                FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr);
                String l;
                while ((l = bf.readLine()) != null) {
                    String busc[] = l.split(" ");
                    for (int i = 0; i < busc.length; i++) {
                        if (busc[i].equals("!")) {
                            nsig++;
                        }
                    }
                    if (nsig==(nLector-1)){
                        System.out.println(l);
                    }
                }
                s.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public void escritura(){
        try {
            s.acquire();
            System.out.println("Que desea escribir");
            Scanner in = new Scanner(System.in);
            String x = (in.nextLine()+" !");
            FileWriter fw = new FileWriter(f,true);
            BufferedWriter bf = new BufferedWriter(fw);
            bf.write(x);
            bf.newLine();
            bf.flush();
            bf.close();
            s.release();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void comprobacionLinea(){
        try {
            File f = new File("src/prueba.txt");
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String l="";
            int nsig = 0;
            while ((l = bf.readLine()) != null) {
                String busc[] = l.split(" ");
                for (int i = 0; i < busc.length; i++) {
                    if (busc[i].equals("!")) {
                        nsig++;
                    }
                }
            }
            if(nsig == (nLector-1)) {
                faltaEscritura=true;
            }
            else{
                faltaEscritura=false;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
