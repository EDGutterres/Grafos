import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class GrafoBipartido {
	
    private List<Vertice> conjuntoVertices = new ArrayList<Vertice>();
    private List<Vertice> conjuntoX = new ArrayList<Vertice>();
    private List<Vertice> conjuntoY = new ArrayList<Vertice>();
	private List<Aresta> conjuntoArestas = new ArrayList<Aresta>();
	private int numeroVertices, numeroArestas;

	public GrafoBipartido() {}

	public GrafoBipartido(File filename) throws IOException {

		int valorGrau = 0;
		boolean firstTime = true, foundVertex = false;
		String rotulo;
		Scanner input = new Scanner(filename);
		input.useLocale(Locale.US);
        Vertice v;
        Vertice u;
		Aresta a;
        input.nextLine();
        input.nextLine();
        input.nextLine();
        input.next();
        input.next();
		List<Vertice> vizinhos;
		
		while(input.hasNext()) {
			
			if(firstTime) {
				firstTime = false;
                numeroVertices = input.nextInt();
                numeroArestas = input.nextInt();
				
			} else {
                
				input.next();

				foundVertex = false;
				rotulo = input.next();
				u = new Vertice();
				u.setRotulo(rotulo);

				for (Vertice ver : conjuntoVertices) {
					if (rotulo.equals(ver.getRotulo())) {
						foundVertex = true;
						u = ver;
						break;
					}
				}

				if (!foundVertex) {
					conjuntoVertices.add(u);
					conjuntoX.add(u);
				}
				
                
				foundVertex = false;
                rotulo = input.next();
                v = new Vertice();
				v.setRotulo(rotulo);

				for (Vertice ver : conjuntoVertices) {
					if (rotulo.equals(ver.getRotulo())) {
						foundVertex = true;
						v = ver;
						break;
					}
				}
				
                if (!foundVertex) {
                    conjuntoVertices.add(v);
                    conjuntoY.add(v);
                }

                a = new Aresta();
                a.setVerticeU(u);
                a.setVerticeV(v);
                conjuntoArestas.add(a);
				
			}
			
		}
		
		for(Vertice ver: conjuntoVertices) {
			vizinhos = new ArrayList<Vertice>();
			for(Aresta ar: conjuntoArestas) {
				if(ar.getVerticeU().equals(ver)) {
					valorGrau++;
					vizinhos.add(ar.getVerticeV());
				} else if(ar.getVerticeV().equals(ver)) {
					valorGrau++;
					vizinhos.add(ar.getVerticeU());
				}
			}
			ver.setVizinhos(vizinhos);
			ver.setGrau(valorGrau);
			valorGrau = 0;
		}
		
		input.close();
	}
	
	public int qtdVertices() {
		return conjuntoVertices.size();
	}
	
	public int qtdArestas() {
		return conjuntoArestas.size();
	}
	
	public int grau(Vertice v) {
		return v.getGrau();
	}
	
	public String rotulo(Vertice v) {
		return v.getRotulo();
	}
	
	public List<Vertice> vizinhos(Vertice v){
		return v.getVizinhos();
	}
	
	public boolean haAresta(Vertice u, Vertice v) {

		for(Aresta a: conjuntoArestas) {
			if((a.getVerticeU().equals(u) && a.getVerticeV().equals(v)) || (a.getVerticeU().equals(v) && a.getVerticeV().equals(u))) {
				return true;
			} 
		}
		
		return false;
	}
	
	public double peso(Vertice u, Vertice v) {
		
		for(Aresta a: conjuntoArestas) {
			if((a.getVerticeU().equals(u) && a.getVerticeV().equals(v)) || (a.getVerticeU().equals(v) && a.getVerticeV().equals(u))) {
				return a.getCapacidade();
			} 
		}
		
		return Double.POSITIVE_INFINITY;
	}

	public List<Aresta> getConjuntoArestas() {
		return conjuntoArestas;
	}

	public void adicionarAresta(Aresta a) {
		conjuntoArestas.add(a);
	}

	public void setConjuntoArestas(List<Aresta> conjuntoArestas) {
		this.conjuntoArestas = conjuntoArestas;
	}

	public List<Vertice> getConjuntoVertices() {
		return conjuntoVertices;
	}

	public void adicionarVertice(Vertice v) {
		conjuntoVertices.add(v);
	}

	public void setConjuntoVertices(List<Vertice> conjuntoVertices) {
		this.conjuntoVertices = conjuntoVertices;
	}

	public int getNumeroVertices() {
		return numeroVertices;
	}

	public void setNumeroVertices(int numeroVertices) {
		this.numeroVertices = numeroVertices;
	}

	public int getNumeroArestas() {
		return numeroArestas;
	}

	public void setNumeroArestas(int numeroArestas) {
		this.numeroArestas = numeroArestas;
	}

    public List<Vertice> getConjuntoX() {
        return conjuntoX;
    }

    public void setConjuntoX(List<Vertice> conjuntoX) {
        this.conjuntoX = conjuntoX;
    }

    public List<Vertice> getConjuntoY() {
        return conjuntoY;
    }

    public void setConjuntoY(List<Vertice> conjuntoY) {
        this.conjuntoY = conjuntoY;
    }
}