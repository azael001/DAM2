

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/*
 * ENUNCIADO
 *
 * EL EJERCICIO TRATA SOBRE CLIENTES QUE QUIEREN RETIRAR DINERO DE UN BANCO
 * SE CUENTA CON 5 BANCOS, QUE DISPONDRÁN DE UNOS FONDOS INICIALES ALEATORIOS
 * EL BANCO DE ESPAÑA (BancoNacional, un banco aparte) REALIZARÁ UNA INYECCIÓN ECONÓMICA A LOS BANCOS CADA CIERTO TIEMPO (ALEATORIO)
 * CADA BANCO SOLO PODRÁ RECIBIR UN MÁXIMO DE 4 INYECCIONES CADA UNO, Y AL REALIZAR LA SEGUNDA INYECCIÓN SE REDUCIRÁ SU PRIORIDAD AL MÍNIMO
 * HABRÁN 15 CLIENTES QUE BUSQUEN RETIRAR DINERO DE UN BANCO ALEATORIO
 * SI LA CANTIDAD A RETIRAR ES MAYOR A LOS FONDOS DISPONIBLES DEL BANCO, EL CLIENTE REINTENTARÁ RETIRAR ESE DINERO, DEL MISMO U OTRO BANCO, UN MÁXIMO DE 3 VECES ANTES DE IRSE A CASA
 * SI LA CANTIDAD A RETIRAR ES MENOR A LOS FONDOS DISPONIBLES DEL BANCO, EL CLIENTE RETIRARÁ EL DINERO DE ESE BANCO Y SE IRÁ A CASA FELIZ
 *
 */


public class PrestamosBancarios {

    static Semaphore semaforo = new Semaphore(1);
    static ArrayList<Bancos> bancos = new ArrayList<Bancos>();

    public static void main(String[] args) {
        Thread bancoNacional = new Thread(new BancoNacional(), "Banco Nacional");
        String[] nombresBancos = {"Sabadell", "Bankia", "BBVA", "Santander", "ING"};
        int numClientes = 15;

        for (int i = 0; i < nombresBancos.length; i++) {
            bancos.add(new Bancos(nombresBancos[i]));
        }

        bancoNacional.start();

        for (int i = 1; i <= numClientes; i++) {
            Thread t = new Thread(new Clientes(), "Cliente " + i);
            t.start();
        }

    }

}
