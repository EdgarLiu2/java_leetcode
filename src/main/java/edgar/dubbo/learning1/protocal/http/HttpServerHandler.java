package edgar.dubbo.learning1.protocal.http;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.io.IOUtils;

import edgar.dubbo.learning1.framework.Invocation;
import edgar.dubbo.learning1.provider.LocalRegister;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HttpServerHandler {

	public void handler(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			Invocation invocation = com.alibaba.fastjson.JSON.parseObject(req.getInputStream(), Invocation.class);
			
			// 获取调用参数
			String interfaceName = invocation.getInterfaceName();
			Class<?> implClass = LocalRegister.get(interfaceName);
			Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
			
			// 方法调用
			Object result = method.invoke(implClass.newInstance(), invocation.getParams());
			System.out.println(result);
			
			// 写输出
			IOUtils.write(result.toString(), resp.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}
