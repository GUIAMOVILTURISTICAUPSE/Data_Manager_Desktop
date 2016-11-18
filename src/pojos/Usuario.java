package pojos;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.*;;

/**
 * Created by ivans on 15-Nov-16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario {

    //@JsonProperty(value = "_id")
    //private String docId;

    private String _rev;

    @JsonProperty(value = "_id")
    private String userName;
    private String email;
    private String nombre;
    private String apellidos;
    private String passwordHash;

    public Usuario()
    {}

    public Usuario(String userName) {
        this.userName = userName;
        this.nombre = "";
        this.apellidos = "";
        this.passwordHash = "";
    }

    public Usuario(String userName, String email) {
        this.userName = userName;
        this.email = email;
        this.nombre = "";
        this.apellidos = "";
        this.passwordHash = "";
    }

    /*
    public String getDocId() {
        return docId;
    }*/

    //@JsonAnyGetter
    public String getUserName() {
        return userName;
    }

    //@JsonAnySetter
    public void setUserName(String userName) {
        this.userName = userName;
    }

    //@JsonAnyGetter
    public String getEmail() {
        return email;
    }

   // @JsonAnySetter
    public void setEmail(String email) {
        this.email = email;
    }

   // @JsonAnyGetter
    public String getNombre() {
        return nombre;
    }

    //@JsonAnySetter
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //@JsonAnyGetter
    public String getApellidos() {
        return apellidos;
    }

   // @JsonAnySetter
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

   // @JsonAnyGetter
    public String getPasswordHash() {
        return passwordHash;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    //@JsonAnySetter
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean verificarPassword(String otherPassword)
    {
        return this.getPasswordHash().equals(otherPassword.trim());
    }

    public void hashPassword()
    {
        //TODO Crear funcion de Hashing para no almacenar la contrasena en seco
        //Mecanismo de Hash
    }

	@Override
	public String toString() {
		return "Usuario [userName=" + userName + ", email=" + email + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", passwordHash=" + passwordHash + "]";
	}
}
