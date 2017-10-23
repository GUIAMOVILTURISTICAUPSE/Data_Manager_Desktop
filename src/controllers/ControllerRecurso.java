package controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.api.ControlOrBuilder;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.Main;
import pojos.*;

public class ControllerRecurso {


	@FXML private Button btnBuscarID;
	@FXML private Button btnCargarSendero;
	@FXML private Button btnCrearSenderos;
	@FXML private Button btnIrImagen;
	@FXML private Button btnCargarCoordenada;
	
	@FXML private TextField textId;
	@FXML private Pane guno;
	@FXML private Accordion gdos;
	@FXML private Pane gtres;
	@FXML private ScrollPane gcuatro;
	@FXML private Pane gcinco;
	@FXML private Pane gseis;


	@FXML private TextField textTelefono;
	@FXML private TextField textFacebook;
	@FXML private TextField textTwitter;
	@FXML private TextField textInstagram;
	@FXML private TextField textEmail;
	@FXML private TextField textSeguridad;
	@FXML private TextField textHorario;
	@FXML private TableView<PreguntasFrecuentes> tablePreRes ;
	@FXML private TableColumn<PreguntasFrecuentes, String> columPregunta;
	@FXML private TableColumn<PreguntasFrecuentes, String> columRespuestas;
	@FXML private TextArea textRespuestas;

	@FXML private TextField textNombre;
	@FXML private TextArea textDescripcion;
	@FXML private TextArea textInfGeneral;
	@FXML private TextArea textDireccion;
	@FXML private TextField textPosicion;
	@FXML private TextField textRanking;
	@FXML private TextField textProvincia;
	@FXML private TextField textCanton;
	@FXML private TextField textParroquia;
	@FXML private TextField textPropietario;
	@FXML private TextField textPersonaEncargada;
	@FXML private TextField textCategoria;
	@FXML private ListView<Idiomas> listViewIdiomas;
	@FXML private CheckBox checkActivo;
	@FXML private CheckBox checkInactivo;
	@FXML private TextArea textpreguntasf;
	@FXML private ComboBox<Costo> comboCosto;
	@FXML private ComboBox<Facilidad> comboFacilidad;
	@FXML private ComboBox<Recomendacion> comboRecomendacion;
	@FXML private ComboBox<Contacto> comboContactos;
	@FXML private ComboBox<Imagen> cmb_Img_Principal;
	
	@FXML private ListView<Imagen> listViewImagenes;
	@FXML private ListView<Animacion> listViewAnimacion;
	@FXML private ListView<Sendero> listViewSenderos;
	@FXML private ListView<String> listTiposParqueo;
	@FXML private ListView<String> listPreguntas;
	@FXML private ListView<TipoAtractivo> listTiposAtractivos;
	@FXML private ListView<TipoAccesibilidad> listTipoAccesibilidad;
	@FXML private Button btnCargarWS;
	ObservableList<Idiomas> selectedItemsIdiomas;
	Imagen selectedItemsImagen;
	ObservableList<Sendero> selectItemsSenderos;
	ObservableList<TipoAtractivo> selectItemsAtractivo;
	ObservableList<String> selectItemsTiposParqueo;
	ObservableList<PreguntasFrecuentes> preguntastable;
	ArrayList<PreguntasFrecuentes> listapreguntas = new ArrayList<PreguntasFrecuentes>();
	ArrayList<PreguntasFrecuentes> listpreguntas = new ArrayList<>();
	ObservableList<TipoAccesibilidad> selectItemsTipoAccesibilidad;

	//Pojos para rellenar en otras pantallas y paso de objetos entre controladores
	private Sendero pojoSendero;
	private Imagen pojoImagen;
	private Animacion pojoAnimacion;

	public String id_sendero_cap;
	
	public ControllerRecurso()
	{	
	}

	Context context = Context.getInstance();
	
	Recurso pojo = new Recurso();
	
	
	public void initialize(){
		if(context.getRecurso()!=null)
		{
			CargarDatos(context.getRecurso());
			context.setRecurso(null);
		}
		setPromptText();
		//cargarListViewSendero();
		cargarTipoAccesibilidad();
		cargarIdiomas();
		cargarTipoAtractivo();
		cargarTiposDeParqueo();
		cargarListasConString();
		buscarCoordenada();
	}
	
