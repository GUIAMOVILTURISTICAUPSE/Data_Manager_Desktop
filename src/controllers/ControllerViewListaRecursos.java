package controllers;

import java.util.ArrayList;
import java.util.List;

import pojos.Recurso;

public class ControllerViewListaRecursos {

	private List<Recurso> listaRecursos = new ArrayList<Recurso>();
	ControllerHelper<Recurso> controllerHelper;
	
	public void initialize()
	{

	}
	
	public ControllerViewListaRecursos()
	{
		controllerHelper= new ControllerHelper<Recurso>();
	}
	
	public void cargarDatosWebService()
	{
		listaRecursos =  controllerHelper.cargarListaDatosWebService(Recurso.class);
		System.out.println(listaRecursos);
	}
}
