package webservices;

import org.springframework.web.client.RestTemplate;

import pojos.Recurso;

public class RecursosWebServices implements ConsumableWebService<Recurso> {

	@Override
	public Recurso consumeGet(String url, String... params) {

		 RestTemplate restTemplate = new RestTemplate();
		 String urlCompleto = url;
		 for(String s: params)
		 {
			 url = url + "/" + s;
		 }
	     Recurso r = restTemplate.getForObject(urlCompleto, Recurso.class);
	     System.out.println(r.toString());	
	     return r;
	}


}
