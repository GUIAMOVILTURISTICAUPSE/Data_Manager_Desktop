package pojos;

public class Atractivo {
	
	private String _id;
	private Sync _sync;
	private String rev;
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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Sync get_sync() {
		return _sync;
	}

	public void set_sync(Sync _sync) {
		this._sync = _sync;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}
	
}
