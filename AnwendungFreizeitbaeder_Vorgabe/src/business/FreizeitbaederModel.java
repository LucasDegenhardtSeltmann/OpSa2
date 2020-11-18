package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ownUtil.PlausiException;

public class FreizeitbaederModel {
	private Freizeitbad freizeitbad;

    public FreizeitbaederModel(String name, String geoeffnetVon, String geoeffnetBis, String beckenlaenge, String temperatur) throws PlausiException{
    	freizeitbad = new Freizeitbad(name, geoeffnetVon, geoeffnetBis, beckenlaenge, temperatur);
    }
           
    public String gibFreizeitbadZurueck(char trenner){
    	return freizeitbad.gibFreizeitbadZurueck(';');
    }
    
	public void schreibeFreizeitbaederInCsvDatei() throws IOException{
	 BufferedWriter aus = new BufferedWriter(new FileWriter("Freizeitbaeder.csv", true));
	 aus.write(freizeitbad.gibFreizeitbadZurueck(';'));
	 aus.close();
	}
}
