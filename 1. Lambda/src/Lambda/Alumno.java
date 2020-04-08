package Lambda;

public class Alumno {
	
	private String nombre;
	private String escuela;
	
	public Alumno(String nombre, String escuela) {
		
		this.nombre = nombre;
		this.escuela = escuela;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEscuela() {
		return escuela;
	}
	
	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}
	

}
