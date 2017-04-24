package controllers;

//import java.awt.ScrollPane;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pojos.*;

public class ControllerRecurso {
public ControllerRecurso() {}


	public int contador=0;
	
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
	@FXML private Button btnCargarWS;
	ObservableList<Idiomas> selectedItems;
	ObservableList<Imagen> selectedItemsImagen;
	ArrayList<AccesibilidadRecurso> pojoOpcionesAccesibilidad;
 
	public void initialize(){
		ObservableList<Idiomas> testes = FXCollections.observableArrayList(Idiomas.values());
		listViewIdiomas.setItems(testes);
		listViewIdiomas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listViewIdiomas.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                selectedItems =  listViewIdiomas.getSelectionModel().getSelectedItems();
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
	        	if (!textRanking.getText().matches("[0-9]")) {
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
	
	
	int act=0;
	int actC1=0;
	int actC2=0;
	int actC3=0;
	int actC4=0;
	private ArrayList<String> preguntas = new ArrayList<>();
	Recurso pojo = new Recurso();
	
	public void Guardar(){
		pojo.setId(textId.getText());
		pojo.setNombre(textNombre.getText());
		pojo.setDescripcion(textDescripcion.getText());
		pojo.setInformacionGeneral(textInfGeneral.getText());
		pojo.setDireccion(textDireccion.getText());
		pojo.setPosicion(textPosicion.getText());
		
		if(selectedItems!=null && !selectedItems.isEmpty())
		{
			for(Idiomas s : selectedItems){
				 pojo.getIdiomasInformac().add(s);
				 System.out.println("selected item " + s.toString());
	        }
		}
		pojo.getCostoRecursos().add(comboCosto.getValue());
		float rank = Integer.parseInt(textRanking.getText());
		pojo.setRanking(rank);
		if (checkActivo.isSelected()== true){
			pojo.setEstado(Estado.ACTIVO);
			
		}
		else{
			pojo.setEstado(Estado.INACTIVO);
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
		
		
	}
	
	public void LimpiarPantalla(){
		textId.setText("");
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
		if(contador == 2){
			contador = 0;
			initialize();
			actC1=0;
			actC2=0;
			actC3=0;
			actC4=0;
		}else{
			listViewIdiomas.setItems(null);	
			listViewImagenes.setItems(null);
		}
		
		checkAcceso1.setSelected(false);
		checkAcceso2.setSelected(false);
		checkAcceso3.setSelected(false);
		checkAcceso4.setSelected(false);
		comboFacilidad.setValue(null);
		comboRecomendacion.setValue(null);
		comboContactos.setValue(null);
	}
	
	public void cargarDatosWebService()
	{
		String id = textId.getText();
		ControllerHelper<Recurso> controllerHelper= new ControllerHelper<Recurso>();
		Recurso r = controllerHelper.cargarDatosWebService(id, Recurso.class);
		if(r!=null)
		{
			pojo = r;
			CargarDatos();
		}
	}
	
	public void guardarDatosWebService()
	{
		ControllerHelper<Recurso> controllerHelper= new ControllerHelper<Recurso>();
		Guardar();
		System.out.println("El pojo a guardar en el WS es: " + pojo);
		if(controllerHelper.guardarNuevosDatosWebService(pojo, Recurso.class))
		{
			System.out.println("Datos guardados con exito");
		}else{
			System.err.println("No se pudo guardar los datos a traves del web service.");
		}
		
	}
	
	public void CargarDatos(){
		contador=2;
		textId.setText(pojo.getId());
		textNombre.setText(pojo.getNombre());
		textDescripcion.setText(pojo.getDireccion());
		textInfGeneral.setText(pojo.getInformacionGeneral());
		textDireccion.setText(pojo.getDireccion());
		textPosicion.setText(pojo.getPosicion());
		textRanking.setText(""+pojo.getRanking());
		ObservableList<Idiomas> idiomasSeleccionado = FXCollections.observableArrayList(pojo.getIdiomasInformac());
		listViewIdiomas.setItems(idiomasSeleccionado);
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
		textpreguntasf.setText(preguntas.get(0));	 
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
	}
	
}
