package edgar.springlearning.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edgar.springlearning.spring.Autowired;
import edgar.springlearning.spring.BeanNameAware;
import edgar.springlearning.spring.Component;
import edgar.springlearning.spring.InitializingBean;
import edgar.springlearning.spring.Scope;
import edgar.springlearning.spring.ScopeType;

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
