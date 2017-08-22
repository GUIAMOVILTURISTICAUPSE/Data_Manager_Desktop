package controllers;

import javafx.scene.Scene;
import pojos.Imagen;
import pojos.Recurso;

public class Context {

		private final static Context instance = new Context();
		
		public static Context getInstance()
		{
			return instance;
		}
			
		private Recurso recurso;
		private Imagen imagen;
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
		
		

}
