public class Aresta {

	boolean visitada = false;
	double capacidade;
	Vertice verticeU;
	Vertice verticeV;
	
	public double getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(double capacidade) {
		this.capacidade = capacidade;
	}

	public Vertice getVerticeU() {
		return verticeU;
	}

	public void setVerticeU(Vertice verticeA) {
		this.verticeU = verticeA;
	}

	public Vertice getVerticeV() {
		return verticeV;
	}

	public void setVerticeV(Vertice verticeB) {
		this.verticeV = verticeB;
	}	

	public boolean isVisitada() {
		return visitada;
	}

	public void setVisitada(boolean visitada) {
		this.visitada = visitada;
	}

	public Vertice outraVertice(Vertice vertice) {
		if (vertice.equals(verticeU))
			return verticeU;
		else if (vertice.equals(verticeV))
			return verticeV;
		else
			throw new IllegalArgumentException("Vertice Invalida");
	}

	public String toString() {
		return "de " + verticeU.getRotulo() + " a " + verticeV.getRotulo() + " com capacidade " + capacidade;
	}
}
