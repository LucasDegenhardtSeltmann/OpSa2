package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ownUtil.PlausiException;

public class FreizeitbaederModel {
	private Freizeitbad freizeitbad;

    public FreizeitbaederModel(String name, String geoeffnetVon, String geoeffnetBis, String beckenlaenge, String temperatur) throws PlausiException{
    	String feldname = pruefeFormal(name, geoeffnetVon, geoeffnetBis, beckenlaenge, temperatur);
    	if(feldname == null){
    		freizeitbad = new Freizeitbad(name, geoeffnetVon, geoeffnetBis, beckenlaenge, temperatur);
       	    feldname = pruefeInhaltlich();
       	    if(feldname != null) {
      	        throw new PlausiException(PlausiException.INHALTLICH, feldname);
       	    }
        }
    	else {
    		throw new PlausiException(PlausiException.FORMAL, feldname);
        } 
    }
    
    private String pruefeFormal(
    	String name, String geoeffnetVon, String geoeffnetBis,
        String beckenlaenge, String temperatur) {
        String erg = null;
        if(name == null || "".equals(name)){
        	return "Name";
        }
        else{
	        try {
	            Float.parseFloat(geoeffnetVon);
	        }
	        catch(NumberFormatException exc) {
	            return "Geöffnet von";
	        }
	        try {
	            Float.parseFloat(geoeffnetBis);
	        }
	        catch(NumberFormatException exc) {
	        	return "Geöffnet bis";
	        }
	        try {
	            Integer.parseInt(beckenlaenge);
	        }
	        catch(NumberFormatException exc) {
	        	return "Beckenlänge";
	        }
	        try {
	            Integer.parseInt(temperatur);
	        }
	        catch(NumberFormatException exc){
	        	return "Temperatur";
	        }
        }
        return erg;
    }
    
    private String pruefeInhaltlich() {
        String erg = null;
        if(freizeitbad.getGeoeffnetVon() < 0 || freizeitbad.getGeoeffnetVon() >= 24){
        	return "Geöffnet von";
        }
        if(freizeitbad.getGeoeffnetBis() < 0 || freizeitbad.getGeoeffnetBis() >= 24
        	|| freizeitbad.getGeoeffnetBis() <= freizeitbad.getGeoeffnetVon()){
        	return "Geöffnet bis";
        }
        if(freizeitbad.getBeckenlaenge() <= 0){
        	return "Beckenlänge";
        }
        if(freizeitbad.getTemperatur() <= 0 || freizeitbad.getTemperatur() >= 100){
        	return "Temperatur";
        }
        return erg;
    }
           
    public String gibFreizeitbadZurueck(char trenner){
    	return freizeitbad.gibFreizeitbadZurueck(';');
    }
    
	public void schreibeFreizeitbaederInCsvDatei() throws IOException
	{
	 BufferedWriter aus = new BufferedWriter(
			 new FileWriter("Freizeitbaeder.csv", true));
	 aus.write(freizeitbad.gibFreizeitbadZurueck(';'));
	 aus.close();
	}

}