	private void buscarCoordenada()
	{
		btnCargarCoordenada.setOnAction(evento ->{
			try {
				Guardar();
				Parent root = FXMLLoader.load(getClass().getResource("/ViewGoogleMap.fxml"));
				Stage escenario = new Stage();
				Scene escena = new Scene(root, 1100,500);
				escenario.setScene(escena);
				escenario.show();
				Stage stageRecurso = (Stage) textDireccion.getScene().getWindow();
				stageRecurso.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			});
	}
	

	private void setPromptText() {
		textNombre.setPromptText("Nombre");
		textDescripcion.setPromptText("Descripcion");
		textInfGeneral.setPromptText("Informacion General");
		textDireccion.setPromptText("Direccion ");
		textPosicion.setPromptText("Latitud - Longitud");
		textRanking.setPromptText("Ranking ");
		textCanton.setPromptText("Cantón ");
		textProvincia.setPromptText("Provincia ");
		textParroquia.setPromptText("Parroquia ");
		textPropietario.setPromptText("Propietario ");
		textPersonaEncargada.setPromptText("Persona Encargada ");
		textpreguntasf.setPromptText("Escriba su Pregunta");
		textCategoria.setPromptText("Categoria ");
		textEmail.setPromptText("Email");
		textFacebook.setPromptText("Facebook");
		textHorario.setPromptText("Horario");
		textInstagram.setPromptText("Instagram");
		textRespuestas.setPromptText("Respuestas");
		textSeguridad.setPromptText("Seguridad");
		textTelefono.setPromptText("Telefono");
		textTwitter.setPromptText("Twitter");
	}


	public Recurso Guardar(){

		Recurso pojoTemp = new Recurso();		

		//Para no poder informacion que puede estar ya cargada en el pojo desde el Ws
		//pero que no esta representada en la GUI
		if(pojo!=null)
		{
			pojoTemp = pojo;

		}		


		//	pojo.setId(textId.getText().trim());

		pojoTemp.setNombre(textNombre.getText());
		pojoTemp.setDescripcion(textDescripcion.getText());
		pojoTemp.setInformacionGeneral(textInfGeneral.getText());
		pojoTemp.setDireccion(textDireccion.getText());
		pojoTemp.setPosicion(textPosicion.getText());
		pojoTemp.setProvincia(textProvincia.getText());
		pojoTemp.setCanton(textCanton.getText());
		pojoTemp.setParroquia(textParroquia.getText());
		pojoTemp.setPropietario(textPropietario.getText());
		pojoTemp.setPersonaEncargada(textPersonaEncargada.getText());
		pojoTemp.setCategoria(textCategoria.getText());
		pojoTemp.setHorario(textHorario.getText());
		pojoTemp.setSeguridad(textSeguridad.getText());


		Contacto datoscontactos = new Contacto();
		datoscontactos.setTelefono(textTelefono.getText());
		datoscontactos.setTwitter(textTwitter.getText());
		datoscontactos.setFacebook(textFacebook.getText());
		datoscontactos.setEmail(textEmail.getText());
		datoscontactos.setInstagram(textInstagram.getText());

		pojoTemp.setInfContacto(datoscontactos);


		pojoTemp.setPreguntasF(listapreguntas);
		if(selectedItemsIdiomas!=null && !selectedItemsIdiomas.isEmpty())
		{
			for(Idiomas s : selectedItemsIdiomas){
				if(!pojoTemp.getIdiomasInformac().contains(s))
				{
					pojoTemp.getIdiomasInformac().add(s);
					System.out.println("selected item " + s.toString());
				}
			}
		}

		pojoTemp.getCostoRecursos().add(comboCosto.getValue());
		if(!textRanking.getText().isEmpty())
		{
			float ranking = Float.parseFloat(textRanking.getText());
			pojoTemp.setRanking(ranking);
		}

		if (checkActivo.isSelected()== true){
			pojoTemp.setEstado(Estado.ACTIVO);

		}
		else{
			pojoTemp.setEstado(Estado.INACTIVO);
		}

		if(selectItemsAtractivo  != null ){
			for (TipoAtractivo a : selectItemsAtractivo ) {
				if(!pojoTemp.getTipoAtractivo().contains(a))
				{
					pojoTemp.getTipoAtractivo().add(a);
					System.out.println("seleccion  " + a.toString());
				}
			}

		}

		if (selectItemsTiposParqueo != null ){
			for (String b :selectItemsTiposParqueo ) {
				if(!pojoTemp.getTiposParqueo().contains(b))
				{
					pojoTemp.getTiposParqueo().add(b);
					System.out.println("seleccion  " + b.toString());
				}
			}

		}

		if (selectItemsSenderos != null){

			for (Sendero c: selectItemsSenderos) {
				if(pojo.getSendero().contains(c))
				{
					pojoTemp.getSendero().add(c);
					System.out.println("seleccion  " + c.toString());
				}
			}

		}
		if (selectItemsTipoAccesibilidad != null){
			for (TipoAccesibilidad d :selectItemsTipoAccesibilidad) {
				if(!pojoTemp.getOpcionesTipoAccesibilidad().contains(d))
				{
					pojoTemp.getOpcionesTipoAccesibilidad().add(d);
					System.out.println("seleccion  " + d.toString());
				}
			}	
		}
		pojoTemp.getFacilidadRecurso().add(comboFacilidad.getValue());
		pojoTemp.getRecomendacion().add(comboRecomendacion.getValue());
		//pojo.setInfContacto(comboContactos.getValue());

		//pojoTemp.getGaleria().addAll(listViewImagenes.getItems());
		pojoTemp.getGaleria().clear();
		if((listViewImagenes.getItems().size() != 0)){
			for(Imagen image : listViewImagenes.getItems()){
				if(!pojo.getGaleria().contains(image))
				{
					pojoTemp.getGaleria().add(image);
				}
			}	
		}
		
		if((listViewAnimacion.getItems().size() != 0)){
			for(Animacion animacion : listViewAnimacion.getItems()){
				if(!pojo.getAnimaciones().contains(animacion))
				{
					pojoTemp.getAnimaciones().add(animacion);
				}
			}	
		}
		
		pojoTemp.setimagenPrincipal(cmb_Img_Principal.getValue());


		/*
		if (selectedItemsImagen != null){
			for(Imagen i : selectedItemsImagen){
				 pojoTemp.getGaleria().add(i);
				 System.out.println("selected item " + i.toString());
	       }	
		}*/	
		
		context.setRecurso(pojoTemp);
		return pojoTemp;
	}

	public void limpiarPantalla(){
		textEmail.setText("");
		textFacebook.setText("");
		textHorario.setText("");
		textInstagram.setText("");
		textRespuestas.setText("");
		textSeguridad.setText("");
		textTelefono.setText("");
		textTwitter.setText("");
		//tablePreRes.setItems(null);
		textNombre.setText("");
		textDescripcion.setText("");
		textInfGeneral.setText("");
		textDireccion.setText("");
		textPosicion.setText("");
		textRanking.setText("");
		checkActivo.setSelected(false);
		checkInactivo.setSelected(false);
		comboCosto.setValue(null);
		textpreguntasf.setText("");
		//listViewIdiomas.setItems(null);	
		listViewImagenes.getItems().clear();
		listViewAnimacion.getItems().clear();
		listViewSenderos.getItems().clear();
		//listViewSenderos.setItems(null);
		comboFacilidad.setValue(null);
		textCanton.setText("");
		textProvincia.setText("");
		textParroquia.setText("");
		textPersonaEncargada.setText("");
		textPropietario.setText("");
		comboRecomendacion.setValue(null);
		//comboContactos.setValue(null);
		textCategoria.setText("");
		tablePreRes.getItems().clear();
		cmb_Img_Principal.getItems().clear();
		
		initialize();
	}

	public void cargarDatosWebService()
	{
		String nombre = textNombre.getText();
		ControllerHelper<Recurso> controllerHelper= new ControllerHelper<Recurso>();
		Recurso r = controllerHelper.cargarDatosWebService(nombre, Recurso.class);
		System.out.println(r);
		if(r!=null)
		{
			pojo = r;
			CargarDatos(r);
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
		ControllerHelper<Recurso> controllerHelper= new ControllerHelper<Recurso>();
		Recurso pojo = Guardar();
		System.out.println("El pojo a guardar en el WS es: " + pojo);
		if(controllerHelper.guardarNuevosDatosWebService(pojo, Recurso.class))
		{
			System.out.println("Datos guardados con exito");
			ControllerHelper.mostrarAlertaInformacion("Recurso creado con Exito.");
		}else{
			System.err.println("No se pudo guardar los datos a traves del web service.");
			ControllerHelper.mostrarAlertaError("No se pudo guardar los datos a traves del web service.");
		}

	}

	public void actualizarDatosWebService()
	{
		ControllerHelper<Recurso> controllerHelper= new ControllerHelper<Recurso>();
		Recurso pojo = Guardar();
		System.out.println("El pojo a guardar en el WS es: " + pojo);
		try {
			controllerHelper.actualizarDatosWebService(pojo, Recurso.class);
			ControllerHelper.mostrarAlertaInformacion("Recurso actualizado con Exito.");
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error para actualizar recurso.");
			ControllerHelper.mostrarAlertaError("Error para actualizar recurso." + e.getMessage());
		}
	}

	public void borrarDatosWebService()
	{
		ControllerHelper<Recurso> controllerHelper= new ControllerHelper<Recurso>();
		Recurso pojoTemp = Guardar();
		System.out.println("El pojo a borrar en el WS es: " + pojoTemp);

		System.out.println("Sync para obtener el rev: " + pojoTemp.get_sync());

		if(pojoTemp!=null && pojoTemp.get_sync()!=null && pojoTemp.get_sync().getRev()!=null && pojoTemp.getId()!=null)
		{
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
					controllerHelper.borrarDatosWebService(pojoTemp.getId(), pojoTemp.get_sync().getRev(), Recurso.class);
					Alert alertBorradoCorrecto = new Alert(AlertType.INFORMATION);
					alertBorradoCorrecto.setTitle("Borrado Correcto");

					String message = "Borrado realizado con exito.";
					alertBorradoCorrecto.setContentText(message);
					alertBorradoCorrecto.show();
					limpiarPantalla();

				}catch(Exception e)
				{
					System.err.println("Excepcion:" + e);
					e.printStackTrace();
					System.err.println("Mensaje de la excepcion:" + e.getMessage());

					ControllerHelper.mostrarAlertaError("Error al borrar " + e.getMessage());
				}
			}
		}
	}


