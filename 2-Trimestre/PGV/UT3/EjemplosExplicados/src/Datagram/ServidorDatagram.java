package Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class ServidorDatagram {
	public static void main(String args[]) {
		System.out.println("Arranca el servidor");
		//Declara una varaiable tipo DatagramSocket que se usará para enviar y reciir datos en red.
		DatagramSocket datagramSocket = null;
		try {
			//Crea un socked udp en el puerto para escuchar conexiones entrantes.
			datagramSocket = new DatagramSocket(25556);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		while (datagramSocket != null) {
			try {
				System.out.println("Esperando mensajes.......");
				//Declara un array de bytes con tamaño 4 para almacenar datos recibidos. El tamaño indica que se esperan mensajes de 4 bytes.
				byte[] entrada = new byte[4];
				//Crea un objeto DatagramPacket que se usará para recibir datos. Está vinculado al array entrada.
				DatagramPacket datagrama1 = new DatagramPacket(entrada, 4);
				//Bloquea la ejecución hasta que se reciba un paquete. Una vez recibido, los datos se almacenan en el array entrada.
				datagramSocket.receive(datagrama1);

				String mensaje = new String(datagrama1.getData());
				//Obtiene la dirección IP del cliente que envió el paquete.
				InetAddress dircliente = datagrama1.getAddress();
				int puertocliente = datagrama1.getPort();
				System.out.println("Mensaje recibido desde: " + dircliente
						+ ", puerto " + puertocliente);
				System.out.println("Mensaje: " + mensaje);

				if (mensaje.equals("hora")) {
					System.out.println("Enviando respuesta");

					Date d = new Date(System.currentTimeMillis());
					byte[] salida = d.toString().getBytes();
					DatagramPacket datagrama2 = new DatagramPacket(salida,
							salida.length, dircliente, puertocliente);
					datagramSocket.send(datagrama2);
					System.out.println("Mensaje enviado");
				} else {
					System.out.println("Esa petici�n no la conocemos ;-)");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Fin");
	}
}
