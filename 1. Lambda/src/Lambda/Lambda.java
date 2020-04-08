package Lambda;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.*;

public class Lambda implements PorDefecto {

	private static Stream<String> stream;
	private static Map<String, List<Alumno>> porEscuela1;

	public static void main(String arg[]) {
		/*
		 * () -> "mi nombre es"; (n) -> n * n; (n) -> n==2;
		 */

		MiNombre miNombreAnomima = new MiNombre() {

			@Override
			public String miNombre() {
				return "Mauricio Anonimo";
			}
		};
		System.out.println(miNombreAnomima.miNombre());

		MiNombre miNombreLambda = () -> "Mauricio Lambda";
		System.out.println(miNombreLambda.miNombre());

		Sumar suma = new Sumar() {
			@Override
			public int suma(int a, int b) {
				return a + b;
			}
		};
		System.out.println(suma.suma(2, 3));

		Sumar suma1 = (a, b) -> a + b;
		System.out.println(suma1.suma(2, 3));

		Sumar suma2 = (a, b) -> {
			a = b * b;
			a = a + b;
			System.out.println("Mensaje dentro del lambda");
			return a;
		};
		System.out.println(suma2.suma(2, 3));
	

		Lambda l = new Lambda();
		System.out.println(l.nombrePorDefecto("Mauricio"));

		Stream<String> strings = Stream.of("b", "a", "c");
		// strings.sorted();
		String p = strings.reduce("", String::concat);
		System.out.println(p);

		String p1 = Stream.of("b", "a", "c").sorted().reduce("", String::concat);
		System.out.println(p1);

		stream = Stream.of("1", "b", "c");
		StringBuilder sb = new StringBuilder();
		// stream.forEach(s -> sb.append(s));
		System.out.println(sb);

		/////////////////////codigo en negro////////////////////////////////////////////////////
		List<Alumno> alumnos = new ArrayList<>();
		alumnos.add(new Alumno("Mauricio", "EAFIT"));
		alumnos.add(new Alumno("Jota", "UdeA"));
		alumnos.add(new Alumno("Andrea", "UdeA"));

		Map<String, List<Alumno>> porEscuela = new HashMap<>();

		for (Alumno alumno : alumnos) {
			if (!porEscuela.containsKey(alumno.getEscuela())) {
				porEscuela.put(alumno.getEscuela(), new ArrayList<>());
			}
			porEscuela.get(alumno.getEscuela()).add(alumno);
		}
		imprimir(porEscuela.keySet());

		/////////////////////codigo en rojo////////////////////////////////////////////////////
		porEscuela = alumnos.stream()
				.collect(Collectors.groupingBy(Alumno::getEscuela));
		imprimir(porEscuela.keySet());

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Integer suma11 = list.parallelStream().peek(System.out::println).reduce(0, (a, b) -> a + b);

		String s = list.parallelStream()
				.map(String::valueOf)
				.collect(Collectors.joining(","));

		System.out.println(s);

		String s1 = Stream.of("1","2")
				.reduce("", (a,b)-> a +","+b);

		System.out.println(s1);
		System.out.println("forEachRemaining");
		list.spliterator().forEachRemaining(System.out::println);

	}
	/////////////////////////////////////////////////////////////////////////

	private static void imprimir(Collection<String> col) {

		for (String elemento : col) {
			System.out.println("Elemento:" + elemento);
		}
		System.out.println("");
	}

	@Override
	public void mostrarNombre(String nombre) {
		// TODO Auto-generated method stub

	}

}
