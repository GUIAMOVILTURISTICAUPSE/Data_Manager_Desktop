package webservices;

public interface ConsumableWebService<X> {
	public X consumeGet(String url, String... params);
	public String consumePost(X x, String url, String... params);
}
