package application;
	
import org.springframework.web.client.RestTemplate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import pojos.Recurso;
import pojos.Usuario;
import pojos.UsuarioAntiguo;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/ViewRecurso.fxml"));
			
			
			Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("/recursos/css/estiloPrincipal.css").toExternalForm());
			primaryStage.setTitle("Recurso");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		//saveTest();
		//getTest();
		consumirWebService();
	}
	
	
	public static void consumirWebService()
	{
		 RestTemplate restTemplate = new RestTemplate();
	     Recurso r = restTemplate.getForObject("http://186.178.10.221:8080/api/recursos/La Chocolatera", Recurso.class);
	     System.out.println(r.toString());	
	}
}
