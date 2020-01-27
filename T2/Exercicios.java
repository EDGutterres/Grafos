public class Exercicios {

	public void ordenacaoTopologica(Grafo grafo) {
		OrdenacaoTopologica ot = new OrdenacaoTopologica();
		ot.topoSort(grafo);
	}

	public void kruskal(Grafo grafo) {
		new Kruskal(grafo);
	}

	public void fc (Grafo grafo) {
		new FortementeConexos(grafo);
	}

}
