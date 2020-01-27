import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

public class FortementeConexos {

    public FortementeConexos(Grafo g) {

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

        for (Vertice v : g.getConjuntoVertices()) {
			if (c.get(v) == false) {
                tempo = DFSVisit(g, v, c, t, a, f, tempo);
			}
        }
        
        Grafo gt = new Grafo();
        gt.setDirigido(true);

        for (Vertice v : g.getConjuntoVertices()) {
            gt.adicionarVertice(v);
        }
        
        for (Aresta ar : g.getConjuntoArestas()) {
            Vertice newU = ar.getVerticeU();
            Vertice newV = ar.getVerticeV();
            Aresta newAresta = new Aresta();
            newAresta.setPeso(1);
            newAresta.setVerticeU(newV);
            newAresta.setVerticeV(newU);
            gt.adicionarAresta(newAresta);
        }

        List<Vertice> vizinhos;

        for(Vertice ver: gt.getConjuntoVertices()) {
			vizinhos = new ArrayList<>();
			for(Aresta ar: gt.getConjuntoArestas()) {
				if(ar.getVerticeU().equals(ver)) {
					vizinhos.add(ar.getVerticeV());
				}
			}
			ver.setVizinhos(vizinhos);
		}

        Map<Vertice, Boolean> ct = new HashMap<>();
		Map<Vertice, Double> tt = new HashMap<>();
		Map<Vertice, Double> ft = new HashMap<>();
        Map<Vertice, Vertice> at = new HashMap<>();
        List<Vertice> tempos = gt.getConjuntoVertices();
        
	    for (Vertice v: gt.getConjuntoVertices()) {
			ct.put(v, false);
			tt.put(v, Double.POSITIVE_INFINITY);
            f.put(v, Double.POSITIVE_INFINITY);
			at.put(v, null);
        }

        for (Vertice v : f.keySet()) {
            v.setDv(f.get(v));
        }
        
        double tempot = 0;
        Collections.sort(tempos, new Comparador());
        
        for (Vertice v : tempos) {
            if (ct.get(v) == false) {
                DFSVisit(gt, v, ct, tt, at, f, tempot);
            }
        }

        Map<Vertice, List<Vertice>> arv = mostrarSubArvores(at, g);
        String msg = "";

        for (Vertice raiz : arv.keySet()) {
            if (arv.get(raiz) != null) {
                for (Vertice v : arv.get(raiz)) {
                    msg += v.getIndice() + ",";
                }
                if (msg.length()>=1) {
                    msg = msg.substring(0, (msg.length())-1);
                    System.out.println(msg);
                }
                msg = "";
            }
        }
        
    }

    private class Comparador implements Comparator<Vertice> {
        @Override
        public int compare(Vertice a, Vertice b) {
            return ((a.getDv() < b.getDv()) ? 1 : (a.getDv() == b.getDv()) ? 0 : -1);
        }        
    }

    private Map<Vertice, List<Vertice>> mostrarSubArvores(Map<Vertice, Vertice> a, Grafo g) {
        Map<Vertice, List<Vertice>> arv = new HashMap<>();

        for (Vertice v : g.getConjuntoVertices()) {
            if (a.get(v) == null) {
                arv.put(v, new ArrayList<>());
            }
        }

        Vertice u;
        Vertice antecessor;

        for (Vertice v : g.getConjuntoVertices()) {
            u = v;
            if (a.get(v) == null) {
                continue;
            }
            while (a.get(v) != null) {
                antecessor = a.get(v);
                v = antecessor;
            }
            arv.get(v).add(u);
        }

        return arv;
    }

    private double DFSVisit(Grafo g, Vertice v, Map<Vertice, Boolean> c, Map<Vertice, Double> t, Map<Vertice, Vertice> a, Map<Vertice, Double> f, double tempo) {
        
        c.put(v, true);
        tempo++;
        t.put(v, tempo);
        
        for (Vertice u : v.getVizinhos()) {
            if (c.get(u) == false) {
                a.put(u,v);
                tempo = DFSVisit(g, u, c, t, a, f, tempo);
            }
        }
        tempo++;
        f.put(v, tempo);
        return tempo;
    }

}