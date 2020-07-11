package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	ExtFlightDelaysDAO dao;
	Map<Integer, Airport> mappa;
	Graph<Airport, DefaultWeightedEdge> grafo;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
		mappa = new HashMap<Integer, Airport>();
		this.dao.loadAllAirports(mappa);
	}
	
	public void creaGrafo(Integer distanza) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		//aggiungo i vertici
		Graphs.addAllVertices(grafo, mappa.values());
		
		for(Rotta r : dao.getRotte(mappa, distanza)) {
			//controllo se esiste un arco: se esiste aggiorno il peso facendo la media con quello nuovo
			DefaultWeightedEdge arco = grafo.getEdge(r.getA1(), r.getA2());
			if(arco==null) {
				Graphs.addEdge(grafo, r.getA1(), r.getA2(), r.getPeso());
			}else {
				double peso = grafo.getEdgeWeight(arco);
				double nuovoPeso = (peso + r.getPeso())/2;
				grafo.setEdgeWeight(arco, nuovoPeso);
			}
		}	
	
	}
	
	public int nVertici(){
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi(){
		return this.grafo.edgeSet().size();
	}
	
	public List<Airport> getVertici(){
		List<Airport> vertici = new ArrayList<>(grafo.vertexSet());
		return vertici;
	}
	
	public List<Vicino> getAeroportiConnessi(Airport aeroporto){
		List<Vicino> vicini = new ArrayList<>();
		List<Airport> aerVicini = Graphs.neighborListOf(grafo, aeroporto);
		for(Airport a : aerVicini) {
			vicini.add(new Vicino(a, this.grafo.getEdgeWeight(grafo.getEdge(a, aeroporto))));
		}
		Collections.sort(vicini);
		return vicini;
	}
	
}
