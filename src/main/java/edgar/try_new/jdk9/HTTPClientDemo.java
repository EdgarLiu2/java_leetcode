package edgar.try_new.jdk9;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Created by Edgar.Liu on 2022/11/22
 */
@Slf4j
public class HTTPClientDemo {

    static void httpClientTest() {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("http://www.baidu.com");
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();

        try {
            // 同步请求
            HttpResponse<String> response = client.send(request, responseBodyHandler);
            log.info("header = {}", response.headers());
            log.info("statusCode = {}", response.statusCode());
            log.info("version = {}", response.version().name());
            log.info("response = {}", response.body());
        } catch (Exception e) {
            log.error("Can't make HTTP request ", e);
        }
    }

    public static void main(String[] args) {
        httpClientTest();
    }
}
