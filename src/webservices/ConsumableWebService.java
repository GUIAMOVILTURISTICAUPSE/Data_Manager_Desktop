package webservices;

import java.util.List;

public interface ConsumableWebService<X> {
	public X consumeGet(String url, String... params);
	public String consumePost(X x, String url, String... params);
	public List<X> consumeGetAll(String url);
	public void consumeDelete(String url, String id, String rev);
	public void consumePut(X x, String url, String[] params);
}
