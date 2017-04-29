package pojos;

public class PreguntasFrecuentes {

	public String preguntas;
	public String respPreguntas;

	
	public String getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(String preguntas) {
		this.preguntas = preguntas;
	}
	

	public String getRespPreguntas() {
		return respPreguntas;
	}

	public void setRespPreguntas(String respPreguntas) {
		this.respPreguntas = respPreguntas;
	}
	

	
	public PreguntasFrecuentes() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "PreguntasFrecuentes [preguntas=" + preguntas + ", respuestas=" + respPreguntas + "]";
	}

	

}
