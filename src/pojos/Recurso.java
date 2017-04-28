package pojos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by evelyn on 18/11/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recurso {

	private ArrayList<Contacto> contactos = new ArrayList<Contacto>();
	private String horario;
	private String seguridad;
	private ArrayList<PreguntasFrecuentes> preguntasF = new ArrayList<PreguntasFrecuentes>();
	
	private String id;
	private String rev;
    private String nombre;
    private String descripcion;
    private String informacionGeneral;
    private String direccion;
    private String provincia;
    private String canton;
    private String parroquia;
    private String categoria;
    private String propietario;
    private String personaEncargada;
    	
    private ArrayList<Costo> costoRecursos = new ArrayList<Costo>();
    private ArrayList<AccesibilidadRecurso> opcionesAccesibilidad = new ArrayList<AccesibilidadRecurso>();
    private ArrayList<Facilidad> facilidadRecurso = new ArrayList<Facilidad>();
    private ArrayList<Recomendacion> recomendacion = new ArrayList<Recomendacion>();
    private Contacto infContacto;
    private float ranking;
    private ArrayList<Imagen> galeria = new ArrayList<Imagen>();
    private Imagen imagenPrincipal;
    private ArrayList<Sendero> sendero = new ArrayList<Sendero>();
    private String posicion;
    private Estado estado;
    private ArrayList<Idiomas> idiomasInformac = new ArrayList<Idiomas>();
    private ArrayList<String> preguntasFrecuentes = new ArrayList<String>();
    private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
    private ArrayList<TipoAtractivo> tipoAtractivo = new ArrayList<TipoAtractivo>();
    private ArrayList<String> tiposParqueo = new ArrayList<>();
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
	
	public ArrayList<PreguntasFrecuentes> getPreguntasF() {
		return preguntasF;
	}

	public void setPreguntasF(ArrayList<PreguntasFrecuentes> preguntasF) {
		this.preguntasF = preguntasF;
	}
    
	public ArrayList<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(ArrayList<Contacto> contactos) {
		this.contactos = contactos;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getSeguridad() {
		return seguridad;
	}

	public void setSeguridad(String seguridad) {
		this.seguridad = seguridad;
	}


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

    public Imagen getimagenPrincipal() {
        return imagenPrincipal;
    }

    public void setimagenPrincipal(Imagen imagen) {
        this.imagenPrincipal = imagen;
    }

    public ArrayList<Sendero> getSendero() {
        return sendero;
    }

    public void setSendero(ArrayList<Sendero> sendero) {
        this.sendero = sendero;
    }



    public float getRanking() {
        return ranking;
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }
    
    public String getProvincia() {
 		return provincia;
 	}

 	public void setProvincia(String provincia) {
 		this.provincia = provincia;
 	}

 	public String getCanton() {
 		return canton;
 	}

 	public void setCanton(String canton) {
 		this.canton = canton;
 	}

 	public String getParroquia() {
 		return parroquia;
 	}

 	public void setParroquia(String parroquia) {
 		this.parroquia = parroquia;
 	}

 	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getPersonaEncargada() {
		return personaEncargada;
	}

	public void setPersonaEncargada(String personaEncargada) {
		this.personaEncargada = personaEncargada;
	}
	
	public ArrayList<TipoAtractivo> getTipoAtractivo() {
		return tipoAtractivo;
	}

	public void setTipoAtractivo(ArrayList<TipoAtractivo> tipoAtractivo) {
		this.tipoAtractivo = tipoAtractivo;
	}
	
    //otros metodos

    public void verReviews(){
    }

    public void llamarContacto(){
    }

    public void calcularRanking(){

    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Costo> getCostoRecursos() {
		return costoRecursos;
	}

	public void setCostoRecursos(ArrayList<Costo> costoRecursos) {
		this.costoRecursos = costoRecursos;
	}

	public ArrayList<AccesibilidadRecurso> getOpcionesAccesibilidad() {
		return opcionesAccesibilidad;
	}

	public void setOpcionesAccesibilidad(ArrayList<AccesibilidadRecurso> opcionesAccesibilidad) {
		this.opcionesAccesibilidad = opcionesAccesibilidad;
	}

	public ArrayList<Facilidad> getFacilidadRecurso() {
		return facilidadRecurso;
	}

	public void setFacilidadRecurso(ArrayList<Facilidad> facilidadRecurso) {
		this.facilidadRecurso = facilidadRecurso;
	}

	public Contacto getInfContacto() {
		return infContacto;
	}

	public void setInfContacto(Contacto infContacto) {
		this.infContacto = infContacto;
	}

	public ArrayList<Imagen> getGaleria() {
		return galeria;
	}

	public void setGaleria(ArrayList<Imagen> galeria) {
		this.galeria = galeria;
	}

	public Imagen getImagenPrinc() {
		return imagenPrincipal;
	}

	public void setImagenPrinc(Imagen imagenPrinc) {
		this.imagenPrincipal = imagenPrinc;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public ArrayList<Idiomas> getIdiomasInformac() {
		return idiomasInformac;
	}

	public void setIdiomasInformac(ArrayList<Idiomas> idiomasInformac) {
		this.idiomasInformac = idiomasInformac;
	}

	public ArrayList<String> getPreguntasFrecuentes() {
		return preguntasFrecuentes;
	}

	public void setPreguntasFrecuentes(ArrayList<String> preguntasFrecuentes) {
		this.preguntasFrecuentes = preguntasFrecuentes;
	}

	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String get_rev() {
		return rev;
	}
	
	public ArrayList<String> getTiposParqueo() {
		return tiposParqueo;
	}

	public void setTiposParqueo(ArrayList<String> tiposParqueo) {
		this.tiposParqueo = tiposParqueo;
	}
	
    
	@Override
	public String toString() {
		return "Recurso [contactos=" + contactos + ", horario=" + horario + ", seguridad=" + seguridad + ", preguntasF="
				+ preguntasF + ", id=" + id + ", rev=" + rev + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", informacionGeneral=" + informacionGeneral + ", direccion=" + direccion + ", provincia=" + provincia
				+ ", canton=" + canton + ", parroquia=" + parroquia + ", categoria=" + categoria + ", propietario="
				+ propietario + ", personaEncargada=" + personaEncargada + ", costoRecursos=" + costoRecursos
				+ ", opcionesAccesibilidad=" + opcionesAccesibilidad + ", facilidadRecurso=" + facilidadRecurso
				+ ", recomendacion=" + recomendacion + ", infContacto=" + infContacto + ", ranking=" + ranking
				+ ", galeria=" + galeria + ", imagenPrincipal=" + imagenPrincipal + ", sendero=" + sendero
				+ ", posicion=" + posicion + ", estado=" + estado + ", idiomasInformac=" + idiomasInformac
				+ ", preguntasFrecuentes=" + preguntasFrecuentes + ", comentarios=" + comentarios + ", tipoAtractivo="
				+ tipoAtractivo + ", tiposParqueo=" + tiposParqueo + "]";
	}

}
