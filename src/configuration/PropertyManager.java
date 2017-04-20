package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan
@PropertySource("application.properties")
public class PropertyManager {

    @Value("${server.hostname}")
    private String serverHostname;
    
    @Value("${server.port}")
    private String serverPort;
    
    @Value("${services.index.path}")
    private String servicesIndexPath;
    
	public String baseURL = "http://"+serverHostname+":"+serverPort+"/"+servicesIndexPath; 
	
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
		baseURL = "http://"+serverHostname+":"+serverPort+"/"+servicesIndexPath; 
		mensajeCargaCorrectaPropiedades();
	}

	private void mensajeCargaCorrectaPropiedades() {
		System.out.println("**********Cargando Propiedades**********");
		System.out.println("Server IP - IP del Servidor: " + serverHostname);
		System.out.println("ServerPort - Puerto del Servicio: " + serverPort);
		System.out.println("ServiceIndexPath - URL Relativo de la Raiz del Servicio: " +servicesIndexPath);
		
		System.out.println("URL Servicio - URL absoluto de la Raiz del Servicio segun archivo de configuracio: " + baseURL);
		System.out.println("**********Carga de propiedades Exitosa**********\n\n\n");
	}
			
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		System.out.println("Entrando a properties");
		return new PropertySourcesPlaceholderConfigurer();
	}
}
