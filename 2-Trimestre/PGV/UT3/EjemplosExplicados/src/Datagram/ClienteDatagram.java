package Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteDatagram {
	public static void main(String args[]) {
		
		try {
			System.out.println("Creando datagrama cliente");
			//Crea un objeto DatagramSocket para enviar y recibir paquetes de datos. El puerto se asigna automáticamente porque no se especifica.
			DatagramSocket datagramSocket = new DatagramSocket();
			//Obtiene la dirección IP del servidor (en este caso, 192.168.0.23). Es necesario para saber a dónde enviar los datos.
			InetAddress dirservidor = InetAddress.getByName("192.168.0.23");
			String mensaje = new String("hora");
			//Crea un paquete udp
			DatagramPacket datagrama1 = new DatagramPacket(mensaje.getBytes(),
					mensaje.getBytes().length, dirservidor, 25556);
			//envia el paquete datagram1 al servidor
			datagramSocket.send(datagrama1);

			System.out.println("Mensaje enviado a " + dirservidor);

			byte[] respuesta = new byte[100];
			DatagramPacket datagrama2 = new DatagramPacket(respuesta,
					respuesta.length);
			datagramSocket.receive(datagrama2);
			System.out.println("Mensaje recibido: " + new String(respuesta));
			datagramSocket.close();
			System.out.println("el cliente termin�");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
