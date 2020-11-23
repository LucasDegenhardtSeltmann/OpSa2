package business;

import java.io.IOException;

import ownUtil.PlausiException;
import factory.CsvCreator;
import factory.Creator;
import factory.Product;
import factory.TxtCreator;

public class FreizeitbaederModel {
	private Freizeitbad freizeitbad;

    public FreizeitbaederModel(String name, String geoeffnetVon, String geoeffnetBis, String beckenlaenge, String temperatur) throws PlausiException{
    	freizeitbad = new Freizeitbad(name, geoeffnetVon, geoeffnetBis, beckenlaenge, temperatur);
    }
           
    public String gibFreizeitbadZurueck(char trenner){
    	return freizeitbad.gibFreizeitbadZurueck(';');
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
