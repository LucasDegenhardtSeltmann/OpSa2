package factory;

import java.io.IOException;

public class TxtCreator extends Creator {

	public Product factoryMethod() throws IOException {
		return new TxtProduct();
	}

}
