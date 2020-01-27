import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Grafo {
	
	private List<Vertice> conjuntoVertices = new ArrayList<Vertice>();
	private List<Aresta> conjuntoArestas = new ArrayList<Aresta>();
	private int numeroVertices, numeroArestas;
	private boolean dirigido;

	public Grafo() {}

	public Grafo(File filename) throws IOException {

		int numeroVertices, numero, valorGrau = 0;
		boolean firstTime = true;
		String rotulo, textotemp;
		Scanner input = new Scanner(filename);
		input.useLocale(Locale.US);
		Vertice v;
		Aresta a;
		input.next();
		List<Vertice> vizinhos;
		this.dirigido = false;
		
		while(input.hasNext()) {
			
			if(firstTime) {
				firstTime = false;
				numeroVertices = input.nextInt();
				for(int i = 0; i < numeroVertices; i++) {
					v = new Vertice();
					v.setIndice(input.nextInt());
					rotulo = input.next();
					if(rotulo.charAt(0) == '"' && rotulo.charAt(rotulo.length()-1) != '"') {
						textotemp = input.next();
						while(textotemp.charAt(textotemp.length()-1) != '"') {
							rotulo += " "+textotemp;
							textotemp = input.next();
						}
						rotulo += " "+textotemp;
						System.out.println();
					}
					v.setRotulo(rotulo);
					conjuntoVertices.add(v);
				}
				if (input.next().equals("*arcs")) {
					this.dirigido = true;
				}
			} else {
				
				a = new Aresta();
				numero = input.nextInt();
				a.setVerticeU(conjuntoVertices.get(numero-1));
				numero = input.nextInt();
				a.setVerticeV(conjuntoVertices.get(numero-1));
				a.setCapacidade(input.nextDouble());
				conjuntoArestas.add(a);
			}
			
		}
		
		for(Vertice ver: conjuntoVertices) {
			vizinhos = new ArrayList<Vertice>();
			for(Aresta ar: conjuntoArestas) {
				if(ar.getVerticeU().equals(ver)) {
					valorGrau++;
					vizinhos.add(ar.getVerticeV());
				} else if(ar.getVerticeV().equals(ver) && !this.dirigido) {
					valorGrau++;
					vizinhos.add(ar.getVerticeU());
				}
			}
			ver.setVizinhos(vizinhos);
			ver.setGrau(valorGrau);
			valorGrau = 0;
		}
		this.numeroArestas = conjuntoArestas.size();
		this.numeroVertices = conjuntoVertices.size();
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
	
	public double capacidade(Vertice u, Vertice v) {
		
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

	public boolean isDirigido() {
		return dirigido;
	}

	public void setDirigido(boolean dirigido) {
		this.dirigido = dirigido;
	}
}