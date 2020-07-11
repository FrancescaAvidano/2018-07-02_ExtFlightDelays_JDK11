package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.Vicino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//controller turno A --> switchare ai branch master_turnoB o master_turnoC per turno B o C

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField distanzaMinima;

    @FXML
    private Button btnAnalizza;

    @FXML
    private ComboBox<Airport> cmbBoxAeroportoPartenza;

    @FXML
    private Button btnAeroportiConnessi;

    @FXML
    private TextField numeroVoliTxtInput;

    @FXML
    private Button btnCercaItinerario;

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	txtResult.clear();
    	String d = distanzaMinima.getText();
    	Integer distanza;
    	try {
    		distanza = Integer.parseInt(d);
    	} catch(NumberFormatException e) {
        	txtResult.clear();
    		txtResult.appendText("Inserisce un numero intero per la distanza.");
    		return;
    	}
    	
    	this.model.creaGrafo(distanza);
    	txtResult.appendText("Grafo creato!\n");
    	txtResult.appendText("# vertici: "+this.model.nVertici()+"\n");
    	txtResult.appendText("# archi: "+this.model.nArchi()+"\n");
    	
    	cmbBoxAeroportoPartenza.getItems().addAll(this.model.getVertici());
    }

    @FXML
    void doCalcolaAeroportiConnessi(ActionEvent event) {
    	Airport aeroporto = cmbBoxAeroportoPartenza.getValue();
    	List<Vicino> listaAeroporti = this.model.getAeroportiConnessi(aeroporto);
    	if(listaAeroporti.isEmpty()) {
    		txtResult.appendText("Non ci sono aeroporti collegati a !\n");
    		return;
    	}
    	txtResult.appendText("\nAeroporti vicini a " + aeroporto.toString() +":\n");
    	for(Vicino v : listaAeroporti) {
    		txtResult.appendText(v.toString()+"\n");
    	}
    }

    @FXML
    void doCercaItinerario(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert cmbBoxAeroportoPartenza != null : "fx:id=\"cmbBoxAeroportoPartenza\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnAeroportiConnessi != null : "fx:id=\"btnAeroportiConnessi\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert numeroVoliTxtInput != null : "fx:id=\"numeroVoliTxtInput\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnCercaItinerario != null : "fx:id=\"btnCercaItinerario\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
