package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ControllerGoogleMap{
	@FXML WebView wView;
	@FXML TextField txtLatitud;
	@FXML TextField txtLongitud;
	@FXML Button btnBuscar;
	@FXML Button btnObtener;
	@FXML Button btnCrearMarcador;
	@FXML Button btnRetornar;
	
	
	Context context = Context.getInstance();
	double latitud;
	double longitud;
	WebEngine webEngine;
	MyBrowser myBrowser;
	List<Coordenadas> listaCoordenadas = new ArrayList<Coordenadas>();
	
	class Coordenadas{
		double lat, lon;
		public Coordenadas(double lat, double lon){
			this.lat = lat;
			this.lon = lon;
		}
		@Override
		public String toString() {
			return lat + ", " + lon;
		}
		
		
	}
	
	class MyBrowser {
		
		public MyBrowser(){
			webEngine = wView.getEngine();
			final URL urlGoogleMaps = getClass().getResource("demo.html");
			webEngine.load(urlGoogleMaps.toExternalForm());
			
		}
	}
	
	public void initialize()
	{
		myBrowser = new MyBrowser();
		txtLatitud.setText("-" + 0.2298500);
		txtLongitud.setText("-" + 78.5249500);
		
		btnBuscar.setOnAction(e ->{
			latitud = Double.parseDouble(txtLatitud.getText());
			longitud = Double.parseDouble(txtLongitud.getText());
			webEngine.executeScript("" +
					"window.lat = " + latitud + ";" +
					"window.lon = " + longitud + ";" +
					"document.goToLocation(window.lat, window.lon);"
					);
		});
		
		btnObtener.setOnAction(e -> {
			obtenerCoordenadas();
		});
		
		btnCrearMarcador.setOnAction(e -> {
			obtenerCoordenadas();
			Coordenadas coordenada = new Coordenadas(latitud, longitud);
			listaCoordenadas.add(coordenada);
			webEngine.executeScript("document.crearNuevoMarcador();");
			
			System.out.println(listaCoordenadas);
		});
		
		btnRetornar.setOnAction(evento -> {
			if(listaCoordenadas.size() == 1)
			{
				context.getRecurso().setPosicion(listaCoordenadas.get(0).lat + ", " + listaCoordenadas.get(0).lon);
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/ViewRecurso.fxml"));
					Stage escenario = new Stage();
					Scene escena = new Scene(root);
					escenario.setScene(escena);
					escenario.show();
					Stage stageMapa = (Stage) txtLatitud.getScene().getWindow();
					stageMapa.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				ArrayList<String> listaRecorrido = new ArrayList<String>();
				for (Coordenadas coordenadas : listaCoordenadas) {
					listaRecorrido.add(coordenadas.lat + " ," + coordenadas.lon);
				}
				context.getSendero().setRecorrido(listaRecorrido);
				Stage stageMapa = (Stage) txtLatitud.getScene().getWindow();
				stageMapa.close();
			}

			
		});
		
	}

	
	
	
	
	private void obtenerCoordenadas() {
			latitud = (double) webEngine.executeScript("document.obtenerLatitud();");
			txtLatitud.setText(String.valueOf(latitud));
			
			longitud = (double) webEngine.executeScript("document.obtenerLongitud();");
			txtLongitud.setText(String.valueOf(longitud));
	}
}
