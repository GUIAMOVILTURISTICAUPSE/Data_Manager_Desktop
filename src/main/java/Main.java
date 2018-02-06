package main.java;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/ViewRecurso.fxml"));
			Scene scene = new Scene(root);
			//root.minHeight(2000);
			//root.minWidth(2800);
			primaryStage.isMaximized();
			primaryStage.setTitle("Recurso");
			primaryStage.setScene(scene);
			//ResponsiveHandler.addResponsiveToWindow(primaryStage);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
