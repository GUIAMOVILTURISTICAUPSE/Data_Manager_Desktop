package data_layer;

public interface RepositoryManager<K,V> {

	public V get(K key);
	public void save(Object o, boolean overwrite);
}
