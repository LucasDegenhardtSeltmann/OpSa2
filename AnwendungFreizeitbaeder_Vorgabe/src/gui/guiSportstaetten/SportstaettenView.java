package gui.guiSportstaetten;

import java.io.IOException;

import business.businessFreizeitbaeder.Freizeitbad;
import business.businessFreizeitbaeder.FreizeitbaederModel;
import business.businessSporthallen.Sporthalle;
import business.businessSporthallen.SporthallenModel;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class SportstaettenView {
	
	// Hier ergaenzen
	private FreizeitbaederModel freizeitbaederModel = FreizeitbaederModel.getInstance();
	private SporthallenModel sporthallenModel = SporthallenModel.getInstance();
		
    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane = new  Pane();
    private MenuBar mnbrMenuLeiste  	= new MenuBar();
    private Menu mnImport            	= new Menu("Datei-Import");
    private Menu mnExport           	= new Menu("Datei-Export");
    private MenuItem mnItmCsvExport 	= new MenuItem("Csv-Export");
    private MenuItem mnItmCsvImportSporthallen 	= new MenuItem("Csv-Import Sporthallen");
    private MenuItem mnItmTxtExport 	= new MenuItem("Txt-Export");
    private MenuItem mnItmTxtImport 	= new MenuItem("Txt-Import");
    private Label lblAnzeigeFreizeitbaeder = new Label("Anzeige Freizeitbäder");
    private TextArea txtAnzeigeFreizeitbaeder  = new TextArea();
    private Button btnAnzeigeFreizeitbaeder = new Button("Anzeige");
    //---Anfang Attribute der Sporthallen Oberflaeche---
    private Label lblAnzeigeSporthallen = new Label("Anzeige Sporthallen");
    private TextArea txtAnzeigeSporthallen  = new TextArea();
    private Button btnAnzeigeSporthallen = new Button("Anzeige");
    	//-------Ende Attribute der grafischen Oberflaeche-------
    
    public SportstaettenView(Stage primaryStage){
    		Scene scene = new Scene(this.pane, 560, 340);
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("Anzeige von Sportstätten");
    		primaryStage.show();
    		// Hier ergaenzen

    		this.initFreizeitbadKomponenten();
    		this.initSporthallenKomponenten();
    		this.initFreizeitbaederListener();
    		this.initSporthallenListener();
    		this.initMenu();
    		this.initMenuListener();
    	}
    
    private void initFreizeitbadKomponenten(){
    	// Label
 		Font font = new Font("Arial", 20);
       	lblAnzeigeFreizeitbaeder.setLayoutX(310);
    	lblAnzeigeFreizeitbaeder.setLayoutY(40);
    	lblAnzeigeFreizeitbaeder.setFont(font);
    	lblAnzeigeFreizeitbaeder.setStyle("-fx-font-weight: bold;"); 
       	pane.getChildren().add(lblAnzeigeFreizeitbaeder);    
        // Textbereich	
        txtAnzeigeFreizeitbaeder.setEditable(false);
     	txtAnzeigeFreizeitbaeder.setLayoutX(310);
    	txtAnzeigeFreizeitbaeder.setLayoutY(90);
     	txtAnzeigeFreizeitbaeder.setPrefWidth(220);
    	txtAnzeigeFreizeitbaeder.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeigeFreizeitbaeder);        	
        // Button
        btnAnzeigeFreizeitbaeder.setLayoutX(310);
        btnAnzeigeFreizeitbaeder.setLayoutY(290);
        pane.getChildren().add(btnAnzeigeFreizeitbaeder); 
   }
   
    private void initSporthallenKomponenten() {
    	// Label
    	Font font = new Font("Arial", 20);
    	lblAnzeigeSporthallen.setLayoutX(20);
    	lblAnzeigeSporthallen.setLayoutY(40);
    	lblAnzeigeSporthallen.setFont(font);
    	lblAnzeigeSporthallen.setStyle("-fx-font-weight: bold;"); 
    	pane.getChildren().add(lblAnzeigeSporthallen);    
    	// Textbereich	
    	txtAnzeigeSporthallen.setEditable(false);
    	txtAnzeigeSporthallen.setLayoutX(20);
    	txtAnzeigeSporthallen.setLayoutY(90);
    	txtAnzeigeSporthallen.setPrefWidth(220);
    	txtAnzeigeSporthallen.setPrefHeight(185);
    	pane.getChildren().add(txtAnzeigeSporthallen);        	
    	// Button
    	btnAnzeigeSporthallen.setLayoutX(20);
    	btnAnzeigeSporthallen.setLayoutY(290);
    	pane.getChildren().add(btnAnzeigeSporthallen); 
  }
    
    private void initMenu() {
    	this.mnbrMenuLeiste.getMenus().addAll(mnImport,mnExport);
  	    this.mnExport.getItems().add(mnItmCsvExport);
  	    this.mnImport.getItems().add(mnItmCsvImportSporthallen);
  	    this.mnExport.getItems().add(mnItmTxtExport);
  	    this.mnImport.getItems().add(mnItmTxtImport);
 	    pane.getChildren().add(mnbrMenuLeiste);
    }
    
    private void initMenuListener() {
    	mnItmCsvImportSporthallen.setOnAction(new EventHandler<ActionEvent>() {
	    	 @Override
	    	 public void handle(ActionEvent e) {
	    		 try {
	    			 sporthallenModel.leseSporthallenAusCsvDatei();
	    			} catch (IOException err) {
	    				err.printStackTrace();
	    			} catch (PlausiException err) {
	    				err.printStackTrace();
	    			}
	    		 zeigeInformationsfensterAn("Csv Erfolgreich Importiert");
	    	 }
	    });
    }
    
   private void initFreizeitbaederListener() {
	    btnAnzeigeFreizeitbaeder.setOnAction(
 			new EventHandler<ActionEvent>() {
	    		@Override
	        	public void handle(ActionEvent e) {
	    			zeigeFreizeitbaederAn();
	        	} 
   	    });
    }
   
   private void initSporthallenListener() {
	   btnAnzeigeSporthallen.setOnAction(
			new EventHandler<ActionEvent>() {
	    		@Override
	        	public void handle(ActionEvent e) {
	    			zeigeSporthallenAn();
	        	} 
  	    });
   }
   
    void zeigeFreizeitbaederAn(){
    	if(this.freizeitbaederModel.getFreizeitbaeder().size() > 0){
    		StringBuffer fulltext = new StringBuffer();
    		for(Freizeitbad freizeitbad : freizeitbaederModel.getFreizeitbaeder()) {
	    		fulltext.append(freizeitbad.gibFreizeitbadZurueck(' ' ) + "\n");
	    	}
    		txtAnzeigeFreizeitbaeder.setText(fulltext.toString());	
    	}
    	else{
    		zeigeInformationsfensterAn(
 				"Bisher wurde kein Freizeitbad aufgenommen!");
    	}
    }	
    
    void zeigeSporthallenAn(){
    	if(this.sporthallenModel.getSporthallen().size() > 0){
    		StringBuffer fulltext = new StringBuffer();
    		for(Sporthalle sporthalle : sporthallenModel.getSporthallen()) {
	    		fulltext.append(sporthalle.gibSporthalleZurueck(' ' ) + "\n");
	    	}
    		txtAnzeigeSporthallen.setText(fulltext.toString());	
    	}
    	else{
    		zeigeInformationsfensterAn(
 				"Bisher wurde keine Sporthalle aufgenommen!");
    	}
    }	
   
    private void zeigeInformationsfensterAn(String meldung){
    	  	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
               	"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
}
