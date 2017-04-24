package pojos;

public class Animacion {

	private String nombreAnimacion;
	private TipoAnimacion tipo;
	private String url;
	
	public Animacion()
	{
		
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
