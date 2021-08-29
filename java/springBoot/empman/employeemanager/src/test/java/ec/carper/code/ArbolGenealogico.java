package ec.carper.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArbolGenealogico {

	public static void main(String[] args) {
		// Creamos el registro del árbol en una lista
		List <Tabla> lista = new ArrayList<Tabla>();
		lista.add( new Tabla(1, "Carlos", 0) );
		lista.add( new Tabla(2, "Juan", 0) );
		lista.add( new Tabla(3, "María", 1) );
		lista.add( new Tabla(4, "Carlos 1", 3) );	        
		lista.add( new Tabla(5, "Sofía", 4) );
		lista.add( new Tabla(6, "Hernán", 1) );
		lista.add( new Tabla(7, "Wilson", 2) );
		lista.add( new Tabla(8, "Diana", 2) );
		lista.add( new Tabla(9, "Mateo", 7) );
		lista.add( new Tabla(10, "Efraín", 6) );

//		System.out.println("Estructura de árbol genealógico...");
//		lista.forEach(e -> System.out.println(e.toString()));
		
		System.out.println("\nEstructura de árbol genealógico en orden descendente...");
		List<Tabla> l = lista.stream().sorted( Comparator.comparing(Tabla::getPadre).reversed() ).collect(Collectors.toList());
		l.forEach(e -> System.out.println(e.toString()));
		
		System.out.println("\nEstructura de árbol genealógico en orden ascendente...");
		l = lista.stream().sorted( Comparator.comparing(Tabla::getPadre) ).collect(Collectors.toList());
		l.forEach(e -> System.out.println(e.toString()));
	}
}

// Clase para almacenar la estructura del árbol genealógico
class Tabla{
	private int registro;
	private String nombre;
	private int padre;
	
	public Tabla(int registro, String nombre, int padre) {
		this.registro = registro;
		this.nombre = nombre;
		this.padre = padre;
	}
	public int getRegistro() {
		return registro;
	}
	public String getNombre() {
		return nombre;
	}
	public int getPadre() {
		return padre;
	}
	@Override
	public String toString() {
		return "Tabla [registro=" + registro + ", nombre=" + nombre + ", padre=" + padre + "]";
	}
}

/*  Salida del programa

	Estructura de árbol genealógico en orden descendente...
	Tabla [registro=9, nombre=Mateo, padre=7]
	Tabla [registro=10, nombre=Efraín, padre=6]
	Tabla [registro=5, nombre=Sofía, padre=4]
	Tabla [registro=4, nombre=Carlos 1, padre=3]
	Tabla [registro=7, nombre=Wilson, padre=2]
	Tabla [registro=8, nombre=Diana, padre=2]
	Tabla [registro=3, nombre=María, padre=1]
	Tabla [registro=6, nombre=Hernán, padre=1]
	Tabla [registro=1, nombre=Carlos, padre=0]
	Tabla [registro=2, nombre=Juan, padre=0]
	
	Estructura de árbol genealógico en orden ascendente...
	Tabla [registro=1, nombre=Carlos, padre=0]
	Tabla [registro=2, nombre=Juan, padre=0]
	Tabla [registro=3, nombre=María, padre=1]
	Tabla [registro=6, nombre=Hernán, padre=1]
	Tabla [registro=7, nombre=Wilson, padre=2]
	Tabla [registro=8, nombre=Diana, padre=2]
	Tabla [registro=4, nombre=Carlos 1, padre=3]
	Tabla [registro=5, nombre=Sofía, padre=4]
	Tabla [registro=10, nombre=Efraín, padre=6]
	Tabla [registro=9, nombre=Mateo, padre=7]
 */

