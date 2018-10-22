package controllers;

import java.io.File;
import java.net.URI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import pojos.Animacion;
import pojos.Sendero;
import pojos.TipoAnimacion;

public class ControllerAnimacion implements ControllerModalBase<Animacion> {

	@FXML private TextField txtId;
	@FXML private TextField txtNombre;
	@FXML private ChoiceBox<TipoAnimacion> chbTipoAnimacion;
	@FXML private TextField txtURL;
	@FXML private MediaView mediaViewAnimacion;
	@FXML private WebView webViewMedia;

	ObservableList<TipoAnimacion> listaTipoAnimacion = FXCollections.observableArrayList(TipoAnimacion.values());

	Animacion pojo = new Animacion();
	Context context = Context.getInstance();

	private Stage stage;

	public void initialize(){
		if(context.getAnimacion()!=null)
		{
			pojo = context.getAnimacion();
			cargarDatos(context.getAnimacion());
			context.setAnimacion(null);
		}else {
			txtId.setText(pojo.getId());
		}
		chbTipoAnimacion.setItems(listaTipoAnimacion);
	}

	public ControllerAnimacion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Animacion getPojo() {
		return pojo;
	}

	@Override
	public void setDialogStage(Stage stage) {
		this.stage = stage;

	}

	@Override
	public void setPojo(Animacion x) {
		pojo = x;
		if(x!=null)
			cargarDatos(x);

	}

	private void cargarDatos(Animacion x) {
		pojo = x;	
		txtId.setText(pojo.getId());
		txtNombre.setText(pojo.getTitulo());
		chbTipoAnimacion.selectionModelProperty().get().select(pojo.getTipo());
		txtURL.setText(pojo.getUrl());

		cargarAnimacion(pojo);
	}

	private Animacion guardarSinSalir()
	{
		Animacion pojoTemp;

		if (pojo!=null) {
			pojoTemp = new Animacion(pojo);
		}else {
			pojoTemp = new Animacion();
		}

		pojoTemp.setTitulo(txtNombre.getText());
		pojoTemp.setTipo(chbTipoAnimacion.getValue());
		pojoTemp.setUrl(txtURL.getText());

		context.setAnimacion(pojoTemp);
		return pojoTemp;
	}

	public Animacion guardar() {
		Animacion pojoTemp = guardarSinSalir();
		stage.close();
		return pojoTemp;
	}

	public void cargarAnimacion()
	{
		if(txtURL.getText()!=null && txtURL.getText()!="")
		{
			pojo = guardarSinSalir();
			cargarAnimacion(pojo);
		}else {
			ControllerHelper.mostrarAlertaError("No hay URL para la seleccion");
		}
	}

	private void cargarAnimacion(Animacion animacion) {
		if(animacion.getTipo()==null) 
		{
			ControllerHelper.mostrarAlertaError("Debe seleccionar un tipo de Animacion");
		}
		switch (animacion.getTipo()) {
		case VIDEO:
			//cargarVideoFileURL(animacion.getUrl());
			cargarVideoYoutube(animacion.getUrl());
			break;
		case ANIMACION_3D:
			ControllerHelper.mostrarAlertaError("Animacion 3D no soportada aun");
			mediaViewAnimacion.setMediaPlayer(null);
			webViewMedia.getEngine().load(null);
			break;
		case SPRINT:
			ControllerHelper.mostrarAlertaError("Animacion SPRINT no soportada aun");
			mediaViewAnimacion.setMediaPlayer(null);
			webViewMedia.getEngine().loadContent("");
			break;
		default:
			cargarVideoFileURL(animacion.getUrl());
			cargarVideoYoutube(animacion.getUrl());
			break;
		}

	}

	private void cargarVideoFileURL(String url) {
		try{

			//Path to Online Video Resource
			URI u = URI.create(url);

			//Path to File
			//String pathName = "/Users/ivansanchez/Downloads/Madamina.mp4";
			//u = new File(pathName).toURI();

			Media media = new Media(u.toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setAutoPlay(true);
			mediaViewAnimacion.setMediaPlayer(mediaPlayer);
			mediaViewAnimacion.setVisible(true);
			mediaPlayer.play();
		}catch (Exception e) {
			ControllerHelper.mostrarAlertaError("Error al cargar video en el Media view. Revise si el URL es valido y si hay conexion a Internet.");
			System.err.println("Error en el media view" + e);
			e.printStackTrace();
		}

	}
	
	private void cargarVideoYoutube(String url)
	{
	    webViewMedia.getEngine().load(url);
	}

	@Override
	public void cancelar() {
		pojo = null;
		stage.close();	
	}

	public void limpiar()
	{
		//txtId.setText("");
		txtNombre.setText("");
		txtURL.setText("");
		chbTipoAnimacion.getItems().clear();
		mediaViewAnimacion.setMediaPlayer(null);
	}
}
