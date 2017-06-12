package controllers;

//Librerias
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javafx.util.converter.LocalDateStringConverter;
import pojos.Imagen;

import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controlador{
	
	Imagen pojo = new Imagen();

	public Controlador() {
		
	}
	
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
	
	public void guardar(){
		pojo.setId(ID1.getText());
		pojo.setDescripcion(descripcion1.getText());
		pojo.setFecha(fecha1.getValue());
		pojo.setTitulo(titulo1.getText());
		pojo.setUrl(url1.getText());
		pojo.setBase64(base641.getText());
		pojo.setAutor(autor1.getText());
		//pojo.setCoordenadas(coordenadas1.getText());
		//se cambio a longitud y latitud
		//pojo.setLongitud(longitud1.getText());
		//pojo.setLatitud(latitud1.getText());
		pojo.setReportado(Reportado.isSelected());
		pojo.setVotosFavor(spinner.getValue());
		pojo.setVotosContra(spinner1.getValue());
		//pojo.setEtiquetas(etiqueta1.getValue().toString());  ***************************************** ETIQUETAS ????? LATITUD Y LONGITUD ?????
		//falta etiquetas array list combobox
		//pojo.setEtiquetas((ArrayList<String>) etiqueta1.getItems());
		//falta votos favor y contra del spinner :C segundo parcial segun indicaciones
		System.out.println("******DATOS GUARDADOS*****");
		System.out.println(pojo);
	}
	
	public void cargar(){
		ID1.setText(pojo.getId());
		descripcion1.setText(pojo.getDescripcion());
		fecha1.setValue(pojo.getFecha());
		titulo1.setText(pojo.getTitulo());
		url1.setText(pojo.getUrl());
		base641.setText(pojo.getBase64());
		autor1.setText(pojo.getAutor());
		Reportado.setSelected(pojo.isReportado());
		etiqueta1.setValue(pojo.getEtiquetas().toString());
		//coordenadas1.setText(pojo.getCoordenadas());
		//se cambio a longitud y latitud
		//latitud1.setText(pojo.getLatitud());
		//longitud1.setText(pojo.getLongitud());
		//falta votos favor y contra del spinner :C pero se esta cargando en un dato text no del spinner
		votosf1.setText(String.valueOf(pojo.getVotosFavor()));
		votosc1.setText(String.valueOf(pojo.getVotosContra()));
		////////////////////////////////////////////////////////
		System.out.println("******DATOS CARGADOS******");
		System.out.println(pojo);
	}

	//Metodo Dinamico para comprobar datos llenos 
	public void carga_llenos(){
		ID1.setText(pojo.getId());
		descripcion1.setText(pojo.getDescripcion());
		fecha1.setValue(pojo.getFecha());
		titulo1.setText(pojo.getTitulo());
		url1.setText(pojo.getUrl());
		base641.setText(pojo.getBase64());
		autor1.setText(pojo.getAutor());
		etiqueta1.setValue(pojo.getEtiquetas().toString());
		//coordenadas1.setText(pojo.getCoordenadas());
		//se cambio a longitud y latitud
		Reportado.setSelected(pojo.isReportado());
		//longitud1.setText(pojo.getLongitud());
		//latitud1.setText(pojo.getLatitud());
		votosf1.setText(String.valueOf(pojo.getVotosFavor()));
		votosc1.setText(String.valueOf(pojo.getVotosContra()));
		
		Imagen pojos = new Imagen();
		ObservableList<String> etiquetaList = FXCollections.observableArrayList();
		
		pojo = crearPojoImagen();
		for(String s: pojo.getEtiquetas())
		{
			etiquetaList.add(s);
		}
		etiqueta1.setItems(etiquetaList);
		System.out.println("******DATOS CARGADOS******");
		System.out.println(pojo);
	}
	
	public void salir(){
		System.exit(0);
	}
	
	public Imagen crearPojoImagen()
	{
		Imagen pojo = new Imagen();
		ArrayList<String> etiquetas = new ArrayList<String>();
		etiquetas.add("playa");
		etiquetas.add("Salinas");
		etiquetas.add("carnaval");
		pojo.setEtiquetas(etiquetas);
		pojo.setTitulo("Carnaval en Chipipe");
		pojo.setId("2400079832");
		pojo.setDescripcion("Es la punta m�s sobresaliente de la Pen�nsula de Santa Elena, reconocida a nivel mundial por sus aguas transparentes y apacibles, ideal para ni�os y surfistas.");
		pojo.setReportado(true);
		pojo.setUrl("https://www.google.com.ec/");
		pojo.setBase64("ABCDEFGHIJKLMNOPQRSTUVWXYZ/ 64datos");
		pojo.setAutor("Paul Q.");
		pojo.setCoordenadas("gps");
		pojo.setVotosContra(50);
		pojo.setVotosFavor(20);
		//pojo.setLatitud(" 90� norte y 90� sur");
		//pojo.setLongitud("180�");
		////metodo de la fecha
		LocalDate f = LocalDate.of(2016, 12, 10);
		pojo.setFecha(f);
		//u.setFecha(d);
		return pojo;
	}
	
	ObservableList<String> etiquetaList = FXCollections.observableArrayList();
	@FXML
	public void initialize(){
		pojo.getEtiquetas().add("SALINAS");
		pojo.getEtiquetas().add("PLAYA");
		pojo.getEtiquetas().add("PAISAJE");
		pojo.getEtiquetas().add("ARENA");
		etiquetaList.addAll(pojo.getEtiquetas());
		etiqueta1.setItems(etiquetaList);
	}
	
	////////////////////////////////////////////////////////////
	//Nuevo metodo que recibe a un pojo como parametro
	public void carga_pojo(Imagen i){
		i = crearPojoImagen();
		ID1.setText(i.getId());
		descripcion1.setText(i.getDescripcion());
		fecha1.setValue(i.getFecha());
		titulo1.setText(i.getTitulo());
		url1.setText(i.getUrl());
		base641.setText(i.getBase64());
		autor1.setText(i.getAutor());
		Reportado.setSelected(i.isReportado());
		etiqueta1.setValue(i.getEtiquetas().toString());
		//coordenadas1.setText(pojo.getCoordenadas());
		//se cambio a longitud y latitud
		//latitud1.setText(i.getLatitud());
		//longitud1.setText(i.getLongitud());
		//falta votos favor y contra del spinner :C pero se esta cargando en un dato text no del spinner
		votosf1.setText(String.valueOf(i.getVotosFavor()));
		votosc1.setText(String.valueOf(i.getVotosContra()));
		////////////////////////////////////////////////////////
		System.out.println("******DATOS CARGADOS******");
		System.out.println(i);
	}
	/////////////////////////////////////////////////////////////////////
	public void carga_pojo1(){
		System.out.println(crearPojoImagen());
		carga_pojo(pojo);
	}

}


