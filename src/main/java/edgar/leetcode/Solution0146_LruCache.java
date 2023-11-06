package edgar.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/lru-cache/">146. LRU 缓存</a>
 *
 * @author Edgar.Liu
 * @since 2023/11/6 - 18:33
 */
public class Solution0146_LruCache {

    public static void main(String[] args) {
        int result;

        // Test 1
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // 缓存是 {1=1}
        lruCache.put(2, 2); // 缓存是 {1=1, 2=2}
        result = lruCache.get(1);    // 返回 1
        assert result == 1;

        lruCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        result = lruCache.get(2);    // 返回 -1 (未找到)
        assert result == -1;

        lruCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        result = lruCache.get(1);    // 返回 -1 (未找到)
        assert result == -1;

        result = lruCache.get(3);    // 返回 3
        assert result == 3;
        result = lruCache.get(4);    // 返回 4
        assert result == 4;

        // Test 2
        lruCache = new LRUCache(2);

        result = lruCache.get(2);
        assert result == -1;

        lruCache.put(2, 6);

        result = lruCache.get(1);
        assert result == -1;

        lruCache.put(1, 5);
        lruCache.put(1, 2);

        result = lruCache.get(1);
        assert result == 2;
        result = lruCache.get(2);
        assert result == 6;

        // Test 3
        lruCache = new LRUCache(2);

        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 1);

        result = lruCache.get(1);
        assert result == -1;
        result = lruCache.get(2);
        assert result == 3;

        // Test 4
        LRUCache2 lruCache2 = new LRUCache2(2);

        lruCache2.put(2, 1);
        lruCache2.put(1, 1);
        lruCache2.put(2, 3);
        lruCache2.put(4, 1);

        result = lruCache2.get(1);
        assert result == -1;
        result = lruCache2.get(2);
        assert result == 3;
    }
}

class LRUCache {

    private final int capacity;
    private final HashMap<Integer, Integer> map;
    private final LinkedList<Integer> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.list = new LinkedList<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            // 调整key在list中的位置，放到list最后（最今访问）
            list.removeFirstOccurrence(key);
            list.addLast(key);

            return map.get(key);
        }

        return -1;
    }

    public void put(int key, int value) {
        // 已有元素，直接更新
        if (map.containsKey(key)) {
            map.put(key, value);
            // 调整key在list中的位置，放到list最后（最今访问）
            list.removeFirstOccurrence(key);
            list.addLast(key);
            return;
        }

        // 如果capacity已满，先移除list头部元素（最少访问）
        if (capacity == list.size()) {
            Integer head = list.removeFirst();
            map.remove(head);
        }

        map.put(key, value);
        // 新添加的元素放在list最后（最今访问）
        list.addLast(key);
    }
}

class LRUCache2 extends LinkedHashMap<Integer, Integer> {
    private final int capacity;

    public LRUCache2(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
