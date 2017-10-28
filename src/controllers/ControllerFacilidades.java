package controllers;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojos.Facilidad;
import pojos.Recomendacion;
import pojos.Sendero;
import pojos.TipoFacilidad;

public class ControllerFacilidades implements ControllerModalBase<Facilidad> {

	@FXML private TextField textIdentificacion;
	@FXML private TextField textTitulo;
	@FXML private TextField textUbicacion;
	@FXML private TextArea textDescripcion;
	@FXML private ComboBox<TipoFacilidad> cmbTipoFacilidad;
	@FXML private Button btnGuardar;
	@FXML private Button btnLimpiar;
	@FXML private Button btnSalir;
	
	private Stage stage;
	Facilidad pojoFacilidad = new Facilidad();
	
	public ControllerFacilidades(){}

	public void initialize(){
		cargarTipoFacilidades();
	}
	
	public void limpiar(){
		textIdentificacion.setText("");
		textTitulo.setText("");
		textUbicacion.setText("");
		textDescripcion.setText("");
		cmbTipoFacilidad.getItems().clear();	
		initialize();
	}
	
	private void cargarTipoFacilidades() {
		cmbTipoFacilidad.setItems(FXCollections.observableArrayList( TipoFacilidad.values()));
	}

	public void guardar(){
		
		
		stage.close();
	}

	private void cargarDatos(Facilidad x) {
		
	}

	@Override
	public Facilidad getPojo() {
		return pojoFacilidad;
	}
	@Override
	public void setDialogStage(Stage stage) {
		this.stage = stage;		
	}

	@Override
	public void setPojo(Facilidad x) {
		pojoFacilidad = x;
		if(x!=null)
			cargarDatos(x);		
	}

	@Override
	public void cancelar() {
		pojoFacilidad = null;
		stage.close();
	}	

}
