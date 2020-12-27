package edgar.dubbo.learning1.protocal.http;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void start(String serverType, String hostname, int port) {
		// Tomcat
		if (serverType.equalsIgnoreCase("tomcat")) {
			startTomcat(hostname, port);
		}
	}
	
	private void startTomcat(String hostname, int port) {
		Tomcat tomcat = new Tomcat();
		
		// StandardServer(), StandardService()
		Server server = tomcat.getServer();
		Service service = server.findService("Tomcat");
		
		Connector connector = new Connector();
		connector.setPort(port);
		
		Engine engine = new StandardEngine();
		engine.setDefaultHost(hostname);
		
		Host host = new StandardHost();
		host.setName(hostname);
		
		String contextPath = "";
		Context context = new StandardContext();
		context.setPath(contextPath);
		context.addLifecycleListener(new Tomcat.FixContextListener());
		
		host.addChild(context);
		engine.addChild(host);
		service.setContainer(engine);
		service.addConnector(connector);
		
		// 配置Servlet
		tomcat.addServlet(contextPath, "defaultDispatcher", new DispatcherServlet());
		context.addServletMappingDecoded("/*", "defaultDispatcher");
		
		try {
			tomcat.start();
			tomcat.getServer().await();
		} catch (LifecycleException e) {
			logger.error("Can't start Tomcat instance", e);
		}
	}
}
