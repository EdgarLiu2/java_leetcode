package edgar.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.kv.PutResponse;

/**
 * https://www.cnblogs.com/yanh0606/p/11765691.html
 * 
 * @author Administrator
 *
 */
public class JetcdUtil {
	private String etcd_host;
	private int etcd_port;
	
	private Client client;
	private KV kvClient;

	public JetcdUtil() {
		this("localhost", 2379);
	}
	
	public JetcdUtil(String host, int port) {
		this.etcd_host = host;
		this.etcd_port = port;
		String url = String.format("http://%s:%d", this.etcd_host, this.etcd_port);
		
		connect(url);
	}
	
	private void connect(String url) {
		connect(new String[] {url});
	}
	
	private void connect(String[] urls) {
		client = Client.builder().endpoints(urls).build();
		kvClient = client.getKVClient();
	}
	
	public void put(String key, String value) {
		ByteSequence keyBytes = ByteSequence.from(key.getBytes());
		ByteSequence valueBytes = ByteSequence.from(value.getBytes());
		
		try {
			PutResponse response= kvClient.put(keyBytes, valueBytes).get();
			System.out.println(response);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public GetResponse get(String key) {
		ByteSequence keyBytes = ByteSequence.from(key.getBytes());
		
		// get the CompletableFuture
		CompletableFuture<GetResponse> getFuture = kvClient.get(keyBytes);

		// get the value from CompletableFuture
		GetResponse response = null;
		try {
			response = getFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	public void delete(String key) {
		ByteSequence keyBytes = ByteSequence.from(key.getBytes());
		
		// delete the key
		try {
			kvClient.delete(keyBytes).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
}
