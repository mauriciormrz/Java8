package StreamPrueba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPrueba {

	private static List<User> users;

	public static void main(String arg[]) {

		setUpUser();
		Stream stream = Stream.of(users);
		users.stream();

		//forEach()
		System.out.println("------------------ForEach-----------------------");
		users.stream().forEach(e -> e.setNombre(e.getNombre() + " Ramírez"));
		imprimirLista();

		//map y Collectors.toList()
		System.out.println("------------------Map----------------------------");
		List<String> ListString = users.stream().map(e -> e.getNombre()).collect(Collectors.toList());
		ListString.stream().forEach(e -> System.out.println(e));
		
		//filter
		System.out.println("------------------Filters-----------------------");
		setUpUser();
		List<User> usersFilter = users.stream()
				.filter(e -> e.getNombre() != "Mauricio")
				.filter(e -> e.getId() <3)
				.collect(Collectors.toList());
		usersFilter.stream().forEach(e -> System.out.println(e.getId() + " " + e.getNombre()));
		
		//findFirst
		System.out.println("------------------Find First---------------------");
		setUpUser();
		User user = users.stream()
				.filter(e -> e.getNombre() == "Mauricio")
				.findFirst()
				.orElse(null);
		System.out.println(user.getId() + " " + user.getNombre());
		
		//flapMap
		System.out.println("------------------Flap Map-----------------------");
		List<List<String>> nombresVariasListas = new ArrayList<List<String>>(
				Arrays.asList(
						new ArrayList<String>(Arrays.asList("Mauricio","Andrea","Mateo")),
						new ArrayList<String>(Arrays.asList("Jota","Fabio"))));
		
		List<String> nombreUnicaLista = nombresVariasListas.stream()
				.flatMap(e -> e.stream())
				.collect(Collectors.toList());
		
		nombreUnicaLista.stream().forEach(e -> System.out.println(e));
		
		//peek
		System.out.println("------------------Peek--------------------------");
		setUpUser();
		List<User> user2 = users.stream()
				.peek(e -> e.setNombre(e.getNombre() + " Ramírez"))
				.collect(Collectors.toList());
		user2.stream().forEach(e -> System.out.println(e.getNombre()));
		
		//count
		System.out.println("------------------Count--------------------------");
		setUpUser();
		long numerFiltrado = users.stream()
				.filter(e -> e.getId()< 3)
				.count();
		System.out.println(numerFiltrado);
		
		//skip y limit
		System.out.println("------------------Skip y Limit--------------------");
		String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		List<String> abcFilter = Arrays.stream(abc)
				.skip(2)
				.limit(4)
				.collect(Collectors.toList());
		
		abcFilter.stream().forEach(e->System.out.println(e));
		
		//sorted
		System.out.println("------------------Sorted--------------------------");
		setUpUser();
		users = users.stream()
			.sorted(Comparator.comparing(User::getNombre))
			.collect(Collectors.toList());
		imprimirLista();
		
		//min y max
		System.out.println("------------------Min y Max------------------------");
		setUpUser();
		User userMin = users.stream()
				.min(Comparator.comparing(User::getId))
				.orElse(null);
		System.out.println(userMin.getId());
		
		User userMax = users.stream()
				.max(Comparator.comparing(User::getId))
				.orElse(null);
		System.out.println(userMax.getId());

		//distinct
		System.out.println("------------------Distinct------------------------");
		String[] abc1 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "a", "c"};
		List<String> abcDistinct = Arrays.stream(abc1)
				.distinct()
				.collect(Collectors.toList());
		abcDistinct.stream().forEach(e ->System.out.println(e));

		//allMatch, anyMatch, noneMatch
		System.out.println("------------------AllMatch, AnyMatch, NoneMatch---");
		List<Integer> listaNumeros = Arrays.asList(100, 300, 900, 5000);
		
		boolean allMatch = listaNumeros.stream().allMatch( e-> e >301);
		System.out.println(allMatch);
		
		boolean anyMatch = listaNumeros.stream().anyMatch( e-> e >301);
		System.out.println(anyMatch);
		
		boolean noneMatch = listaNumeros.stream().noneMatch( e-> e >10000);
		System.out.println(noneMatch);
		
		//sum, average, range
		System.out.println("------------------Sum, Average, Range------------");
		setUpUser();
		double result = users.stream()
				.mapToInt(User::getId)
				.average()
				.orElse(0);
		System.out.println(result);
		
		result = users.stream()
				.mapToInt(User::getId)
				.sum();
		System.out.println(result);
		System.out.println(IntStream.range(0,100).sum());
		
		//reduce
		System.out.println("------------------Reduce-----------------------");
		setUpUser();
		int numero = users.stream()
				.map(User::getId)
				.reduce(0,Integer::sum);
		System.out.println(numero);
		
		//joining
		System.out.println("------------------Joining---------------------");
		setUpUser();
		String names = users.stream()
				.map(User::getNombre)
				.collect(Collectors.joining(" - "))
				.toString();
		System.out.println(names);
		
		//toSet
		System.out.println("------------------toSet---------------------");
		setUpUser();
		Set<String> setNames = users.stream()
				.map(User::getNombre)
				.collect(Collectors.toSet());

		setNames.stream().forEach(e->System.out.println(e));
		
		//summarizingDouble
		System.out.println("------------------summarizingDouble---------");
		setUpUser();
		DoubleSummaryStatistics statistics = users.stream()
				.collect(Collectors.summarizingDouble(User::getId));

		System.out.println(statistics.getAverage() + ", " + statistics.getMax() + ", "
				+ statistics.getMin() + ", " + statistics.getCount() + ", " + statistics.getSum());
		
		DoubleSummaryStatistics statistics1 = users.stream()
				.mapToDouble(User::getId)
				.summaryStatistics();
		System.out.println(statistics1.getAverage() + ", " + statistics1.getMax() + ", "
				+ statistics1.getMin() + ", " + statistics1.getCount() + ", " + statistics1.getSum());
		
		//partitioningBy
		System.out.println("------------------partitioningBy---------");
		setUpUser();
		List<Integer> numeros = Arrays.asList(5,17,34,56,2,3,67,4,98);
		Map<Boolean, List<Integer>> esMayor = numeros.stream()
				.collect(Collectors.partitioningBy(e->e>10));
		esMayor.get(true).stream().forEach(e ->System.out.println(e));
		esMayor.get(false).stream().forEach(e ->System.out.println(e));
		
		//groupingBy
		System.out.println("------------------groupingBy-------------");
		setUpUser();
		Map<Character, List<User>> grupoAlfabetico = users.stream()
				.collect(Collectors.groupingBy(e->new Character(e.getNombre().charAt(0))));
		//grupoAlfabetico.get('A').stream().forEach(e->System.out.println(e.getNombre()));
		grupoAlfabetico.get('M').stream().forEach(e->System.out.println(e.getNombre()));
		//grupoAlfabetico.get('J').stream().forEach(e->System.out.println(e.getNombre()));
		
		//mapping
		System.out.println("------------------mapping---------------");
		setUpUser();
		List<String> personas = users.stream()
				.collect(Collectors.mapping(User::getNombre, Collectors.toList()));
		personas.stream().forEach(e->System.out.println(e));
		
		//map vs mapping 
		System.out.println("------------------map vs mapping--------");
		setUpUser();
		List<String> personas1 = users.stream()
				.map(User::getNombre)
				.collect(Collectors.toList());
		personas.stream().forEach(e->System.out.println(e));
		
		//streamParallel 
		System.out.println("------------------Stream Paralelo--------");
		setUpUser();
		long tiempo1 = System.currentTimeMillis();
		personas.stream().forEach(e->convertirAMayusculas(e));
		long tiempo2 = System.currentTimeMillis();
		System.out.println("Normal: " + (tiempo2-tiempo1));
		
		tiempo1 = System.currentTimeMillis();
		personas.parallelStream().forEach(e->convertirAMayusculas(e));
		tiempo2 = System.currentTimeMillis();
		System.out.println("Paralelo: " + (tiempo2-tiempo1));
	}
	
	private static String convertirAMayusculas(String nombre) {
		
		try {
			Thread.sleep(1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		return nombre.toUpperCase();
	}

	private static void setUpUser() {

		users = new ArrayList<>();
		users.add(new User(1, "Mauricio"));
		users.add(new User(2, "Mateo"));
		users.add(new User(3, "Andrea"));
		users.add(new User(4, "Jota"));
		users.add(new User(5, "Mauricio"));
	}

	private static void imprimirLista() {

		users.stream().forEach(e -> System.out.println(e.getId() + " " + e.getNombre()));
	}
}
