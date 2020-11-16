package business;

public class Freizeitbad {
	// Name des Freizeitbads
    private String name;
    // Oeffnungszeiten
    private float geoeffnetVon;
    private float geoeffnetBis;
    // Laenge des laengsten Beckens
    private int beckenlaenge;
    // Wassertemperatur des laengsten Beckens
    private int temperatur;
    
    public Freizeitbad(String name, String geoeffnetVon, String geoeffnetBis, String beckenlaenge, String temperatur){
        		this.name = name;
          	    this.geoeffnetVon = Float.parseFloat(geoeffnetVon);
          	    this.geoeffnetBis = Float.parseFloat(geoeffnetBis);
           	    this.beckenlaenge = Integer.parseInt(beckenlaenge);
           	    this.temperatur = Integer.parseInt(temperatur);
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getGeoeffnetVon() {
		return geoeffnetVon;
	}

	public void setGeoeffnetVon(float geoeffnetVon) {
		this.geoeffnetVon = geoeffnetVon;
	}

	public float getGeoeffnetBis() {
		return geoeffnetBis;
	}

	public void setGeoeffnetBis(float geoeffnetBis) {
		this.geoeffnetBis = geoeffnetBis;
	}

	public int getBeckenlaenge() {
		return beckenlaenge;
	}

	public void setBeckenlaenge(int beckenlaenge) {
		this.beckenlaenge = beckenlaenge;
	}

	public int getTemperatur() {
		return temperatur;
	}

	public void setTemperatur(int temperatur) {
		this.temperatur = temperatur;
	}
	
	public String gibFreizeitbadZurueck(char trenner){
  		return this.getName() + trenner 
  			+ this.getGeoeffnetVon() + trenner
  		    + this.getGeoeffnetBis() + trenner
  		    + this.getBeckenlaenge() + trenner
  		    + this.getTemperatur();
  	}
}
