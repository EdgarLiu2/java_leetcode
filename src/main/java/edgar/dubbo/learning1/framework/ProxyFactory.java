package edgar.dubbo.learning1.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import edgar.dubbo.learning1.protocal.http.HTTPProtocol;
import edgar.dubbo.learning1.protocal.http.MyHttpClient;
import edgar.dubbo.learning1.protocal.http.ProtocolFactory;
import edgar.dubbo.learning1.registryCenter.RemoteMapRegister;

public class ProxyFactory {

	public static <T> T getProxy(Class interfaceClass) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] {interfaceClass}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Invocation invocation = new Invocation(
						interfaceClass.getName(),
						method.getName(),
						method.getParameterTypes(),
						args);
				
				List<URL> urlList = RemoteMapRegister.get(interfaceClass.getName());
				URL url = LoadBalance.random(urlList);
				
				//MyHttpClient httpClient = new MyHttpClient();
				//String result = httpClient.send(url.getHostname(), url.getPort(), invocation);
				
				Protocol protocol = ProtocolFactory.getProtocol();
				String result = protocol.send(url, invocation);
				
				return result;
			}
		});
	}
}