	public void abrirPantallaModalListaRecurso(){
		pojo = ControllerHelper.abrirVistaModal("/ViewTableRecurso.fxml", "Lista Recursos", null);
		System.out.println(pojo);
		CargarDatos(pojo);
	}

	public void abrirPantallaModalNuevoSendero()
	{
		pojoSendero = ControllerHelper.abrirVistaModal("/ViewSenderos.fxml", "Sendero", null);
		if(pojoSendero!=null)
			listViewSenderos.getItems().add(pojoSendero);

		pojo.getSendero().add(pojoSendero);			

		if(pojoSendero!=null){
			pojo.getSendero().add(pojoSendero);
		}else{
			ControllerHelper.mostrarAlertaError("Cerro sin llenar el formulario.");
		}

	}

	public void abrirPantallaModalCargarSendero()
	{
		if(!listViewSenderos.getItems().isEmpty()){
			if(listViewSenderos.getSelectionModel().getSelectedItem() != null){
				Sendero pojoCargado = ControllerHelper.abrirVistaModal("/ViewSenderos.fxml", "Sendero", pojoSendero);
				if(pojoCargado!=null) 
					pojoSendero = pojoCargado;
			}else{
				ControllerHelper.mostrarAlertaError("Seleccione algun sendero... por favor");
			}
		}else{
			ControllerHelper.mostrarAlertaError("El recurso no cuenta con ningun sendero... Debe crear un sendero y seleccionarla");
		}
	}

