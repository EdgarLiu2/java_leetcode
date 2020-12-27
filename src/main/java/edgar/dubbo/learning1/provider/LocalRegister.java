package edgar.dubbo.learning1.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalRegister {

	private static Map<String, Class<?>> map = new ConcurrentHashMap<>();
	
	public static void register(String interfaceName, Class<?> implClass) {
		map.put(interfaceName, implClass);
	}
	
	public static Class<?> get(String interfaceName) {
		return map.get(interfaceName);
	}
}
