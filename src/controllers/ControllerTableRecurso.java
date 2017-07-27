package controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojos.Recurso;

public class ControllerTableRecurso implements ControllerModalBase<Recurso> {
	
	private Recurso pojo = new Recurso();
	
	public void setPojo(Recurso pojo) { this.pojo = pojo; }
	
	@FXML private TableView<Recurso> tvRecurso ;
	@FXML private Label lblRecurso;
	
	private Stage stage;
	
	@Override
	public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
	
	private List<Recurso> listaRecursos = new ArrayList<Recurso>();
	ControllerHelper<Recurso> controllerHelper;
	
	public ControllerTableRecurso()
	{
		controllerHelper= new ControllerHelper<Recurso>();
	}
	
	public void cargarDatosWebService()
	{
		listaRecursos =  controllerHelper.cargarListaDatosWebService(Recurso.class);
	}
	
	public void initialize()
	{
		cargarDatosWebService();
		ObservableList<Recurso> data = FXCollections.observableArrayList();
		for(int i = 0 ; i < listaRecursos.size() ; i++ ){
			data.add(listaRecursos.get(i));
		}
		TableColumn<Recurso, String> firstNameCol = new TableColumn<>("Nombre");
        firstNameCol.setMinWidth(225);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Recurso, String>("Id"));
        TableColumn<Recurso, String> lastNameCol = new TableColumn<>("Descripcion");
        lastNameCol.setMinWidth(225);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Recurso, String>("descripcion"));
        tvRecurso.getColumns().addAll(firstNameCol, lastNameCol);
        tvRecurso.setItems(data);
        tvRecurso.setOnMouseClicked(new EventHandler<Event>() {
        	@Override
        	public void handle(Event event) {
        		pojo = listaRecursos.get(tvRecurso.getSelectionModel().getSelectedIndex());
        		System.out.println(tvRecurso.getSelectionModel().getSelectedItem().getId());
				stage.close();
        	}
		});
	}
	
	@Override
	public Recurso getPojo() { return pojo;}
}
