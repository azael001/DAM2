import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //En esta parte del ejercicio escribiremos en un fichero
        File equipoFutbol = new File("./src/datosEquipos.txt");
        Equipos tel = new Equipos(100, "Playa del Hombre", "Messi", "555666777", "Telde");
        Equipos estre = new Equipos(101, "Estrella Roja", "Pepito Rojo", "666888777", "Santa Brígida");
        Equipos siete = new Equipos(202, "Siete Palmas", "Manuel Feo", "123123123", "Las Palmas de GC");
        ArrayList<Equipos> arrayEquipos = new ArrayList<Equipos>();
        arrayEquipos.add(tel);
        arrayEquipos.add(estre);
        arrayEquipos.add(siete);
        try {
            FileWriter fw = new FileWriter(equipoFutbol);
            BufferedWriter bf = new BufferedWriter(fw);
            for (Equipos equipo : arrayEquipos) {
                bf.write(equipo.getnClub() + "##" + equipo.getPresident() + "##" +
                        equipo.getNomClub() + "##" + equipo.getTel() + "##" + equipo.getLocal());
                bf.newLine();
                bf.flush();
            }
            bf.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File f = new File("./src/datosEquipos.dat");
        try {
            RandomAccessFile alea = new RandomAccessFile(f, "rw");
            //En esta parte del ejercicio estamos leyendo el fichero y poniendolo en un array
            try {
                FileReader fr = new FileReader(equipoFutbol);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                String arrayLinea[] = new String[3];
                int arrayNumero = 0;


                while ((linea = br.readLine()) != null) {
                    arrayLinea[arrayNumero] = linea;
                    arrayNumero++;
                }
                // Guardamos las variables individuales leidas en arrays para meterlas dentro del Random
                int nClub[] = new int[3];
                String president[] = new String[3];
                String nomClub[] = new String[3];
                String telClub[] = new String[3];
                String local[] = new String[3];
                for (int o = 0; o < arrayLinea.length; o++) {
                    String partes[] = arrayLinea[o].split("##");
                    nClub[o] = Integer.parseInt(partes[0]);
                    president[o] = partes[1];
                    nomClub[o] = partes[2];
                    telClub[o] = partes[3];
                    local[o] = partes[4];
                }
                //Escribimos en el fichero random
                for (int u = 0; u < nClub.length; u++) {
                    // El tamaño del int será 4 Bits
                    alea.writeInt(nClub[u]);
                    StringBuffer bufferPresident = new StringBuffer(president[u]);
                    bufferPresident.setLength(15);
                    //El tamaño del nombre del presidente medirá 15- es decir 30 bits en total 34
                    for (int j = president[u].length(); j < 15; j++) {
                        bufferPresident.setCharAt(j, ' ');
                    }
                    alea.writeChars(bufferPresident.toString());
                    StringBuffer bufferTel = new StringBuffer(telClub[u]);
                    StringBuffer bufferNomClub = new StringBuffer(nomClub[u]);
                    bufferNomClub.setLength(25);
                    //El tamaño del nombre del club medirá 25 - es decir 50 bits en total 84
                    for (int c = nomClub[u].length(); c < 25; c++) {
                        bufferNomClub.setCharAt(c, ' ');
                    }
                    alea.writeChars(bufferNomClub.toString());
                    bufferTel.setLength(9);
                    // El tamaño del telefono medirá 9 - es decir 18 bits en total 102
                    for (int t = telClub[u].length(); t < 9; t++) {
                        bufferTel.setCharAt(t, ' ');
                    }
                    alea.writeChars(bufferTel.toString());
                    StringBuffer bufferLocal = new StringBuffer(local[u]);
                    bufferLocal.setLength(20);
                    //El tamaño de la localidad medirá 20 - es decir 40 bits en total 142
                    for (int l = local[u].length(); l < 20; l++) {
                        bufferLocal.setCharAt(l, ' ');
                    }
                    alea.writeChars(bufferLocal.toString());
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


            cambiarNumero();
            leerNúmero();
        leerEquipos();
        leerEquiposObj();
        leerEquiposAsc();
          ObjToJason();
        leerJason();
    }

    //métodods
    //Este método pasará por consola el número del club y el nuevo número de telefono a asignar y te lo cambiará en el archivo
    private static void cambiarNumero() {
        File fr = new File("./src/datosEquipos.dat");
        System.out.println("Dime el número del club al que quieres cambiar el número");
        Scanner in = new Scanner(System.in);
        int nuClub = in.nextInt();
        System.out.println("Dime el número nuevo que quieras que escriba");
        Scanner input = new Scanner(System.in);
        String numeroNuevo = input.nextLine();
        char ctel[] = new char[9], auxx;

        while (true) {
            if (numeroNuevo.length() != 9) {
                System.out.println("El número tiene que ser de 9 carácteres, Introdúzcalo de nuevo por favor");
                numeroNuevo = input.nextLine();
            } else {
                try {
                    Integer.parseInt(numeroNuevo);
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("por favor introduzca solo números, inténtelo de nuevo");
                    numeroNuevo = input.nextLine();
                }


            }

        }

        try {
            RandomAccessFile aleat = new RandomAccessFile(fr, "rw");
            int pos = 0;
            while (true) {
                aleat.seek(pos);
                int a = aleat.readInt();
                if (nuClub == a) {
                    aleat.seek(pos + 4);
                    aleat.writeChars(numeroNuevo);
                    aleat.seek(pos + 4);
                    for (int i = 0; i < ctel.length; i++) {
                        auxx = aleat.readChar();
                        ctel[i] = auxx;
                    }
                    String vtel = new String(ctel);
                    System.out.println("El nuevo número será " + vtel);
                    break;
                }
                pos = pos + 142;
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("No existe el Equipo");
        }
    }

    //Este método simplemente te pasa por consola el número asignado a el presidente Pepito Rojo
    private static void leerNúmero() {
        File fr = new File("./src/datosEquipos.dat");
        //Le estamos pasando el presidente por un String y nos sale directamente el número
        String nombrePresident = "Pepito Rojo";
        char cPresident[] = new char[15], aux;
        char ctel[] = new char[9], auxx;

        try {
            RandomAccessFile aleat = new RandomAccessFile(fr, "rw");
            int pos = 4;
            while (true) {
                aleat.seek(pos);
                for (int i = 0; i < 15; i++) {
                    aux = aleat.readChar();
                    cPresident[i] = aux;
                }
                String president = new String(cPresident);
                if (nombrePresident.equals(president.trim())) {
                    aleat.seek(pos + 80);
                    for (int i = 0; i < ctel.length; i++) {
                        auxx = aleat.readChar();
                        ctel[i] = auxx;
                    }
                    String telefonoPresi = new String(ctel);
                    System.out.println("El telefono del presidente del tercer equipo es " + telefonoPresi);
                    break;

                }
                pos = pos + 142;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("No existe el Presidente");
        }
    }

    //Este método lo utilizaremos para leer el archivo dat y exportamos en obj
    private static void leerEquipos() {
        File fr = new File("./src/datosEquipos.dat");
        ArrayList<ObjEquipos> arrayObj = new ArrayList<>();

        try {
            RandomAccessFile aleat = new RandomAccessFile(fr, "r");
            int id, pos = 0;
            char cPresident[] = new char[15], auxP;
            char cNomClub[] = new char[25], auxN;
            char cTelClub[] = new char[9], auxT;
            char cLocal[] = new char[20], auxL;
            int numArray = 0;


            while (true) {
                aleat.seek(pos);
                id = aleat.readInt();
                for (int i = 0; i < cPresident.length; i++) {
                    auxP = aleat.readChar();
                    cPresident[i] = auxP;
                }
                String vPresident = new String(cPresident).trim();

                for (int i = 0; i < cNomClub.length; i++) {
                    auxN = aleat.readChar();
                    cNomClub[i] = auxN;
                }
                String vClub = new String(cNomClub).trim();
                for (int i = 0; i < cTelClub.length; i++) {
                    auxT = aleat.readChar();
                    cTelClub[i] = auxT;
                }
                String vTelClub = new String(cTelClub).trim();

                for (int i = 0; i < cLocal.length; i++) {
                    auxL = aleat.readChar();
                    cLocal[i] = auxL;
                }
                String vLocal = new String(cLocal).trim();

                ObjEquipos equipoObj = new ObjEquipos(id, vPresident, vClub, vTelClub, vLocal);
                arrayObj.add(equipoObj);


                pos = pos + 142;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {

        }
        File fl = new File("./src/datosEquipos.obj");
        try {
            FileOutputStream fo = new FileOutputStream(fl);
            ObjectOutputStream oj = new ObjectOutputStream(fo);

            oj.writeObject(arrayObj);
            oj.flush();
            oj.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Este método leera el objeto y escribe en Equipos.asc
    private static void leerEquiposObj() {
        File fl = new File("./src/datosEquipos.obj");
        ArrayList<ObjEquipos> equipos = new ArrayList<>();
        int nClub[]= new int[3];
        String cPresident[] = new String[3];
        String cNomClub[] = new String[3];
        String cTelClub[] = new String[3];
        String cLocal[] = new String[3];
        int numV=0;
        try {
            FileInputStream fi = new FileInputStream(fl);
            ObjectInputStream obi = new ObjectInputStream(fi);
            equipos = (ArrayList<ObjEquipos>) obi.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (ObjEquipos e : equipos) {
            System.out.println(e);
            nClub[numV]=e.getnClub();
            cPresident[numV] =e.getPresident();
            cNomClub[numV] = e.getNomClub();
            cTelClub[numV] = e.getTel();
            cLocal[numV] = e.getLocal();
            numV++;
        }
        try {
            File file = new File("./src/Equipos.asc");
            FileWriter fw = new FileWriter(file);
            for(int i = 0; i<nClub.length; i++) {
                fw.write(nClub[i] + "," + cPresident[i]+ ","+ cNomClub[i] + ","+ cTelClub[i]+ ","+ cLocal[i] + "\n");

                fw.flush();
            }
            fw.close();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
    //Este método como su nombre indica leerá archivos asci
    private static void leerEquiposAsc() {
        File fl = new File("./src/Equipos.asc");
        try {
            FileReader fr = new FileReader(fl);
            BufferedReader bf = new BufferedReader(fr);
            String linea;
            String numero[]=new String[5];
            int numero200;
            String numeroString;
            while((linea =bf.readLine())!=null){

                   String [] partes = linea.split(",");
                   numero200= Integer.parseInt(partes[0]);
                   if ((numero200 > 200) && (numero200<300)) {
                       System.out.println(linea);
                   }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //Este será el último apartado leerá todos los objetos y escribirá un  jason
    private static void ObjToJason(){
        File fl = new File("./src/datosEquipos.obj");
        ArrayList<ObjEquipos> equipos = new ArrayList<>();
        Gson gson = new Gson();
        String json ;
        try {
            FileInputStream fi = new FileInputStream(fl);
            ObjectInputStream obi = new ObjectInputStream(fi);
            equipos = (ArrayList<ObjEquipos>) obi.readObject();
            json = gson.toJson(equipos);
            try{
                File f = new File("./src/Equipos.json");
                FileWriter fw =new FileWriter(f);
                fw.write(json);
                fw.flush();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }
    private static void leerJason(){
        File fl = new File("./src/Equipos.json");
        try{
            FileReader fr = new FileReader(fl);
            BufferedReader bf = new BufferedReader(fr);

            String linea;
            while((linea=bf.readLine())!=null){
                System.out.println(linea);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}









