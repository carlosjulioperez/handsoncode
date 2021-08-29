package ec.carper.javacore.code;

public class MoverCeros {

	// Mueve todos los ceros al final del arreglo
	static void moverCeros(int arreglo[]) {
		
		// Posición del elemento actual cuyo valor es diferente de cero.
		int posicion = 0;
		
		// Tamaño del arreglo
		int n = arreglo.length;

		// El for no necesita llaves {} para el bloque de código, porque Java, al heredar
		// la sintaxis de C, cuando tienes un solo elemento en un bloque de código, tal como
		// for, loop, if, etc., no necesitas encerrarlo con llaves {}. 
		
		// Recorrer la lista de los elementos y si el elemento cero es encontrado
		for (int i=0; i<n; i++) 
			if (arreglo [i] != 0)
				// incremento de posicion, después de haber asignado el valor
				arreglo [posicion++] = arreglo [i]; 
			
		
		// "posicion" tiene el valor del último indice donde se encontró un cero.
		// Desde ahí en adelante, deben "moverse" los ceros.
		while (posicion < n) 
			arreglo[posicion++] = 0; // Aquí realizo el incremento de la variable y ahorro una línea de código
		
	}
	
	private static void imprimir(int arreglo[]) {
		for (int i = 0; i < arreglo.length; i++)
			System.out.print(arreglo[i] + " ");
	}
	
	public static void main(String[] args) {
		
		int arreglo [] = { 7,10,0,9,11,0,17 };
		
		System.out.println("Arreglo original: ");
		imprimir(arreglo);
		
		// Mover los ceros en al arreglo
		moverCeros(arreglo);

		System.out.println("\n\nArreglo con los ceros al final: ");
		imprimir(arreglo);

	}

	/*  Salida del programa

	Arreglo original: 
	7 10 0 9 11 0 17 
	
	Arreglo con los ceros al final: 
	7 10 9 11 17 0 0 
	 
	 */
}
