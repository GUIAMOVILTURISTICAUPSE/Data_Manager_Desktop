package controllers;

//import java.awt.ScrollPane;
import java.util.ArrayList;

import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.Main;
import pojos.*;

public class ControllerRecurso {
public ControllerRecurso() {}

	@FXML private Button btnBuscarID;
	@FXML private TextField textId;
	@FXML private Pane guno;
	@FXML private Accordion gdos;
	@FXML private Pane gtres;
	@FXML private ScrollPane gcuatro;
	@FXML private Pane gcinco;
	@FXML private Pane gseis;
	
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
	@FXML private CheckBox checkAcceso1;
	@FXML private CheckBox checkAcceso2;
	@FXML private CheckBox checkAcceso3;
	@FXML private CheckBox checkAcceso4;
	@FXML private ComboBox<Facilidad> comboFacilidad;
	@FXML private ComboBox<Recomendacion> comboRecomendacion;
	@FXML private ComboBox<Contacto> comboContactos;
	@FXML private ListView<Imagen> listViewImagenes;
	@FXML private ListView<Sendero> listViewSenderos;
	@FXML private ListView<String> listTiposParqueo;
	@FXML private ListView<String> listPreguntas;
	@FXML private ListView<TipoAtractivo> listTiposAtractivos;
	@FXML private Button btnCargarWS;
	ObservableList<Idiomas> selectedItems;
	ObservableList<Imagen> selectedItemsImagen;
	ArrayList<AccesibilidadRecurso> pojoOpcionesAccesibilidad;
	ObservableList<Sendero> selectItemsSenderos;
	ObservableList<TipoAtractivo> selectItemsAtractivo;
	ObservableList<String> selectItemsTiposParqueo;
	public void initialize(){
		setPromptText();
		ObservableList<String> tiposParqueo = FXCollections.observableArrayList("Bicicleta","Moto","Vehiculo","Casilleros");
		listTiposParqueo.setItems(tiposParqueo);
		listTiposParqueo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listTiposParqueo.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				selectItemsTiposParqueo = listTiposParqueo.getSelectionModel().getSelectedItems();
			}
		});
		
		Sendero sendero1 = new Sendero();
		sendero1.setNombre("Camino Hacia el terror");
		ArrayList<Sendero> nombreSenderos = new ArrayList<Sendero>();
		nombreSenderos.add(sendero1);	
			ObservableList<Sendero> senderosList = FXCollections.observableArrayList(nombreSenderos);
			listViewSenderos.setItems(senderosList);
			listViewSenderos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			listViewSenderos.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					selectItemsSenderos = listViewSenderos.getSelectionModel().getSelectedItems();
				}
			});
		ObservableList<Idiomas> testes = FXCollections.observableArrayList(Idiomas.values());
		listViewIdiomas.setItems(testes);
		listViewIdiomas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listViewIdiomas.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                selectedItems =  listViewIdiomas.getSelectionModel().getSelectedItems();
            }

        });
		
		ObservableList<TipoAtractivo> tipoAtractivos = FXCollections.observableArrayList(TipoAtractivo.values());
		listTiposAtractivos.setItems(tipoAtractivos);
		listTiposAtractivos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listTiposAtractivos.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				selectItemsAtractivo = listTiposAtractivos.getSelectionModel().getSelectedItems();
				
			}
		});
		
		Imagen pojoI1 = new Imagen();
		pojoI1.setTitulo("Foto1");
		pojoI1.setUrl("imagenes/foto1.jpg");
		Imagen pojoI2 = new Imagen();
		pojoI2.setTitulo("Foto2");
		pojoI2.setUrl("imagenes/foto2.jpg");
		Imagen pojoI3 = new Imagen();
		pojoI3.setTitulo("Foto3");
		pojoI3.setUrl("imagenes/foto3.jpg");
		Imagen pojoI4 = new Imagen();
		pojoI4.setTitulo("Foto4");
		pojoI4.setUrl("imagenes/foto4.jpg");
		ArrayList<Imagen> imagenlista = new ArrayList<Imagen>();
		imagenlista.add(pojoI1);
		imagenlista.add(pojoI2);
		imagenlista.add(pojoI3);
		imagenlista.add(pojoI4);
		
		ObservableList<Imagen> testesImagen = FXCollections.observableArrayList(imagenlista);
		listViewImagenes.setItems(testesImagen);
		listViewImagenes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listViewImagenes.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
            	selectedItemsImagen =  listViewImagenes.getSelectionModel().getSelectedItems();
            }
        });
		
		textRanking.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	        	if (!textRanking.getText().matches("^[0-9]*\\.?[0-9]*$")) {
	        		textRanking.setText("");
                }
	        }
	    });
		
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
		
		Contacto pojoCT1 = new Contacto();
		pojoCT1.setNombreResponsable("Nombre 1");
		pojoCT1.setTelefono("Telefono 1");
		Contacto pojoCT2 = new Contacto();
		pojoCT2.setNombreResponsable("Nombre 2");
		pojoCT2.setTelefono("Telefono 2");
		ArrayList<Contacto> contactolista = new ArrayList<Contacto>();
		contactolista.add(pojoCT1);
		contactolista.add(pojoCT2);
		ObservableList<Contacto> contactos = FXCollections.observableArrayList(contactolista);
		comboContactos.setItems(contactos);
		
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
	}
	
	
	int act=0;
	int actC1=0;
	int actC2=0;
	int actC3=0;
	int actC4=0;
	private ArrayList<String> preguntas = new ArrayList<>();
	Recurso pojo = new Recurso();
	
	public Recurso Guardar(){
		Recurso pojo = new Recurso();
	//	pojo.setId(textId.getText().trim());
		pojo.setNombre(textNombre.getText());
		pojo.setDescripcion(textDescripcion.getText());
		pojo.setInformacionGeneral(textInfGeneral.getText());
		pojo.setDireccion(textDireccion.getText());
		pojo.setPosicion(textPosicion.getText());
		pojo.setProvincia(textProvincia.getText());
		pojo.setCanton(textCanton.getText());
		pojo.setParroquia(textParroquia.getText());
		pojo.setPropietario(textPropietario.getText());
		pojo.setPersonaEncargada(textPersonaEncargada.getText());
		pojo.setCategoria(textCategoria.getText());
		
		if(selectedItems!=null && !selectedItems.isEmpty())
		{
			for(Idiomas s : selectedItems){
				 pojo.getIdiomasInformac().add(s);
				 System.out.println("selected item " + s.toString());
	        }
		}
		
		pojo.getCostoRecursos().add(comboCosto.getValue());
		float ranking = Float.parseFloat(textRanking.getText());
		pojo.setRanking(ranking);
		
		if (checkActivo.isSelected()== true){
			pojo.setEstado(Estado.ACTIVO);
			
		}
		else{
			pojo.setEstado(Estado.INACTIVO);
		}
		
		
			for (TipoAtractivo a : selectItemsAtractivo) {
				pojo.getTipoAtractivo().add(a);
				System.out.println("seleccion  " + a.toString());
			}
		
		
			for (String b :selectItemsTiposParqueo ) {
				pojo.getTiposParqueo().add(b);
				System.out.println("seleccion  " + b.toString());
			}

		
		
			for (Sendero c: selectItemsSenderos) {
				pojo.getSendero().add(c);
				System.out.println("seleccion  " + c.toString());
			}
		pojoOpcionesAccesibilidad = new  ArrayList<AccesibilidadRecurso>();
		if (checkAcceso1.isSelected()== true){
			actC1=1;
			pojoOpcionesAccesibilidad.add(new AccesibilidadRecurso("Acceso1"));
		}if (checkAcceso2.isSelected()== true){
			actC2=1;
			pojoOpcionesAccesibilidad.add(new AccesibilidadRecurso("Acceso2"));
		}if (checkAcceso3.isSelected()== true){
			actC3=1;
			pojoOpcionesAccesibilidad.add(new AccesibilidadRecurso("Acceso3"));
		}if (checkAcceso4.isSelected()== true){
			actC4=1;
			pojoOpcionesAccesibilidad.add(new AccesibilidadRecurso("Acceso4"));
		}
		pojo.setOpcionesAccesibilidad(pojoOpcionesAccesibilidad);
		pojo.getFacilidadRecurso().add(comboFacilidad.getValue());
		pojo.getRecomendacion().add(comboRecomendacion.getValue());
		pojo.setInfContacto(comboContactos.getValue());
		
		for(Imagen i : selectedItemsImagen){
			 pojo.getGaleria().add(i);
			 System.out.println("selected item " + i.toString());
       }	
		
		return pojo;
	}
	
	public void LimpiarPantalla(){

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
		listViewIdiomas.setItems(null);	
		listViewImagenes.setItems(null);
		checkAcceso1.setSelected(false);
		checkAcceso2.setSelected(false);
		checkAcceso3.setSelected(false);
		checkAcceso4.setSelected(false);
		comboFacilidad.setValue(null);
		textCanton.setText("");
		textProvincia.setText("");
		textParroquia.setText("");
		textPersonaEncargada.setText("");
		textPropietario.setText("");
		comboRecomendacion.setValue(null);
		comboContactos.setValue(null);
		textCategoria.setText("");
		listPreguntas.setItems(null);
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
			CargarDatos(r);
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
		}else{
			System.err.println("No se pudo guardar los datos a traves del web service.");
		}
		
	}
	
	private String id_;
	
	public String getId_() {
		return id_;
	}


	public void setId_(String id_) {
		this.id_ = id_;
	}


	public void abrirListaRecurso(){
		try {
			Recurso pojo_ = new Recurso();
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("/ViewListaRecurso.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        
	        Stage stage = new Stage();
	        stage.setTitle("Lista Recursos");
	        stage.initModality(Modality.WINDOW_MODAL);
	        Scene scene = new Scene(page);
	        stage.setScene(scene);
	        ControllerViewListaRecursos controller = loader.getController();
	        controller.setDialogStage(stage);
	        stage.showAndWait();
	        
	        pojo_ = controller.getPojo();
	        CargarDatos(pojo_);
		} catch(Exception e) {
			e.printStackTrace();
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
		textPersonaEncargada.setText(pojo.getPersonaEncargada());
		textCategoria.setText(pojo.getCategoria());
		
		ObservableList<Idiomas> idiomasSeleccionado = FXCollections.observableArrayList(pojo.getIdiomasInformac());
		listViewIdiomas.setItems(idiomasSeleccionado);
		ObservableList<String> ParqueoSeleccionado = FXCollections.observableArrayList(pojo.getTiposParqueo());
		listTiposParqueo.setItems(ParqueoSeleccionado);
		ObservableList<TipoAtractivo> TatractivoSeleccionado = FXCollections.observableArrayList(pojo.getTipoAtractivo());
		listTiposAtractivos.setItems(TatractivoSeleccionado);
		ObservableList<Sendero> senderoSeleccionado = FXCollections.observableArrayList(pojo.getSendero());
		listViewSenderos.setItems(senderoSeleccionado);
		
		comboCosto.setValue(pojo.getCostoRecursos().get(0));
		
			if(act==1){
				checkActivo.setSelected(true);
			}else{
				checkInactivo.setSelected(true);
			}
			if(actC1==1){
				checkAcceso1.setSelected(true);
			}
			if(actC2==1){
				checkAcceso2.setSelected(true);
			}
			if(actC3==1){
				checkAcceso3.setSelected(true);
			}
			if(actC4==1){
				checkAcceso4.setSelected(true);
			}
		comboFacilidad.setValue(pojo.getFacilidadRecurso().get(0));
		comboRecomendacion.setValue(pojo.getRecomendacion().get(0));
		comboContactos.setValue(pojo.getInfContacto());
		
		ObservableList<Imagen> imagenesSeleccionado = FXCollections.observableArrayList(pojo.getGaleria());
		listViewImagenes.setItems(imagenesSeleccionado);
		
		//textpreguntasf.setText(preguntas.get(0));	 
		
	}
	
	public void Salir(){
		System.exit(0);
	}
	
	public void checkActivo(){
		if(checkActivo.isSelected()){
			checkActivo.setSelected(true);
			checkInactivo.setSelected(false);
			act=1;
			
		}
	}
		
	public void checkInactivo(){
			if(checkInactivo.isSelected()){
				checkActivo.setSelected(false);
				checkInactivo.setSelected(true);
			}				
	}
	
	public void cargarPreguntas(){
		preguntas.add(textpreguntasf.getText());
		textpreguntasf.setText("");
		ObservableList<String> preguntas1 = FXCollections.observableArrayList(preguntas);
		listPreguntas.setItems(preguntas1);
	}
	
}
