public class BancoNacional implements Runnable {
	
	int inyeccionesPermitidas = 4;
	
	public BancoNacional() {
	}

	@Override
	public void run() {
		try {
			for (int i = 1; i <= inyeccionesPermitidas; i++) {
				for (int j = 0; j < PrestamosBancarios.bancos.size(); j++) {
					PrestamosBancarios.semaforo.acquire();
					int inyeccionMonetaria = (int) (Math.random()*5000);
					PrestamosBancarios.bancos.get(j).aumentarFondos(inyeccionMonetaria);
					System.out.println("El banco " + PrestamosBancarios.bancos.get(j).getNombre() + " acaba de recibir una inyección de " + inyeccionMonetaria + "€ y dispone de " + PrestamosBancarios.bancos.get(j).getFondos() + "€");
					PrestamosBancarios.semaforo.release();
					
					//Para ver la inyección monetaria a todos a la vez, mover esta línea fuera del bucle interno, o dejarla dentro para ver las inyecciones individualmente
					Thread.sleep((long) (Math.random()*3000));
				}
				//Tras realizar la inyeccion monetaria por segunda vez se reduce la prioridad al minimo 
				if (i == 1) {
					Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
				}
				
			}
		} catch (InterruptedException e) {  e.printStackTrace(); }
	}
	
}
