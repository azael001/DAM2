import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;

public class ClienteDatagramD extends Thread {
	private String name;
	public ClienteDatagramD(String name){
		this.name=name;
	}
	@Override
	public void run() {
		try {
			System.out.println("Creando datagrama cliente");
			System.out.println("El nombre del hilo es " + name + "y la hora de conexión es " + LocalTime.now());
			DatagramSocket datagramSocket = new DatagramSocket();

			InetAddress dirservidor = InetAddress.getByName("192.168.0.23");

			String mensaje = new String("ECO:Esto es una prueba");
			DatagramPacket datagrama1 = new DatagramPacket(mensaje.getBytes(),
					mensaje.getBytes().length, dirservidor, 5678);
			datagramSocket.send(datagrama1);

			System.out.println("Mensaje enviado a " + dirservidor);

			byte[] respuesta = new byte[100];
			DatagramPacket datagrama2 = new DatagramPacket(respuesta,
					respuesta.length);
			datagramSocket.receive(datagrama2);

			System.out.println("Mensaje recibido: " + new String(respuesta,0,datagrama2.getLength()));
			datagramSocket.close();
			System.out.println("el cliente termino");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

		


}
