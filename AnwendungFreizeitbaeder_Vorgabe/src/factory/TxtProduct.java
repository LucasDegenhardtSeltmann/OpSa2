package factory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class TxtProduct extends Product {
	BufferedWriter aus;
	public TxtProduct() throws IOException {
		aus = new BufferedWriter(new FileWriter("Freizeitbaeder.txt", true));
	}
	public void fuegeInDateiHinzu(Object object) throws IOException {
		String badString = String.valueOf(object);
		String endmsg = "";
		endmsg = "Daten des Freizeitbades\n";
		endmsg += "Name des Freizeitbades:\t\t\t"+badString.substring(0,(ordinalIndexOf(badString, ";", 0)))+"\n";
		endmsg += "Öffnungszeit des Freizeitbads:\t\t"+badString.substring((ordinalIndexOf(badString, ";", 0))+1,(ordinalIndexOf(badString, ";", 1)))+" - "+badString.substring(ordinalIndexOf(badString, ";", 1)+1,ordinalIndexOf(badString, ";", 2))+"\n";
		endmsg += "Beckenlänge des Freizeitbads:\t\t"+badString.substring(ordinalIndexOf(badString, ";", 2)+1,(ordinalIndexOf(badString, ";", 3)))+"\n";
		endmsg += "Wassertemperatur des Freizeitbads:\t"+badString.substring(ordinalIndexOf(badString, ";", 3)+1,badString.length())+"\n";
		aus.write(endmsg);
	}
	
	public void schliesseDatei() throws IOException {
		aus.close();
	}
	
	public static int ordinalIndexOf(String str, String substr, int n) {
	    int pos = -1;
	    do {
	        pos = str.indexOf(substr, pos + 1);
	    } while (n-- > 0 && pos != -1);
	    return pos;
	}

}
