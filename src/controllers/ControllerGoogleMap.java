package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
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
		txtLatitud.setText("-2.2262200");
		txtLongitud.setText("-80.8587300");
		myBrowser = new MyBrowser();

		wView.getEngine().getLoadWorker().stateProperty().addListener(
				  new ChangeListener<Worker.State>() {
				  @Override
				  public void changed(
				    ObservableValue<? extends Worker.State> observable,
				    Worker.State oldValue, Worker.State newValue ) {

				    if( newValue != Worker.State.SUCCEEDED ) {
				      return;
				    }

				    ubicarCoordenadaSimpleEnMapa();
				  }
				} );
		
		btnBuscar.setOnAction(e -> ubicarCoordenadaSimpleEnMapa());
		
		
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
			if(context.getSendero()==null)
			{
				if(listaCoordenadas.size()==1)
				{
					context.getRecurso().setPosicion(listaCoordenadas.get(0).lat + ", " + listaCoordenadas.get(0).lon);
				}
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
			}else if (listaCoordenadas.size() > 2) {
				ArrayList<String> listaRecorrido = new ArrayList<String>();
				for (Coordenadas coordenadas : listaCoordenadas) {
					listaRecorrido.add(coordenadas.lat + " ," + coordenadas.lon);
				}

				//context.getSendero().setRecorrido(listaRecorrido);
				
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/ViewSenderos.fxml"));
					Stage escenario = new Stage();
					Scene escena = new Scene(root);
					escenario.setScene(escena);
					escenario.show();
					Stage stageMapa = (Stage) txtLatitud.getScene().getWindow();
					stageMapa.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			
		});
		
		if(!context.getRecurso().getPosicion().isEmpty())
		{
			String[] coordenadaSeparadas = context.getRecurso().getPosicion().split("\\,");
			txtLatitud.setText(coordenadaSeparadas[0]);
			txtLongitud.setText(coordenadaSeparadas[1]);
			
			latitud = Double.parseDouble(txtLatitud.getText());
			longitud = Double.parseDouble(txtLongitud.getText());
		}
		
	}
	
	private void ubicarCoordenadaSimpleEnMapa()
	{
		latitud = Double.parseDouble(txtLatitud.getText());
		longitud = Double.parseDouble(txtLongitud.getText());
		webEngine.executeScript("" +
				"window.lat = " + latitud + ";" +
				"window.lon = " + longitud + ";" +
				"document.goToLocation(window.lat, window.lon);"
				);
	}
	
	private void obtenerCoordenadas() {
			latitud = (double) webEngine.executeScript("document.obtenerLatitud();");
			txtLatitud.setText(String.valueOf(latitud));
			
			longitud = (double) webEngine.executeScript("document.obtenerLongitud();");
			txtLongitud.setText(String.valueOf(longitud));
	}
}
