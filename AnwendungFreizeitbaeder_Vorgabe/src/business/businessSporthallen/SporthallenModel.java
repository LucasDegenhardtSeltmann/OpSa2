package business.businessSporthallen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import business.businessFreizeitbaeder.Freizeitbad;
import ownUtil.PlausiException;

public class SporthallenModel {
	
	private static SporthallenModel instance;
	private SporthallenModel() {}
	public static synchronized SporthallenModel getInstance() {
		if(SporthallenModel.instance == null) {
			SporthallenModel.instance = new SporthallenModel();
		}
		return SporthallenModel.instance;
	}
	
	private ArrayList<Sporthalle> sporthallen = new ArrayList<>();
	
	public void leseSporthallenAusCsvDatei()
			throws IOException, PlausiException{
			BufferedReader ein = new BufferedReader(
		 		new FileReader("Sporthallen.csv"));
			ArrayList<Sporthalle> ergebnis = new ArrayList<>(); 
			String zeileStr = ein.readLine();
			while(zeileStr != null) {
				String[] zeile = zeileStr.split(";");
		           ergebnis.add( 
					new Sporthalle(zeile[0], zeile[1], zeile[2]));
		           zeileStr = ein.readLine();
			}    
		 	ein.close();
		 	this.sporthallen = ergebnis;
	}
	
	public ArrayList<Sporthalle> getSporthallen() {
		return this.sporthallen;
    }

}
