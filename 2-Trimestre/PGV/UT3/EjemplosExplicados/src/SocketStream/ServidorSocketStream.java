package SocketStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class ServidorSocketStream {
	public static void main(String args[]) throws IOException {

		System.out.println("Creando socket del servidor");
		//Crea un socket del servidor (ServerSocket) que escucha conexiones en el puerto 5555.
		ServerSocket serverSocket = new ServerSocket(5555);
		/*Nota: En el código comentado, se muestran las instrucciones para realizar un
		 bind explícito a una dirección específica (localhost), pero aquí se usa una
		 creación directa que ya realiza el bind automáticamente.*/
	/*
		ServerSocket serverSocket = new ServerSocket();

		System.out.println("Realizando el bind");
		InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
		serverSocket.bind(addr);
*/
		//Declara una variable booleana para controlar si el servidor debe seguir procesando mensajes.
		boolean seguir = true;
		System.out.println("Acepta conexiones");
		//El servidor se bloquea aquí hasta que un cliente se conecta.
		// Una vez que eso sucede, se crea un objeto Socket (newSocket) para gestionar la comunicación con el cliente.
		Socket newSocket = serverSocket.accept();

		System.out.println("Conexion recibida");
		//Obtiene el flujo de entrada del socket del cliente, a través del cual se recibirán los mensajes.
		InputStream is = newSocket.getInputStream();

		
		while (seguir) {
			byte[] mensaje = new byte[25];

			is.read(mensaje);
			String smens = (new String(mensaje)).trim();

			if (!smens.isEmpty()){

				System.out.println("Mensaje recibido: " + smens);
				if (smens.equals("exit"))
				seguir = false;
			}
		}
		System.out.println("Cerramos el socket que escucha al cliente");
		newSocket.close();

		System.out.println("Y cerrando el socket del servidor");
		serverSocket.close();
	}
}
