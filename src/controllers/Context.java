package controllers;

import javafx.scene.Scene;
import pojos.Animacion;
import pojos.Imagen;
import pojos.Recurso;
import pojos.Sendero;

public class Context {

		private final static Context instance = new Context();
		
		public static Context getInstance()
		{
			return instance;
		}
			
		private Recurso recurso;
		private Sendero sendero;
		private Imagen imagen;
		private Animacion animacion;
		private Scene scene;

		public Scene getScene() {
			return scene;
		}
		
		public void setScene(Scene scene) {
			this.scene = scene;
		}
		
		public Recurso getRecurso() {
			return recurso;
		}
		
		public void setRecurso(Recurso recurso) {
			this.recurso = recurso;
		}
		
		public Imagen getImagen() {
			return imagen;
		}
		
		public void setImagen(Imagen imagen) {
			this.imagen = imagen;
		}

		public Sendero getSendero() {
			return sendero;
		}

		public void setSendero(Sendero sendero) {
			this.sendero = sendero;
		}

		public Animacion getAnimacion() {
			return animacion;
		}

		public void setAnimacion(Animacion animacion) {
			this.animacion = animacion;
		}
		
		
		

}
