import java.text.DecimalFormat;

public class Voto {
	private int totalEleitores;
	private int votosValidos;
	private int votosBrancos;
	private int votosNulos;

	
	public Voto() {
	}

	private float calcularPercentualVotos(int votos) {
		return ((float) votos / totalEleitores) * 100;
	}

	public float calcularPercVotosValidos() {
        return calcularPercentualVotos(votosValidos);
	}

	public float calcularPercVotosBrancos() {
		return calcularPercentualVotos(votosBrancos);
	}

	public float calcularPercVotosNulos() {
		return calcularPercentualVotos(votosNulos);
	}

	public int getTotalEleitores() {
		return totalEleitores;
	}

	public void setTotalEleitores(int totalEleitores) {
		this.totalEleitores = totalEleitores;
	}

	public int getVotosValidos() {
		return votosValidos;
	}

	public void setVotosValidos(int votosValidos) {
		this.votosValidos = votosValidos;
	}

	public int getVotosBrancos() {
		return votosBrancos;
	}

	public void setVotosBrancos(int votosBrancos) {
		this.votosBrancos = votosBrancos;
	}

	public int getVotosNulos() {
		return votosNulos;
	}

	public void setVotosNulos(int votosNulos) {
		this.votosNulos = votosNulos;
	}

	@Override
	public String toString() {
    	DecimalFormat df = new DecimalFormat("##.#");

		String avaliacaoPercentualDeVotos = "";
		avaliacaoPercentualDeVotos += "% de votos válidos: " + df.format(this.calcularPercVotosValidos()) + "%\n";
		avaliacaoPercentualDeVotos += "% de votos brancos: " + df.format(this.calcularPercVotosBrancos()) + "%\n";
		avaliacaoPercentualDeVotos += "% de votos nulos: " + df.format(this.calcularPercVotosNulos()) + "%\n";
		
		return avaliacaoPercentualDeVotos;
	}
	
	
}