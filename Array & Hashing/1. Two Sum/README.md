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