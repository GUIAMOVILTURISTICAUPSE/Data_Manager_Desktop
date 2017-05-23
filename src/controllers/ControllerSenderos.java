package controllers;

import java.awt.Image;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pojos.Atractivo;
import pojos.Comentario;
import pojos.DificultadRecorrido;
import pojos.DisponibilidadCelular;
import pojos.Estado;
import pojos.Imagen;
import pojos.LocacionAtractivo;
import pojos.Sendero;
import pojos.TransporteSendero;
import javafx.event.Event;

/**
 * Created by ChicoLoco & Joao on 14/12/2016.
 */

public class ControllerSenderos {
	
	@FXML private TextField id;
	@FXML private TextField duracion;
	@FXML private TextArea descripcion;
	@FXML private TextArea instrucciones;
	@FXML private TextField nombre;
	@FXML private TextField distancia;
	@FXML private CheckBox checkActivo;
	@FXML private CheckBox checkInactivo;
	@FXML private ListView<DisponibilidadCelular> listDisponibilidadCelular;
	@FXML private ListView<String> listEquipamiento;
	@FXML private ListView<TransporteSendero> listTransporteSendero;
	@FXML private ListView<LocacionAtractivo> listAtractivos;
	@FXML private ListView<DificultadRecorrido> listDificultadRecorrido;
	@FXML private ListView<Imagen> listImagenes;
	@FXML private ComboBox<Comentario> comboComentario;
	
