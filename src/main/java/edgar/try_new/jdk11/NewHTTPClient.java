package edgar.try_new.jdk11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewHTTPClient {
	
	static void httpClientSyncCall() {
		HttpClient client = HttpClient.newHttpClient();
		URI uri = URI.create("http://www.baidu.com");
		HttpRequest request = HttpRequest.newBuilder(uri).build();
		HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
		
		try {
			// 同步请求
			HttpResponse<String> response = client.send(request, responseBodyHandler);
			String body = response.body();
			log.info("header = {}", response.headers());
			log.info("response = {}", body);
		} catch (Exception e) {
			log.error("Can't make HTTP request ", e);
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
			log.info("response = {}", response.body());
		} catch (Exception e) {
			log.error("Can't make HTTP request ", e);
		}
	}

	public static void main(String[] args) {
		httpClientSyncCall();
		httpClientAsyncCall();
	}

}
