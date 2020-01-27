import java.util.List;

public class Vertice {
	
	boolean cv;
	double dv;
	Vertice av;
	int indice, grau;
	String rotulo;
	List<Vertice> vizinhos;
	
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public String getRotulo() {
		return rotulo;
	}

	public String toString() {
		return this.indice+"";
	}


	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public int getGrau() {
		return grau;
	}
	
	public void setGrau(int grau) {
		this.grau = grau;
	}

	public List<Vertice> getVizinhos() {
		return vizinhos;
	}

	public void setVizinhos(List<Vertice> vizinhos) {
		this.vizinhos = vizinhos;
	}

	public Vertice getAv() {
		return av;
	}

	public void setAv(Vertice av) {
		this.av = av;
	}

	public double getDv() {
		return dv;
	}

	public void setDv(double dv) {
		this.dv = dv;
	}

	public boolean isCv() {
		return cv;
	}

	public void setCv(boolean cv) {
		this.cv = cv;
	}
	
}
