package controllers;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pojos.Atractivo;
import pojos.Comentario;
import pojos.DificultadRecorrido;
import pojos.DisponibilidadCelular;
import pojos.Estado;
import pojos.Imagen;
import pojos.LocacionAtractivo;
import pojos.Recurso;
import pojos.Sendero;
import pojos.TipoTransporte;
import pojos.Transporte;
import javafx.event.ActionEvent;
import javafx.event.Event;

public class ControllerSenderos implements ControllerModalBase<Sendero>{

	@FXML private TextField txtid;
	@FXML private TextField txtduracion;
	@FXML private TextArea txtdescripcion;
	@FXML private TextArea txtinstrucciones;
	@FXML private TextField txtnombre;
	@FXML private TextField txtdistancia;
	@FXML private CheckBox checkActivo;
	@FXML private CheckBox checkInactivo;
	@FXML private ListView<DisponibilidadCelular> listDisponibilidadCelular;
	@FXML private ListView<String> listEquipamiento;
	@FXML private ListView<Transporte> listTransporteSendero;
	@FXML private ListView<LocacionAtractivo> listLAtractivos;
	@FXML private ListView<DificultadRecorrido> listDificultadRecorrido;
	@FXML private ListView<Imagen> listImagenes;
	@FXML private ComboBox<Comentario> comboComentario;
	@FXML private ListView<Atractivo> listatractivos; 
	@FXML private ListView<String> list_Recorrido; 
	@FXML private TextField txt_punto;
	@FXML private Button btn_agregar;
	@FXML private Button btn_modificar;
	@FXML private Button btn_eliminar;
	@FXML private Button btnBorrarAtractivo;
	
	
	Sendero pojo = new Sendero();
	private Transporte pojoTransporte ;
	private Atractivo pojoAtractivo ;
	public ControllerSenderos() {

	}
	ObservableList<Imagen> selectedItemsImagen;
	ObservableList<DificultadRecorrido> LTipoDificultad;
	ObservableList<DisponibilidadCelular> TipoDisponibilidadCelular;
	ObservableList<Transporte> TransporteOpciones;
	ObservableList<LocacionAtractivo> TipoLocacionAtractivo;
	ObservableList<String> TipoEquipamento;
	ObservableList<Atractivo> tipoAtractivo;
	ObservableList<String> punto;
	private Stage stage;

