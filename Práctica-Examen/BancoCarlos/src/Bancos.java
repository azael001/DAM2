public class Bancos {
	
	String nombre;
	Integer fondos = (int) (Math.random() * 5000); //Fondo inicial aleatorio
	
	public Bancos(String nombre) {
		this.nombre = nombre;
	}
	
	public void aumentarFondos(Integer cantidad) {
		this.fondos += cantidad;
	}
	
	public boolean reducirFondos(Integer cantidad) {
		if (cantidad > this.fondos) {
			return false;
		}
		this.fondos -= cantidad;
		return true;
	}
	
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getFondos() {
		return this.fondos;
	}
}
