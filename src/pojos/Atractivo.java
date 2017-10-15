package pojos;

import java.util.ArrayList;

public class Atractivo {
	
	private String _id;
	private Sync _sync;
	private String rev;
	public String nombre;
	public String descripcion;
	public TipoAtractivo tipo;
	public Imagen imagenPrincipal;
	public Estado estado;
	private ArrayList<Imagen> galeria = new ArrayList<Imagen>();
	
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

	public ArrayList<Imagen> getGaleria() {
		return galeria;
	}

	public void setGaleria(ArrayList<Imagen> galeria) {
		this.galeria = galeria;
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	
}
