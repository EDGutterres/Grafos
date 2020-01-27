
public class FloydWarshall {

	public double[][] algoritmo(Grafo grafo){
		
		int vAmount = grafo.getNumeroVertices();
		double result[][] = new double[vAmount][vAmount];
		
		for(int linha = 0; linha < vAmount; linha++) {
			for(int coluna = 0; coluna < vAmount; coluna++) {
				result[linha][coluna] = (double) grafo.peso(grafo.getConjuntoVertices().get(linha), grafo.getConjuntoVertices().get(coluna));
			}
		}
		

		for (int k = 0; k < vAmount; k++) { 
            for (int i = 0; i < vAmount; i++) { 
                for (int j = 0; j < vAmount; j++) { 

                    if (result[i][k] + result[k][j] < result[i][j]) {
                        result[i][j] = result[i][k] + result[k][j]; 
                        
                    }
                    if(i == j) {
                    	result[i][j] = 0.0;
                    }
                    
                } 
            } 
        } 
		
		
		return result;
	}
	
	
	
	
}
