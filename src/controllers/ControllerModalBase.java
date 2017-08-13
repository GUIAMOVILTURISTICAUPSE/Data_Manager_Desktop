package controllers;

import javafx.stage.Stage;

public interface ControllerModalBase<X> {
	X getPojo();
	void setDialogStage(Stage stage);
	void setPojo(X x);
	void cancelar();
}
