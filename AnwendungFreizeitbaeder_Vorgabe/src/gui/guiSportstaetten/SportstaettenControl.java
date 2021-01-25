package gui.guiSportstaetten;

import business.businessFreizeitbaeder.FreizeitbaederModel;
import business.businessSporthallen.SporthallenModel;
import gui.Observer;
import javafx.stage.Stage;

public class SportstaettenControl implements Observer{
	private SportstaettenView sportstaettenView;
	private FreizeitbaederModel freizeitbad;
	private SporthallenModel sporthalle;
	public SportstaettenControl(Stage primaryStage) {
		sportstaettenView = new SportstaettenView(primaryStage);
		freizeitbad = FreizeitbaederModel.getInstance();
		freizeitbad.addObserver(this);
		
		sporthalle = SporthallenModel.getInstance();
	}
	@Override
	public void update() {
		sportstaettenView.zeigeFreizeitbaederAn();
	}

}