	public ControllerSenderos() {
		
	}
	//ObservableList<Comentario> comentarios =FXCollections.observableArrayList();
	ObservableList<Imagen> selectedItemsImagen;
	ObservableList<DificultadRecorrido> LTipoDificultad;
	ObservableList<DisponibilidadCelular> TipoDisponibilidadCelular=FXCollections.observableArrayList();
	ObservableList<Estado> TipoEstado=FXCollections.observableArrayList();
	ObservableList<TransporteSendero> TipoTransporteSendero;
	ObservableList<LocacionAtractivo> TipoLocacionAtractivo;
	ObservableList<String> TipoRecorrido =FXCollections.observableArrayList ("Reco 1", "Reco 2");
	ObservableList<String> TipoEquipamento =FXCollections.observableArrayList ("Equipa 1", "Equipa 2");
	
	
	public void initialize(){
		Sendero pojo = new Sendero();
		
		
		/**********comentario*********/
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
		listAtractivos.setItems(listlocacionAtractivo);
		listAtractivos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listAtractivos.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				//
				TipoLocacionAtractivo = listAtractivos.getSelectionModel().getSelectedItems();
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
		
		/**********Equipamento*********/
		ListView<String> e1 = new ListView<String>();
				
		ObservableList<DificultadRecorrido> listaDificultad = FXCollections.observableArrayList(DificultadRecorrido.values());
		listDificultadRecorrido.setItems(listaDificultad);
		listDificultadRecorrido.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listDificultadRecorrido.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				LTipoDificultad = listDificultadRecorrido.getSelectionModel().getSelectedItems();
			}
		});
	
	}
	
	
	public void limpiar(){
		System.out.println("************** HA SIDO LIMPIADDO *********\n");
		id.setText("");
		duracion.setText("");
		descripcion.setText("");
		instrucciones.setText("");
		nombre.setText("");
		distancia.setText("");
		
	}
	
	public void guardar(){
		System.out.println("************** SU PROCESO HA SIDO GUARDADO *********\n");	
		String idg = id.getText();
		String msgID =" ID :  " + idg;
		System.out.println(msgID);		
	
		String nombreg = nombre.getText();
		String msgNombre =" NOMBRE  :  " + nombreg;
		System.out.println(msgNombre);	

		float duraciong = Float.parseFloat(duracion.getText());
		String msgduracion =" DURACION :  " + duraciong;
		System.out.println(msgduracion);
		
		float distanciag = Float.parseFloat(distancia.getText());
		String msgdistancia =" DISTANCIA :  " + distanciag;
		System.out.println(msgdistancia);
		
		String descripciong = descripcion.getText();
		String msgdescripcion =" DESCRIPCION:  " + descripciong;
		System.out.println(msgdescripcion);
		
		String instruccionesg = instrucciones.getText();
		String msginstrucciones =" DESCRIPCION:  " + instruccionesg;
		System.out.println(msginstrucciones);
		/*
		Estado estadog = cmbEstado.getValue();
		String msgestado =" Estado:  " + estadog;
		System.out.println(msgestado);
		
		DificultadRecorrido dificultadg = cmbdificultad.getValue();
		String msgdificultad =" Dificultad:  " + dificultadg;
		System.out.println(msgdificultad);
		
		DisponibilidadCelular disponibilidadg = cmbDisponibilidadCelular.getValue();
		String msgDisponibilidad =" Disponibiliada:  " + disponibilidadg;
		System.out.println(msgDisponibilidad);
		
		Comentario comentariog = cmbComentario.getValue();
		String msgComentario =" Comentario:  " + comentariog;
		System.out.println(msgComentario);
		
		TransporteSendero transporteSenderog = cmbTransporteSendero.getValue();
		String msgTransporteSendero =" Transporte:  " + transporteSenderog;
		System.out.println(msgTransporteSendero);
		
		LocacionAtractivo locacionAtractivog = cmbLocacionAtractivo.getValue();
		String msgLocacionAtractivo =" Locacion Atractivo:  " + locacionAtractivog;
		System.out.println(msgLocacionAtractivo);
		
		pojo.set_id(id.getText());
		pojo.setDuracion(Float.valueOf(duracion.getText()));
		pojo.setDescripcion(descripcion.getText());
		pojo.setNombre(nombre.getText());
		pojo.setInstrucciones(instrucciones.getText());
		pojo.setDistancia(Float.valueOf(distancia.getText()));
		pojo.setEstado(cmbEstado.getValue());
		pojo.setDisponibilidadSenalCelular(cmbDisponibilidadCelular.getValue());
		pojo.setDificultad(cmbdificultad.getValue());
		pojo.getComentarios();
		pojo.getTransporte();
		pojo.getLocacionAtractivos();
		*/
	}
	
	public void cargar(){
		/*
		System.out.println("************** CARGADO *********\n");
		id.setText(pojo.get_id());
		duracion.setText(String.valueOf(pojo.getDuracion()));
		descripcion.setText(pojo.getDescripcion());
		nombre.setText(pojo.getNombre());
		instrucciones.setText(pojo.getInstrucciones());
		distancia.setText(String.valueOf(pojo.getDistancia()));
		cmbEstado.setValue(pojo.getEstado());
		cmbDisponibilidadCelular.setValue(pojo.getDisponibilidadSenalCelular());
		cmbdificultad.setValue(pojo.getDificultad());		
		imagen.setText(i1.getDescripcion());
		
		//Borrar el combo box y cargarlo de nuevo
		cmbComentario.getItems().clear();
		comentarios.add(c1);
		cmbComentario.setItems(comentarios);
		
		cmbTransporteSendero.getItems().clear();
		//TipoTransporteSendero.add();
		cmbTransporteSendero.setItems(TipoTransporteSendero);
		
		cmbLocacionAtractivo.getItems().clear();
		TipoLocacionAtractivo.add(l1);
		cmbLocacionAtractivo.setItems(TipoLocacionAtractivo);
		
		//Cargar las listas
		r1.setItems(TipoRecorrido);
		recorrido.setItems(TipoRecorrido);;
		
		e1.setItems(TipoEquipamento);
		equipamento.setItems(TipoEquipamento);;
		*/
	}
	
	public void salir(){
		System.out.println("************** EXIT *********\n");
		System.exit(0);
	}
	
}


