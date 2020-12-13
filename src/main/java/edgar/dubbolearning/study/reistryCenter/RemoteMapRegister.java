package edgar.dubbolearning.study.reistryCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RemoteMapRegister {

	private static Map<String, List<URL>> REGISTER = new ConcurrentHashMap<>();
	
	public static void register(String interfaceName, URL url) {
		List<URL> list = REGISTER.get(interfaceName);
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(url);
		
		REGISTER.put(interfaceName, list);
		//saveFile();
	}
	
	public static List<URL> get(String interfaceName) {
		
		List<URL> list = REGISTER.get(interfaceName);
		return list;
	}
	
	public static void saveFile() {
		
	}
}
