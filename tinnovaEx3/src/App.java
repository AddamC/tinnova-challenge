import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
    	System.out.println("Cálculo de fatorial");
		System.out.print("Informe um valor: ");

    	Scanner scanner = new Scanner(System.in);
		int valor = scanner.nextInt();
    	
    	OperacaoMatematica op = new OperacaoMatematica();
    	int resultadoFatorial = op.calcularFatorial(valor);
    	
    	System.out.println("\t" + valor + "! = " + resultadoFatorial);
    }
}
