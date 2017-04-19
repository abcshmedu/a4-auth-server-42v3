package edu.hm.rfurch.shareit.data;

public class ResourceManager {
	private static IData data = new MediaResource();
	
	public static IData dataAccess() {
		return data;
	}
}
