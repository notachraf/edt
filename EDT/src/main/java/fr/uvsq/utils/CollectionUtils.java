package fr.uvsq.utils;

import java.util.List;
public class CollectionUtils {

	
	private CollectionUtils() {}
	
	/**
	 * 
	 * @param <T>
	 * @param objects
	 * @return
	 */
	static public <T> boolean isEmpty(List<T> objects) {
		if (objects == null || objects.isEmpty()) return true; 
		return false; 
	}
	
 
}