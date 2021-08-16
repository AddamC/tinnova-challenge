public class App {
    public static void main(String[] args) throws Exception {    	
    	Voto voto = new Voto();
    	voto.setTotalEleitores(1000);
    	voto.setVotosValidos(800);
    	voto.setVotosBrancos(150);
    	voto.setVotosNulos(50);

    	System.out.println(voto);
    }
}
