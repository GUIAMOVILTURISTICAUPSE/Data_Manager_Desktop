package application;
	
import data_layer.CouchbaseManager;
import data_layer.RepositoryManager;
import es.codigoandroid.pojos.Usuario;
import javafx.application.Application;
import javafx.stage.Stage;
import pojos.UsuarioAntiguo;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static final RepositoryManager<String, Usuario> repo = new CouchbaseManager<String, Usuario>(Usuario.class);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		saveTest();
		getTest();
	}
	
	public static void saveTest()
	{
		Usuario u = new Usuario();
		u.setEmail("testupse@upse.edu.ec");
		u.setContraseniaHash("123");
		u.setDireccion("UPSE");
		u.setNombre("Usuario de Prueba UPSE");
		u.setTelefono("123456");
		repo.save(u, true);
	}
	
	public static void getTest()
	{
		Usuario u = repo.get("testupse@upse.edu.ec");
		System.out.println(u.toString());
	}
}
