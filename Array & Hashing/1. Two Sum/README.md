```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[2];
        Map<Integer, Integer> hash = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // hash.put(nums[i], i);
            if (hash.containsKey(target - nums[i])) {
                res[0] = hash.get(target - nums[i]);
                res[1] = i;
                break;
            }
            hash.put(nums[i], i);
        }

        return res;
    }
}
```
在for循环里面，if() 和 hash.put 的顺序一定不能反，不然的话会2个元素会是相同元素：
举个例子：8 = 4 + 4

先把4放进去的话，再判断8-4 =? 4的话，返回就是(4,4);所以要先判断，再填hashtable
```java
    for (int i = 0; i < nums.length; i++) {
        // hash.put(nums[i], i);
        if (hash.containsKey(target - nums[i])) {
        res[0] = hash.get(target - nums[i]);
        res[1] = i;
        break;
        }
        hash.put(nums[i], i);
```

另外，hashtable的key, value 还是需要理清一下:
hash.get(key) 得到的是与这个 key 对应的 value
```java
res[0] = hash.get(target - nums[i]);
res[1] = i;
```

在以下两种声明中：

Map<Integer, Integer> hash = new HashMap<>();
HashMap<Integer, Integer> hash = new HashMap<>();
1. 主要区别：引用的类型
   Map 是接口，而 HashMap 是 Map 接口的一个实现类。
   如果使用 Map 作为引用类型，代码可以更灵活，因为它允许替换为其他 Map 实现（如 TreeMap、LinkedHashMap、ConcurrentHashMap 等），而不需要修改变量声明。
   如果使用 HashMap 作为引用类型，代码的实现就被固定为 HashMap，失去了灵活性。
2. 推荐使用 Map 引用类型
   通常，推荐用接口作为变量的类型，以遵循面向接口编程的原则（也叫依赖抽象而非具体实现）。这是一个良好的设计习惯。