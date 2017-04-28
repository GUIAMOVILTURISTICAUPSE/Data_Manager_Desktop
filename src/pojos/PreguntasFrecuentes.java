package pojos;

public class PreguntasFrecuentes {

	public String preguntas;
	public String respuestas;

	
	public String getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(String preguntas) {
		this.preguntas = preguntas;
	}

	public String getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(String respuestas) {
		this.respuestas = respuestas;
	}

	
	public PreguntasFrecuentes() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "PreguntasFrecuentes [preguntas=" + preguntas + ", respuestas=" + respuestas + "]";
	}

	

}
