package controllers;

import java.util.List;

import configuration.PropertyManager;
import webservices.GenericWebServiceConsumer;

public class ControllerHelper<R> {

	PropertyManager propertyManager = new PropertyManager();
	
	public R cargarDatosWebService(String id, Class<R> clase)
	{
		R pojoCargado = null;
		if(!id.equals(null) && !id.equals(""))
		{
			String urlBase = PropertyManager.getURLBase();
			
			String urlRelativo = propertyManager.getUrlRelativoDesdeClase(clase.getName());
			GenericWebServiceConsumer<R> webService = new GenericWebServiceConsumer<R>(clase);
			
			
			
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
		GenericWebServiceConsumer<R> webService = new GenericWebServiceConsumer<R>(clase);
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
	
	public void borrarDatosWebService(String id, String rev, Class<R> clase)
	{
		String urlBase = PropertyManager.getURLBase();
		
		String urlRelativo = propertyManager.getUrlRelativoDesdeClase(clase.getName());
		String urlFinal = urlBase.concat("/").concat(urlRelativo);
		GenericWebServiceConsumer<R> webService = new GenericWebServiceConsumer<R>(clase);
		webService.consumeDelete(urlFinal, id, rev);
		
	}
	
	public List<R> cargarListaDatosWebService(Class<R> clase)
	{
		List<R> listaPojosCargado = null;
		if(clase!=null)
		{
			String urlBase = PropertyManager.getURLBase();
			
			String urlRelativo = propertyManager.getUrlRelativoDesdeClase(clase.getName());
			GenericWebServiceConsumer<R> webService = new GenericWebServiceConsumer<R>(clase);
			
			String urlFinal = urlBase.concat("/").concat(urlRelativo);
			
			listaPojosCargado = webService.consumeGetAll(urlFinal);
			if(listaPojosCargado==null)
			{
				System.err.println("Error con el webservice, lista de objetos no cargada!");
			}else{
				return listaPojosCargado;
				
			}
		}
		return listaPojosCargado;
		
	}
}
