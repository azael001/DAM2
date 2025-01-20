public class Clientes implements Runnable {
	
	int solicitudesPermitidas = 3;
	
	public Clientes() {
	}

	@Override
	public void run() {
		try {
			for (int i = 1; i <= solicitudesPermitidas; i++) {
				Thread.sleep((long) (Math.random()*3000 + 1000));
				PrestamosBancarios.semaforo.acquire();
				int banco = getBancosIndex();
				int prestamo = (int) (Math.random() * 5000);
				if (!PrestamosBancarios.bancos.get(banco).reducirFondos(prestamo)) {
					System.out.println("El " + Thread.currentThread().getName() + " no puede disponer del préstamo de " + PrestamosBancarios.bancos.get(banco).getNombre() + " (pidió " + prestamo + " y solo hay fondos hasta " + PrestamosBancarios.bancos.get(banco).getFondos() + "€)");
					if (i == solicitudesPermitidas) {
						//Este SLEEP es para mostrar el mensaje de intentos fallidos adecuadamente y no se adelante la impresión del mensaje
						Thread.sleep(100);
						System.err.println("El " + Thread.currentThread().getName() + " ha intentado solicitar un préstamo " + solicitudesPermitidas + " veces sin éxito y se vuelve a casa enfadado.");
					}
					PrestamosBancarios.semaforo.release();
					continue;
				} else {
					System.out.println("El " + Thread.currentThread().getName() + " tiene un préstamo de " + prestamo + " de " + PrestamosBancarios.bancos.get(banco).getNombre() + " y al banco le quedan " + PrestamosBancarios.bancos.get(banco).getFondos() + "€");
					PrestamosBancarios.semaforo.release();
					return;
				}
			}
			} catch (InterruptedException e) {  e.printStackTrace(); }
		
	}
	
	//Estupidez como un templo. Solo elige el índice de lista de un banco aleatorio de todos los disponibles
	private int getBancosIndex() {
		int index = 0;
		for (int i = 1; i < PrestamosBancarios.bancos.size(); i++) {
			if (((int)(Math.random()*10)) % 2 == 0) {
				index++;
			}
		}
		return index;
	}
	
	
	
}
