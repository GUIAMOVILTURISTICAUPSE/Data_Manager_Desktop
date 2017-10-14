package controllers;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pojos.Atractivo;
import pojos.Estado;
import pojos.Imagen;
import pojos.TipoAtractivo;


public class ControllerAtractivo implements ControllerModalBase<Atractivo>{

	@FXML private TextField txtFieldID;
	@FXML private TextField txtFieldNombre;
	@FXML private TextField txtFieldDescripcion;
	@FXML private TextField txtFieldTituloImagen;
	@FXML private ComboBox<Imagen> cboGaleria;
	@FXML private ComboBox<Estado> cboEstado;
	@FXML private ComboBox<TipoAtractivo> cboTipoAtractivo;
	@FXML private Button btnCargar;
	@FXML private Button btnGuardar;
	@FXML private Button btnLimpiar;
	@FXML private Button btnSalir;
	@FXML private Button btnAtras;
	@FXML private Button btncargarD;
	@FXML private ListView<Estado> listE;
	@FXML private ListView<Imagen> listViewImagenes;
	Imagen selectedItemsImagen;
	
	Atractivo pojo = new Atractivo();
	private Imagen pojoImagen;
	private Stage stage;
	public ControllerAtractivo() {
	
	}
	public void initialize(){
		setPromptText();
		cargarTipoAtractivo();
		cargarListasString();
		cargarEstado()	;
	}
	
	
	private void setPromptText() {
		txtFieldID.setPromptText("Ingrese ID");
		txtFieldNombre.setPromptText("Nombre");
		txtFieldDescripcion.setPromptText("Descripcion");
		txtFieldTituloImagen.setPromptText("Titulo ");
		cboTipoAtractivo.setPromptText("Tipo Atractivo");
		cboEstado.setPromptText("Estado");

	}
	
	public void Limpiar(){
		txtFieldID.setText("");
		txtFieldNombre.setText("");
		txtFieldDescripcion.setText("");
		txtFieldTituloImagen.setText("");		
		cboEstado.setValue(null);
		cboTipoAtractivo.setValue(null);
		cboGaleria.setValue(null);
		listViewImagenes.getItems().clear();
	}
	
	public Atractivo Guardar(){
		Atractivo pojoTemp = new Atractivo();		
		if(pojo!=null)
		{
			pojoTemp = pojo;

		}		
		pojoTemp.set_id(txtFieldID.getText().trim());
		pojoTemp.setNombre(txtFieldNombre.getText());
		pojoTemp.setDescripcion(txtFieldDescripcion.getText());
		pojoTemp.setEstado(cboEstado.getValue());
		pojoTemp.setTipo(cboTipoAtractivo.getValue());
		pojoTemp.getGaleria().clear();
		if((listViewImagenes.getItems().size() != 0)){
			for(Imagen image : listViewImagenes.getItems()){
				pojoTemp.getGaleria().add(image);
			}	
		}
		pojoTemp.setImagenPrincipal(selectedItemsImagen);
		stage.close();
		pojo = pojoTemp;
		return pojoTemp;
	}

	public void cargarDatos(Atractivo pojos){
		if(pojo==null)
		{
			pojo = new Atractivo();
		}
		txtFieldID.setText(pojos.get_id());
		txtFieldNombre.setText(pojos.getNombre());
		txtFieldDescripcion.setText(pojos.getDescripcion());
		ObservableList<Estado> ListEstado = FXCollections.observableArrayList(pojos.getEstado());
		cboEstado.setItems(ListEstado);
		ObservableList<TipoAtractivo> LTipoAtractivo = FXCollections.observableArrayList(pojos.getTipo());
		cboTipoAtractivo.setItems(LTipoAtractivo);
		
		ObservableList<Imagen> imagenesSeleccionado = FXCollections.observableArrayList(pojos.getGaleria());
		listViewImagenes.setItems(imagenesSeleccionado);
		listViewImagenes.setOnMouseClicked(e -> pojoImagen = listViewImagenes.getSelectionModel().getSelectedItem());
		txtFieldTituloImagen.setText(pojos.imagenPrincipal.toString());
	}
	
