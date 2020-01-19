package edgar.try_new.etcd;

import edgar.util.JetcdUtil;

public class Sample1HelloJetcd {

	public static void main(String[] args) {
		JetcdUtil jetcd = new JetcdUtil();
		
		// put the key-value
		jetcd.put("test_key", "test_value");
		
		// get the value
		jetcd.get("test_key");
	}
}
