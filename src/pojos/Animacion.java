package pojos;

import java.util.UUID;

public class Animacion {

	private String id;
	private String nombreAnimacion;
	private TipoAnimacion tipo;
	private String url;
	
	public Animacion()
	{
		id = UUID.randomUUID().toString();
	}
	
	public Animacion(Animacion animacion)
	{
		id = animacion.getId();
		nombreAnimacion = animacion.getNombreAnimacion();
		tipo = animacion.getTipo();
		url = animacion.getUrl();
	}


	public String getId() {
		return id;
	}

	public String getNombreAnimacion() {
		return nombreAnimacion;
	}
	
	public void setNombreAnimacion(String nombreAnimacion) {
		this.nombreAnimacion = nombreAnimacion;
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
		return "Animacion [nombreAnimacion=" + nombreAnimacion + ", tipo=" + tipo + ", url=" + url + "]";
	}
	
	
}
