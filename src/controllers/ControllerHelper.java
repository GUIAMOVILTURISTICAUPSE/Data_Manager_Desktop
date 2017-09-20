package controllers;

import java.io.IOException;
import java.util.List;

import configuration.PropertyManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.Main;
import pojos.Recurso;

import webservices.GenericWebServiceConsumer;

public class ControllerHelper<R> {

	PropertyManager propertyManager = new PropertyManager();
	
	public R cargarDatosWebService(String id, Class<R> clase)
	{
		R pojoCargado = null;
		if(!id.equals(null) && !id.equals(""))
		{
			String urlBase = PropertyManager.getURLBase();
			
			String urlRelativo = propertyManager.getUrlRelativoDesdeClase(clase.getName());
			GenericWebServiceConsumer<R> webService = new GenericWebServiceConsumer<R>(clase);
			
			
			
			pojoCargado = webService.consumeGet(urlBase, urlRelativo, id);
			if(pojoCargado==null)
			{
				System.err.println("Error con el webservice, objeto no cargado!");
			}else{
				return pojoCargado;
				
			}
		}else{
			System.err.println("No hay id valido para buscar en WebService");
		}
		return pojoCargado;
		
	}
	
	public boolean guardarNuevosDatosWebService(R r, Class<R> clase)
	{
		String urlBase = PropertyManager.getURLBase();
		
		String urlRelativo = propertyManager.getUrlRelativoDesdeClase(clase.getName());
		GenericWebServiceConsumer<R> webService = new GenericWebServiceConsumer<R>(clase);
		String respuesta = webService.consumePost(r, urlBase, urlRelativo);
		System.out.println("La respuesta es: " + respuesta);
		if(respuesta== null || respuesta.equals(""))
		{
			System.err.println("Error con el webservice, objeto no cargado!");
			return false;
		}
		else{
			return true;
		}	
	}
	
	public void actualizarDatosWebService(R r, Class<R> clase)
	{
		String urlBase = PropertyManager.getURLBase();
		
		String urlRelativo = propertyManager.getUrlRelativoDesdeClase(clase.getName());
		GenericWebServiceConsumer<R> webService = new GenericWebServiceConsumer<R>(clase);
		webService.consumePut(r, urlBase, urlRelativo);
	}
	
	public void borrarDatosWebService(String id, String rev, Class<R> clase)
	{
		String urlBase = PropertyManager.getURLBase();
		
		String urlRelativo = propertyManager.getUrlRelativoDesdeClase(clase.getName());
		String urlFinal = urlBase.concat("/").concat(urlRelativo);
		GenericWebServiceConsumer<R> webService = new GenericWebServiceConsumer<R>(clase);
		webService.consumeDelete(urlFinal, id, rev);
		
	}
	
	public List<R> cargarListaDatosWebService(Class<R> clase)
	{
		List<R> listaPojosCargado = null;
		if(clase!=null)
		{
			String urlBase = PropertyManager.getURLBase();
			
			String urlRelativo = propertyManager.getUrlRelativoDesdeClase(clase.getName());
			GenericWebServiceConsumer<R> webService = new GenericWebServiceConsumer<R>(clase);
			
			String urlFinal = urlBase.concat("/").concat(urlRelativo);
			
			listaPojosCargado = webService.consumeGetAll(urlFinal);
			if(listaPojosCargado==null)
			{
				System.err.println("Error con el webservice, lista de objetos no cargada!");
			}else{
				return listaPojosCargado;
				
			}
		}
		return listaPojosCargado;
		
	}

	public static void mostrarAlertaError(String mensaje)
	{
		Alert alertError = new Alert(AlertType.ERROR);
		alertError.setTitle("Error");
		alertError.setContentText(mensaje);
		alertError.show();
	}
	
	public static void mostrarAlertaInformacion(String mensaje)
	{
		Alert alertError = new Alert(AlertType.INFORMATION);
		alertError.setTitle("Informacion");
		alertError.setContentText(mensaje);
		alertError.show();
	}
	
	public static <X> X abrirVistaModal(String uriVista, String titulo, X x){
		X x1 = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(uriVista));
			Parent page = (Parent) loader.load();
			Stage stage = new Stage();
			stage.setTitle(titulo);
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(page);
			stage.setScene(scene);
			ControllerModalBase<X> controller = loader.getController();
			controller.setPojo(x);
			controller.setDialogStage(stage);
			stage.showAndWait();

			x1 = controller.getPojo();
			System.out.println(x1);
		} catch(Exception e) {
			e.printStackTrace(); //Retorna Connection reset cuando demora mucho
		}
		return x1;
	}
}
