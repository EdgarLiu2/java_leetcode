package edgar.springlearning.app;

import edgar.springlearning.app.service.OrderService;
import edgar.springlearning.spring.ApplicationContext;

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
