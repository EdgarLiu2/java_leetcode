package edgar.dubbolearning.study.provider;

import edgar.dubbolearning.study.framework.URL;
import edgar.dubbolearning.study.protocal.http.HttpServer;
import edgar.dubbolearning.study.provider.api.HelloService;
import edgar.dubbolearning.study.provider.impl.HelloServiceImpl;
import edgar.dubbolearning.study.reistryCenter.RemoteMapRegister;

public class Provider {

	public static void main(String[] args) {
		// 1. 远程注册服务：接口名-[服务提供者的URL]
		RemoteMapRegister.register(HelloService.class.getName(), new URL("localhost", 8080));

		// 2. 本地注册服务：接口名-实现类
		LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
		
		// 3. 启动Tomcat
		HttpServer httpServer = new HttpServer();
		httpServer.start("tomcat", "localhost", 8080);
	}

}
