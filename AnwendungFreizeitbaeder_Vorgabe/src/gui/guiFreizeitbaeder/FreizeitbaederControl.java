package gui.guiFreizeitbaeder;

import java.io.IOException;

import business.FreizeitbaederModel;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ownUtil.PlausiException;

public class FreizeitbaederControl implements Observer{
	private FreizeitbaederView freizeitbaederView;
	private FreizeitbaederModel freizeitbad;

	public FreizeitbaederControl(Stage primaryStage) {
		freizeitbaederView = new FreizeitbaederView(primaryStage,this);
		this.freizeitbad = FreizeitbaederModel.getInstance();
		freizeitbad.addObserver(this);
	}
	public void nehmeFreizeitbadAuf(String name, String von, String bis, String laenge, String temp){
    	try{
    		freizeitbad.newFreizeitbad(name, von, bis, laenge, temp);
    		//freizeitbaederView.zeigeInformationsfensterAn("Das Freizeitbad wurde aufgenommen!");
       	}
       	catch(PlausiException exc){
       		freizeitbaederView.zeigeFehlermeldungsfensterAn(exc.getPlausiTyp() + "er ", exc.getMessage());
     	}
    }
	public void zeigeFreizeitbaederAn(TextArea text){
    	if(this.freizeitbad != null){
    		text.setText(
    			this.freizeitbad.gibFreizeitbadZurueck(' '));
    	}
    	else{
    		freizeitbaederView.zeigeInformationsfensterAn("Bisher wurde kein Freizeitbad aufgenommen!");
    	}
    }
	void schreibeFreizeitbaederInDatei(String typ){
		 try{
			 if("csv".equals(typ)){
				 freizeitbad.schreibeFreizeitbaederInCsvDatei();
				 freizeitbaederView.zeigeInformationsfensterAn("Die Freizeitbäder wurden in csv gespeichert!");
			 }else if("txt".contentEquals(typ)) {
				 freizeitbad.schreibeFreizeitbaederInTxtDatei();
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
