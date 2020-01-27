import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class EdmondsKarp {

    public EdmondsKarp(GrafoFluxoMaximo g) {

        Vertice s = g.getS();
        Vertice t = g.getT();
        List<Aresta> arestasPossiveis = new LinkedList<>();
        Map<Aresta, Double> f = new HashMap<>();
        List<Vertice> p;

        for (Vertice u : g.getConjuntoVertices()) {
            for (Vertice v : g.getConjuntoVertices()) {
                Aresta ar = new Aresta();
                ar.setVerticeU(u);
                ar.setVerticeV(v);
                arestasPossiveis.add(ar);
                f.put(ar, (double)0);
            }
        }

        while (true) {
            p = BFS(g, f, s, t, arestasPossiveis);
            if (p == null) {
                break;
            }
            Vertice u = p.get(0);
            Vertice v = p.get(1);
            double flow = g.capacidade(u, v) - f.get(recuperarAresta(u, v, arestasPossiveis));

            for (int i=0; i<p.size()-2; i++) {
                u = p.get(i+1);
                v= p.get(i+2);
                flow = Math.min(flow, g.capacidade(u, v) - f.get(recuperarAresta(u, v, arestasPossiveis)));
            }

            for (int i=0; i<p.size()-1; i++) {
                u = p.get(i);
                v= p.get(i+1);
                f.put(recuperarAresta(u, v, arestasPossiveis), f.get(recuperarAresta(u, v, arestasPossiveis)) + flow);
                f.put(recuperarAresta(v, u, arestasPossiveis), f.get(recuperarAresta(u, v, arestasPossiveis)) - flow);
            }
        }

        double soma = 0;

        for (Vertice ver : g.getConjuntoVertices()) {
            if (ver != s)
                soma += f.get(recuperarAresta(s, ver, arestasPossiveis));
        }
       
        System.out.println("Fluxo maximo: " + soma);
        
    }

    public List<Vertice> BFS (GrafoFluxoMaximo g, Map<Aresta, Double> f, Vertice s, Vertice t, List<Aresta> arestasPossiveis) {

        Map<Vertice, Boolean> c = new HashMap<>();
        Map<Vertice, Vertice> a = new HashMap<>();
        Queue<Vertice> q = new LinkedList<>();

        for (Vertice v : g.getConjuntoVertices()) {
            c.put(v, false);
            a.put(v, null);
        }
        c.put(s, true);
        q.add(s);

        while (!q.isEmpty()) {
            
            Vertice u = q.remove();
            
            for (Vertice v : u.getVizinhos()) {
                if ((!c.get(v)) && (g.capacidade(u, v) - f.get(recuperarAresta(u, v, arestasPossiveis)) > 0)) {
                    c.put(v, true);
                    a.put(v, u);

                    if (v == t) {
                        List<Vertice> p = new LinkedList<>();
                        p.add(t);
                        Vertice w = t;
                        while (w != s) {
                            w = a.get(w);
                            p.add(0, w);
                        }
                        return p;
                    }
                    q.add(v);
                }
            }
        }

        return null;

    }

    public Aresta recuperarAresta(Vertice u, Vertice v, List<Aresta> arestasPossiveis) {
        for(Aresta a: arestasPossiveis) {
			if((a.getVerticeU().equals(u) && a.getVerticeV().equals(v))) {
				return a;
			} 
        }
        return new Aresta();
    }
}