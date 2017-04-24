package webservices;

import java.util.List;

public interface ConsumableWebService<X> {
	public X consumeGet(String url, String... params);
	public String consumePost(X x, String url, String... params);
	public List<X> consumeGetAll(String url);
}
