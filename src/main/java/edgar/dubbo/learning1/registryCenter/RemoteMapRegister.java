package edgar.dubbo.learning1.registryCenter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.filefilter.CanReadFileFilter;

import edgar.dubbo.learning1.framework.URL;

public class RemoteMapRegister {

	private static Map<String, List<URL>> REGISTER = new ConcurrentHashMap<>();
	
	public static void register(String interfaceName, URL url) {
		List<URL> list = REGISTER.get(interfaceName);
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(url);
		
		REGISTER.put(interfaceName, list);
		saveFile();
	}
	
	public static List<URL> get(String interfaceName) {
		REGISTER = readFile();
		
		List<URL> list = REGISTER.get(interfaceName);
//		if (list.isEmpty()) {
//			URL url = new URL("localhost", 8080);
//			list.add(url);
//		}
		
		return list;
	}
	
	public static void saveFile() {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		
		try {
			fileOutputStream = new FileOutputStream("D:\\workspace\\projects\\java_leetcode\\target\\temp.txt");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(REGISTER);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static Map<String, List<URL>> readFile() {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		
		try {
			fileInputStream = new FileInputStream("D:\\workspace\\projects\\java_leetcode\\target\\temp.txt");
			objectInputStream = new ObjectInputStream(fileInputStream);
			return (Map<String, List<URL>>)objectInputStream.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				objectInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
