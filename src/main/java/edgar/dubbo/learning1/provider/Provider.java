package edgar.dubbo.learning1.provider;

import edgar.dubbo.learning1.framework.Protocol;
import edgar.dubbo.learning1.framework.URL;
import edgar.dubbo.learning1.protocal.http.HTTPProtocol;
import edgar.dubbo.learning1.protocal.http.HttpServer;
import edgar.dubbo.learning1.protocal.http.ProtocolFactory;
import edgar.dubbo.learning1.provider.api.HelloService;
import edgar.dubbo.learning1.provider.impl.HelloServiceImpl;
import edgar.dubbo.learning1.registryCenter.RemoteMapRegister;

public class Provider {

	public static void main(String[] args) {
		// 1. 远程注册服务：接口名-[服务提供者的URL]
		RemoteMapRegister.register(HelloService.class.getName(), new URL("localhost", 8080));

		// 2. 本地注册服务：接口名-实现类
		LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
		
		// 3. 启动Tomcat
//		HttpServer httpServer = new HttpServer();
//		httpServer.start("tomcat", "localhost", 8080);
		Protocol protocol = ProtocolFactory.getProtocol();
		protocol.start(new URL("localhost", 8080));
	}

}
