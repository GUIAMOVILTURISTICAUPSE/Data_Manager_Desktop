package main.java;


	
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cloud.GoogleCloudStorageWorker;
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
		//testImageGoogleCloud();
		launch(args);
	}
	
	public static void testImageGoogleCloud()
	{
		GoogleCloudStorageWorker gcsWorker = new GoogleCloudStorageWorker();
		byte[] imageData;
		try {
			imageData = extractBytes(Main.class.getClass().getResource("/testImageMeme.jpg").toURI());
			if(imageData!=null)
			{
				String blobID = "TestImage1";
				gcsWorker.saveImage(blobID, imageData);
				gcsWorker.readImage(blobID);
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static byte[] extractBytes (URI uri)
	{
		 // open image
		 File imgPath = new File(uri);
		 BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(imgPath);
			 // get DataBufferBytes from Raster
			 WritableRaster raster = bufferedImage .getRaster();
			 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

			 return ( data.getData() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
	}
}
