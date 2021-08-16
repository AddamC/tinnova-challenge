import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
    	System.out.println("Algoritmo para somar números múltiplos de 3 ou 5");
		System.out.print("Informe um valor: ");

    	Scanner scanner = new Scanner(System.in);
		int valor = scanner.nextInt();
    	
    	OperacaoMatematica op = new OperacaoMatematica();
    	int resultadoFatorial = op.calcularSomaMultiplos(valor);
    	
    	System.out.println("\nA soma de todos os múltiplos de 3 ou 5 abaixo de " + valor + " é: " + resultadoFatorial);
    }
}