	public void cargarDatosWebService()
	{
		String nombre = txtFieldNombre.getText();
		ControllerHelper<Atractivo> controllerHelper= new ControllerHelper<Atractivo>();
		Atractivo r = controllerHelper.cargarDatosWebService(nombre, Atractivo.class);
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
		ControllerHelper<Atractivo> controllerHelper= new ControllerHelper<Atractivo>();
		Atractivo pojo = Guardar();
		System.out.println("El pojo a guardar en el WS es: " + pojo);
		if(controllerHelper.guardarNuevosDatosWebService(pojo, Atractivo.class))
		{
			System.out.println("Datos guardados con exito");
		}else{
			System.err.println("No se pudo guardar los datos a traves del web service.");
		}

	}

	public void borrarDatosWebService()
	{
		ControllerHelper<Atractivo> controllerHelper= new ControllerHelper<Atractivo>();
		Atractivo pojos = Guardar();
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
					controllerHelper.borrarDatosWebService(pojos.getNombre(), pojos.get_sync().getRev(),Atractivo.class);
					Alert alertBorradoCorrecto = new Alert(AlertType.INFORMATION);
					alertBorradoCorrecto.setTitle("Borrado Correcto");

					String message = "Borrado realizado con exito.";
					alertBorradoCorrecto.setContentText(message);
					alertBorradoCorrecto.show();
					Limpiar();

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
	
	public void abrirPantallaModalNuevaImagen()
	{

		System.out.println("Pojo de la Imagen: " + pojo);
		if(!txtFieldNombre.getText().isEmpty()){
			pojoImagen = ControllerHelper.abrirVistaModal("/ViewImagen.fxml", "Imagen", null);
			if(pojoImagen!=null){
				if(!pojoImagen.getUrl().isEmpty()){
					listViewImagenes.getItems().add(pojoImagen);				
					pojo.getGaleria().add(pojoImagen);
				}
				/*if (pojoImagen.getUrl()==null){
					pojoImagen.setUrl("www.google.com.ec");
				}
				listViewImagenes.getItems().add(pojoImagen);				
				pojo.getGaleria().add(pojoImagen);*/
			}		
		}else{
			ControllerHelper.mostrarAlertaInformacion("El recurso al que se asignara la imagen no tiene nombre... asignele un nombre al Recurso ");
		}
	}

	public void abrirPantallaModalCargarImagen()
	{
		if(!listViewImagenes.getItems().isEmpty()){
			if(listViewImagenes.getSelectionModel().getSelectedItem() != null){
				Imagen pojoCargado = ControllerHelper.abrirVistaModal("/ViewImagen.fxml", "Imagen", pojoImagen);
				if(pojoCargado!=null){
					pojoImagen = pojoCargado;
				}else {
					ControllerHelper.mostrarAlertaInformacion("No se cargo el pojo de Imagen.");
				}
			}else{
				ControllerHelper.mostrarAlertaInformacion("Seleccione alguna imagen");
			}
		}else{
			ControllerHelper.mostrarAlertaInformacion("El recurso no cuenta con ninguna imagen... Debe crear alguna imagen y seleccionarla");
		}
	}
	
	public void cargarListasString()
	{
		listViewImagenes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewImagenes.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				selectedItemsImagen =  listViewImagenes.getSelectionModel().getSelectedItem();
				txtFieldTituloImagen.setText(selectedItemsImagen.toString());
			}
		});
	}
	public void cargarEstado()
	{
		//******* tiposEstado
		ObservableList<Estado> tiposEstado = FXCollections.observableArrayList(Estado.values());
		cboEstado.setItems(tiposEstado);
	}
	public void cargarTipoAtractivo()
	{
		//******* TipoAtractivo
		ObservableList<TipoAtractivo> tiposAtractivo = FXCollections.observableArrayList(TipoAtractivo.values());
		cboTipoAtractivo.setItems(tiposAtractivo);
	}
	@Override
	public void cancelar()
	{
		pojo = null;
		stage.close();
	}

	@Override
	public Atractivo getPojo() {
		return pojo;
	}

	@Override
	public void setPojo(Atractivo x) {
		pojo = x;
		if(x!=null)
			cargarDatos(x);
	}

	@Override
	public void setDialogStage(Stage stage) {
		this.stage = stage;
	}
	public void salir(){
		System.out.println("************** EXIT *********\n");
		stage.close();
	}
}
