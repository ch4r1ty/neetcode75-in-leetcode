## java 操作
```java
Set<Integer> hash = new HashSet<>();


Map<Integer, Integer> hash = new HashMap<>();
if (hash.containsKey(target - nums[i])) {
    ...
}
res[0] = hash.get(target - nums[i]);
hash.put(nums[i], i);

```