	public void abrirPantallaModalNuevaImagen()
	{
		pojo = Guardar();	

		System.out.println("Pojo de la Imagen: " + pojo);
		if(!textNombre.getText().isEmpty()){
			pojoImagen = ControllerHelper.abrirVistaModal("/ViewImagen.fxml", "Imagen", null);
			if(pojoImagen!=null){
				if(!pojoImagen.getUrl().isEmpty()){
					listViewImagenes.getItems().add(pojoImagen);				
					pojo.getGaleria().add(pojoImagen);
				}
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
	
	public void abrirPantallaModalNuevaAnimacion()
	{
		pojo = Guardar();	

		System.out.println("Pojo actual: " + pojo);
		if(!textNombre.getText().isEmpty()){
			pojoAnimacion = ControllerHelper.abrirVistaModal("/ViewAnimacion.fxml", "Animacion", null);
			if(pojoAnimacion!=null){
				if(!pojoAnimacion.getUrl().isEmpty()){
					listViewAnimacion.getItems().add(pojoAnimacion);				
					pojo.getAnimaciones().add(pojoAnimacion);
				}
			}		
		}else{
			ControllerHelper.mostrarAlertaInformacion("El recurso al que se asignara la animacion no tiene nombre... asignele un nombre al Recurso ");
		}
	}

	public void abrirPantallaModalCargarAnimacion()
	{
		if(!listViewAnimacion.getItems().isEmpty()){
			if(listViewAnimacion.getSelectionModel().getSelectedItem() != null){
				Animacion pojoCargado = ControllerHelper.abrirVistaModal("/ViewAnimacion.fxml", "Animacion", pojoAnimacion);
				if(pojoCargado!=null){
					pojoAnimacion = pojoCargado;
				}else {
					ControllerHelper.mostrarAlertaInformacion("No se cargo el pojo de Animacion.");
				}
			}else{
				ControllerHelper.mostrarAlertaInformacion("Seleccione alguna animacion");
			}
		}else{
			ControllerHelper.mostrarAlertaInformacion("El recurso no cuenta con ninguna animacion... Debe crear alguna animacion y seleccionarla");
		}
	}
	

	public void CargarDatos(Recurso pojo){
		textNombre.setText(pojo.getId());
		textDescripcion.setText(pojo.getDescripcion());
		textInfGeneral.setText(pojo.getInformacionGeneral());
		textDireccion.setText(pojo.getDireccion());
		textPosicion.setText(pojo.getPosicion());
		textRanking.setText(String.valueOf(pojo.getRanking()));
		textCanton.setText(pojo.getCanton());
		textProvincia.setText(pojo.getProvincia());
		textParroquia.setText(pojo.getParroquia());
		textPropietario.setText(pojo.getPropietario());



		textPersonaEncargada.setText(pojo.getPersonaEncargada()!=null?pojo.getPersonaEncargada():"");

		textCategoria.setText(pojo.getCategoria());
		textSeguridad.setText(pojo.getSeguridad());
		textHorario.setText(pojo.getHorario());

		if(pojo.getInfContacto()!=null)

		{
			Contacto c = pojo.getInfContacto();
			textFacebook.setText(c.getFacebook());
			textInstagram.setText(c.getInstagram());
			textEmail.setText(c.getEmail());
			textTwitter.setText(c.getTwitter());
			textTelefono.setText(c.getTwitter());
		}
		ObservableList<Idiomas> idiomasSeleccionado = FXCollections.observableArrayList(pojo.getIdiomasInformac());
		listViewIdiomas.setItems(idiomasSeleccionado);
		ObservableList<String> ParqueoSeleccionado = FXCollections.observableArrayList(pojo.getTiposParqueo());
		listTiposParqueo.setItems(ParqueoSeleccionado);
		ObservableList<TipoAtractivo> TatractivoSeleccionado = FXCollections.observableArrayList(pojo.getTipoAtractivo());
		listTiposAtractivos.setItems(TatractivoSeleccionado);
		ObservableList<Sendero> senderoSeleccionado = FXCollections.observableArrayList(pojo.getSendero());
		senderoSeleccionado = ControllerHelper.limpiarNullsObservableList(senderoSeleccionado);
		listViewSenderos.setItems(senderoSeleccionado);
		listViewSenderos.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				System.out.println("clicked on " + listViewSenderos.getSelectionModel().getSelectedItem());
				pojoSendero = listViewSenderos.getSelectionModel().getSelectedItem();
				//System.out.println(pojos);				
			}
		});
		ObservableList<TipoAccesibilidad> TaccesibilidadSelecionados = FXCollections.observableArrayList(pojo.getOpcionesTipoAccesibilidad());
		listTipoAccesibilidad.setItems(TaccesibilidadSelecionados);

		if(pojo.getCostoRecursos()!=null && pojo.getCostoRecursos().size()>0)
		{
			comboCosto.setValue(pojo.getCostoRecursos().get(0));
		}

		listpreguntas = pojo.getPreguntasF();
		System.out.println("estas son las preguntas "+listpreguntas);
		ObservableList<PreguntasFrecuentes> data = FXCollections.observableArrayList(listpreguntas);
		columPregunta.setCellValueFactory(new PropertyValueFactory<>("Preguntas"));
		columRespuestas.setCellValueFactory(new PropertyValueFactory<>("respPreguntas"));
		tablePreRes.setItems(data);

		if(pojo.getFacilidadRecurso()!=null && pojo.getFacilidadRecurso().size()>0)
		{
			comboFacilidad.setValue(pojo.getFacilidadRecurso().get(0));
		}
		if(pojo.getRecomendacion()!=null && pojo.getRecomendacion().size()>0)
		{
			comboRecomendacion.setValue(pojo.getRecomendacion().get(0));
		}

		ObservableList<Imagen> imagenesSeleccionado = FXCollections.observableArrayList(pojo.getGaleria());
		listViewImagenes.setItems(imagenesSeleccionado);
		listViewImagenes.setOnMouseClicked(e -> pojoImagen = listViewImagenes.getSelectionModel().getSelectedItem());
		
		ObservableList<Animacion> animaciones = FXCollections.observableArrayList(pojo.getAnimaciones());
		listViewAnimacion.setItems(animaciones);
		listViewAnimacion.setOnMouseClicked(e -> pojoAnimacion = listViewAnimacion.getSelectionModel().getSelectedItem());
		
		
		cmb_Img_Principal.setValue(pojo.getimagenPrincipal());
		cmb_Img_Principal.setItems(listViewImagenes.getItems());

	}

