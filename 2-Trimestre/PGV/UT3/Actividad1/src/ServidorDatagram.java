import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class ServidorDatagram {
	public static void main(String args[]) {
		System.out.println("Arranca el servidor");
		DatagramSocket datagramSocket = null;
		try {
			datagramSocket = new DatagramSocket(5678);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		while (datagramSocket != null) {
			try {
				System.out.println("Esperando mensajes.......");
				byte[] entrada = new byte[100];
				DatagramPacket datagrama1 = new DatagramPacket(entrada, entrada.length);
				datagramSocket.receive(datagrama1);

				String mensaje = new String(datagrama1.getData(),0,datagrama1.getLength());
				InetAddress dircliente = datagrama1.getAddress();
				int puertocliente = datagrama1.getPort();
				System.out.println("Mensaje recibido desde: " + dircliente
						+ ", puerto " + puertocliente);
				System.out.println("Mensaje: " + mensaje);
				String[] msg = mensaje.split(":");
				if (msg[0].equals("Nom")) {
					System.out.println("Enviando respuesta");
					String d = "Hola " + msg[1];
					byte[] salida = d.toString().getBytes();
					DatagramPacket datagrama2 = new DatagramPacket(salida,
							salida.length, dircliente, puertocliente);
					datagramSocket.send(datagrama2);
					System.out.println("Mensaje enviado");
				}
				else if(msg[0].equals("ECO")){
						System.out.println("Enviando respuesta");
						String d = "Linea OK, " + msg[1];
						byte[] salida = d.toString().getBytes();
						DatagramPacket datagrama2 = new DatagramPacket(salida,
								salida.length, dircliente, puertocliente);
						datagramSocket.send(datagrama2);
						System.out.println("Mensaje enviado");
				}
				else if(mensaje.equals("Fin:")){
					System.out.println("El cliente " + dircliente + "Se ha desconectado " + " A las " + LocalTime.now());
				}
				else {
					System.out.println("Enviando respuesta");
					String d = "No conocemos ese mensaje";
					byte[] salida = d.toString().getBytes();
					DatagramPacket datagrama2 = new DatagramPacket(salida,
							salida.length, dircliente, puertocliente);
					datagramSocket.send(datagrama2);
					System.out.println("Mensaje enviado");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Fin");
	}
}