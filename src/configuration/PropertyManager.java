package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import pojos.Recurso;
import pojos.Sendero;

@Configuration
@ComponentScan
@PropertySource("application.properties")
public class PropertyManager {

    private String serverHostname;
    
    private String serverPort;
    
    private String servicesIndexPath;
    
	public static String baseURL;
	
	private String recursosPath;
	private String senderosPath;
	public Map<String, String> mapClaseURLRelativo;
	
	public PropertyManager() {
		
		String resourceName = "application.properties"; // could also be a constant
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
		    props.load(resourceStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error cargando propiedades de application.properties. Asegurese de que el archivo existe y que se encuentre en el build path.");
			e.printStackTrace();
			System.exit(1);
		}
		
		serverHostname = props.getProperty("server.hostname");
		serverPort = props.getProperty("server.port");
		servicesIndexPath = props.getProperty("services.index.path");
		recursosPath = props.getProperty("recursos.path");
		senderosPath = props.getProperty("senderos.path");
		baseURL = "http://"+serverHostname+":"+serverPort+"/"+servicesIndexPath; 
		mensajeCargaCorrectaPropiedades();
		mapearClasesAPropiedadURLRelativo();
	}
	
	private void mapearClasesAPropiedadURLRelativo()
	{
		mapClaseURLRelativo = new HashMap<String, String>();
		mapClaseURLRelativo.put(Recurso.class.getName(), recursosPath);
		mapClaseURLRelativo.put(Sendero.class.getName(), senderosPath);
	}
	
	public String getUrlRelativoDesdeClase(String nombreClase)
	{
		String urlRelativo = mapClaseURLRelativo.get(nombreClase);
		if(urlRelativo.equals(null) || urlRelativo.equals(""))
		{
			System.err.println("No se encontro URL relativo para este nombre de clase. Revisar mapeo");
		}
		
		return urlRelativo;
	}

	private void mensajeCargaCorrectaPropiedades() {
		System.out.println("**********Cargando Propiedades**********");
		System.out.println("Server IP - IP del Servidor: " + serverHostname);
		System.out.println("ServerPort - Puerto del Servicio: " + serverPort);
		System.out.println("ServiceIndexPath - URL Relativo de la Raiz del Servicio: " +servicesIndexPath);
		
		System.out.println("URL Servicio - URL absoluto de la Raiz del Servicio segun archivo de configuracio: " + baseURL);
		System.out.println("**********Carga de propiedades Exitosa**********\n\n\n");
	}
	
	public static String getURLBase()
	{
		if(PropertyManager.baseURL.equals(null) || PropertyManager.baseURL.equals(""))
		{
			PropertyManager pm = new PropertyManager();
		}
		
		return PropertyManager.baseURL;
	}

	public String getServerHostname() {
		return serverHostname;
	}

	public void setServerHostname(String serverHostname) {
		this.serverHostname = serverHostname;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getServicesIndexPath() {
		return servicesIndexPath;
	}

	public void setServicesIndexPath(String servicesIndexPath) {
		this.servicesIndexPath = servicesIndexPath;
	}

	public String getRecursosPath() {
		return recursosPath;
	}

	public void setRecursosPath(String recursosPath) {
		this.recursosPath = recursosPath;
	}

	public String getSenderosPath() {
		return senderosPath;
	}

	public void setSenderosPath(String senderosPath) {
		this.senderosPath = senderosPath;
	}
	
	
	
	
}
