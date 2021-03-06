/**
 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
 */

package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController {
	
	Model model = new Model();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboCorso"
    private ComboBox<String> comboCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaNome"
    private ImageView btnCercaNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    /*
     * restituisce la lista di corsi ai quali � iscritto lo studente di cui si inserisce la matricola
     */
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	//ripulisco l'area di testo da eventuali elenchi precendenti
    	txtResult.clear();
    	
    	String mat = txtMatricola.getText();
    	List<Corso> corsiIscritto = new LinkedList<Corso>();
    	corsiIscritto = model.cercaCorsiIscrizione(mat);
    	
    	//controlli
    	if(corsiIscritto == null){
    		txtResult.setText("Lo studente selezionato non � iscritto a nessun corso");
    	}else {
	    	//stampa dei corsi
	    	for(Corso c : corsiIscritto){
	    		String riga = "" + c.toString() + "\n";
	    		txtResult.appendText(riga);
	    	}
    	}
    }
    
    
    /*
     *restituisce la lista di tutti gli iscritti al corso il cui codice viene selezionato dall'utente
     * nel menu' a tendina
     */
    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	//Controllo che sia selezionato almeno un corso
    	if(comboCorso.getValue()==null){
    		txtResult.setText("Inserire un corso di cui si vogliono ricercare gli iscritti\n");
    	} else {
    		//ripulisci la casella di testo da eventuali elenchi gi� presenti
    		txtResult.clear();
    		
    		//individuo il nome del corso
        	String corso = comboCorso.getValue();
        	
        	//chiamo il metodo cercaIscritti in Model
        	List<Studente> studenti = new LinkedList<Studente>();
        	studenti = model.cercaIscrittiAlCorso(corso);
        	
        	//stampo la lista di iscritti sulla UI
        	for(Studente s : studenti) {
        		txtResult.appendText(s.getMatricola()+" "+s.getCognome()+" "+s.getNome()+" "+s.getCds()+"\n");
        	}
    	}
    }

    @FXML
    void doCercaNome(MouseEvent event) {
    	String mat = txtMatricola.getText();
    	//System.out.println(""+mat);
    	
    	if(mat.length()<6){
    		txtResult.appendText("Matricola non valida\n");
    		return;
    	}

    	Studente temp = model.cercaStudente(mat);
    	
    	if(temp==null){
    		txtResult.appendText("Matricola "+mat+" non trovata \n");
    	}else{
    		txtResult.appendText("Matricola "+mat+" trovata \n");
    		
    		txtNome.setText(temp.getNome());
        	txtCognome.setText(temp.getCognome());
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }
    
    public void setModel(Model modello){
    	this.model = modello;
    	comboCorso.getItems().addAll(model.getListaCorsi());
        if(comboCorso.getItems().size()>0){
         	comboCorso.setValue(comboCorso.getItems().get(0));
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
    }

}


