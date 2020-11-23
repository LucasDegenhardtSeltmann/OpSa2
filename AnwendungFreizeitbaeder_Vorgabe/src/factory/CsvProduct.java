package factory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvProduct extends Product {
	BufferedWriter aus;
	public CsvProduct() throws IOException {
		aus = new BufferedWriter(new FileWriter("Freizeitbaeder.csv", true));
	}
	public void fuegeInDateiHinzu(Object object) throws IOException {
		aus.write(String.valueOf(object));
	}
	
	public void schliesseDatei() throws IOException {
		aus.close();
	}

}
