package pojos;

public class Contacto {
	private String id;
    private String nombreResponsable;
    private String telefono;
    private String facebook;
    private  String twitter;
    private  String instagram;
    private String email;
    private String web;
    private Imagen imagenContacto;
    

    public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

    public Contacto() {
    }
    
    public Contacto(String nombreResponsable) {
    	this.nombreResponsable = nombreResponsable;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public Imagen getImagenContacto() {
		return imagenContacto;
	}

	public void setImagenContacto(Imagen imagenContacto) {
		this.imagenContacto = imagenContacto;
	}

	@Override
	public String toString() {
		return "Responsable:" + nombreResponsable + "- Telefono:" + telefono;
	}

   /* @Override
    public String toString() {
        return "Contacto{" +
                "id='" + id + '\'' +
                ", nombreResponsable='" + nombreResponsable + '\'' +
                ", telefono='" + telefono + '\'' +
                ", facebook='" + facebook + '\'' +
                ", twitter='" + twitter + '\'' +
                ", email='" + email + '\'' +
                '}';
    }*/
    
    
}