	public void initialize(){

		setPromptText(); 
		
		list_Recorrido.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				punto = list_Recorrido.getSelectionModel().getSelectedItems();
				if(!list_Recorrido.getItems().isEmpty()){
					for(String puntito : punto){
						txt_punto.setText(puntito);	
					}			
				}
			}
		});

		listaTransportes();
		listaAtractivo();
		dificultadRecorrido();
		disponibilidadCelular();
		 ListaLlenasString();
	}

	private void setPromptText() {
		txtid.setPromptText("Ingrese Id");
		txtduracion.setPromptText("Ingrese Duracion");
		txtdescripcion.setPromptText("Ingrese Descripcion");
		txtinstrucciones.setPromptText("Ingrese Instrucciones");
		txtnombre.setPromptText("Ingrese Nombre");
		txtdistancia.setPromptText("Ingrese distancia");
		comboComentario.setPromptText("Seleccion Comentario");		
		txt_punto.setPromptText("Example: -2.323232 , -80.808080");

	}
	public void limpiar(){
		txtid.setText("");
		txtduracion.setText("");
		txtdescripcion.setText("");
		txtinstrucciones.setText("");
		txtnombre.setText("");
		txtdistancia.setText("");
		comboComentario.setItems(null);
		listLAtractivos.setItems(null);
		listDificultadRecorrido.setItems(null);
		listDisponibilidadCelular.setItems(null);
		listEquipamiento.setItems(null);
		listImagenes.setItems(null);
		listTransporteSendero.setItems(null);
		listatractivos.setItems(null);
		list_Recorrido.setItems(null);
	}

	public Sendero guardar(){
		Sendero pojotemp = new Sendero();
		if (pojo!=null) {
			pojotemp = pojo;
		}
		pojotemp.set_id(txtnombre.getText().trim());
		pojotemp.setNombre(txtnombre.getText());
		float distancia = (!txtdistancia.getText().isEmpty())?Float.parseFloat(txtdistancia.getText()):0;   
		pojotemp.setDistancia(distancia);
		float duracion = (!txtduracion.getText().isEmpty())?Float.parseFloat(txtduracion.getText()):0;
		pojotemp.setDuracion(duracion);
		pojotemp.setDescripcion(txtdescripcion.getText());
		pojotemp.setInstrucciones(txtinstrucciones.getText());
		if (checkActivo.isSelected()== true){
			pojotemp.setEstado(Estado.ACTIVO);
		}
		else{
			pojotemp.setEstado(Estado.INACTIVO);
		}

		pojotemp.getComentarios().add(comboComentario.getValue());

		for (DificultadRecorrido dr : LTipoDificultad) {

			pojotemp.setDificultad(dr);
		}
		for (DisponibilidadCelular dc : TipoDisponibilidadCelular) {
			pojotemp.setDisponibilidadSenalCelular(dc);
		}
		
		if(TransporteOpciones!=null)
		for (Transporte t : TransporteOpciones) {
			pojotemp.getTransporte().add(t);
			System.out.println("seleccion " + t.toString());
		}
		if (tipoAtractivo!=null)
		{
			for (Atractivo at : tipoAtractivo) {
				pojotemp.getAtractivos().add(at);
				System.out.println("seleccion " + at.toString());
			}
		}
		for (LocacionAtractivo a : TipoLocacionAtractivo) {
			pojotemp.getLocacionAtractivos().add(a);
		}

		for (String eq : TipoEquipamento) {
			pojotemp.getEquipamento().add(eq);
		}
		for (Imagen i: selectedItemsImagen) {
			pojotemp.getGaleria().add(i);

		}
		for(String r : list_Recorrido.getItems()){
			pojotemp.getRecorrido().add(r);
		}
		
		stage.close();
		pojo = pojotemp;
		return pojotemp;
	}

	public void cargarDatos(Sendero pojos){
		if(pojo==null)
		{
			pojo = new Sendero();
		}
		
		System.out.println("pojo capturado  "+pojos.getNombre().toString());
		txtid.setText(pojos.get_id());
		txtdescripcion.setText(pojos.getDescripcion());
		txtdistancia.setText(String.valueOf(pojos.getDistancia()));
		txtduracion.setText(String.valueOf(pojos.getDuracion()));
		txtinstrucciones.setText(pojos.getInstrucciones());
		txtnombre.setText(pojos.getNombre().toString());

		ObservableList<LocacionAtractivo> LatractivoSelecionado = FXCollections.observableArrayList(pojos.getLocacionAtractivos());
		listLAtractivos.setItems(LatractivoSelecionado);
		ObservableList<Transporte> LtransporteSendero = FXCollections.observableArrayList(pojos.getTransporte());
		listTransporteSendero.setItems(LtransporteSendero);
		ObservableList<String> Lequipamiento = FXCollections.observableArrayList(pojos.getEquipamento());
		listEquipamiento.setItems(Lequipamiento);
		ObservableList<Imagen> LimagenesGaleria = FXCollections.observableArrayList(pojos.getGaleria());
		listImagenes.setItems(LimagenesGaleria);
		ObservableList<DisponibilidadCelular> LdisponibilidadCelular = FXCollections.observableArrayList(pojos.getDisponibilidadSenalCelular());
		listDisponibilidadCelular.setItems(LdisponibilidadCelular);
		ObservableList<DificultadRecorrido> LdificultadRecorrido = FXCollections.observableArrayList(pojos.getDificultad());
		listDificultadRecorrido.setItems(LdificultadRecorrido);
		ObservableList<Atractivo> lAtractivo = FXCollections.observableArrayList(pojos.getAtractivos());
		listatractivos.setItems(lAtractivo);
		
		if (pojos.getComentarios()!=null && pojos.getComentarios().size()>0) {
			comboComentario.setValue(pojos.getComentarios().get(0));
		}
		
		if(!pojos.getRecorrido().isEmpty()){
			list_Recorrido.getItems().addAll(pojos.getRecorrido());
		}
		
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

	public void cargarDatosWebService()
	{
		String nombre = txtnombre.getText();
		ControllerHelper<Sendero> controllerHelper= new ControllerHelper<Sendero>();
		Sendero r = controllerHelper.cargarDatosWebService(nombre, Sendero.class);
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
		ControllerHelper<Sendero> controllerHelper= new ControllerHelper<Sendero>();
		Sendero pojo = guardar();
		System.out.println("El pojo a guardar en el WS es: " + pojo);
		if(controllerHelper.guardarNuevosDatosWebService(pojo, Sendero.class))
		{
			System.out.println("Datos guardados con exito");
		}else{
			System.err.println("No se pudo guardar los datos a traves del web service.");
		}

	}

	public void borrarDatosWebService()
	{
		ControllerHelper<Sendero> controllerHelper= new ControllerHelper<Sendero>();
		Sendero pojos = guardar();
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
					controllerHelper.borrarDatosWebService(pojos.getNombre(), pojos.get_sync().getRev(),Sendero.class);
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
	public void abrirPantallaModalNuevoTransporteSendero()
	{
		pojoTransporte = ControllerHelper.abrirVistaModal("/ViewTransporteSendero.fxml", "Transporte", null);
		if(pojoTransporte!=null)
			listTransporteSendero.getItems().add(pojoTransporte);
		
		if(pojoTransporte!=null){
			pojo.getTransporte().add(pojoTransporte);
		}else{
			ControllerHelper.mostrarAlertaError("Cerro sin llenar el formulario.");
		} 
	}
	
	public void abrirPantallaModalCargarTransporteSendero()
	{
		pojoTransporte = ControllerHelper.abrirVistaModal("/ViewTransporteSendero.fxml", "Transporte", pojoTransporte);
	}
	
	
	//***********************
	public void abrirPantallaModalNuevoAtractivo()
	{
		pojoAtractivo = ControllerHelper.abrirVistaModal("/ViewAtractivo.fxml", "Atractivo", null);
		if(pojoAtractivo!=null)
			listatractivos.getItems().add(pojoAtractivo);
		
		if(pojoAtractivo!=null){
			pojo.getAtractivos().add(pojoAtractivo);
		}else{
			ControllerHelper.mostrarAlertaError("Cerro sin llenar el formulario.");
		}
	}
	
	public void abrirPantallaModalCargarAtractivo()
	{
		pojoAtractivo = ControllerHelper.abrirVistaModal("/ViewAtractivo.fxml", "Atractivo", pojoAtractivo);
	}
	
	
	public void addPunto(){
		if(!txt_punto.getText().isEmpty())
			list_Recorrido.getItems().add(txt_punto.getText());			
		txt_punto.setText("");
	}
	
	public void updatePunto(){
		if(!list_Recorrido.getItems().isEmpty() && !txt_punto.getText().isEmpty())
			list_Recorrido.getItems().set(list_Recorrido.getSelectionModel().getSelectedIndex(), txt_punto.getText());
		txt_punto.setText("");
	}
	
	public void deletePunto(){
		if(!list_Recorrido.getItems().isEmpty() && !txt_punto.getText().isEmpty())
			list_Recorrido.getItems().remove(list_Recorrido.getSelectionModel().getSelectedIndex());		
	}
	
	public void salir(){
		System.out.println("************** EXIT *********\n");
		stage.close();
	}
	
	@Override
	public void cancelar()
	{
		pojo = null;
		stage.close();
	}

	@Override
	public Sendero getPojo() {
		return pojo;
	}

	@Override
	public void setPojo(Sendero x) {
		pojo = x;
		if(x!=null)
			cargarDatos(x);
	}

	@Override
	public void setDialogStage(Stage stage) {
		this.stage = stage;
	}
	
	public void dificultadRecorrido()
	{
		ObservableList<DificultadRecorrido> listaDificultad = FXCollections.observableArrayList(DificultadRecorrido.values());
		listDificultadRecorrido.setItems(listaDificultad);
		listDificultadRecorrido.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listDificultadRecorrido.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				LTipoDificultad = listDificultadRecorrido.getSelectionModel().getSelectedItems();
			}
		});
	}
	
	public void disponibilidadCelular()
	{
		ObservableList<DisponibilidadCelular> listaDispoCelular = FXCollections.observableArrayList(DisponibilidadCelular.values());
		listDisponibilidadCelular.setItems(listaDispoCelular);
		listDisponibilidadCelular.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listDisponibilidadCelular.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				TipoDisponibilidadCelular=listDisponibilidadCelular.getSelectionModel().getSelectedItems();
			}
		});
	}
	
	public void ListaLlenasString()
	{
		Comentario comenta = new Comentario();
		comenta.set_id("001");
		comenta.setDescipcion("Amen :v");
		Comentario comenta1 = new Comentario();
		comenta1.set_id("002");
		comenta1.setDescipcion("Amen x2 :v");
		ArrayList<Comentario> ArrayComentario = new ArrayList<Comentario>();
		ArrayComentario.add(comenta);
		ArrayComentario.add(comenta1);
		ObservableList<Comentario> testescomentarios = FXCollections.observableArrayList(ArrayComentario);
		comboComentario.setItems(testescomentarios);
		
		
		/**********LocacionAtractivo*********/
		LocacionAtractivo LocacionA = new  LocacionAtractivo();
		LocacionA.setCoordenadasGPS("123456 ");
		LocacionA.setEstado(Estado.ACTIVO);
		LocacionAtractivo LocacionA2 = new  LocacionAtractivo();
		LocacionA2.setCoordenadasGPS("123456 -41454545");
		LocacionA2.setEstado(Estado.ACTIVO);
		ArrayList<LocacionAtractivo> arraylocacionA = new ArrayList<LocacionAtractivo>();
		arraylocacionA.add(LocacionA);
		arraylocacionA.add(LocacionA2);
		ObservableList<LocacionAtractivo> listlocacionAtractivo = FXCollections.observableArrayList(arraylocacionA);
		listLAtractivos.setItems(listlocacionAtractivo);
		listLAtractivos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listLAtractivos.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				//
				TipoLocacionAtractivo = listLAtractivos.getSelectionModel().getSelectedItems();
			}
		});
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
		listImagenes.setItems(listaImagen);
		listImagenes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listImagenes.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				selectedItemsImagen = listImagenes.getSelectionModel().getSelectedItems();
			}

		});
		/**************LISTA DE EQUIPAMIENTO *****************/
		ObservableList<String> ListaEquipamiento= FXCollections.observableArrayList ("Equipa 1", "Equipa 2");
		listEquipamiento.setItems(ListaEquipamiento);
		listEquipamiento.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listEquipamiento.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				TipoEquipamento=listEquipamiento.getSelectionModel().getSelectedItems();
			}			
		});
	}
	
	public void listaTransportes()
	{
		//TODO Corregir esta lista transporte, traer llena si amerita (desde base de Datos).
				ObservableList<Transporte> listaTransporte = FXCollections.observableArrayList();
				listTransporteSendero.setItems(listaTransporte);
				listTransporteSendero.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				listTransporteSendero.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						TransporteOpciones=listTransporteSendero.getSelectionModel().getSelectedItems();
					}
				});
	}
	
	public void listaAtractivo()
	{
		//TODO Corregir esta lista transporte, traer llena si amerita (desde base de Datos).
		ObservableList<Atractivo> listaAtractivos = FXCollections.observableArrayList();
		listatractivos.setItems(listaAtractivos);	
		listatractivos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listatractivos.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tipoAtractivo = listatractivos.getSelectionModel().getSelectedItems();
			}
		});
	}
	
	public void handleDeleteButton() {
		btnBorrarAtractivo.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	            final int selectedIdx = listatractivos.getSelectionModel().getSelectedIndex();
	            if (selectedIdx != -1) {
	                Atractivo itemToRemove = listatractivos.getSelectionModel().getSelectedItem();

	                final int newSelectedIdx =
	                        (selectedIdx == listatractivos.getItems().size() - 1)
	                                ? selectedIdx - 1
	                                : selectedIdx;

	                listatractivos.getItems().remove(selectedIdx);
	                listatractivos.getSelectionModel().select(newSelectedIdx);
	               //removes the player for the array
	                System.out.println("selectIdx: " + selectedIdx);
	                System.out.println("item: " + itemToRemove);
	                pojo.getAtractivos().remove(selectedIdx);

	            }
	        }
	    });
	}
}


