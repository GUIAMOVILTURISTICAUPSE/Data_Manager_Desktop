package pojos;

public class Atractivo {
	
	public String nombre;
	public String descripcion;
	public TipoAtractivo tipo;
	public Imagen imagenPrincipal;
	public Estado estado;
	
	public Atractivo()
	{
		
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



	public TipoAtractivo getTipo() {
		return tipo;
	}



	public void setTipo(TipoAtractivo tipo) {
		this.tipo = tipo;
	}



	public Imagen getImagenPrincipal() {
		return imagenPrincipal;
	}



	public void setImagenPrincipal(Imagen imagenPrincipal) {
		this.imagenPrincipal = imagenPrincipal;
	}



	public Estado getEstado() {
		return estado;
	}



	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	

	
	
	@Override
	public String toString() {
		return "Atractivo [nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo + ", imagenPrincipal="
				+ imagenPrincipal + ", estado=" + estado + "]";
	}
	
}
