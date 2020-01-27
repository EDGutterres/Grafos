import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class HopcroftKarp {

    public HopcroftKarp(GrafoBipartido g) {

        Map<Vertice,Double> d = new HashMap<>();
        Map<Vertice,Vertice> mate = new HashMap<>();

        for (Vertice v : g.getConjuntoVertices()) {
            d.put(v, Double.POSITIVE_INFINITY);
            mate.put(v, null);
        }

        int m = 0;

        while (BFS(g, mate, d)) {
            
            for (Vertice x : g.getConjuntoX()) {
                
                if (mate.get(x) == null) {
                    if (DFS(g, mate, x, d)) {
                        m++;
                    }
                }
            }
        }

        System.out.println("Emparelhamento maximo: " + m + ".");
        System.out.println(mate);

    }

    public boolean BFS(GrafoBipartido g, Map<Vertice,Vertice> mate, Map<Vertice,Double> d) {
        
        Queue<Vertice> q = new LinkedList<>();

        for (Vertice x : g.getConjuntoX()) {
            
            if (mate.get(x) == null) {
                d.put(x, (double) 0);
                q.add(x);
            } else {
                d.put(x, Double.POSITIVE_INFINITY);
            }
        }

        d.put(null, Double.POSITIVE_INFINITY);

        while(!q.isEmpty()) {
            Vertice x = q.remove();
            if (d.get(x) < d.get(null)) {
                for (Vertice y : x.getVizinhos()) {
                    if (d.get(mate.get(y)) == Double.POSITIVE_INFINITY) {
                        d.put(mate.get(y), (d.get(x)+1));
                        q.add(mate.get(y));
                    }
                }
            }
        }
        
        return d.get(null) != Double.POSITIVE_INFINITY;
    }

    public boolean DFS(GrafoBipartido g, Map<Vertice,Vertice> mate, Vertice x, Map<Vertice,Double> d) {
        
        if (x != null) {
            
            for (Vertice y : x.getVizinhos()) {
                if (d.get(mate.get(y)) == (d.get(x) + 1)) {
                    if (DFS(g, mate, mate.get(y), d)) {
                        mate.put(y, x);
                        mate.put(x, y);
                        return true;
                    }
                }
            }

            d.put(x, Double.POSITIVE_INFINITY);
            return false;
        }
        
        return true;
    }
}