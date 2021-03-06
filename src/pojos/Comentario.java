package pojos;

import java.security.Timestamp;
import java.util.ArrayList;

public class Comentario {
	 private String _id;
	    private Timestamp fecha;
	    private String titulo;
	    private String descipcion;
	    private boolean reportado;
	    private int votosFavor;
	    private int votosContra;
	    private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

	    //constructor por defecto

	    public Comentario() {
	    	
	    }
	    
	    

	    public Comentario(String _id, Timestamp fecha, String titulo, String descipcion, boolean reportado,
				int votosFavor, int votosContra, ArrayList<Comentario> comentarios) {
			super();
			this._id = _id;
			this.fecha = fecha;
			this.titulo = titulo;
			this.descipcion = descipcion;
			this.reportado = reportado;
			this.votosFavor = votosFavor;
			this.votosContra = votosContra;
			this.comentarios = comentarios;
		}



		//getter and setters
	    public String get_id() {
	        return _id;
	    }

	    public void set_id(String _id) {
	        this._id = _id;
	    }

	    public Timestamp getFecha() {
	        return fecha;
	    }

	    public void setFecha(Timestamp fecha) {
	        this.fecha = fecha;
	    }

	    public String getTitulo() {
	        return titulo;
	    }

	    public void setTitulo(String titulo) {
	        this.titulo = titulo;
	    }

	    public String getDescipcion() {
	        return descipcion;
	    }

	    public void setDescipcion(String descipcion) {
	        this.descipcion = descipcion;
	    }

	    public boolean isReportado() {
	        return reportado;
	    }

	    public void setReportado(boolean reportado) {
	        this.reportado = reportado;
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

	    public ArrayList<Comentario> getComentarios() {
	        return comentarios;
	    }

	    public void setComentarios(ArrayList<Comentario> comentarios) {
	        this.comentarios = comentarios;
	    }
	    
	    //metodos
	    public void responder(){

	    }
	    @Override
		public String toString() {
			return "Comentario [fecha=" + fecha + ", descipcion=" + descipcion + ", comentarios=" + comentarios + "]";
		}

		public void reportar(){

	    }
}
