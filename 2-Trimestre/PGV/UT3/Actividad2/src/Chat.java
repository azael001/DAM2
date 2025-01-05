import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Semaphore;

public class Chat extends Thread{
	private String name;
	Semaphore s = new Semaphore(1);
	public Chat(String name, Semaphore s){
		this.name=name;
		this.s=s;
	}

	@Override
	public void run() {
		try {
			String direccion = "225.255.255.0";
			int puerto = 6999;
			MulticastSocket m = new MulticastSocket(puerto);
			InetAddress dir = InetAddress.getByName(direccion);
			InetSocketAddress grupo = new InetSocketAddress(dir, puerto);
			NetworkInterface netIf = NetworkInterface.getByInetAddress(dir);

			m.joinGroup(grupo, netIf);

			String cad = "";
			String mens = "";
			try {
				s.acquire();
				System.out.println(name + " se ha unido al grupo");
				for (int i = 0; i <= 3; i++) {
					if (i == 0) {

						cad = "Hola soy " + name;
					} else if (i == 1) {
						cad = "La hora es " + LocalTime.now();
					} else if (i == 2) {
						cad = "El día es " + LocalDateTime.now();
					} else {
						cad = "mi nombre es " + name + " y no escribire mas";

					}
					DatagramPacket paqueteS = new DatagramPacket(cad.getBytes(), cad.length(), grupo.getAddress(), puerto);
					m.send(paqueteS);
				}
				s.release();
			} catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (!mens.trim().equals("/")) {
				byte[] buf = new byte[1000];
				DatagramPacket paquete = new DatagramPacket(buf, buf.length);
				m.receive(paquete);
				mens = new String(paquete.getData());
				System.out.println("Soy " + name + " y he recibido el mensaje: " + mens.trim());
			}
			m.leaveGroup(grupo, netIf);
			m.close();
			System.out.println("Socket cerrado");
		} catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
