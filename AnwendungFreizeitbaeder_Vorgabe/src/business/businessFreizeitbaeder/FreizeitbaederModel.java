package business.businessFreizeitbaeder;

import java.io.IOException;
import java.util.ArrayList;

import business.Observable;
import ownUtil.PlausiException;
import factory.CsvCreator;
import factory.Creator;
import factory.Product;
import factory.TxtCreator;
import gui.Observer;


public class FreizeitbaederModel implements Observable{
	
	private static FreizeitbaederModel instance;
	private FreizeitbaederModel() {}
	public static FreizeitbaederModel getInstance() {
		if(FreizeitbaederModel.instance == null) {
			FreizeitbaederModel.instance = new FreizeitbaederModel();
		}
		return FreizeitbaederModel.instance;
	}
	private ArrayList<Freizeitbad> freizeitbaeder = new ArrayList<Freizeitbad>();
    private Observer[] observers = new Observer[2];
    private int obscount = 0;
	
    public void newFreizeitbad (String name, String geoeffnetVon, String geoeffnetBis, String beckenlaenge, String temperatur) throws PlausiException {
    	addFreizeitbad(new Freizeitbad(name, geoeffnetVon, geoeffnetBis, beckenlaenge, temperatur));
    }
    
    public void addFreizeitbad(Freizeitbad freizeitbad) {
    	freizeitbaeder.add(freizeitbad);
    	notifyObservers();
    }
    public ArrayList<Freizeitbad> getFreizeitbaeder() {
    	return freizeitbaeder;
    }
    
	public void schreibeFreizeitbaederInCsvDatei() throws IOException{
		Creator factory = new CsvCreator();
		
		Product writer = factory.factoryMethod();
		for(Freizeitbad freizeitbad : freizeitbaeder) {
			writer.fuegeInDateiHinzu(freizeitbad.gibFreizeitbadZurueck(';') + "\n");
		}
	 	writer.schliesseDatei();
	}
	public void schreibeFreizeitbaederInTxtDatei() throws IOException{
		Creator factoryTxt = new TxtCreator();
		
		Product writerTxt = factoryTxt.factoryMethod();
		for(Freizeitbad freizeitbad : freizeitbaeder) {
			writerTxt.fuegeInDateiHinzu(freizeitbad.gibFreizeitbadZurueck(';') + "\n");
		}
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
