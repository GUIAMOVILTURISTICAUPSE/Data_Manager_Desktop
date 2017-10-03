package controllers;


import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pojos.Costo;
import pojos.Estado;
import pojos.Imagen;
import pojos.TipoTransporte;
import pojos.Transporte;

public class ControllerTransporteSendero implements ControllerModalBase<Transporte>{
	@FXML private TextField txtIdTSendero;
	@FXML private TextField textDuracion;
	@FXML private TextField textDistancia;
	@FXML private TextArea textAreaDescripcion;
	@FXML private ComboBox<TipoTransporte> combotipoTransporte;
	@FXML private ComboBox<Costo> comboCosto;
	@FXML private ListView<Imagen> listViewImagen;
	@FXML private CheckBox checkObligatorio;
	@FXML private CheckBox checkActivo;
	
	
	Transporte pojo = new Transporte();
	private Stage stage;
	
	ObservableList<Imagen> selectedItemsImagen;
	public void initialize(){
		setPromptText(); 
		listViewImagenes();
		TipoTransporte();
		costoss();

	}
	
	public ControllerTransporteSendero() {
		// TODO Auto-generated constructor stub
	}
	
	
	private void setPromptText() {
		txtIdTSendero.setPromptText("Ingrese Id");
		textDuracion.setPromptText("Ingrese Duracion");
		textDistancia.setPromptText("Ingrese Distancia");
		textAreaDescripcion.setPromptText("Seleccione Ingrese Descripcion");
		combotipoTransporte.setPromptText("Seleccione Tipo Transporte");
		comboCosto.setPromptText("Seleccione un Costos");
	}
	public void limpiar(){
		txtIdTSendero.setText("");
		textDuracion.setText("");
		textDistancia.setText("");
		textAreaDescripcion.setText("");
		combotipoTransporte.setItems(null);
		comboCosto.setItems(null);
		listViewImagen.setItems(null);
		checkActivo.isSelected();
		initialize();
	}
	
	public Transporte guardar(){


		Transporte pojotemp = new Transporte();
		if (pojo!=null) {
			pojotemp = pojo;
		}
		pojotemp.set_id(txtIdTSendero.getText().trim());
		pojotemp.setDuracion(Float.parseFloat(textDuracion.getText()));
		pojotemp.setDistancia(Float.parseFloat(textDistancia.getText()));
		pojotemp.setDescripcion(textAreaDescripcion.getText());
		if (checkActivo.isSelected()== true){
			pojotemp.setEstado(Estado.ACTIVO);

		}
		else{
			pojotemp.setEstado(Estado.INACTIVO);
		}

		pojotemp.setCostoRecurso(comboCosto.getValue());
		pojotemp.setTipoTransporte(combotipoTransporte.getValue());
		pojotemp.setImagen(null);

		stage.close();
		pojo = pojotemp;
		return pojotemp;
	}
	
	public void cargarDatos(Transporte pojos){
		if(pojo==null)
		{
			pojo = new Transporte();
		}
		txtIdTSendero.setText(pojos.get_id());
		textDuracion.setText(String.valueOf(pojos.getDuracion()));
		textDistancia.setText(String.valueOf(pojos.getDistancia()));
		textAreaDescripcion.setText(String.valueOf(pojos.getDescripcion()));
		ObservableList<Costo> ListCosto = FXCollections.observableArrayList(pojos.getCostoRecurso());
		comboCosto.setItems(ListCosto);
		ObservableList<TipoTransporte> LtransporteSendero = FXCollections.observableArrayList(pojos.getTipo());
		combotipoTransporte.setItems(LtransporteSendero);
		ObservableList<Imagen> LImagenes = FXCollections.observableArrayList(pojos.getImagen());
		listViewImagen.setItems(LImagenes);
	}
	
	public void cargarDatosWebService()
	{
		String nombre = txtIdTSendero.getText();
		ControllerHelper<Transporte> controllerHelper= new ControllerHelper<Transporte>();
		Transporte r = controllerHelper.cargarDatosWebService(nombre, Transporte.class);
		System.out.println(r);
		if(r!=null)
		{
			pojo = r;
			cargarDatos(r);
		}else{
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("Error al cargar Recurso");

			String errorMessage = "Problema para cargar el recurso, probablemente no existe el recurso con el id: " + nombre;
			alertError.setContentText(errorMessage);
			alertError.show();
		}
	}
	
