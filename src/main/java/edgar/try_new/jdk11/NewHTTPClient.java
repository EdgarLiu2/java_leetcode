package edgar.try_new.jdk11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewHTTPClient {
	
	private final static Logger logger = LoggerFactory.getLogger(NewHTTPClient.class);
	
	static void httpClientSyncCall() {
		HttpClient client = HttpClient.newHttpClient();
		URI uri = URI.create("http://www.baidu.com");
		HttpRequest request = HttpRequest.newBuilder(uri).build();
		HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
		
		try {
			// 同步请求
			HttpResponse<String> response = client.send(request, responseBodyHandler);
			String body = response.body();
			logger.info("header = {}", response.headers());
			logger.info("response = {}", body);
		} catch (Exception e) {
			logger.error("Can't make HTTP request ", e);
		}
		
	}
	
	static void httpClientAsyncCall() {
		HttpClient client = HttpClient.newHttpClient();
		URI uri = URI.create("http://www.baidu.com");
		HttpRequest request = HttpRequest.newBuilder(uri).build();
		HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
		
		// 异步请求
		CompletableFuture<HttpResponse<String>> sendAsync = client.sendAsync(request, responseBodyHandler);
		sendAsync.thenApply(r -> r.body()).thenAccept(System.out::println);
		
		try {
			HttpResponse<String> response = sendAsync.get();
		} catch (Exception e) {
			logger.error("Can't make HTTP request ", e);
		}
	}

	public static void main(String[] args) {
		httpClientSyncCall();
		httpClientAsyncCall();
	}

}
