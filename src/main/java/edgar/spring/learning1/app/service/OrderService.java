package edgar.spring.learning1.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edgar.spring.learning1.spring.Autowired;
import edgar.spring.learning1.spring.BeanNameAware;
import edgar.spring.learning1.spring.Component;
import edgar.spring.learning1.spring.InitializingBean;
import edgar.spring.learning1.spring.Scope;
import edgar.spring.learning1.spring.ScopeType;

@Component("orderService")
@Scope(ScopeType.PROTOTYPE)
public class OrderService implements APIService, InitializingBean, BeanNameAware {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;
	
	private String beanName;


	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void afterPropertiesSet() {
		logger.info("call afterPropertiesSet()");
	}

	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
 	}
	
	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}

}
