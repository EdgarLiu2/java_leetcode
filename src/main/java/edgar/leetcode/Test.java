package edgar.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
	
	public static ListNode reverseList(ListNode head) {
		
		if (head == null) {
			return null;
		}
		
		ListNode newHead = null;
		ListNode tmp;
		
		while (head != null) {
			
			// 当前老链表的第一个
			tmp = head;
			// 老链表head指针向后移动
			head = head.next;
			
			// tmp指向新链表的第一个
			tmp.next = newHead;
			// 修改新链表头指针
			newHead = tmp;
		}
		
		return newHead;
	}
	
	public static void qsort(int[] inputs) {
		if (inputs.length <= 1) {
			return;
		}
		
		_qsort(inputs, 0, inputs.length - 1);
	}
	
	public static void _qsort(int[] inputs, int start, int end) {
		
		// start和end中间没有元素结束
		if (end - start < 1) {
			return;
		}
	
		// index=start作为标兵
		int pivot = inputs[start];
		int left = start;
		int right = end;
		
		while (left < right) {
			// 从右侧找到第一个比pivot小的
			while (left < right && inputs[right] >= pivot) {
				right--;
			}
			// 将比pivot小的元素放到left位置
			inputs[left] = inputs[right];
			
			// 从左侧找到第一个比pivot大的
			while (left < right && inputs[left] <= pivot) {
				left++;
			}
			// 将比pivot大的元素放到right位置
			inputs[right] = inputs[left];
		}
		
		// left和right重合，放pivot
		inputs[left] = pivot;
		
		
		_qsort(inputs, start, left - 1);
		_qsort(inputs, left + 1, end);
	}
	
	public static int binarySearch(int[] inputs, int n) {
		
		int left = 0;
		int right = inputs.length - 1;
		
		if (inputs[left] > n || inputs[right] < n) {
			return -1;
		}
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (inputs[mid] == n) {
				// 找到
				return mid;
			} else if (inputs[mid] < n) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return -1;
	}
	
	public static int binarySearchQsort(int[] inputs, int n) {
		// 先排序
		_qsort2(inputs, 0, inputs.length - 1);
		Util.printOneDimIntArray(inputs, inputs.length);
		
		// 二分查找
		int left = 0;
		int right = inputs.length - 1;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(inputs[mid] == n) {
				return mid;
			}
			
			if (inputs[mid] > n) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return -1;
	}
	
	public static void _qsort2(int[] inputs, int start, int end) {
		
		if (end - start <= 0) {
			return;
		}
		
		int pivot = inputs[start];
		int left = start;
		int right = end;
		
		while (left < right) {
			while (left < right && inputs[right] >= pivot) {
				right--;
			}
			inputs[left] = inputs[right];
			
			while (left < right && inputs[left] <= pivot) {
				left++;
			}
			inputs[right] = inputs[left];
		}
		
		inputs[left] = pivot;
		
		_qsort2(inputs, start, left - 1);
		_qsort2(inputs, left + 1, end);
	}
	
	public static int[][] matrixConvert(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] result = new int[cols][rows];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[j][i] = matrix[i][j];
			}
		}
	
		
		return result;
	}

	public static void main(String[] args) {
		int[] inputs;
		
		// 反转链表
		inputs = new int[]{1, 2, 3, 4, 5};
		ListNode list = ListNode.buildList(inputs);
		list.print();
		ListNode reversedList =  reverseList(list);
		reversedList.print();
		
		// 快排
		inputs = new int[] {4, 7, 3, 2, 6, 7, 8, 32, 67, 23, 867, 23};
		qsort(inputs);
		Util.printOneDimIntArray(inputs, inputs.length);
		inputs = new int[] {5, 2, 1, 8, 9, 3, 7, 0, 4, 6};
		qsort(inputs);
		Util.printOneDimIntArray(inputs, inputs.length);
		
		// 二分查找，如果找到返回数组下标，没有找到返回-1
		inputs = new int[] {1, 3, 6, 8, 9, 10, 34, 68, 83, 99};
		System.out.println(binarySearch(inputs, 68));
		
		// 二维数组转置
		int[][] matrix = new int[][] {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
		Util.printTwoDimIntArray(matrixConvert(matrix));
		
		// 快排+二分查找
		inputs = new int[] {4, 7, 3, 2, 6, 7, 8, 32, 67, 23, 867, 23};
		System.out.println(binarySearchQsort(inputs, 32));
	}
}
