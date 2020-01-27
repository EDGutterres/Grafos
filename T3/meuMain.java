import java.io.File;
import java.io.IOException;

public class meuMain {

	public static void main(String[] args) throws IOException {

		File gfmfile = new File("C:\\Users\\edgut\\OneDrive\\Documentos\\Grafos\\T3_Eduardo_Daniela\\db128.gr");
		GrafoFluxoMaximo gfm = new GrafoFluxoMaximo(gfmfile);

		File gbfile = new File("C:\\Users\\edgut\\OneDrive\\Documentos\\Grafos\\T3_Eduardo_Daniela\\pequeno.gr");
		GrafoBipartido gb = new GrafoBipartido(gbfile);
		
		File file = new File("C:\\Users\\edgut\\OneDrive\\Documentos\\Grafos\\T3_Eduardo_Daniela\\adjnoun.net");
		Grafo g = new Grafo(file);
		
		System.out.println("Questao 1:");
		EdmondsKarp ek = new EdmondsKarp(gfm);
		System.out.println("Questao 2:");
		HopcroftKarp hk = new HopcroftKarp(gb);
		System.out.println("Questao 3:");
		Coloracao c = new Coloracao(g);
		

	}

}
