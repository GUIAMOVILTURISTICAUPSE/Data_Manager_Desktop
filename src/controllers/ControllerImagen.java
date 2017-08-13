package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
//Librerias
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.util.converter.LocalDateStringConverter;
import pojos.Imagen;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import cloud.GoogleCloudStorageWorker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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
	
	Image image2;
	BufferedImage bufferedImageToExchage;
	
	public ControllerImagen() {
		twelveMonkeyLibraryTestForImage();
	}

	private void twelveMonkeyLibraryTestForImage() {
		ImageIO.scanForPlugins();
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
		while (readers.hasNext()) {
		    System.out.println("reader: " + readers.next());
		}
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
        		bufferedImageToExchage = bufferedImage;
        		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        		//image2 = image;
        		imgImagen.setImage(image);
        } catch (IOException ex) {
            System.err.println("Error al cargar imagen");
            ex.printStackTrace();
        } catch (Exception e) {
			System.err.println("Otro error al cargar imagen, quiza no selecciono ningun archivo.");
			e.printStackTrace();
		}
	}
	
	//FIXME : Aqui se hace negra la imagen al convertir a ByteArray
	private byte[] convertirImagenEnImageViewAByteArray() throws IOException
	{
		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image2, null);
		bufferedImage = bufferedImageToExchage;
		//bufferedImageToExchage = bufferedImage;
		//bufferedImage  = fixBadJPEG(bufferedImage);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		if(!ImageIO.write(bufferedImage, "jpg", s)) {
			System.err.println("Error al escribir imagen al byte Array Output Stream");
		}

		byte[] res = s.toByteArray();
		s.flush();
		s.close();
		convertirByteArrayEnImagenArchivo(res);
		return res;
	}

	public void convertirByteArrayEnImagenArchivo(byte[] image)
	{
		try {
			//BufferedImage img = ImageIO.read(new ByteArrayInputStream(image));
			BufferedImage img = bufferedImageToExchage;
			String nombreArchivo = LocalDate.now().toString() + "imagen.jpg";
			File archivo = new File("/Users/ivansanchez/eclipse-workspace/GuiaMovilDataManager/test-image/" + nombreArchivo);
			ImageIO.write(img, "jpg", archivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static BufferedImage fixBadJPEG(BufferedImage img)
	{
		int[] ary = new int[img.getWidth() * img.getHeight()];
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), ary, 0, img.getWidth());
		for (int i = ary.length - 1; i >= 0; i--)
		{
			int y = ary[i] >> 16 & 0xFF; // Y
			int b = (ary[i] >> 8 & 0xFF) - 128; // Pb
			int r = (ary[i] & 0xFF) - 128; // Pr

			int g = (y << 8) + -88 * b + -183 * r >> 8; //
			b = (y << 8) + 454 * b >> 8;
			r = (y << 8) + 359 * r >> 8;

			if (r > 255)
				r = 255;
			else if (r < 0) r = 0;
			if (g > 255)
				g = 255;
			else if (g < 0) g = 0;
			if (b > 255)
				b = 255;
			else if (b < 0) b = 0;

			ary[i] = 0xFF000000 | (r << 8 | g) << 8 | b;
		}
		img.setRGB(0, 0, img.getWidth(), img.getHeight(), ary, 0, img.getWidth());
		return img;
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
	
	public void salir(){		
		System.exit(0);
	}
}

