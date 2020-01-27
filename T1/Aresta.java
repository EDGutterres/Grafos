public class Aresta {

	boolean visitada = false;
	double peso;
	Vertice verticeU;
	Vertice verticeV;
	
	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
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
}
