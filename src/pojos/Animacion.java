package pojos;

import java.util.UUID;

public class Animacion {

	private String id;
	private String titulo;
	private TipoAnimacion tipo;
	private String descripcion;
	private String url;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Animacion()
	{
		id = UUID.randomUUID().toString();
	}
	
	public Animacion(Animacion animacion)
	{
		id = animacion.getId();
		titulo = animacion.getTitulo();
		descripcion = animacion.getDescripcion();
		tipo = animacion.getTipo();
		url = animacion.getUrl();
	}


	public String getId() {
		return id;
	}

	public TipoAnimacion getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoAnimacion tipo) {
		this.tipo = tipo;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Animacion [nombreAnimacion=" + titulo + ", tipo=" + tipo + ", url=" + url + "]";
	}
	
	
}
