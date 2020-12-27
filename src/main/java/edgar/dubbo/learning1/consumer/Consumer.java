package edgar.dubbo.learning1.consumer;

import edgar.dubbo.learning1.framework.ProxyFactory;
import edgar.dubbo.learning1.provider.api.HelloService;

public class Consumer {

	public static void main(String[] args) throws InterruptedException {
		
		HelloService helloService = ProxyFactory.getProxy(HelloService.class);
		String result = helloService.sayHello("Dubbo world");
		System.out.println(result);
	}

}
