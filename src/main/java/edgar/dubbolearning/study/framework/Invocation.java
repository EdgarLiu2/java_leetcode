package edgar.dubbolearning.study.framework;

public class Invocation {

	private String interfaceName;
	private String methodName;
	private Class<?>[] paramTypes;
	private Object[] params;
	
	public Invocation(String interfaceName, String methodName, Class<?>[] paramTypes, Object[] params) {
		this.interfaceName = interfaceName;
		this.methodName = methodName;
		this.paramTypes = paramTypes;
		this.params = params;
	}

	/**
	 * @return the interfaceName
	 */
	public String getInterfaceName() {
		return interfaceName;
	}

	/**
	 * @param interfaceName the interfaceName to set
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the paramTypes
	 */
	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	/**
	 * @param paramTypes the paramTypes to set
	 */
	public void setParamTypes(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	/**
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}
	
}
