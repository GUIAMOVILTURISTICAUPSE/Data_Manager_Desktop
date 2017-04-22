package controllers;

import configuration.PropertyManager;
import webservices.GenericWebService;

public class ControllerHelper<R> {

	PropertyManager propertyManager = new PropertyManager();
	
	public R cargarDatosWebService(String id, Class<R> clase)
	{
		R pojoCargado = null;
		if(!id.equals(null) && !id.equals(""))
		{
			String urlBase = PropertyManager.getURLBase();
			
			String urlRelativo = propertyManager.getUrlRelativoDesdeClase(clase.getName());
			GenericWebService<R> webService = new GenericWebService<R>(clase);
			
			
			
			pojoCargado = webService.consumeGet(urlBase, urlRelativo, id);
			if(pojoCargado==null)
			{
				System.err.println("Error con el webservice, objeto no cargado!");
			}else{
				return pojoCargado;
				
			}
		}else{
			System.err.println("No hay id valido para buscar en WebService");
		}
		return pojoCargado;
		
	}
	
	public boolean guardarNuevosDatosWebService(R r, Class<R> clase)
	{
		String urlBase = PropertyManager.getURLBase();
		
		String urlRelativo = propertyManager.getUrlRelativoDesdeClase(clase.getName());
		GenericWebService<R> webService = new GenericWebService<R>(clase);
		String respuesta = webService.consumePost(r, urlBase, urlRelativo);
		System.out.println("La respuesta es: " + respuesta);
		if(respuesta== null || respuesta.equals(""))
		{
			System.err.println("Error con el webservice, objeto no cargado!");
			return false;
		}
		else{
			return true;
		}	
	}
}
