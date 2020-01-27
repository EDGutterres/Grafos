import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;


public class Euleriano {
	
	public Euleriano(){}

	public Pair<Boolean,List<Vertice>> hierholzer(Grafo grafo) {
		
		Map<Aresta, Boolean> C = new HashMap<Aresta, Boolean>();
		for (Aresta a : grafo.getConjuntoArestas()) {
			C.put(a, false);
		}
		
		Vertice v = grafo.getConjuntoVertices().get(0);

		Pair<Boolean, List<Vertice>> parCiclo = buscarSubcicloEuleriano(grafo, v, C);
		boolean r = parCiclo.getKey();
		List<Vertice> ciclo = parCiclo.getValue();

		if (!r) {
			System.out.println(0);
			return new Pair<Boolean,List<Vertice>>(false, null);
		} else {
			for (boolean i : C.values()) {
				if (!i) {
					System.out.println("Erro");
					return new Pair<Boolean,List<Vertice>>(false, null);
				}
			}
			System.out.println(1);
			String msg = "";
			for (Vertice vAux: ciclo) {
				msg += vAux.getIndice() + ",";
			}
			msg = msg.substring(0, msg.length()-1);
			System.out.println(msg);
			return new Pair<Boolean,List<Vertice>>(true, ciclo);
		}

	}
	
	private Pair<Boolean, List<Vertice>> buscarSubcicloEuleriano(Grafo grafo, Vertice v, Map<Aresta, Boolean> C) {
		
		List<Vertice> ciclo = new ArrayList<>();
		ciclo.add(v);
		
		Vertice t = v;
		Aresta aux = null;

		while (true) {
			boolean todasVisitadas = true;
			for (Vertice u : v.getVizinhos()) {
				for (Aresta a : grafo.getConjuntoArestas()) {
					if ((a.getVerticeU().equals(v) && a.getVerticeV().equals(u)) || (a.getVerticeU().equals(u) && a.getVerticeV().equals(v))) {
						aux = a;
						break;
					}
				}
				if (C.containsKey(aux)) {
					if (!C.get(aux)) {
						todasVisitadas = false;
						C.replace(aux, true);
						v = u;
						break;
					}
				}
			}
			if (todasVisitadas) {
				return new Pair<Boolean,List<Vertice>>(false, null);
			}
			ciclo.add(v);
			if (v.equals(t)) {
				break;
			}
		}

		boolean continuar = false;

		for (boolean b : C.values()) {
			if (!b) {
				continuar = true;
				break;
			}
		}

		if (continuar) {
			List<Vertice> xLinha = new ArrayList<>();
			for (Aresta a : C.keySet()) {
				if (!C.get(a)) {
					if (!xLinha.contains(a.getVerticeV())) {
						xLinha.add(a.getVerticeV());
					} else if (!xLinha.contains(a.getVerticeU())) {
						xLinha.add(a.getVerticeU());
					}
				}
			}
			Vertice x = xLinha.get(0);

			Pair<Boolean, List<Vertice>> novoPar = buscarSubcicloEuleriano(grafo, x, C);
			boolean rLinha = novoPar.getKey();
			List<Vertice> ciclo_ = novoPar.getValue();

			if (!rLinha) {
				return new Pair<Boolean,List<Vertice>>(false, null);
			}
			
			if (ciclo.contains(x)) {
				System.out.println("chegou aqui");
				for (int i=0; i<ciclo.size(); i++) {
					if (ciclo.get(i).equals(x)) {
						ciclo.remove(x);
						for (int j=0; j<ciclo_.size(); j++) {
							Vertice vAux = ciclo_.get(j);
							ciclo.add(i+j, vAux);
						}
						break;
					}
				}
			}

		}

		return new Pair<Boolean,List<Vertice>>(true, ciclo);
	}

	
}
