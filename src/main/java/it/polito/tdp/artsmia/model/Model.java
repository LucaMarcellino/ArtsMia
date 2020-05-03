package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	private Graph<ArtObject,DefaultWeightedEdge> grafo;
	private Map<Integer,ArtObject> idMap;
	
	
	public Model() {
		this.idMap=new HashMap<>();
		
		
	}
	
	public void creaGrafo() {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		ArtsmiaDAO dao= new ArtsmiaDAO();
		//contine oggetti
		dao.listObjects(idMap);
		
		//Aggiungere vertici
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		//Aggiungere Archi
		
		
		//Approccio 1-- doppio ciclo for sui vertici-- dati due vertici -- controllo se sono collegati-- TROPPO LUNGO PER TROPPI VERTICI
		/*for(ArtObject a1:this.grafo.vertexSet() ) {
			for(ArtObject a2: this.grafo.vertexSet()) {
				//devo collegare a1 ed a2
				//controllo a priori se esiste gia visto che è semplice
				int peso = dao.getPeso(a1,a2);
						if(peso>0) {
							if(this.grafo.containsEdge(a1,a2)) {
								Graphs.addEdge(this.grafo, a1, a2, peso);
							}
						}
			
			}
		}*/
	
		
		//Approccio 2 mi faccio passare direttamente dal db gli archi
		
		for(Adiacenza a :dao.getAdiacenze()) {
			if(a.getPeso()>0) {
				Graphs.addEdge(this.grafo,idMap.get(a.getObj1()) ,idMap.get(a.getObj2()) ,a.getPeso() );
			}
		}
		
		
		System.out.println(grafo.vertexSet().size()+"    "+ this.grafo.edgeSet().size());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
