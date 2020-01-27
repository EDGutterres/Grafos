import java.io.File;
import java.io.IOException;

public class meuMain {

	public static void main(String[] args) throws IOException {

		File file = new File("C:\\Users\\edgut\\OneDrive\\Documentos\\Grafos\\T1\\ContemCicloEuleriano.net");
        Grafo grafo = new Grafo(file);
		Exercicios exs = new Exercicios();
		Vertice v = grafo.getConjuntoVertices().get(0);
		Vertice u = grafo.getConjuntoVertices().get(1);
		Aresta a = grafo.getConjuntoArestas().get(0);
		String vizinhos = "Vizinhos: ";

		System.out.println("Grafo inserido.");
		System.out.println("Quantidade de vertices: " + grafo.getNumeroVertices());
		System.out.println("Quantidade de arestas: " + grafo.getNumeroArestas());
		System.out.println("Grau do vertice " + v.getIndice() + ": " + v.getGrau());
		System.out.println("Rotulo do vertice " + v.getIndice() + ": " + v.getRotulo());
		for (Vertice vAux: v.getVizinhos()) {
			vizinhos += (vAux.getIndice() + ", ");
		}
		vizinhos = vizinhos.substring(0, vizinhos.length()-2);
		System.out.println(vizinhos);
		System.out.println("Ha aresta entre " + v.getIndice() + " e " + u.getIndice() + "? Resposta: " + grafo.haAresta(v, u));
		System.out.println("Peso da aresta que conecta " + a.getVerticeU().getIndice() + " e " + a.getVerticeV().getIndice() + ": " + a.getPeso());
		System.out.println("Busca em largura a partir do vertice " + v.getIndice() + ":");
		exs.buscas(grafo, v.getIndice());
		System.out.println("Ciclo Euleriano do grafo:");
		exs.cicloEuleriano(grafo);
		System.out.println("Bellman-Ford a partir do vertice " + v.getIndice() + ":");
		exs.bellmanFord(grafo, v);
		System.out.println("Floyd-Warshall do grafo:");
		exs.floydWarshall(grafo);

	}

}
