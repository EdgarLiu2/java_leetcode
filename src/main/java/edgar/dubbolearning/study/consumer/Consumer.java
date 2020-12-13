package edgar.dubbolearning.study.consumer;

import edgar.dubbolearning.study.framework.Invocation;
import edgar.dubbolearning.study.protocal.http.MyHttpClient;
import edgar.dubbolearning.study.provider.api.HelloService;

public class Consumer {

	public static void main(String[] args) throws InterruptedException {
		
		Invocation invocation = new Invocation(
				HelloService.class.getName(),
				"sayHello",
				new Class[] {String.class},
				new Object[] {"Dubbo world"});
		
		MyHttpClient httpClient = new MyHttpClient();
		String result = httpClient.send("localhost", 8080, invocation);

		System.out.println(result);
	}

}
