package pojos;

public class LocacionAtractivo {
	private String coordenadasGPS;
	private Estado estado;
	
	public LocacionAtractivo()
	{
		
	}

	public String getCoordenadasGPS() {
		return coordenadasGPS;
	}

	public void setCoordenadasGPS(String coordenadasGPS) {
		this.coordenadasGPS = coordenadasGPS;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "LocacionAtractivo [coordenadasGPS=" + coordenadasGPS + ", estado=" + estado + "]";
	}
	
	
}
