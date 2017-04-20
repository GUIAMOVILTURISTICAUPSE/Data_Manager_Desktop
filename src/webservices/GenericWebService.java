package webservices;

import org.springframework.web.client.RestTemplate;

import pojos.Recurso;

public class GenericWebService<X> implements ConsumableWebService<X>{

	private final Class<X> valueTypeParameterClass;
	
	 public GenericWebService(final Class<X> valueClass)
	 {
	    	this.valueTypeParameterClass = valueClass;
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

	
}
