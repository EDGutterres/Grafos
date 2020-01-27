import java.util.ArrayList;
import java.util.Collections;

public class BellmanFord {
    
    public BellmanFord(Grafo grafo, Vertice s) {

        for (Vertice v : grafo.getConjuntoVertices()) {
            v.setDv(Double.POSITIVE_INFINITY);
            v.setAv(null);
            v.setCv(false);
        }

        s.setDv(0);

        for (int i=1; i<grafo.getConjuntoVertices().size()-1; i++) {
            for (Aresta a : grafo.getConjuntoArestas()) {

                if (a.getVerticeV().getDv() > a.getVerticeU().getDv() + a.getPeso()) {
                    a.getVerticeV().setDv(a.getVerticeU().getDv()+a.getPeso());
                    a.getVerticeV().setAv(a.getVerticeU());
                }

                if (a.getVerticeU().getDv() > a.getVerticeV().getDv() + a.getPeso()) {
                    a.getVerticeU().setDv(a.getVerticeV().getDv()+a.getPeso());
                    a.getVerticeU().setAv(a.getVerticeV());
                }

            }
        }


        for (Aresta a : grafo.getConjuntoArestas()) {
            if (a.getVerticeV().getDv() > a.getVerticeU().getDv() + a.getPeso()) {
                System.out.println("Erro");
                return;
            }
        }

        ArrayList<Integer> lista = new ArrayList<>();

        for (Vertice v : grafo.getConjuntoVertices()) {
            System.out.print(v.getIndice() + ": ");
            Vertice vaux = v;
            while (vaux.getAv()!=null) {
                lista.add(vaux.getAv().getIndice());
                vaux = vaux.getAv();
            }
            Collections.reverse(lista);
            for (Integer i : lista) {
                System.out.print(i + ",");
            }
            lista.clear();
            System.out.println(v.getIndice() + "; d=" + v.getDv());
        }
    }
}