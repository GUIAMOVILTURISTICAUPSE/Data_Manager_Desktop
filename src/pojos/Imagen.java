package pojos;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Imagen {
	private String id;
    private String descripcion;
    private String titulo;
    
    //@JsonFormat(pattern = "dd::MM::yyyy")
    private LocalDate fecha;
    
    private String coordenadas;
    private int votosFavor;
    private int votosContra;
    private String url;
    private String autor;
    private Usuario autorUsuario;
    private ArrayList<String> etiquetas = new ArrayList<String>();
    private boolean reportado;

    //constructor por defecto
    public Imagen() {
    }

    //getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate date) {
        this.fecha = date;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public int getVotosFavor() {
        return votosFavor;
    }

    public void setVotosFavor(int votosFavor) {
        this.votosFavor = votosFavor;
    }

    public int getVotosContra() {
        return votosContra;
    }

    public void setVotosContra(int votosContra) {
        this.votosContra = votosContra;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Usuario getAutorUsuario() {
        return autorUsuario;
    }

    public void setAutorUsuario(Usuario autorUsuario) {
        this.autorUsuario = autorUsuario;
    }

    public ArrayList<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public boolean isReportado() {
        return reportado;
    }

    public void setReportado(boolean reportado) {
        this.reportado = reportado;
    }

    //metodos
    public void reportar(){

    }
    public void votarFavor(){

    }
    public void votarContra(){

    }
    public void etiquetar(){

    }
    public void convertirABase64(){

    }
    public void convertirDeBase64(){

    }

	public String toStringComplete() {
		return "Imagen [id=" + id + ", descripcion=" + descripcion + ", titulo=" + titulo + ", fecha=" + fecha
				+ ", coordenadas=" + coordenadas + ", votosFavor=" + votosFavor + ", votosContra=" + votosContra
				+ ", url=" + url + ", autor=" + autor + ", autorUsuario=" + autorUsuario + ", etiquetas=" + etiquetas
				+ ", reportado=" + reportado + "]";
	}

	@Override
	public String toString() {
		return "Titulo:" + titulo + ", url:" + url ;
	}

	
    
}
