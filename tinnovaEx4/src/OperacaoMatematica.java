public class OperacaoMatematica {
	public int calcularSomaMultiplos(int x) {
		int resultado = 0;
		for (int i = 1; i < x; i++) {
			if (i % 3 == 0 || i % 5 == 0) {
				resultado += i;
			}
		}
		return resultado;
	}
}