package pojos;

import java.util.ArrayList;

/**
 * Created by evelyn on 18/11/2016.
 */

public class Recurso {
    //id
    private String nombre;
    private String descripcion;
    private String informacionGeneral;
    private String direccion;
    //private ArrayList<Costo> costoRecursos = new ArrayList<Costo>();
    //private ArrayList<Facilidad> facilidadRecurso = new ArrayList<Facilidad>();
    private ArrayList<Recomendacion> recomendacion = new ArrayList<Recomendacion>();
    //informacion contacto
    private ArrayList<Imagen> imagen = new ArrayList<Imagen>();
    private ArrayList<Senderos> sendero = new ArrayList<Senderos>();
    //posicion
    //estado
    //private ArrayList<Idiomas> idiomasInformac = new ArrayList<Idiomas>();
    private float ranking;

    //constructor

    public Recurso(){

    }

    /*
    public Recurso(String nombre, String descripcion, String informacionGeneral, String direccion, ArrayList<Costo> costoRecursos, ArrayList<Facilidad> facilidadRecurso, ArrayList<Recomendacion> recomendacion, ArrayList<Imagen> imagen, ArrayList<Senderos> sendero, ArrayList<Idiomas> idiomasInformac, float ranking) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacionGeneral = informacionGeneral;
        this.direccion = direccion;
        //this.costoRecursos = costoRecursos;
        //this.facilidadRecurso = facilidadRecurso;
        this.recomendacion = recomendacion;
        this.imagen = imagen;
        this.sendero = sendero;
        //this.idiomasInformac = idiomasInformac;
        this.ranking = ranking;
    }
    */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInformacionGeneral() {
        return informacionGeneral;
    }

    public void setInformacionGeneral(String informacionGeneral) {
        this.informacionGeneral = informacionGeneral;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /*
    public ArrayList<Costo> getCostoRecursos() {
        return costoRecursos;
    }

    public void setCostoRecursos(ArrayList<Costo> costoRecursos) {
        this.costoRecursos = costoRecursos;
    }

    public ArrayList<Facilidad> getFacilidadRecurso() {
        return facilidadRecurso;
    }

    public void setFacilidadRecurso(ArrayList<Facilidad> facilidadRecurso) {
        this.facilidadRecurso = facilidadRecurso;
    }
    
    public ArrayList<Idiomas> getIdiomasInformac() {
        return idiomasInformac;
    }

    public void setIdiomasInformac(ArrayList<Idiomas> idiomasInformac) {
        this.idiomasInformac = idiomasInformac;
    }
    */

    public ArrayList<Recomendacion> getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(ArrayList<Recomendacion> recomendacion) {
        this.recomendacion = recomendacion;
    }

    public ArrayList<Imagen> getImagen() {
        return imagen;
    }

    public void setImagen(ArrayList<Imagen> imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Senderos> getSendero() {
        return sendero;
    }

    public void setSendero(ArrayList<Senderos> sendero) {
        this.sendero = sendero;
    }



    public float getRanking() {
        return ranking;
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }

    //otros metodos

    public void verReviews(){
    }

    public void llamarContacto(){
    }

    public void calcularRanking(){

    }










}
