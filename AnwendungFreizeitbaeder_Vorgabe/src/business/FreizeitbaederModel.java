package business;

import java.io.IOException;

import ownUtil.PlausiException;
import factory.CsvCreator;
import factory.Creator;
import factory.Product;
import factory.TxtCreator;
import gui.guiFreizeitbaeder.Observer;


public class FreizeitbaederModel implements Observable{
	
	private static FreizeitbaederModel instance;
	private FreizeitbaederModel() {}
	public static FreizeitbaederModel getInstance() {
		if(FreizeitbaederModel.instance == null) {
			FreizeitbaederModel.instance = new FreizeitbaederModel();
		}
		return FreizeitbaederModel.instance;
	}
	private Freizeitbad freizeitbad;
    private Observer[] observers = new Observer[2];
    private int obscount = 0;
	
    public void newFreizeitbad (String name, String geoeffnetVon, String geoeffnetBis, String beckenlaenge, String temperatur) throws PlausiException {
    	freizeitbad = new Freizeitbad(name, geoeffnetVon, geoeffnetBis, beckenlaenge, temperatur);
    	notifyObservers();
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

	@Override
	public void addObserver(Observer obs) {
		observers[obscount] = obs;
		obscount++;
	}

	@Override
	public void removeObserver(Observer obs) {
		obs = null;
		obscount--;
	}

	@Override
	public void notifyObservers() {
		for(int i = 0; i<obscount;i++) {
			observers[i].update();
		}
	}
}
