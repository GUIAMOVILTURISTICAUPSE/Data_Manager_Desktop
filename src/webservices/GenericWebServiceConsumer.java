package webservices;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JacksonTransformers;

public class GenericWebServiceConsumer<X> implements ConsumableWebService<X>{

	private final Class<X> valueTypeParameterClass;
	//private final Class<X[]> valueTypeParameterClassArray;
	
	 public GenericWebServiceConsumer(final Class<X> valueClass)
	 {
	    	this.valueTypeParameterClass = valueClass;
	 //   	this.valueTypeParameterClassArray
	 }
	
	@Override
	public X consumeGet(String url, String... params) {
		 RestTemplate restTemplate = new RestTemplate();
		 String urlCompleto = url;
		 for(String s: params)
		 {
			 urlCompleto = urlCompleto + "/" + s;
		 }
		 X x;
		 
		 try
		 {
			 x = restTemplate.getForObject(urlCompleto, valueTypeParameterClass);
			 System.out.println(x.toString());	
		 }catch (Exception e) {
			System.err.println("Error en el webservice");
			System.err.println("Causa:" + e.getCause());
			System.err.println("Mensaje: " + e.getMessage());
			e.printStackTrace();
			x = null;
		}
	     
	     return x;
	}

	@Override
	public String consumePost(X x, String url, String... params) {
		RestTemplate restTemplate = new RestTemplate();
		String urlCompleto = url;
		for(String s: params)
		 {
			 urlCompleto = urlCompleto + "/" + s;
		 }

		String respuesta;		
		try{
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			String xAsJson = JacksonTransformers.MAPPER.writeValueAsString(x);
			System.out.println("Objeto a imprimir como JSON: " + xAsJson);
			HttpEntity<String> entity = new HttpEntity<String>(xAsJson,headers);
			respuesta = restTemplate.postForObject(urlCompleto, entity, String.class);
			
			//respuesta = restTemplate.postForObject(urlCompleto, x, String.class);
			

		}catch (Exception e) {
			System.err.println("Error en el webservice");
			System.err.println("Causa:" + e.getCause());
			System.err.println("Mensaje: " + e.getMessage());
			e.printStackTrace();
			respuesta = null;
		}
		return respuesta;
	}
	
	@Override
	public void consumeDelete(String url, String id, String rev) {
		RestTemplate restTemplate = new RestTemplate();
		
		try{
			//String urlCompleto = url + "/" + URLEncoder.encode(id, "UTF-8") + "?" + "rev="+rev;
			String urlCompleto = url + "/" + id.replaceAll(" ","%20") + "?" + "rev="+rev;
			//String urlCompleto = url + "/" + id + "?" + "rev="+rev;
			//urlCompleto = URLEncoder.encode(urlCompleto, "UTF-8");
			System.out.println("URL para Delete: " + urlCompleto);
			URI deleteURI = new URI(urlCompleto);
			//No elimina..!! 
			restTemplate.delete(deleteURI);
			
		}catch (Exception e) {
			System.err.println("Error en el webservice");
			System.err.println("Causa:" + e.getCause());
			System.err.println("Mensaje: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<X> consumeGetAll(String url) {
		RestTemplate restTemplate = new RestTemplate();
		String urlCompleto = url;
		
		
		//@SuppressWarnings("unchecked")
		X[] arrayOfx = (X[]) Array.newInstance(valueTypeParameterClass, 1000);;
		 
		try
		{			
			arrayOfx = (X[]) restTemplate.getForObject(urlCompleto, arrayOfx.getClass());
			System.out.println(arrayOfx.toString());	
		}catch (Exception e) {
			System.err.println("Error en el webservice");
			System.err.println("Causa:" + e.getCause());
			System.err.println("Mensaje: " + e.getMessage());
			e.printStackTrace();
			arrayOfx = null;
		}
	     
	     return Arrays.asList(arrayOfx);
	}



	
}
