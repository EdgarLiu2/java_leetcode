package edgar.dubbolearning.study.protocal.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import edgar.dubbolearning.study.framework.Invocation;

public class MyHttpClient {
	public String send(String hostname, int port, Invocation invocation) throws InterruptedException {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("http", null, hostname, port, "/", null, null))
					.POST(HttpRequest.BodyPublishers.ofString(com.alibaba.fastjson.JSON.toJSONString(invocation)))
					.build();
			HttpClient client = HttpClient.newHttpClient();
			
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