	public void Salir(){
		System.exit(0);
	}

	public void checkActivo(){
		if(checkActivo.isSelected()){
			checkActivo.setSelected(true);
			checkInactivo.setSelected(false);			
		}
	}

	public void checkInactivo(){
		if(checkInactivo.isSelected()){
			checkActivo.setSelected(false);
			checkInactivo.setSelected(true);
		}				
	}

	public void guardarPreguntas(){
		PreguntasFrecuentes preguntas1 = new PreguntasFrecuentes();
		preguntas1.setPreguntas(textpreguntasf.getText());
		preguntas1.setRespPreguntas(textRespuestas.getText());
		listapreguntas.add(preguntas1);
		System.out.println(listapreguntas);
		cargarDatosTablaPreguntas();
		textpreguntasf.setText("");
		textRespuestas.setText("");
	}


	public void cargarDatosTablaPreguntas(){

		if(tablePreRes.getItems().size()>0)
		{
			preguntastable.removeAll(preguntastable);
			tablePreRes.setItems(preguntastable);
		}
		preguntastable = FXCollections.observableArrayList(listapreguntas);
		tablePreRes.setEditable(true);

		columPregunta.setCellValueFactory(new PropertyValueFactory<>("Preguntas"));
		columRespuestas.setCellValueFactory(new PropertyValueFactory<>("respPreguntas"));
		tablePreRes.setItems(preguntastable);
	}





