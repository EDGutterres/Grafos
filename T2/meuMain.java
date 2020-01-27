import java.io.File;
import java.io.IOException;

public class meuMain {

	public static void main(String[] args) throws IOException {

		File file = new File("C:\\Users\\edgut\\OneDrive\\Documentos\\Grafos\\T1\\dirigido1.net");
        Grafo grafo = new Grafo(file);
		Exercicios exs = new Exercicios();

		if (grafo.isDirigido()) {

			System.out.println("Ordenacao Topologica:");
			exs.ordenacaoTopologica(grafo);
			
			System.out.println("Componentes Fortemente Conexos:");
			exs.fc(grafo);
		} else {
			System.out.println("Arvore geradora minima utilizando Kruskal:");
			exs.kruskal(grafo);
		}

	}

}