	public void guardarDatosWebService()
	{
		ControllerHelper<Transporte> controllerHelper= new ControllerHelper<Transporte>();
		Transporte pojo = guardar();
		System.out.println("El pojo a guardar en el WS es: " + pojo);
		if(controllerHelper.guardarNuevosDatosWebService(pojo, Transporte.class))
		{
			System.out.println("Datos guardados con exito");
		}else{
			System.err.println("No se pudo guardar los datos a traves del web service.");
		}

	}

	public void borrarDatosWebService()
	{
		ControllerHelper<Transporte> controllerHelper= new ControllerHelper<Transporte>();
		Transporte pojos = guardar();
		System.out.println("El pojo a borrar en el WS es: " + pojos);

		System.out.println("Sync para obtener el rev: " + pojos.get_sync());

		if(pojos!=null && pojos.get_sync()!=null && pojos.get_sync().getRev()!=null && pojos.get_id()!=null)
		{
			//Enviar pantalla modal para confirmar que desea borrar
			//Hacer Leo
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmar Borrado");

			String s = "Confirme que desea borrar el recurso. El proceso no es reversible!";

			alert.setContentText(s);
			Optional<ButtonType> result = alert.showAndWait();
			//Ejecutar borrado

			System.out.println("antes de borrar");
			if ((result.isPresent()) && (result.get() == ButtonType.OK))
			{
				System.out.println("Borrando");
				try{
					controllerHelper.borrarDatosWebService(pojos.get_id(), pojos.get_sync().getRev(),Transporte.class);
					Alert alertBorradoCorrecto = new Alert(AlertType.INFORMATION);
					alertBorradoCorrecto.setTitle("Borrado Correcto");

					String message = "Borrado realizado con exito.";
					alertBorradoCorrecto.setContentText(message);
					alertBorradoCorrecto.show();
					limpiar();

				}catch(Exception e)
				{
					System.err.println("Excepcion:" + e);
					e.printStackTrace();
					System.err.println("Mensaje de la excepcion:" + e.getMessage());

					Alert alertError = new Alert(AlertType.ERROR);
					alertError.setTitle("Error al borrar");

					String errorMessage = "Problema para borrar el recurso. " + e.toString();
					alertError.setContentText(errorMessage);
					alertError.show();
				}
			}
		}
	} 
	
	public void Salir(){
		stage.close();
	}
	
	public void listViewImagenes()
	{
		/**********Imagen*********/
		Imagen imagen1 = new Imagen();
		imagen1.setTitulo("1234 ");
		imagen1.setUrl("dfghjtrewasrhj");
		Imagen imagen2 = new Imagen();
		imagen2.setTitulo("1478 ");
		imagen2.setUrl("asdfdgerdffgbth");
		ArrayList<Imagen> arrayImagenes = new ArrayList<Imagen>();
		arrayImagenes.add(imagen1);
		arrayImagenes.add(imagen2);
		ObservableList<Imagen> listaImagen = FXCollections.observableArrayList(arrayImagenes);
		listViewImagen.setItems(listaImagen);
		listViewImagen.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewImagen.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				selectedItemsImagen = listViewImagen.getSelectionModel().getSelectedItems();
			}

		});
	}
	
	public void TipoTransporte()
	{
		ObservableList<TipoTransporte> ListaTipoTransporte = FXCollections.observableArrayList(TipoTransporte.values());
		combotipoTransporte.setItems(ListaTipoTransporte);
		
	}
	public void costoss()
	{
		Costo costo1= new Costo();
		costo1.setDescripcion("001");
		costo1.setCosto(20);
		Costo costo2 = new Costo();
		costo2.setDescripcion("002");
		costo2.setCosto(20);
		ArrayList<Costo> ArrayCosto = new ArrayList<Costo>();
		ArrayCosto.add(costo1);
		ArrayCosto.add(costo2);
		ObservableList<Costo> CostosCargados = FXCollections.observableArrayList(ArrayCosto);
		comboCosto.setItems(CostosCargados);
	}

	@Override
	public Transporte getPojo() {
		// TODO Auto-generated method stub
		return pojo;
	}

	@Override
	public void setDialogStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}

	@Override
	public void setPojo(Transporte x) {
		// TODO Auto-generated method stub
		pojo = x;
		if(x!=null)
			cargarDatos(x);
	}

	//TODO Refactorizar esto en un metodo general en clase abstracta de nivel superior
	//Todos los metodos cancelar son iguales.
	@Override
	public void cancelar() {
		pojo = null;
		stage.close();
	}	
}
