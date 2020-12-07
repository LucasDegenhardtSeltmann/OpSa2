package gui.guiSportstaetten;

import javafx.stage.Stage;

public class SportstaettenControl {
	private SportstaettenView sportstaettenView;
	public SportstaettenControl(Stage primaryStage) {
		sportstaettenView = new SportstaettenView(primaryStage);
	}

}
