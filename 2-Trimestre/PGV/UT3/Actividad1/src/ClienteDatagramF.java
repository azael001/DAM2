import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;

public class ClienteDatagramF extends Thread {
	private String name;
	public ClienteDatagramF(String name){
		this.name=name;
	}
	@Override
	public void run() {
		try {
			System.out.println("Creando datagrama cliente");
			System.out.println("El nombre del hilo es " + name + "y la hora de conexión es " + LocalTime.now());

			DatagramSocket datagramSocket = new DatagramSocket();

			InetAddress dirservidor = InetAddress.getByName("192.168.0.23");

			String mensajeNombre = "Name:" + name;
			DatagramPacket datagramaNombre = new DatagramPacket(mensajeNombre.getBytes(),
					mensajeNombre.getBytes().length, dirservidor, 5678);
			datagramSocket.send(datagramaNombre);
			System.out.println("Nombre enviado a " + dirservidor);

			String mensaje = new String("Fin:");
			DatagramPacket datagrama1 = new DatagramPacket(mensaje.getBytes(),
					mensaje.getBytes().length, dirservidor, 5678);
			datagramSocket.send(datagrama1);

			System.out.println("Mensaje enviado a " + dirservidor);
			datagramSocket.close();
			System.out.println("el cliente termino");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
