package edgar.dubbo.learning1.protocal.http;

import edgar.dubbo.learning1.framework.Invocation;
import edgar.dubbo.learning1.framework.Protocol;
import edgar.dubbo.learning1.framework.URL;

public class HTTPProtocol implements Protocol {

	@Override
	public void start(URL url) {
		HttpServer httpServer = new HttpServer();
		httpServer.start("tomcat", url.getHostname(), url.getPort());

	}

	@Override
	public String send(URL url, Invocation invocation) throws InterruptedException {
		MyHttpClient httpClient = new MyHttpClient();
		return httpClient.send(url.getHostname(), url.getPort(), invocation);
	}

}
