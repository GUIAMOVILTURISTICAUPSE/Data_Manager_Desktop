package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pojos.UsuarioAntiguo;

public class ControllerUsuario {

	@FXML TextField tvUserName;
	
	public ControllerUsuario() {
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioAntiguo grabarObjeto()
	{
		UsuarioAntiguo u = new UsuarioAntiguo();
		u.setUserName(tvUserName.getText());
		//TODO llenar todos los campos
		
		return u;
		
		
	}
	
	public void traerObjeto(UsuarioAntiguo u)
	{
		//Validaciones
		tvUserName.setText(u.getUserName());
	}

}
