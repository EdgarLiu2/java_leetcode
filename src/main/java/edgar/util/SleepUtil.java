package edgar.util;

import java.util.concurrent.TimeUnit;

public class SleepUtil {
	
	public static void milliSleep(int milli) {
		try {
			TimeUnit.MILLISECONDS.sleep(milli);
		} catch (InterruptedException e) {
		}
	}
	
	public static void secondSleep(int second) {
		try {
			TimeUnit.SECONDS.sleep(second);
		} catch (InterruptedException e) {
		}
	}
}
