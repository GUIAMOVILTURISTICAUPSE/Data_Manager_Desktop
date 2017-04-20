package webservices;

public interface ConsumableWebService<X> {
	public X consumeGet(String url, String... params); 
}
