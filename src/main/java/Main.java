package main.java;
	
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import configuration.PropertyManager;
import controllers.ControllerViewListaRecursos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import pojos.Recurso;
import pojos.Usuario;
import pojos.UsuarioAntiguo;
import webservices.GenericWebServiceConsumer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/ViewRecurso.fxml"));
			Scene scene = new Scene(root,1025,650);
			primaryStage.setTitle("Recurso");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
