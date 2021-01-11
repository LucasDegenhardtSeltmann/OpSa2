package gui.guiSportstaetten;

import business.FreizeitbaederModel;
import gui.Observer;
import javafx.stage.Stage;

public class SportstaettenControl implements Observer{
	private SportstaettenView sportstaettenView;
	private FreizeitbaederModel freizeitbad;
	public SportstaettenControl(Stage primaryStage) {
		sportstaettenView = new SportstaettenView(primaryStage);
		freizeitbad = FreizeitbaederModel.getInstance();
		freizeitbad.addObserver(this);
	}
	@Override
	public void update() {
		sportstaettenView.zeigeFreizeitbaederAn();
	}

}