	public void cargarTipoAccesibilidad()
	{
		//********TIPO ACCESIBILIDAD
		ObservableList<TipoAccesibilidad> tipoAccesibilidad = FXCollections.observableArrayList(TipoAccesibilidad.values());
		listTipoAccesibilidad.setItems(tipoAccesibilidad);
		listTipoAccesibilidad.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listTipoAccesibilidad.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				selectItemsTipoAccesibilidad = listTipoAccesibilidad.getSelectionModel().getSelectedItems();

			}
		});
	}
	public void cargarIdiomas()
	{
		ObservableList<Idiomas> testes = FXCollections.observableArrayList(Idiomas.values());
		listViewIdiomas.setItems(testes);
		listViewIdiomas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listViewIdiomas.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				selectedItemsIdiomas =  listViewIdiomas.getSelectionModel().getSelectedItems();
			}

		});

	}
	public void cargarTipoAtractivo()
	{

		//*********TIPO ATRACTIVO	
		ObservableList<TipoAtractivo> tipoAtractivos = FXCollections.observableArrayList(TipoAtractivo.values());
		listTiposAtractivos.setItems(tipoAtractivos);
		listTiposAtractivos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listTiposAtractivos.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				selectItemsAtractivo = listTiposAtractivos.getSelectionModel().getSelectedItems();

			}
		});
	}

	public void cargarTiposDeParqueo()
	{
		//******* TIPOS DE PARQUEOS
		ObservableList<String> tiposParqueo = FXCollections.observableArrayList("Bicicleta","Moto","Vehiculo","Casilleros");
		listTiposParqueo.setItems(tiposParqueo);
		listTiposParqueo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listTiposParqueo.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				selectItemsTiposParqueo = listTiposParqueo.getSelectionModel().getSelectedItems();
			}
		});
	}

	public void cargarListasConString()
	{
		listViewImagenes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewImagenes.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				selectedItemsImagen =  listViewImagenes.getSelectionModel().getSelectedItem();
			}
		});
		
		listViewAnimacion.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		//TODO Decidir si debo ubicar un selected image como en el listView de Imagen arriba
		
		cmb_Img_Principal.setItems(listViewImagenes.getItems());

		Costo pojoC1 = new Costo();
		pojoC1.setDescripcion("Comida");
		pojoC1.setCosto(14);
		Costo pojoC2 = new Costo();
		pojoC2.setDescripcion("Hotel");
		pojoC2.setCosto(19);
		Costo pojoC3 = new Costo();
		pojoC3.setDescripcion("Motel");
		pojoC3.setCosto(19);
		ArrayList<Costo> costoslista = new ArrayList<Costo>();
		costoslista.add(pojoC1);
		costoslista.add(pojoC2);
		costoslista.add(pojoC3);
		ObservableList<Costo> costos = FXCollections.observableArrayList(costoslista);
		comboCosto.setItems(costos);

		Facilidad pojoA1 = new Facilidad();
		pojoA1.setTitulo("Facilidad1");
		pojoA1.setDescripcion("La facilidad1 es muy chevere");
		Facilidad pojoA2 = new Facilidad();
		pojoA2.setTitulo("Facilidad2");
		pojoA2.setDescripcion("La facilidad nos vamos en las dos");
		Facilidad pojoA3 = new Facilidad();
		pojoA3.setTitulo("Facilidad3");
		pojoA3.setDescripcion("Super facilidad");
		ArrayList<Facilidad> facilidadlista = new ArrayList<Facilidad>();
		facilidadlista.add(pojoA1);
		facilidadlista.add(pojoA2);
		facilidadlista.add(pojoA3);
		ObservableList<Facilidad> facilidades = FXCollections.observableArrayList(facilidadlista);
		comboFacilidad.setItems(facilidades);

		Recomendacion pojoR1 = new Recomendacion();
		pojoR1.setTitulo("Recomendacion1");
		pojoR1.setDescripcion("La Recomendacion1 es muy chevere");
		Recomendacion pojoR2 = new Recomendacion();
		pojoR2.setTitulo("Recomendacion2");
		pojoR2.setDescripcion("La recomendacion nos vamos en las dos");
		ArrayList<Recomendacion> recomendacionlista = new ArrayList<Recomendacion>();
		recomendacionlista.add(pojoR1);
		recomendacionlista.add(pojoR2);
		ObservableList<Recomendacion> recomendaciones = FXCollections.observableArrayList(recomendacionlista);
		comboRecomendacion.setItems(recomendaciones);

		textRanking.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!textRanking.getText().matches("^[0-9]*\\.?[0-9]*$")) {
					textRanking.setText("");
				}
			}
		});
	}

	
}
