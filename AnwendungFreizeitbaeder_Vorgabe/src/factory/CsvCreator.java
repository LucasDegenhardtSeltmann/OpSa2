package factory;

import java.io.IOException;

public class CsvCreator extends Creator {

	public Product factoryMethod() throws IOException {
		return new CsvProduct();
	}

}
