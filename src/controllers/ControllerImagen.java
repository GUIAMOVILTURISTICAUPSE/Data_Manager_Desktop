package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
//Librerias
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.util.converter.LocalDateStringConverter;
import pojos.Imagen;
import pojos.Recurso;

import javax.imageio.ImageIO;

import cloud.GoogleCloudStorageWorker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerImagen{
		
	@FXML private TextField ID1;
	@FXML private TextArea descripcion1;
	@FXML private TextField titulo1;
	@FXML private DatePicker fecha1;
	@FXML private Text votosf1;
	@FXML private Text votosc1;
	@FXML private TextField url1;
	@FXML private TextField base641;
	@FXML private TextField autor1;
	@FXML private ComboBox<String> etiqueta1;
	@FXML private TextField coordenadas1;
	@FXML private TextField longitud1;
	@FXML private TextField latitud1;
	@FXML private CheckBox Reportado;
	@FXML private Spinner<Integer> spinner;
	@FXML private Spinner<Integer> spinner1;
	@FXML private ImageView imgImagen;
	
	ObservableList<String> etiquetaList = FXCollections.observableArrayList();
	
	GoogleCloudStorageWorker googleStorageWorker = new GoogleCloudStorageWorker();
	Imagen pojo = new Imagen();
	
	Context context = Context.getInstance();
	ControllerHelper<Imagen> controllerHelper = new ControllerHelper<Imagen>();
	
	public ControllerImagen() {
		
	}
	
	public void initialize(){
		imgImagen.setFitWidth(400);
		imgImagen.setFitHeight(400);
		imgImagen.setPreserveRatio(true);
	}
	
	/**
	 * Metodo valido, creado por Ivan en Junio 2017
	 */
	public void guardar(){
		pojo.setId(ID1.getText());
		pojo.setDescripcion(descripcion1.getText());
		pojo.setFecha(fecha1.getValue());
		pojo.setTitulo(titulo1.getText());
		
		if(!url1.getText().trim().equals(""))
		{
			if(googleStorageWorker.checkIfImageExists(ID1.getText(), url1.getText().trim()))
			{
				pojo.setUrl(url1.getText());
			}else{
				String mensajeError = "No se puede guardar porque el url no es valido.";
				System.err.println(mensajeError);
				ControllerHelper.mostrarAlertaError(mensajeError);
				return;
			}
			
		}else{
		
			if(imgImagen.getImage()!=null)
			{
				try {
					almacenarNube(convertirImagenEnImageViewAByteArray());
				} catch (IOException e) {
					String mensajeErrorTransformarImagen =  "Error en al almacenar la imagen en la nube. " + e;
					ControllerHelper.mostrarAlertaError(mensajeErrorTransformarImagen);
					System.err.println(mensajeErrorTransformarImagen);
					e.printStackTrace();
					return;
				}
			}else{
				String mensajeError = "No se puede guardar porque no hay cargada una imagen en el imageView.";
				System.err.println(mensajeError);
				ControllerHelper.mostrarAlertaError(mensajeError);
				return;
			}			
		}
		
		
		pojo.setUrl(url1.getText());
		pojo.setAutor(autor1.getText());
		pojo.setCoordenadas(latitud1.getText()+","+longitud1.getText());
		//TODO Soportar agregar a un usuario como autor de la imagen (a futuro).
		//pojo.setAutorUsuario(); 
		pojo.setReportado(Reportado.isSelected());
		pojo.setVotosFavor(spinner.getValue());
		pojo.setVotosContra(spinner1.getValue());
		//TODO Permitir agregar y editar etiquetas con un generador de Strings a traves de GUI
		//pojo.setEtiquetas(etiqueta1.getValue().toString());  ***************************************** ETIQUETAS ????? LATITUD Y LONGITUD ?????
		//falta etiquetas array list combobox
		//pojo.setEtiquetas((ArrayList<String>) etiqueta1.getItems());
		//falta votos favor y contra del spinner :C segundo parcial segun indicaciones
		System.out.println("******DATOS GUARDADOS*****");
		System.out.println(pojo.toStringComplete());
		
		Recurso r = context.getRecurso();
		r.getGaleria().add(pojo);
		context.setRecurso(r);
		
		//controllerHelper.llamarGUI("/ViewRecurso.fxml", ID1, "VISTA RECURSO");
		if(context != null && context.getScene() != null)
			controllerHelper.llamarGUI(context.getScene(), ID1, "VISTA RECURSO");
		
	}
	
	public void cargar(){
		carga_pojo(pojo);
	}

	////////////////////////////////////////////////////////////
	//Nuevo metodo que recibe a un pojo como parametro
	public void carga_pojo(Imagen i){
		ID1.setText(i.getId());
		descripcion1.setText(i.getDescripcion());
		fecha1.setValue(i.getFecha());
		titulo1.setText(i.getTitulo());
		url1.setText(i.getUrl());
		autor1.setText(i.getAutor());
		Reportado.setSelected(i.isReportado());
		etiqueta1.setValue(i.getEtiquetas().toString());
		if(i.getCoordenadas().contains(","))
		{
			latitud1.setText(i.getCoordenadas().split(",")[0]);
			longitud1.setText(i.getCoordenadas().split(",")[1]);
		}
		//falta votos favor y contra del spinner :C pero se esta cargando en un dato text no del spinner
		votosf1.setText(String.valueOf(i.getVotosFavor()));
		votosc1.setText(String.valueOf(i.getVotosContra()));
		////////////////////////////////////////////////////////
		System.out.println("******DATOS CARGADOS******");
		System.out.println(i.toStringComplete());
	}

	
	public void cargarImagenDesdeArchivo()
	{
		//Tomado de http://java-buddy.blogspot.com/2013/01/use-javafx-filechooser-to-open-image.html
		FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(imgImagen.getScene().getWindow());  
        
        if(file!=null)	cargarImagenEnImageView(file);
	}
	
	private void cargarImagenEnImageView(File file)
	{
        try {
        	
            BufferedImage bufferedImage = ImageIO.read(file);
            
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgImagen.setImage(image);
        } catch (IOException ex) {
            System.err.println("Error al cargar imagen");
            ex.printStackTrace();
        } catch (Exception e) {
			System.err.println("Otro error al cargar imagen, quiza no selecciono ningun archivo.");
			e.printStackTrace();
		}
	}
	
	private byte[] convertirImagenEnImageViewAByteArray() throws IOException
	{
		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imgImagen.getImage(), null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", s);
		byte[] res = s.toByteArray();
		return res;
	}
	
	public void almacenarNube(byte[] imagen)
	{
		if(pojo.getId()!=null && !pojo.getId().trim().isEmpty())
		{
			String url = googleStorageWorker.saveImage(pojo.getId(), imagen);
			url1.setText(url);
			url1.setDisable(true);
		}else{
			String mensajeErrorID = "No hay ide de imagen valido";
			System.err.println(mensajeErrorID);	
			ControllerHelper.mostrarAlertaError(mensajeErrorID);
		
		}
	}
	
	public void limpiarPantalla(){
		ID1.setText("");
		descripcion1.setText("");
		titulo1.setText("");
		fecha1.setValue(null);
		url1.setText("");
		base641.setText("");
		autor1.setText("");
		etiqueta1.setValue(null);
		Reportado.setSelected(false);
		//coordenadas1.setText("");
		//se cambio a longitud y latitud
		longitud1.setText("");
		latitud1.setText("");
		//falta votos favor y contra del spinner :C para segundo parcial segun indicaciones
		
		System.out.println("******DATOS LIMPIOS*****");
	}
	
	public void cancelar(){
		if(context != null && context.getScene() != null)
			controllerHelper.llamarGUI(context.getScene(), ID1, "VISTA RECURSO");
	}
	
	public void salir(){
		System.exit(0);
	}
}


