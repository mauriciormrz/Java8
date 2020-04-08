package Referencia;

public class User {
	
	private String nombre;
	
	public User(String nombre) {
		this.nombre = nombre;
	}
	
	public static void referenciaAMetodoEstatico() {
		System.out.println("Probando referencia a Método Estático");
	}
	
	public  void referenciaAMetodoParticular() {
		System.out.println("Probando referencia a Método de Objeto Particular");
	}
	
	public  void mostrarNombre() {
		System.out.println(nombre);
	}

}
