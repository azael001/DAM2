package SocketStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocketStream {
	public static void main(String args[]) throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Hola, escribe tu nombre: ");
		String myname=sc.nextLine();
		String[] vecmens = { "Hi 1", "Simulo el 2", "exit" };
		System.out.println("Creando nuevo socket cliente");
		//Crea un objeto Socket sin conectarlo aún. Este objeto se usará para establecer la conexión con el servidor.
		Socket clientSocket = new Socket();
		System.out.println("Estableciendo la conexion");
		//Define la dirección del servidor. En este caso, se utiliza localhost (la misma máquina) y el puerto 5555.
		InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
		//stablece la conexión con el servidor en la dirección y puerto especificados.
		clientSocket.connect(addr);
		//Obtiene el flujo de entrada del socket para recibir datos del servidor. Aunque no se utiliza en este programa, se incluye para posibles lecturas.
		InputStream is = clientSocket.getInputStream();
		//Obtiene el flujo de salida del socket para enviar datos al servidor.
		OutputStream os = clientSocket.getOutputStream();

		for (int i = 0; i < vecmens.length; i++) {
			System.out.println("Enviando mensaje " + i);
			os.write((myname+"=> "+vecmens[i]).getBytes());
			System.out.println("Mensaje enviado");
		}
		
		System.out.println("Escribe Q para salir");
		while (!(sc.nextLine()).equalsIgnoreCase("Q")) {};
		clientSocket.close();
	}
}
