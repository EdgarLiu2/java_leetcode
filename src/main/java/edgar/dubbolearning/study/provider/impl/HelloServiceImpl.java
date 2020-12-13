
package edgar.dubbolearning.study.provider.impl;

import edgar.dubbolearning.study.provider.api.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String username) {
		return "hello: " + username;
	}

}
