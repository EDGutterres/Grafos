import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdenacaoTopologica {

	public OrdenacaoTopologica() {}

	public void topoSort(Grafo g) {


		Map<Vertice, Boolean> c = new HashMap<>();
		Map<Vertice, Double> t = new HashMap<>();
		Map<Vertice, Double> f = new HashMap<>();
		Map<Vertice, Vertice> a = new HashMap<>();

	    for (Vertice v: g.getConjuntoVertices()) {
			c.put(v, false);
			t.put(v, Double.POSITIVE_INFINITY);
			f.put(v, Double.POSITIVE_INFINITY);	
			a.put(v, null);
		}

		double tempo = 0;

		List<Vertice> o = new ArrayList<> ();

		for (Vertice v : g.getConjuntoVertices()) {
			if (c.get(v) == false) {
				DFSVisitOT(g, v, c, t, a, f, tempo, o);
			}
		}
		
		String msg = "", rotulo;

		for (Vertice v : o) {
			rotulo = v.getRotulo();
			msg += rotulo + " => ";
		}

		msg = msg.substring(0, msg.length()-4);

		System.out.println(msg);
		System.out.println("");

	}

	private void DFSVisitOT(Grafo g, Vertice v, Map<Vertice, Boolean> c, Map<Vertice, Double> t, Map<Vertice, Vertice> a, Map<Vertice, Double> f, double tempo, List<Vertice> o) {

		c.put(v, true);
		
		tempo++;

		t.put(v, tempo);

		for (Vertice u : v.getVizinhos()) {
			if (c.get(u) == false) {
				a.put(u, v);
				DFSVisitOT(g, u, c, t, a, f, tempo, o);
			}
		}

		tempo++;
		f.put(v, tempo);
		o.add(0, v);

	}
	
}
