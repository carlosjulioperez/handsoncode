package ec.carper.code;

public class InvertirBitsEntero {

	private static int invertir(int numero) {
		String binario = "" + Integer.toBinaryString(numero);

		int bina = Integer.parseInt(binario);
		binario = String.format("%08d", bina); // para 8 bits
		
		String inverso = new StringBuilder(binario).reverse().toString();

		System.out.println("Valor binario: " + binario); 
		System.out.println("Valor binario invertido: " + inverso);
		
		return Integer.parseInt(inverso,2);
	}

    
	public static void main(String[] args) {
		int numero = 14;
		
		System.out.println("Valor decimal original: " + numero);
        System.out.println("Valor nuevo obtenido: " + invertir(numero));
		
	}
	
	/*
	 * Valor decimal original: 14
	 * Valor binario: 00001110
	 * Valor binario invertido: 01110000
	 * Valor nuevo obtenido: 112
	 */
}
