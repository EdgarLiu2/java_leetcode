package edgar.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 70. 爬楼梯
 * https://leetcode-cn.com/problems/climbing-stairs/
 * 
 * @author liuzhao
 *
 */
public class Soluction0070_ClimbingStairs {
	
	static Map<Integer, Integer> localCache = new HashMap<>();
	
    public static int climbStairs(int n) {
    	
    	if (n == 1) {
    		return 1;
    	}
    	
    	if (n == 2) {
    		return 2;
    	}
    	
    	
    	int n1 = 0;
    	int n2 = 0;
    	
    	if (localCache.containsKey(n - 2)) {
    		n2 = localCache.get(n - 2);
    	} else {
    		n2 = climbStairs(n - 2);
    		localCache.put(n - 2, n2);
    	}
    	
    	if (localCache.containsKey(n - 1)) {
    		n1 = localCache.get(n - 1);
    	} else {
    		n1 = climbStairs(n - 1);
    		localCache.put(n - 1, n1);
    	}
    	
    	return n1 + n2;
    }
    
    public static int climbStairs1(int n) {
    	
    	if (n == 1) {
    		return 1;
    	}
    	
    	if (n == 2) {
    		return 2;
    	}
    	
    	return climbStairs(n-1) + climbStairs(n-2);
    }

	public static void main(String[] args) {
		
		/*
		 * 输入： 2
		 * 输出： 2
		 */
		assert 2 == climbStairs(2);
		
		/*
		 * 输入： 3
		 * 输出： 3
		 */
		assert 3 == climbStairs(3);
		
		/*
		 * 输入： 45
		 * 输出： 1836311903
		 */
		assert 1836311903 == climbStairs(45);
	}

}
