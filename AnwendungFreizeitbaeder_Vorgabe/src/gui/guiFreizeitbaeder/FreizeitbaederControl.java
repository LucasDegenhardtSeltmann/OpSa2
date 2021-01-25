package gui.guiFreizeitbaeder;

import java.io.IOException;

import business.businessFreizeitbaeder.Freizeitbad;
import business.businessFreizeitbaeder.FreizeitbaederModel;
import gui.Observer;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ownUtil.PlausiException;

public class FreizeitbaederControl implements Observer{
	private FreizeitbaederView freizeitbaederView;
	private FreizeitbaederModel freizeitbaeder;

	public FreizeitbaederControl(Stage primaryStage) {
		freizeitbaederView = new FreizeitbaederView(primaryStage,this);
		this.freizeitbaeder = FreizeitbaederModel.getInstance();
		freizeitbaeder.addObserver(this);
	}
	public void nehmefreizeitbaederAuf(String name, String von, String bis, String laenge, String temp){
    	try{
    		freizeitbaeder.newFreizeitbad(name, von, bis, laenge, temp);
    		//freizeitbaederView.zeigeInformationsfensterAn("Das freizeitbad wurde aufgenommen!");
       	}
       	catch(PlausiException exc){
       		freizeitbaederView.zeigeFehlermeldungsfensterAn(exc.getPlausiTyp() + "er ", exc.getMessage());
     	}
    }
	public void zeigeFreizeitbaederAn(TextArea text){
    	if(this.freizeitbaeder.getFreizeitbaeder().size() > 0){
    		StringBuffer fulltext = new StringBuffer();
    		for(Freizeitbad freizeitbad : freizeitbaeder.getFreizeitbaeder()) {
	    		fulltext.append(freizeitbad.gibFreizeitbadZurueck(' ' ) + "\n");
	    	}
	    text.setText(fulltext.toString());	
    	}
    	else{
    		freizeitbaederView.zeigeInformationsfensterAn("Bisher wurde kein freizeitbaeder aufgenommen!");
    	}
    }
	void schreibeFreizeitbaederInDatei(String typ){
		 try{
			 if("csv".equals(typ)){
				 freizeitbaeder.schreibeFreizeitbaederInCsvDatei();
				 freizeitbaederView.zeigeInformationsfensterAn("Die Freizeitbäder wurden in csv gespeichert!");
			 }else if("txt".contentEquals(typ)) {
				 freizeitbaeder.schreibeFreizeitbaederInTxtDatei();
				 freizeitbaederView.zeigeInformationsfensterAn("Die Freizeitbäder wurden in txt gespeichert!");
			 }
			 else{
				 freizeitbaederView.zeigeInformationsfensterAn("Noch nicht implementiert!");
			 }
		 }
		catch(IOException exc){
			 freizeitbaederView.zeigeFehlermeldungsfensterAn("IOException","IOException beim Speichern!");
		}
		 catch(Exception exc){
				freizeitbaederView.zeigeFehlermeldungsfensterAn("unknown","Unbekannter Fehler beim Speichern!");
		}
	}
	@Override
	public void update() {
		freizeitbaederView.zeigeFreizeitbaederAn();
	}
		 
	
		

}
