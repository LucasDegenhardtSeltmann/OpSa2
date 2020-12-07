package business;

import java.io.IOException;

import ownUtil.PlausiException;
import factory.CsvCreator;
import factory.Creator;
import factory.Product;
import factory.TxtCreator;

public class FreizeitbaederModel {
	
	private static FreizeitbaederModel instance;
	
	private FreizeitbaederModel() {}
	
	public static FreizeitbaederModel getInstance() {
		if(FreizeitbaederModel.instance == null) {
			FreizeitbaederModel.instance = new FreizeitbaederModel();
		}
		return FreizeitbaederModel.instance;
	}
	private Freizeitbad freizeitbad;
    
    public void newFreizeitbad (String name, String geoeffnetVon, String geoeffnetBis, String beckenlaenge, String temperatur) throws PlausiException {
    	freizeitbad = new Freizeitbad(name, geoeffnetVon, geoeffnetBis, beckenlaenge, temperatur);
    }
           
    public String gibFreizeitbadZurueck(char trenner){
    	return freizeitbad.gibFreizeitbadZurueck(';');
    }
    
    public Freizeitbad getFreizeitbad() {
    	return freizeitbad;
    }
    
	public void schreibeFreizeitbaederInCsvDatei() throws IOException{
		Creator factory = new CsvCreator();
		
		Product writer = factory.factoryMethod();
		writer.fuegeInDateiHinzu(freizeitbad.gibFreizeitbadZurueck(';'));
	 	writer.schliesseDatei();
	}
	public void schreibeFreizeitbaederInTxtDatei() throws IOException{
		Creator factoryTxt = new TxtCreator();
		
		Product writerTxt = factoryTxt.factoryMethod();
		writerTxt.fuegeInDateiHinzu(freizeitbad.gibFreizeitbadZurueck(';'));
		writerTxt.schliesseDatei();
	}
}
