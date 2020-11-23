package factory;

import java.io.IOException;

public abstract class Creator {

	public Product factoryMethod() throws IOException {
		return new CsvProduct();
	}
}
