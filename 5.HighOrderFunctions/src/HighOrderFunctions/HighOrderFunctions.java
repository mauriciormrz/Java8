package HighOrderFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HighOrderFunctions implements SumarInterface{

	public static void main(String arg[]) {

		HighOrderFunctions hof = new HighOrderFunctions();
		
		//------------------Función----------------------------//
		System.out.println(hof.suma(2,3));
		
		//------------------Interface--------------------------//
		System.out.println(hof.apply(2,4));
		
		//------------------High Order Functions--------------//
		
		//SumarInterface sumarInterface = new SumarInterface() {
		//	@Override
		//	public int apply(int a, int b) {
		//		return a + b;
		//	}
		//};
		// mejora del codigo anterior con lambda
		
		SumarInterface sumarInterface = (a,b) -> a+b;
		
		System.out.println(hof.sumarHighOrderFun(sumarInterface,2,5));
		
		//------------------Interface Funcional Function------//
		/*
		 * interface Function<T,R>{
		 *      R apply(T t)
		 *  }
		 */
		Function<String, String> convertirAMayusculas = e -> e.toUpperCase();
		hof.imprimirMayuscula(convertirAMayusculas,"mauricio");
		
		//------------------Interface Funcional BiFunction------//
		/*
		 * interface BiFunction<T,U,R>{
		 *      R apply(T t, U u)
		 *  }
		 */
		
		
		//------------------Interface Funcional Predicate------//
		/*
		 * interface Predicate<T>{
		 *      Boolean test(T t)
		 *  }
		 */
		List<Integer> numeros = Arrays.asList(6,23,-5,4,68,-9,-67,46);
		
		BiFunction<
			List<Integer>,
			Predicate<Integer>,
			List<Integer>
		> filtrar;
		
		filtrar = (Lista, predicado) -> Lista.stream()
				.filter(e->predicado.test(e))
				.collect(Collectors.toList());
		System.out.println(filtrar.apply(numeros,e->e>0));
		
		//------------------Interface Funcional Consumer------//
		/*
		 * interface Consumer<T>{
		 *      void accept(T t)
		 *  }
		 */
		List<String> nombres = new ArrayList<>();
		nombres.add("Mauricio");
		nombres.add("Andrea");
		nombres.add("Mateo");
		
		hof.filtrar(nombres, e->System.out.println(e),6);
		
	}
	
	public void filtrar(List<String> lista, Consumer<String> consumer, int maximoCaracteres) {
		lista.stream()
		.filter(logicaPredicado(maximoCaracteres))
		.forEach(consumer);
	}
	
	public Predicate<String> logicaPredicado (int maximoCaracteres){
		return e -> e.length() < maximoCaracteres;
	}
	

	public static int suma(int a, int b) {
		return a + b;
	}

	@Override
	public int apply(int a, int b) {
		return a + b;
	}
	
	public int sumarHighOrderFun(SumarInterface sumar, int a, int b) {
		return sumar.apply(a,b);
	}
	
	public void imprimirMayuscula(Function<String, String> function, String nombre) {
		System.out.println(function.apply(nombre));
	}



}
