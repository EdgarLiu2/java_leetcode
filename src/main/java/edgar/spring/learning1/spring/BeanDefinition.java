package edgar.spring.learning1.spring;

public class BeanDefinition {

	private ScopeType scope;
	private Class<?> beanClass;
	
	public BeanDefinition() {
		
	}
	
	public BeanDefinition(ScopeType scope, Class<?> beanClass) {
		this.scope = scope;
		this.beanClass = beanClass;
	}
	
	/**
	 * @return the scope
	 */
	public ScopeType getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(ScopeType scope) {
		this.scope = scope;
	}
	/**
	 * @return the beanClass
	 */
	public Class<?> getBeanClass() {
		return beanClass;
	}
	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}
	
	
}
