package kenijey.harshenuniverse.objecthandlers;

import java.util.HashMap;

public class HarshenMap<K, V> extends HashMap<K, V> 
{
	public HarshenMap<K, V> addToMap(K key, V value)
	{
		put(key, value);
		return this;
	}
}
