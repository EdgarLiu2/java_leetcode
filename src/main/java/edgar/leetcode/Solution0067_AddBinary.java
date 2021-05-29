package edgar.leetcode;

/**
 * 67. 二进制求和
 * https://leetcode-cn.com/problems/add-binary/
 * 
 * @author Administrator
 *
 */
public class Solution0067_AddBinary {

    public static String addBinary(String a, String b) {
    	// a为0
    	if (a.isEmpty() || a.equals("0")) {
    		return b;
    	}
    	
    	// b为0
    	if (b.isEmpty() || b.equals("0")) {
    		return a;
    	}
    	
    	// 前面补0
    	if (a.length() > b.length()) {
    		// a比较长
    		b = "0".repeat(a.length() - b.length()) + b;
    	} else {
    		// b比较长
    		a = "0".repeat(b.length() - a.length()) + a;
    	}
    	
    	StringBuffer result = new StringBuffer();
    	int carry = 0;
    	
    	// 从低位开始相加
    	for (int i = a.length() - 1; i >=0; i--) {
    		carry += a.charAt(i) - '0';
    		carry += b.charAt(i) - '0';
    		
    		result.append(carry % 2);
    		carry /= 2;
    	}

    	// 最高位是否有进位
    	if (carry > 0) {
    		result.append("1");
    	}
    	// 字符串反转
    	result.reverse();

    	return result.toString();
    }
    
    public static String addBinary2(String a, String b) {
    	// a为0
    	if (a.isEmpty() || a.equals("0")) {
    		return b;
    	}
    	
    	// b为0
    	if (b.isEmpty() || b.equals("0")) {
    		return a;
    	}
    	
    	Integer aInteger = 0;
    	Integer bInteger = 0;
    	
    	try {
        	aInteger = Integer.parseInt(a, 2);
        	bInteger = Integer.parseInt(b, 2);
    	} catch (NumberFormatException e) {
			return "0";
		}

    	
    	return Integer.toBinaryString(aInteger + bInteger);
    }
    
	public static void main(String[] args) {
		
		/*
		 * 输入: a = "11", b = "1"
		 * 输出: "100"
		 */
		assert "100".equals(addBinary("11", "1"));

		/*
		 * 输入: a = "1010", b = "1011"
		 * 输出: "10101"
		 */
		assert "10101".equals(addBinary("1010", "1011"));

		/*
		 * 输入: a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101", b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"
		 * 输出: "110111101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000"
		 */
		assert "110111101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000".equals(addBinary("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101", "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"));
	}

}
