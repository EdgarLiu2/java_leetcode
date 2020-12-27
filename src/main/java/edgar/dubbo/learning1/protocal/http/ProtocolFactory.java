package edgar.dubbo.learning1.protocal.http;

import edgar.dubbo.learning1.framework.Protocol;

public class ProtocolFactory {

	public static Protocol getProtocol() {
		String name = System.getProperty("protocalName", "http");
		
		switch (name) {
		case "http":
			return new HTTPProtocol();
		case "dubbo":
			return null;
		default:
			return new HTTPProtocol();
		}
	}
}
