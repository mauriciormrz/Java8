package optional;

import java.util.Optional;


public class OptionalPrueba {

	
	public static void main(String arg[]) {
		//probarOptional(null);
		//probarOptional("Mauricio");
		orElseOptional("Mauricio");
		orElseOptional(null);
		
		//orElseThrow("Mauricio");
		//orElseThrow(null);
		
		//isPresent("Mauricio");
		//isPresent(null);

	}
	
	public static void probarOptional(String nombre) {
		System.out.println(nombre.length());
	}
	
	public static void crearOptional() {
		Optional<String> optional = Optional.empty();
		optional.get();
	}
	
	public static void orElseOptional(String nombre) {
		
		Optional<String> optional = Optional.ofNullable(nombre);
		//Optional<String> optional1 = Optional.of(nombre);
		
		System.out.println(optional);
		String nombreOfNullable = optional.orElse("Vacio");
		System.out.println(nombreOfNullable);
		
	}
	
	public static void orElseThrow(String nombre) {
		
		Optional<String> optional = Optional.ofNullable(nombre);
		optional.orElseThrow(NullPointerException::new);
		String nombre1 = optional.get();
		System.out.println(nombre1);
	}
	
	public static void isPresent(String nombre) {
		
		Optional<String> optional = Optional.ofNullable(nombre);
		System.out.println(optional.isPresent());
	}
}
