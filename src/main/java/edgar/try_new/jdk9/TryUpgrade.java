package edgar.try_new.jdk9;

import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryUpgrade {
	private static final Logger logger = LoggerFactory.getLogger(TryUpgrade.class);
	
	/*
	 * Java 8之前，资源关闭在finally进行
	 */
	static void tryBeforeJava8() {
		InputStreamReader reader = null;
		reader = new InputStreamReader(System.in);
		char[] cbuf = new char[20];
		int len = 0;
		
		try {
			if ( (len = reader.read(cbuf)) != -1) {
				String msg = new String(cbuf, 0, len);
				logger.info("msg={}", msg);
			}
		} catch (IOException e) {
			logger.error("InputStreamReader.reader", e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				logger.error("InputStreamReader.close", e);
			}
		}
	}
	
	/*
	 * Java 8，可以在try()进行资源初始化，catch()之后自动关闭
	 */
	static void tryJava8() {
		
		// 初始化希望自动管理的资源
		try (InputStreamReader reader = new InputStreamReader(System.in)) {
			char[] cbuf = new char[20];
			int len = 0;
			if ( (len = reader.read(cbuf)) != -1) {
				String msg = new String(cbuf, 0, len);
				logger.info("msg={}", msg);
			}
		} catch (IOException e) {
			logger.error("InputStreamReader.reader", e);
		}
		// catch语句之后，自动关闭reader
	}
	
	/*
	 * Java 9，可以把资源声明在外面，在try()声明需要自动关闭的资源，catch()之后自动关闭
	 */
	static void tryJava9() {
		
		InputStreamReader reader = new InputStreamReader(System.in);

		// 声明希望自动管理的资源
		try (reader) {
			char[] cbuf = new char[20];
			int len = 0;
			if ( (len = reader.read(cbuf)) != -1) {
				String msg = new String(cbuf, 0, len);
				logger.info("msg={}", msg);
			}
		} catch (IOException e) {
			logger.error("InputStreamReader.reader", e);
		}
		// catch语句之后，自动关闭reader
	}

	public static void main(String[] args) {
		tryBeforeJava8();
		tryJava8();
		tryJava9();
	}

}
