import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Kruskal {
	
	public Kruskal(Grafo grafo){
		
		int vertices = grafo.getNumeroVertices();

		List<Aresta> conjuntoArestas = grafo.getConjuntoArestas();
		
		PriorityQueue<Aresta> pq = new PriorityQueue<>(conjuntoArestas.size(), Comparator.comparingDouble(o -> o.peso));

		for (int i = 0; i <conjuntoArestas.size() ; i++) {
			pq.add(conjuntoArestas.get(i));
		}

		int [] parent = new int[vertices];

		formarSet(parent, vertices);

		ArrayList<Aresta> listaArestas = new ArrayList<>();

		int index = 0;
		while(index<vertices-1){
			Aresta edge = pq.remove();
			int x_set = find(parent, edge.verticeU.indice-1);
			int y_set = find(parent, edge.verticeV.indice-1);

			if(x_set!=y_set){
				listaArestas.add(edge);
				index++;
				unir(parent,x_set,y_set);
			}
		}
		imprime(listaArestas);
	}

	public void formarSet(int [] parent, int vertices){
		for (int i = 0; i < vertices ; i++) {
			parent[i] = i;
		}
	}

	public int find(int [] parent, int vertex){

		if(parent[vertex]!=vertex)
			return find(parent, parent[vertex]);;
			return vertex;
	}

	public void unir(int [] parent, int x, int y){
		int x_set_parent = find(parent, x);
		int y_set_parent = find(parent, y);
		parent[y_set_parent] = x_set_parent;
	}

	public void imprime(ArrayList<Aresta> listaArestas) {
		
		double somapeso = 0;
		String arvore = "";
		for (int i = 0; i <listaArestas.size() ; i++) {
			Aresta ar = listaArestas.get(i);
			somapeso += ar.getPeso();
			if(i == 0) {
				arvore += ar.verticeU.indice + "-" + ar.verticeV.indice;
			} else {
				arvore += ", " + ar.verticeU.indice + '-' + ar.verticeV.indice;
			}
		}
		
		System.out.println(somapeso);
		System.out.println(arvore);
		System.out.println("");

	}

}
