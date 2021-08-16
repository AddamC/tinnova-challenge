public class OperacaoMatematica {
	public int calcularFatorial(int n) {
		int resultado = 1;
		if (n > 0) {
			resultado = n * calcularFatorial(n-1);
		}
		return resultado;
	}
}