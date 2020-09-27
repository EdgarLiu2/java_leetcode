package edgar.springlearning.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edgar.springlearning.spring.BeanPostProcessor;
import edgar.springlearning.spring.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		logger.info("call postProcessBeforeInitialization() for bean={}", beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		logger.info("call postProcessAfterInitialization() for bean={}", beanName);
		return bean;
	}

}
