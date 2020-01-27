import java.util.LinkedList;
import java.util.Queue;

public class Exercicios {

	public void buscas(Grafo grafo, int indice) {

		for (Vertice v : grafo.getConjuntoVertices()) {
			v.setCv(false);
			v.setDv(Double.POSITIVE_INFINITY);
			v.setAv(null);
		}
		
		Vertice s = grafo.getConjuntoVertices().get(indice-1);

		s.setCv(true);
		s.setDv(0);
		int level = 0;

		Queue<Vertice> q = new LinkedList<>();
		q.add(s);

		System.out.print(level + ": " + s.getIndice());

		while (!(q.isEmpty())) {
			Vertice u = q.remove();
			for (Vertice v : u.getVizinhos()) {
				if (!(v.isCv())) {
					v.setCv(true);
					v.setDv(u.getDv() + 1);
					if (!(level == (u.getDv() + 1))) {
						level++;
						System.out.print( "\n" + level + ": ");
					}
					v.setAv(u);
					q.add(v);
					System.out.print(v.getIndice() + " ");
				}
			}
		}
		System.out.println();
	}

	public void cicloEuleriano(Grafo grafo) {
		Euleriano eu = new Euleriano();
		eu.hierholzer(grafo);
	}

	public void bellmanFord(Grafo grafo, Vertice s) {
		
		BellmanFord bf = new BellmanFord(grafo, s);

	}

	public void floydWarshall(Grafo grafo) {

		FloydWarshall f = new FloydWarshall();
		double[][] matriz = f.algoritmo(grafo);
		String line = "";

		for(int linha = 0; linha < matriz.length; linha++) {
			line += grafo.getConjuntoVertices().get(linha).getIndice()+":";
			for(int coluna = 0; coluna < matriz.length; coluna++) {
				line += matriz[linha][coluna];
				if(coluna < matriz.length-1) {
					line += ',';
				}
			}
			System.out.println(line);

			line = "";
		}
	}

}
