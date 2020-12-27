
package edgar.dubbo.learning1.provider.impl;

import edgar.dubbo.learning1.provider.api.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String username) {
		return "hello: " + username;
	}

}
