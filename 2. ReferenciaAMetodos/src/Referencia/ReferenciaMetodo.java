package Referencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenciaMetodo{

	public static void main(String arg[]) {

		/*
		 Referencia a un 			Class::staticMethod		Math::abs		n -> Math.abs(n)
		 static method
		 */
		Trabajo trabajo = new Trabajo() {
			@Override
			public void accion() {
				User.referenciaAMetodoEstatico();
			}
		};
		Trabajo trabajoL = () -> User.referenciaAMetodoEstatico();
		Trabajo trabajoMR = User::referenciaAMetodoEstatico;
		
		trabajo.accion();
		trabajoL.accion();
		trabajoMR.accion();
	
		
		/*
		 Referencia a un 			instancia::metodoInstancia		s::toString		() -> "string".toString
		 metodo de instancia
		 de un objeto
		 particular
		 */
		User user = new User("Mauricio");
		Trabajo trabajo2 = new Trabajo() {
			@Override
			public void accion() {
				user.referenciaAMetodoParticular();
			}
		};
		Trabajo trabajoL2 = () -> user.referenciaAMetodoParticular();
		Trabajo trabajoMR2 = user::referenciaAMetodoParticular;
		
		trabajo2.accion();
		trabajoL2.accion();
		trabajoMR2.accion();
		
		/*
		 Referencia a un 			Class::metodoInstancia		String::toString		s -> s.toString()
		 metodo de instancia
		 de un objeto
		 arbitrario de
		 un tipo particular
		 */
		TrabajoString trabajoString = new TrabajoString() {
			@Override
			public String accion(String palabra) {
				return palabra.toUpperCase();
			}
		};
		
		TrabajoString trabajoStringL = (palabra) -> palabra.toUpperCase();
		TrabajoString trabajoStringMR = String::toUpperCase;
		
		System.out.println(trabajoString.accion("mauricio-string"));
		System.out.println(trabajoStringL.accion("mauricio-lambda"));
		System.out.println(trabajoStringMR.accion("mauricio-mr"));
		
		List<User> users = new ArrayList<>();
		users.add(new User("Andrea"));
		users.add(new User("Mateo"));
		users.add(new User("Jota"));
		
		users.forEach(nombre -> nombre.mostrarNombre());
		users.forEach(User::mostrarNombre);
		
		
		/*
		 Referencia a un 			Class::new		String::new		() -> new String()
		 constructor
		 */
		IUser user1 = new IUser() {
			@Override
			public User crear(String nombre) {
				return new User(nombre);
			}
		};
		IUser userL = (nombre -> new User(nombre));
		IUser userMR = User::new;

	}

}
