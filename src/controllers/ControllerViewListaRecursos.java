package controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pojos.Recurso;

public class ControllerViewListaRecursos {
	private Recurso pojo = new Recurso();
	public Recurso getPojo() { return pojo;}

	public void setPojo(Recurso pojo) { this.pojo = pojo; }

	@FXML private ListView<String> lvRecursos;
	private List<Recurso> listaRecursos = new ArrayList<Recurso>();
	ControllerHelper<Recurso> controllerHelper;
	
	private Stage stage;
	public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
	
	public void initialize()
	{
		cargarDatosWebService();
		ObservableList<String> data = FXCollections.observableArrayList();
		for(int i = 0 ; i < listaRecursos.size() ; i++ ){
			data.add(listaRecursos.get(i).getId());			
		}
		lvRecursos.setItems(data);	
		lvRecursos.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				System.out.println("clicked on " + lvRecursos.getSelectionModel().getSelectedItem());
				pojo = listaRecursos.get(lvRecursos.getSelectionModel().getSelectedIndex());
				System.out.println(lvRecursos.getSelectionModel().getSelectedIndex());
				stage.close();
			}
		});
	}
	

	public ControllerViewListaRecursos()
	{
		controllerHelper= new ControllerHelper<Recurso>();
	}
	
	public void cargarDatosWebService()
	{
		listaRecursos =  controllerHelper.cargarListaDatosWebService(Recurso.class);
		//System.out.println(listaRecursos);
	}
}
