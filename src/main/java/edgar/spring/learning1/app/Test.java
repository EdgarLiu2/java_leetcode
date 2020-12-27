package edgar.spring.learning1.app;

import edgar.spring.learning1.app.service.OrderService;
import edgar.spring.learning1.spring.ApplicationContext;

public class Test {

	public static void main(String[] args) {
		// 启动MySpring
		ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);
		
		// getBean()
		OrderService orderService = (OrderService)applicationContext.getBean("orderService"); 
		System.out.println(orderService.getUserService());
		System.out.println(orderService.getBeanName());
	}

}
