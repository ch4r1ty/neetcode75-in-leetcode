# 代码随想录心得

## Day 1

首先要定义区间，到底是左闭右闭，还是左闭右开（一般都是这俩）。这个区间，是循环中，搜索的区间。

### 704 binary search

**左闭右闭**：循环搜索中，while判断应该是

```java
while (left <= right)
```

这样子的话保证还能进行一次判断，因为是左闭右闭

**左闭右开**：更新left 和 right，应该是：（这个错了一次）

```java
if (nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid;
            }
```

### 27 remove element

有的同学可能说了，多余的元素，删掉不就得了。

**要知道数组的元素在内存地址中是连续的，不能单独删除数组中的某个元素，只能覆盖。**

**暴力法**：

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < n; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;		//这个很关键，继续检查当前位置的元素，不然会出先连续两个target无法检测的错误
                n--;
            }
        }
        return n;
    }
}
```

**快慢指针**：是这题的终点，慢指针用来指要被更新的位置，快指针用来指要被更新的元素

这题返回slow，slow 还有计数的功能

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int slow = 0;
        for (int fast = 0; fast < n; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
```

### [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)

**左右指针**是这题精髓，新数组其实是一个开口向上的模样，所以比较左右大小，然后放入新数组。

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        for (int i = right; i >= 0; i--) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                res[i] = nums[right] * nums[right];
                right--;
            } else {
                res[i] = nums[left] * nums[left];
                left++;
            }
        }
        return res;
    }
}
```

## Day 2

### [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum)

有个思想上的问题：写代码时很本能想开始情况、模拟第一步第二步情况。其实，在while循环中只需要关注终止条件，以及符合条件的情况就可以了。比如下面的

```java
res = Math.min(right - left + 1, res);
```

**滑动窗口**：

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                res = Math.min(right - left + 1, res);
                sum -= nums[left];
                left++;
            }
        }
        if (res == 2147483647) return 0;
        return res;
    }
}
```

### [59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii)

1. 循环不变量：坚持一个规则去遍历每一条边
2. 模拟过程，一圈就是一个循环，一个循环要遍历四条边

这题很细，要的点很多

* startX 用来保存每一次循环的起始行
* startY 用来保存每一次循环的起始列（这个可以和startX合成一个变量，因为是正方形的矩阵）
* offset 用来保存结束的列、行位置（已经循环多少次了）
* loop 用来控制循环次数，次数为 n / 2，如果是奇数次的话，最中心的元素最后手动赋值
* count 用来赋值（12345....）

看到 while 里面很多 for 不用害怕，这只是一个循环的内部过程，是可以模拟的

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int offset = 1;
        int start = 0;
        int i = 0;
        int j = 0;
        int count = 1;
        int loop = 1;
        int[][] nums = new int[n][n];

        while (loop <= n / 2) {
            for (j = start; j < n - offset; j++) {
                nums[start][j] = count++;
            }

            for (i = start; i < n - offset; i++) {
                nums[i][j] = count++;
            }

            for (; j > start; j--) {
                nums[i][j] = count++;
            }

            for (; i > start; i--) {
                nums[i][j] = count++;
            }

            start++;
            offset++;
            loop++;
        }

        if (n % 2 == 1) {
            nums[start][start] = count;
        }

        return nums;
    }
}
```

### [58. 区间和](https://kamacoder.com/problempage.php?pid=1070)

这题acm模式，注意scanner：处理用户输入和文件输入的常用工具

**前缀和**，是这题的精髓

```java

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] vec = new int[n];
        int[] p = new int[n];

        int presum = 0;
        for (int i = 0; i < n; i++) {
            vec[i] = scanner.nextInt();
            presum += vec[i];
            p[i] = presum;
        }

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int sum;
            if (a == 0) {
                sum = p[b];
            } else {
                sum = p[b] - p[a - 1];
            }
            System.out.println(sum);
        }

        scanner.close();
    }
}

 
```

## day 3

### [203. Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements)

这题想要引入哑节点 dummy

在自己写的时候，while里面的逻辑有点问题：

```java
		while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                // 只有在不删除节点时才移动指针
                curr = curr.next;
            }
            
        }
```

删除下一个节点值的时候，指针是不移动的，因为还需要下一次的循环来判断.next的值

另外，不能只能操作head，不能用head代替curr，这样会判断不了第一个节点，因为是从curr.next开始的。链表题都要用 curr 这个临时指针

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
      
				// 底下这个判断，在引入curr之后可以删
        if (head == null || head.val == val && head.next == null) {
            return null;
        }

        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                // 只有在不删除节点时才移动指针
                curr = curr.next;
            }
            
        }

        return dummy.next;
    }
}
```

### [707. Design Linked List](https://leetcode.com/problems/design-linked-list/)

在插入的时候，要先更新被插入的节点.next，再更新之前节点.next

```java
class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }
}

class MyLinkedList {
    int size;
    ListNode head;

    public MyLinkedList() {
        size = 0;
        head = new ListNode(0); // 虚拟头节点
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode currentNode = head;
        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head.next;
        head.next = newNode;
        size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        size++;
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        ListNode toAdd = new ListNode(val);
        toAdd.next = pre.next;
        pre.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        ListNode pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        pred.next = pred.next.next;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index, val);
 * obj.deleteAtIndex(index);
 */

```

### [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)

这题有三个指针：pre、curr、temp，temp是curr后面的，用来解决curr.next更改之后找不到后面一个节点的问题

一层循环中，要做的事有四个，要移动pre、curr、temp、改变curr.next，注意操作的步骤

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        ListNode temp = null;
        while (curr != null) {
            temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }

        return pre;
    }
}
```

这题也可以用递归来写，更简洁

## day 4

### [24. Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/)

123456变成214365

* 在交换的过程中，要有temp来记录前一个元素

* 要交换位置1、2的元素，curr要在0的位置

* 这里要先curr.next，不然curr.next为空，curr.next.next会报错
  ```
  while (curr.next != null && curr.next.next != null) {
  }
  ```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        ListNode temp = new ListNode(0);

        //这里要先curr.next，不然curr.next为空，curr.next.next会报错
        while (curr.next != null && curr.next.next != null) {
            temp.next = curr.next;
            curr.next = curr.next.next;
            temp.next.next = curr.next.next;
            curr.next.next = temp.next;

            curr = curr.next.next;
        }
        
        return dummy.next;
    }
}
```

### [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

**快慢指针**是这题核心思想

还有就是，要操作第i+1个节点，必须要在第i个节点进行操作，操作它的.next

快指针先移动n+1步，慢指针再走，这样慢指针会停在要操作节点的前一个节点

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode fast = dummy;
        ListNode slow = dummy;
        dummy.next = head;
      
      // 快指针先移动n+1步，慢指针再走，这样慢指针会停在要操作节点的前一个节点
        for (int i = 0; i < n + 1; i++) {
            if (fast == null) return null;

            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        
        return dummy.next;
    }
}
```

### [160. Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/)

最浪漫的一集：他朝若是同淋雪，此生也算共白头

双指针算出**链表长度差值**，是这题核心思想

这题有个技巧，交换a和b，然后统一操作，这样就不要进行if的判断了：

```java
			if (lenB > lenA) {
            //1. swap (lenA, lenB);
            int tempLen = lenA;
            lenA = lenB;
            lenB = tempLen;
            //2. swap (curA, curB);
            ListNode tempNode = curA;
            curA = curB;
            curB = tempNode;
        }
```

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0;
        int lenB = 0;
        while (curA != null) {
            curA = curA.next;
            lenA++;
        }

        while (curB != null) {
            curB = curB.next;
            lenB++;
        }

        curA = headA;
        curB = headB;

        // 让curA为最长链表的头，方便底下的统一操作（c++代码及其简单）
        if (lenB > lenA) {
            //1. swap (lenA, lenB);
            int tempLen = lenA;
            lenA = lenB;
            lenB = tempLen;
            //2. swap (curA, curB);
            ListNode tempNode = curA;
            curA = curB;
            curB = tempNode;
        }

        // 求长度差
        int gap = lenA - lenB;
        while (gap > 0) {
            curA = curA.next;
            gap--;
        }

        while (curA != null && curB != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        
        return null;
    }
}
```

### [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)

这题关键在以下两点

1. 快慢指针判断是否有环？
   快指针速度为2，慢指针速度为1，同时从head开始走循环。如果能相等，那就是有环
2. 怎样找到入口处？
   slow: x + y
   fast : x + y + n (y + z)
   让 2 * slow == fast，
   x = (n - 1)(y + z) +z
   n = 1, x = z（n是转了多少圈，n不重要，因为这里要找到环的入口）
   也就是说，一个指针从相交处走，一个指针从头走，相交处就是环的入口

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        if (head == null) {
            return null;
        }
        
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode index = fast;  // fast, slow都行，要的是相遇的那个点
                slow = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }

        return null;
    }
}
```

## day 5

休息！

## day 6

### [242. Valid Anagram](https://leetcode.com/problems/valid-anagram/)

数组是哈希表的一种方式

这里循环内的操作留意下，是++操作，不是常规的赋值操作。写的时候卡了一下

```java
for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;
        }
```

这里有个小技巧：不用设置两个数组来对比26个元素是否一样。只需要在一个数组，第一个加，第二个减，最后判断是不是每个元素都等于零就行

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] record = new int[26];

        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }

        // record数组所有元素都为零0，说明字符串s和t是字母异位词
        for (int i = 0; i < record.length; i++) {
            if (record[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
}
```

### [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/)

Set是Java的一种集合，继承自Collection接口，主要有两个常用的实现类**HashSet类和TreeSet类**。

它没有固定的大小限制，可以动态地添加和删除元素。并且Set集合中的元素都是唯一的，不会有重复的元素，即使是null值也只能有一个。

另外Set集合是无序的，不能记住元素的添加顺序，因为没有索引值，所以Set集合中的对象不会按特定的方式排序，它只是简单地把对象放到集合中。

这题用set做。

* set有个上面没提到的特性：**没有索引**。set就是集合

* 增强型for循环有了新的理解：res是一个set，加强型for循环可以遍历res

* ```java
  for (int i : res) {
              ans[j] = i;
              j++;
          }
  ```

  这题思路：

  1. set1用来保存nums1里出现过的数
  2. res用来保存nums1和nums2都有的数
  3. set转换成int[]

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> res = new HashSet<>();

        for (int i : nums1) {
            set1.add(i);
        }

        for (int i : nums2) {
            if (set1.contains(i)) {
                res.add(i);
            }
        }

        int[] ans = new int[res.size()];
        int j = 0;
        // 增强型for循环，能这么用
        for (int i : res) {
            ans[j] = i;
            j++;
        }

        return ans;
    }
}
```

### [202. Happy Number](https://leetcode.com/problems/happy-number/)

这题仔细看描述，只有最后为1才是happy number，循环起来的话就不是了。

而这题的思想，就是用set来判断是否循环起来了

```java
class Solution {
    public boolean isHappy(int n) {
        // record 只保存经过平方和运算的和
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNumber(n);
        }

        return n == 1;
    }

    private int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }
}
```

### [1. Two Sum](https://leetcode.com/problems/two-sum/)

用哈希表来判断，需要的元素差是否曾经出现过，若是，则返回下标

因为需要到下标，所以集合需要下标，所以用 map 结构

其实循环就三件事：

```java
 int temp = target - nums[i];		//来定义差值
```

```java
// 判断是否有符合条件，符合的话就操作res数组
if (map.containsKey(temp)) {
                res[0] = map.get(temp);
                res[1] = i;
                break;
            }
```

```java
// 保存数组i处的元素到map里面
map.put(nums[i], i);
```

完整代码：

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++){
            int temp = target - nums[i];    //这个temp的定义，做题的时候卡了下
            if (map.containsKey(temp)) {
                res[0] = map.get(temp);
                res[1] = i;
                break;
            }

            map.put(nums[i], i);
        }

        return res;
    }
}
```

## day 7

### [454. 4Sum II](https://leetcode.com/problems/4sum-ii/)

map.getOrDefault(a, b)方法：存在a的话，就返回key a对应的value，不存在的话就返回b

```java
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        Map<Integer, Integer> map= new HashMap<>();
        // 将 nums1 和 nums2 的元素和存入 map，并记录出现次数
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        // 遍历 nums3 和 nums4，寻找与之前的和互补为 0 的组合
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int temp = 0 - (nums3[i] + nums4[j]);
                res += map.getOrDefault(temp, 0);
            }
        }

        return res;
    }
}
```

### [383. Ransom Note](https://leetcode.com/problems/ransom-note/)

1. 加强型for循环遍历string两种写法：

```java
// 第一种：char的操作
for (char c : magazine.toCharArray()) {
		res[c - 'a'] += 1;
}

// 第二种：string中int的操作
for (int i = 0; i < magazine.length(); i++) {
    res[magazine.charAt(i) - 'a'] += 1;
}
```

2. 加强型for循环遍历数组，遍历的是元素，而不是下标：

```java
				for (int i : res) {
            // 这里是i，而不是res[i]，加强型for循环
            if (i < 0) {
                return false;
            }
        }
```

完整代码：

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int res[] = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            res[magazine.charAt(i) - 'a'] += 1;
        }

        for (char c : ransomNote.toCharArray()) {
            res[c - 'a'] -= 1;
        }

        for (int i : res) {
            // 这里是i，而不是res[i]，加强型for循环
            if (i < 0) {
                return false;
            }
        }

        return true;
    }
}
```

### [15. 3Sum](https://leetcode.com/problems/3sum/)

用双指针法，哈希表法很麻烦

* 这题要对a, b, c都进行去重
* 对a的去重逻辑是 ：是否 nums[i] == nums[i - 1]，因为这样的话保证有一个 i 已经被考虑过了
* 先对数组进行排序
* 这里用到了list

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
          	// 如果第一个数都大于0，那肯定不满足，因为排序过了
            if (nums[i] > 0) {
                return res;
            }

            // 去重a
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 去重逻辑应该放在找到一个三元组之后，对 b 和 c 去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    right--;
                    left++;
                }
            }
        }

        return res;
    }
}
```

### [18. 4Sum](https://leetcode.com/problems/4sum/)

这也是双指针，就是上一题3sum外面又套了一个循环。

不同的是，剪枝的时候这里的第二个数，要和第一个数一起，作为一个整体进行剪枝

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int k = 0; k < nums.length; k++) {
            // 剪枝
            if (nums[k] > target && nums[k] >= 0) {
                break;
            }

            // 对第一个数nums[k]去重
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }

            for (int i = k + 1; i < nums.length; i++) {
                // 二级剪枝，这里和题目三数之和一样了
                // 这里把nums[k] + nums[i]看作一个整体
                if (nums[k] + nums[i] > target && nums[k] + nums[i] >= 0) {
                    break;
                }

                // 对第二个数nums[i]去重
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        res.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));

                        // 对nums[left]和nums[right]去重
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                        left++;
                    }
                }
            }
        }
        
        return res;
    }
}
```

## day 8

### [344. Reverse String](https://leetcode.com/problems/reverse-string/)

双指针，前跟后交换

不能直接把一个数组赋值给一个数组！

```java
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        char temp;

        while (left < right) {
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }
}
```

### [541. Reverse String II](https://leetcode.com/problems/reverse-string-ii/)

* 这题刚开始想是有点复杂的，又要k又要2k，还要判断是否满足k的条件。
  但其实就是在2k区间里，交换前k个，后面k个不动。
  然后开始第二个2k区间的操作。这时候只要把起始位置+2k就好了。
  交换操作跟上一题一模一样。
* 另外，这题的输入输出和上一题不一样：给string返回string，我们要操作的是字符串，要先 string -> char，返回的时候再char -> string
* 注意边界情况，k和n之间的关系

```java
class Solution {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();  // 将字符串转为字符数组
        int n = arr.length;

        for (int start = 0; start < n; start += 2 * k) {
            int left = start;
            int right = Math.min(start + k - 1, n - 1);  // 处理边界情况

            // 交换字符
            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        return new String(arr);  // 将字符数组转换回字符串
    }
}

```

### [#](https://programmercarl.com/kamacoder/0054.替换数字.html#替换数字)替换数字

1. 计算新 sting 的长度（java中string不能改变长度）
2. 替换，这里一定要从后向前替换，因为从前往后的话，之后的字符会被覆盖

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  
        String s = sc.next();  // 从输入中读取一个字符串
        int len = s.length();  // 原字符串的长度

        // 1. 计算新字符串的长度，如果有数字，长度需要增加 5
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                len += 5;  // 每遇到一个数字字符，长度加 5
            }
        }

        // 2. 创建一个字符数组，长度为新计算的长度
        char[] ret = new char[len];  

        // 3. 将原字符串逐字符复制到新数组的前面部分
        for (int i = 0; i < s.length(); i++) {
            ret[i] = s.charAt(i);
        }

        // 4. 从原字符串末尾开始，将字符复制到新数组中，并处理数字替换为 "number"
        for (int i = s.length() - 1, j = len - 1; i >= 0; i--) {
            if ('0' <= ret[i] && ret[i] <= '9') {  
                // 遇到数字字符，将其替换为 'n', 'u', 'm', 'b', 'e', 'r'
                ret[j--] = 'r';
                ret[j--] = 'e';
                ret[j--] = 'b';
                ret[j--] = 'm';
                ret[j--] = 'u';
                ret[j--] = 'n';
            } else {
                // 非数字字符，直接复制到新数组中的对应位置
                ret[j--] = ret[i];
            }
        }

        // 5. 打印结果
        System.out.println(ret);
    }
}

```

## day 9

### [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/)

四个函数：

* 主函数
* 消除多余空格：（难）
  最前面的空格、单词间大于1个点空格、最后的空格
* 反转整个string（EZ收徒）
* 反转每个单词（中等，难在移动指针找单词字母数量，和指针到下一个单词的位置）

这题参杂了char, StringBuilder, String的转换，挺麻烦的。
不要用char类型数组去保存结果了，只要在中间过程char类型去接受字符就好，这样能保证是string（输入输出）与stringbuilder（中间过程）在做转换。
为什么要stringbuilder呢？因为java中string类是不能修改的

```java
class Solution {
    public String reverseWords(String s) {
        StringBuilder res = removeSpace(s); // 去除多余空格
        reverseString(res, 0, res.length() - 1); // 整体反转字符串
        reverseEachWord(res); // 逐个单词反转
        return res.toString(); // 转成 String 返回
    }

    // 消除多余空格
    private StringBuilder removeSpace(String s) {
        char[] temp = s.toCharArray();
        int fast = 0;
        int slow = 0;
        // 先把最前面的空格消了，快指针先动
        while (temp[fast] == ' ') {
            fast++;
        }

        // 开始快慢指针
        while (fast < temp.length) {
            if (fast > 0 && temp[fast] == ' ' && temp[fast - 1] == ' ') {
                fast++;
                continue;
            }

            temp[slow] = temp[fast];
            fast++;
            slow++;
        }

        while (slow > 0 && temp[slow - 1] == ' ') {
            slow--;
        }

        return new StringBuilder(new String(temp, 0, slow));
    }

    // 反转整个string
    private void reverseString(StringBuilder s, int left, int right) {
        // int left = 0;
        // int right = s.length() - 1;
        char temp;
        while (left < right) {
            temp = s.charAt(left);
            s.setCharAt(left, s.charAt(right));
            s.setCharAt(right, temp);

            left++;
            right--;
        }
    }

    // 反转每个单词
    private void reverseEachWord(StringBuilder s) {
        int start = 0;
        int end = 0;
        while (start < s.length()) {
            while (end < s.length() && s.charAt(end) != ' ') {
                end++;
            }

            reverseString(s, start, end - 1);
            start = end + 1;
            end += 1;
        }
    }
}
```

### [右旋字符串](https://kamacoder.com/problempage.php?pid=1065)

这题两个难点：

1. acm模式，输入输出费点时间

2. 思想，太关键了。不要额外空间的话，算法是：
   翻转三次：

   ```java
   reverseString(res, 0, res.length() - 1);
   reverseString(res, 0, n - 1);
   reverseString(res, n, res.length() - 1);
   ```

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String s = in.nextLine();
        
        int len = s.length();
        StringBuilder res = new StringBuilder(s);
        
        // 开始翻转
        reverseString(res, 0, res.length() - 1);
        reverseString(res, 0, n - 1);
        reverseString(res, n, res.length() - 1);
        
        System.out.println(res.toString());
    }
    
    private static void reverseString(StringBuilder s, int left, int right) {
        while (left < right) {
            char temp = s.charAt(left);
            s.setCharAt(left, s.charAt(right));
            s.setCharAt(right, temp);
            
            left++;
            right--;
        }
    }
}
```

### KMP

过程理解了，代码学不明白，二刷再看

### 字符串总结

### 双指针总结

**通过两个指针在一个for循环下完成两个for循环的工作。**

## day 10

### [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/)

工业开发中，函数复用很重要

在类中声明成员变量时，需要把**变量声明**和**初始化**分开进行。变量声明发生在类的成员部分，而初始化通常是在构造函数中完成。

用两个栈来实现队列

```java
class MyQueue {

    // **变量声明**
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        // **初始化**
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }
    
    public void push(int x) {
        stackIn.push(x);
    }
    
    public int pop() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                int temp = stackIn.pop();
                stackOut.push(temp);
            }
        }

        return stackOut.pop();
    }
    
    public int peek() {
        int x = pop();
        stackOut.push(x);
        return x;
    }
    
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```

### [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/)

queue是队列，deque是双端队列.

stack的push(), pop()对应queue的offer(), poll().

```java
class MyStack {

    Queue<Integer> que;

    public MyStack() {
        que = new LinkedList<>();
    }
    
    public void push(int x) {
        que.offer(x);
    }
    
    public int pop() {
        int size = que.size();
        size--;
        int x = size;
        while (x > 0) {
            int temp = que.poll();
            que.offer(temp);
            x--;
        }

        return que.poll();
    }
    
    public int top() {
        int x = pop();
        que.offer(x);
        return x;
    }
    
    public boolean empty() {
        return que.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
```

### [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

这题技巧性偏强，遇到左括号就入栈对应的右括号，遇到右括号就跟string比较同样的位置，是不是char一样

遇到三种情况就return false：

* 多了左括号
* 多了右括号
* 左、右括号没匹配上

```java
class Solution {
    public boolean isValid(String s) {
        // 底下这里要用Character，new 后面要大写S
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(')');
            } else if (s.charAt(i) == '[') {
                stack.push(']');
            } else if (s.charAt(i) == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.peek() != s.charAt(i)) {
                return false;
            } else {
                stack.pop();
            }
        }

        // 这里要加一个判断，不然过不了 s = '[' 的情况
        // 其实这里也就对应了三种里面的第一种错误
        if (!stack.isEmpty()) return false;
        return true;
    }
}
```

### [1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/)

栈特别擅长处理相邻字母的某些判断

一个栈、一个s，遍历比较、push, pop

这题最后的返回格式有点麻烦，直接stack.toString()的话格式不对，是堆栈的形式，
要先stack构造StringBuilder，再转换成string

```java
class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty() || ch != stack.peek()) {
                stack.push(ch);
            } else {
                stack.pop();
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            // 从前插入字符，保证顺序
            res.insert(0, stack.pop());
        }

        return res.toString();
    }
}
```

## day 11

### [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/)

思路是，遇到数字就push进去，遇到符号就进行运算，然后把结果压进去

对 Integer, String 的操作要熟悉，.equals(), Integer.valueOf()方法

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int num1, num2;

        for (int i = 0; i < tokens.length; i++) {
            // 这里要用.equals()
            if (tokens[i].equals("+")) {
                // num1, num2的顺序，要想一下
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 + num2);
            } else if (tokens[i].equals("-")) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 - num2);
            } else if (tokens[i].equals("*")) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 * num2);
            } else if (tokens[i].equals("/")) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(num1 / num2);
            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }

        // 返回最后一个数
        return stack.pop();
    }
}
```

### [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)

在设计MyQueue的时候有点乱，poll和add是一套组合拳：在add方法里面，对que里面的最左边处理过了，所以在poll方法里面要对最左边做判断，是不是传入的值和最左边的值相等，换句话说，看下最左边的数有没有在add()的时候被卷走。
这样的目的是，保证最左边出口的数一定是当前滑动窗口的最大值

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length - k + 1;  // 结果数组长度
        int res[] = new int[len];       // 存储每个窗口的最大值
        int num = 0;                    // 结果数组的索引
        MyQueue que = new MyQueue();    // 初始化自定义队列

        // 初始化第一个窗口
        for (int i = 0; i < k; i++) {
            que.add(nums[i]);
        }
        res[num] = que.peek();  // 存储第一个窗口的最大值
        num++;

        // 滑动窗口
        for (int i = k; i < nums.length; i++) {
            que.poll(nums[i - k]);  // 移除窗口最左侧的元素
            que.add(nums[i]);       // 加入新元素
            res[num] = que.peek();  // 存储当前窗口的最大值
            num++;
        }

        return res;
    }
}

// 自定义队列，用于管理窗口的最大值
class MyQueue {
    Deque<Integer> que = new LinkedList<>();

    // 弹出元素，只有当弹出的元素是当前最大值时才移除
    // 这是因为在add方法里面，对que里面的最左边处理过了
    void poll(int val) {
        if (!que.isEmpty() && val == que.peek()) {
            que.poll();
        }
    }

    // 添加元素，并移除所有比新元素小的尾部元素
    void add(int val) {
        while (!que.isEmpty() && val > que.peekLast()) {
            que.removeLast();
        }
        que.add(val);
    }

    // 获取队列的头部元素，即当前窗口的最大值
    int peek() {
        return que.peek();
    }
}

```

### [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)

这道题目主要涉及到如下三块内容：

1. 要统计元素出现频率
2. 对频率排序
3. 找出前K个高频元素

在 Java 中，**`PriorityQueue`** 是一个基于**优先级堆（Heap）**的数据结构，它与普通队列（FIFO）不同，**元素会按照优先级顺序出队**，而不是按插入的顺序。

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // key为数组元素值,val为对应出现次数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 小顶堆：按出现次数从小到大排序
        PriorityQueue<int[]> que = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]);

        // 遍历哈希表，将元素加入堆中
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (que.size() < k) {
                que.add(new int[]{entry.getKey(), entry.getValue()});
            } else if (entry.getValue() > que.peek()[1]) {
                que.poll();
                que.add(new int[]{entry.getKey(), entry.getValue()});
            }
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = que.poll()[0];
        }

        return ans;
    }
}
```

### 总结

`Deque` 可以被当作 **栈（LIFO）** 或 **队列（FIFO）** 使用，因此它比 `Stack` 和普通队列更加灵活。所以尽量用deque吧

在 Java 中，**`PriorityQueue`** 是一个基于**优先级堆（Heap）**的数据结构，它与普通队列（FIFO）不同，**元素会按照优先级顺序出队**，而不是按插入的顺序。

什么是堆呢？
**堆是一棵完全二叉树，树中每个结点的值都不小于（或不大于）其左右孩子的值。** 如果父亲结点是大于等于左右孩子就是大顶堆，小于等于左右孩子就是小顶堆。
所以大家经常说的大顶堆（堆头是最大元素），小顶堆（堆头是最小元素），如果懒得自己实现的话，就直接用PriorityQueue（优先级队列）就可以了，底层实现都是一样的，从小到大排就是小顶堆，从大到小排就是大顶堆。

## day 12

休息

## day 13

### 递归遍历

```java
// 前序遍历·递归·LC144_二叉树的前序遍历
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        preorder(root, result);
        return result;
    }

    public void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }
}
// 中序遍历·递归·LC94_二叉树的中序遍历
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);             // 注意这一句
        inorder(root.right, list);
    }
}
// 后序遍历·递归·LC145_二叉树的后序遍历
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);             // 注意这一句
    }
}
```



### [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)

deque的用法：

####  1. **插入操作**

- `offerFirst(E e)`：尝试在队首插入元素，返回 `true`/`false`。
- `offerLast(E e)`：尝试在队尾插入元素，返回 `true`/`false`。

#### 2. **删除操作**

- `pollFirst()`：删除并返回队首元素，若为空则返回 `null`。
- `pollLast()`：删除并返回队尾元素，若为空则返回 `null`。

#### 3. **查看操作**

- `peekFirst()`：查看队首元素，不删除，若为空则返回 `null`。
- `peekLast()`：查看队尾元素，不删除，若为空则返回 `null`。

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        Deque<TreeNode> que = new LinkedList<TreeNode>();
        
        // 这个返回resList, 而不是null
        if (root == null) return resList;
        que.offerFirst(root);
        while (!que.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int len = que.size();
            while (len > 0) {
                TreeNode temp = que.pollLast();
                // 在这里添加每一层的元素！
                level.add(temp.val);

                if (temp.left != null) que.offerFirst(temp.left);
                if (temp.right != null) que.offerFirst(temp.right);
                len--;

            }
            resList.add(level);
        }

        return resList;
    }
}
```

## day 14

### [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/)

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
        // 参数和返回值
    public TreeNode invertTree(TreeNode root) {
        // 终止条件
        if (root == null) return root;
        // 单层处理逻辑
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
```

### [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)

- 如果直接访问 `left.left` 或 `right.right`，但其中一个为 `null`，会抛出 **NullPointerException**。

- 你需要先判断节点是否为 `null`，然后再判断它们的值是否相等。
  ```java
  // 注意顺序
  				if (left == null && right != null) return false;
          if (left != null && right == null) return false;
          if (left == null && right == null) return true;
          if (left.val != right.val) return false;
  ```

比较内侧和外侧，体现的方式是递归下一层递归当前层的内外侧，而不是在同一层处理：
```java
if (left.left.val != right.right.val)			// 这是不对的‼️
```

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        // 如果直接访问 left.left 或 right.right，但其中一个为 null，会抛出 NullPointerException。
        // 需要先判断节点是否为 null，然后再判断它们的值是否相等。
        if (left == null && right != null) return false;
        if (left != null && right == null) return false;
        if (left == null && right == null) return true;  // 结束条件
        if (left.val != right.val) return false;
        
        boolean outside = compare(left.left, right.right);
        boolean inside = compare(left.right, right.left);
        return outside && inside;
    }
}
```

### [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

后序遍历求高度，前序遍历求深度

```java
class Solution {
    public int maxDepth(TreeNode root) {
        // 后序遍历求高度，这题要求最大深度，其实就是求的高度
        if (root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        int mid = Math.max(leftDepth, rightDepth) + 1;
        return mid;
    }
}
```

### [111. Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)

这题有个细节要注意：最小深度，指的是根节点到叶子节点到最小长度。假如左子树为空，右子树为空，那就去右子树去找最小高度（一个 if 判断）

其他地方和上一题差不多

```java
class Solution {
    public int minDepth(TreeNode root) {
        // 后序
        if (root == null) return 0;
        
        int ldep = minDepth(root.left);
        int rdep = minDepth(root.right);

        if (root.left == null && root.right != null) {
            return rdep + 1;
        } else if (root.left != null && root.right == null) {
            return ldep + 1;
        } else {
            return Math.min(ldep, rdep) + 1;
        }
    }
}
```

## day 15

### [110. Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/)

不要想那么多，具体怎么实现。只要是递归，就想递归三部曲

巩固一下，高度是左右叶子结点的最大值加一

**高度用后序遍历**：只有先定义了左右高度，才能在中节点进行判断二者差，是否是-1或者进行赋值

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        int res = getHeight(root);
        return res != -1;
    }

    public int getHeight (TreeNode root) {  // 不用传入 height，这个是由每一层递归的left, right高度来决定的
        //  后序遍历求高度
        int height = 0;
        if (root == null) return 0;

        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) return -1;    // 这个要判断，遇到-1的话一层一层上去，相当于返回false了
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        return height;
    }
}
```

### [257. Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/)

回溯，没什么思路，有点印象

有个int数组转换成string数组的细节要处理

```java
sb.append(paths.get(i)).append("->");
```

```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();   // 存最后结果
        if (root == null) {
            return res;
        }
        List<Integer> paths = new ArrayList<>();    // 存每一条路径
        traversal(root, paths, res);
        return res;
    }

    private void traversal(TreeNode root, List<Integer> paths, List<String> res) {
        paths.add(root.val); // 前序遍历
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));   // 记录最后一个节点
            res.add(sb.toString());
            return;
        }

        if (root.left != null) {
            traversal(root.left, paths, res);
            paths.remove(paths.size() - 1);
        }
        if (root.right != null) {
            traversal(root.right, paths, res);
            paths.remove(paths.size() - 1);
        }
    }
}
```

### [404. Sum of Left Leaves](https://leetcode.com/problems/sum-of-left-leaves/)

这题和之前二叉树题不一样的是：通过父节点，来判断左孩子是不是需要的节点
```java
root.left.right == null
```

后序遍历。这题中间节点处理逻辑，就是用sum保存左右子树“左叶子”的和

```java
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        
        int leftSum = sumOfLeftLeaves(root.left);
        if (root.left != null && root.left.left == null && root.left.right == null) {
            leftSum += root.left.val;
        }
        int rightSum = sumOfLeftLeaves(root.right);

        int sum = leftSum + rightSum;
        return sum;
    }
}
```

### [222. Count Complete Tree Nodes](https://leetcode.com/problems/count-complete-tree-nodes/)

这题前中后序遍历很好写，主要是针对完全二叉树的写法。

终止条件不止是 (root == null)

只判断外侧 left.left和right.right就是利用了完全二叉树的特性

原理就是：如果是满二叉树，那直接用满二叉树的公式计算出节点数量；如果不是的话，那就分别往左往右去找，总会有不少满二叉树。这样做不用一个节点一个节点去遍历，是一块一块的

```java
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0, rightDepth = 0;
        while (left != null) {
            left = left.left;
            leftDepth++;
        }
        while (right != null) {
            right = right.right;
            rightDepth++;
        }
        // 在当前节点，如果是满二叉树，那么左子树深度和右子树深度是相等的
        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;	// 这是另一种return情况
    }
}
```

## day 16

### [513. Find Bottom Left Tree Value](https://leetcode.com/problems/find-bottom-left-tree-value/)

这题用层序遍历，也就是迭代法简单，但是这里用的是递归

```java
class Solution {
    // 这题怎样遍历都行，只要先左后右，这题没有中间节点处理逻辑
    // 底下这两个全局变量
    private int maxDepth = -1;
    private int value = 0;
    public int findBottomLeftValue(TreeNode root) {
        value = root.val;
        findLeftValue(root, 0);
        return value;
    }

    public void findLeftValue(TreeNode root, int depth) {
        if (root == null) return;
        // 这一步保证保存的是最左边的元素
        if (root.left == null && root.right == null) {
            if (maxDepth < depth) {
                maxDepth = depth;
                value = root.val;
            }
        }
        if (root.left != null) {
            findLeftValue(root.left, depth + 1);
        }
        // 底下这种写法，是回溯思想的体现；上面的话简短一点
        if (root.right != null) {
            depth++;
            findLeftValue(root.right, depth);
            depth--;
        }
    }
}
```

### [112. Path Sum](https://leetcode.com/problems/path-sum/)

这一步用来判断，假如有找到一条路径，符合条件，那就true一直返回上去了

```java
if(traversal(root.left, count)) return true;
```

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return traversal(root, targetSum - root.val);
    }

    private boolean traversal(TreeNode root, int count) {
        if (root.left == null && root.right == null && count == 0) return true;
        if (root.left == null && root.right == null && count!= 0) return false;

        // 这么写可以体现回溯
        if (root.left != null) {
            count -= root.left.val;
            if(traversal(root.left, count)) return true;
            count += root.left.val;
        }

        if (root.right != null) {
            count -= root.right.val;
            if(traversal(root.right, count)) return true;
            count += root.right.val;
        }

        return false;
    }
}
```

### [113. Path Sum II](https://leetcode.com/problems/path-sum-ii/)

这题要一个list来保存路径，其他和上一题一样

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return res;
        path.add(root.val);
        traversal(root, targetSum - root.val);
        return res;
    }

    private void traversal(TreeNode root, int count) {
        if (root.left == null && root.right == null && count == 0) {
            res.add(new ArrayList<>(path));
        }
        if (root.left == null && root.right == null) {
            return;
        }

        if (root.left != null) {
            path.add(root.left.val);
            count -= root.left.val;
            traversal(root.left, count);
            count += root.left.val;
            path.remove(path.size() - 1);
        }

        if (root.right != null) {
            path.add(root.right.val);
            count -= root.right.val;
            traversal(root.right, count);
            count += root.right.val;
            path.remove(path.size() - 1);
        }
    }
}
```

### [106. Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)

1. **后序数组为空时：**
   - 如果后序遍历数组为空，返回一个 **空节点**，说明没有更多元素构建子树。
2. **后序数组最后一个元素为根节点：**
   - 在后序遍历中，**最后一个元素**就是当前子树的 **根节点**。
3. **查找根节点在中序数组中的位置：**
   - 在中序遍历中找到这个根节点的位置，用来**划分左子树和右子树**。
4. **切割中序数组：**
   - 根据根节点的位置，将中序数组**切分为左子树和右子树**的部分。
5. **切割后序数组：**
   - 根据左子树的大小，切分后序数组，分别得到**左子树和右子树**的后序数组。
6. **递归处理左右子树：**
   - 递归地构建 **左子树** 和 **右子树**，直到所有子树都构建完成。

刚看开始看的时候懵了，这么多参数，还有个map，怎么做？
其实只要把传入的参数，定义清楚了，那也就清晰了。

* map：用来保存前序遍历的，这样找中序遍历值的索引，更效率
* inBegin：标记当前子树的 **中序遍历序列的开始位置**
* inEnd：标记当前子树的 **中序遍历序列的结束位置**，等于 postBegin + lenOfLeft
* postBegin：标记当前子树的 **后序遍历序列的开始位置**
* postEnd：标记当前子树的 **后序遍历序列的结束位置**
* lenOfLeft：计算 **左子树的节点数量**

```java
class Solution {
    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return findNode(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    public TreeNode findNode(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd) {
        // 参数里的范围都是前闭后开
        if (inBegin >= inEnd || postBegin >= postEnd) { // 不满足左闭右开，说明没有元素，返回空树
            return null;
        }
        int rootIndex = map.get(postorder[postEnd - 1]); // 记得是左闭右开！找到后序遍历的最后一个元素在中序遍历中的位置
        TreeNode root = new TreeNode(inorder[rootIndex]);
        int lenOfLeft = rootIndex - inBegin; // 保存中序左子树节点个数，用来确定中序、后序中哪些节点属于左子树

        // 递归
        root.left = findNode(inorder, inBegin, rootIndex, postorder, postBegin, postBegin + lenOfLeft);
        root.right = findNode(inorder, rootIndex + 1, inEnd, postorder, postBegin + lenOfLeft, postEnd - 1);

        return root;
    }
}
```

## day 17

### [654. Maximum Binary Tree](https://leetcode.com/problems/maximum-binary-tree/)

注意区间开闭

```java
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // 这里定义了左右开闭，这里是左闭右闭
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int leftIndex, int rightIndex) {
        if (rightIndex < leftIndex) return null;
        if (rightIndex == leftIndex) return new TreeNode(nums[leftIndex]);
      
        int maxIndex = leftIndex;
        int maxValue = nums[maxIndex];
        for (int i = maxIndex; i <= rightIndex; i++) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
                maxValue = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxValue);
        root.left = construct(nums, leftIndex, maxIndex - 1);
        root.right = construct(nums, maxIndex + 1, rightIndex);
        return root;
    }
}
```

### [617. Merge Two Binary Trees](https://leetcode.com/problems/merge-two-binary-trees/)

这题终止条件很关键

```java
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 终止条件！这题最关键的就是这儿
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        TreeNode resRoot = new TreeNode(root1.val + root2.val);
        resRoot.left = mergeTrees(root1.left, root2.left);
        resRoot.right = mergeTrees(root1.right, root2.right);

        return resRoot;
    }
}
```

### [700. Search in a Binary Search Tree](https://leetcode.com/problems/search-in-a-binary-search-tree/)

java的要求：每个分支都必须明确地返回一个值，即使逻辑上已经涵盖了所有情况：

这样不行：

```java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return root;
        if (root.val == val) return root;

        if (root.val < val) {
            return searchBST(root.right, val);
        }

        if (root.val > val) {
            return searchBST(root.left, val);
        }
    }
}
```

可以在最后加上return null，尽管到达不了这一步；或者把最后一个 if 改成 else if；或者直接把最后一个 if 判断删掉，把里面的内容摘出来

### [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)

**不能单纯的比较左节点小于中间节点，右节点大于中间节点就完事了**
**我们要比较的是 左子树所有节点小于中间节点，右子树所有节点大于中间节点**。

注意traversal函数的返回类型，void

```java
class Solution {
    private List<Integer> list = new ArrayList<Integer>();

    public boolean isValidBST(TreeNode root) {
        // 中序遍历，转换成数组，然后判断
        traversal(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) return;

        traversal(root.left, list);
        list.add(root.val);
        traversal(root.right, list);
    }
}
```

## day 18

### [530. Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst/)

可以优化，把数组剪掉，直接用一个数来比较

```java
class Solution {
    // 遇到在二叉搜索树上求什么最值啊，差值之类的，就把它想成在一个有序数组上求最值，求差值，这样就简单多了
    // 那么二叉搜索树采用中序遍历，其实就是一个有序数组。
    public int res = Integer.MAX_VALUE;
    public List<Integer> list = new ArrayList<>();

    public int getMinimumDifference(TreeNode root) {
        traversal(root, list);
        for (int i = 1; i < list.size(); i++) {
            int minus = list.get(i) - list.get(i - 1);
            res = Math.min(minus, res);
        }
        return res;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) return;

        traversal(root.left, list);
        list.add(root.val);
        traversal(root.right, list);
    }
}
```

### [501. Find Mode in Binary Search Tree](https://leetcode.com/problems/find-mode-in-binary-search-tree/)

可以利用二叉搜索树特性，只遍历一次。resList.clear()是关键

```java
class Solution {
    ArrayList<Integer> resList;
    int maxCount;
    int count;
    TreeNode pre;

    public int[] findMode(TreeNode root) {
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        pre = null;
        traversal(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    private void traversal(TreeNode root) {
        if (root == null) return;

        traversal(root.left);

        int rootValue = root.val;
        // 计数
        if (pre == null || rootValue != pre.val) {
            count = 1;
        } else {
            count++;
        }

        if (count > maxCount) {
            resList.clear();
            resList.add(rootValue);
            maxCount = count;
        } else if (count == maxCount) {
            resList.add(rootValue);
        }

        pre = root;

        traversal(root.right);
    }
}
```

### [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) { // 递归结束条件
            return root;
        }

        // 后序遍历
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null && right == null) { // 若未找到节点 p 或 q
            return null;
        }else if(left == null && right != null) { // 若找到一个节点
            return right;
        }else if(left != null && right == null) { // 若找到一个节点
            return left;
        }else { // 若找到两个节点
            return root;
        }
    }
}
```

## day 19

休息

## day 20

### [235. Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

这题的 if (root == null) return null;
可以不要，下面的也没有

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) root = lowestCommonAncestor(root.right, p, q);
        if (root.val > p.val && root.val > q.val) root = lowestCommonAncestor(root.left, p, q);

        return root;
    }
}
```

### [701. Insert into a Binary Search Tree](https://leetcode.com/problems/insert-into-a-binary-search-tree/)

这题的关键是：BST中插入的所有节点，都可以在叶子结点插入

另外，这题的终止条件，return了一个叶子结点到了上层。这个过程想明白

```java
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            TreeNode res = new TreeNode(val);
            return res;
        }

        if (root.val < val) root.right = insertIntoBST(root.right, val);
        if (root.val > val) root.left = insertIntoBST(root.left, val);
        
        return root;
    }
}
```

### [450. Delete Node in a BST](https://leetcode.com/problems/delete-node-in-a-bst/)

调整二叉树的结构，最复杂

这题五种情况，最后一种最难：

- 第一种情况：没找到删除的节点，遍历到空节点直接返回了
- 找到删除的节点
  - 第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
  - 第三种情况：删除节点的左孩子为空，右孩子不为空，删除节点，右孩子补位，返回右孩子为根节点
  - 第四种情况：删除节点的右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
  - 第五种情况：左右孩子节点都不为空，则将删除节点的左子树头结点（左孩子）放到删除节点的右子树的最左面节点的左孩子上，返回删除节点右孩子为新的根节点。

五种情况 return 的值都不相同，要想一下

```java
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        // 第一种情况
        if (root == null)
            return root;
        if (root.val == key) {
            // 第二种情况
            if (root.left == null && root.right == null) {
                return null;
                // 第三种情况，马上删除一下试试
            } else if (root.left == null && root.right != null) {
                return root.right;
                // 第四种情况
            } else if (root.left != null && root.right == null) {
                return root.left;
                // 第五种情况
            } else {
                TreeNode temp = root.right;
                // 一直往左叶子找
                while (temp.left != null) {
                    temp = temp.left;
                }
                temp.left = root.left;
                // 这里返回的是root.right，而不是temp
                // temp是root.right最左左左左的子节点，被删节点的左子树接到了这里
                return root.right;
            }
        }

        // 这里要有传递值，而不是单单进行迭代。这是根据上面返回值决定的
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }
}
```

## day 21

### [669. Trim a Binary Search Tree](https://leetcode.com/problems/trim-a-binary-search-tree/)

注意bst特性和修剪之间的关系

```java
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;

        // 不在范围内
        // 如果当前节点的值小于 low，则当前节点和它的左子树所有节点都小于 low
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        // 如果当前节点的值大于 high，则当前节点和它的右子树所有节点都大于 high
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        // 在范围内
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
```

### [108. Convert Sorted Array to Binary Search Tree](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/)

有点类似二分法，做之前要确定区间开闭

```java
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        return construct(nums, left, right);
    }

    private TreeNode construct(int[] nums, int left, int right) {
        // 这个要放在最前面
        if (left > right) return null;

        int mid = (left + right) / 2;
        // 值是nums[mid], 而不是mid
        TreeNode root = new TreeNode(nums[mid]);

        root.left = construct(nums, left, mid - 1);
        root.right = construct(nums, mid + 1, right);

        return root;
    }
}
```

### [538. Convert BST to Greater Tree](https://leetcode.com/problems/convert-bst-to-greater-tree/)

这题的重点，遍历顺序是右中左；要一个pre来保存上一个节点值

```java
class Solution {
    int sum;
    public TreeNode convertBST(TreeNode root) {
        sum = 0;
        convert(root);
        return root;
    }

    private void convert(TreeNode root) {
        if (root == null) return;
        convert(root.right);
        sum += root.val;
        root.val = sum;
        convert(root.left);
    }
}
```

## day 22

开始回溯

### [77. Combinations](https://leetcode.com/problems/combinations/)

这题的剪枝很关键

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        // 为什么是1？注意看题，题目是要从1开始的integer
        backtracking(n, k, 1);
        return res;
    }

    private void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 在底下剪枝
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backtracking(n, k, i + 1);
            path.removeLast();
        }
    }
}
```

### [216. Combination Sum III](https://leetcode.com/problems/combination-sum-iii/)

剪枝有点麻烦

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(n, k, 1, 0);
        return res;
    }

    private void backtracking(int targetSum, int k, int startIndex, int sum) {
        // 剪枝
        if (sum > targetSum) return;
        if (path.size() == k) {
            if (sum == targetSum) res.add(new ArrayList<>(path));
            return;
        }

        // 剪枝 9 - (k - path.size()) + 1
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            backtracking(targetSum, k, i + 1, sum);
            path.removeLast();
            // 回溯
            sum -= i;
        }
    }
}
```

### [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)

```java
class Solution {

    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTracking(digits, numString, 0);
        return list;
    }

    StringBuilder temp = new StringBuilder();

    public void backTracking(String digits, String[] numString, int num) {
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            backTracking(digits, numString, num + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
```

## day 23

### [39. Combination Sum](https://leetcode.com/problems/combination-sum/)

```java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);    // 先排序
        backtracking(res, new ArrayList<>(), candidates, target, 0, 0);
        return res;
    }

    public void backtracking(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int sum, int idx) {
        // 找到了数字和为 target 的组合
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            // 如果 sum + candidates[i] > target 就终止遍历
            if (sum + candidates[i] > target) break;
            path.add(candidates[i]);
            backtracking(res, path, candidates, target, sum + candidates[i], i);    // 这里最后是i，而不是idx
            path.remove(path.size() - 1);
        }
    }
}
```

### [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)

不使用标记数组的方法

```java
class Solution {
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtracking(candidates, target, 0);
        return res;
    }

    private void backtracking(int[] candidates, int target, int startIndex) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            // 正确剔除重复解的办法
            // 跳过同一树层使用过的元素
            if (i > startIndex && candidates[i] == candidates[i - 1]) continue;
            sum += candidates[i];
            path.add(candidates[i]);
            // i+1 代表当前组内元素只选取一次
            backtracking(candidates, target, i + 1);
            path.removeLast();
            sum -= candidates[i];
        }

    }
}
```

### [131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/)

有点儿难，涉及到string的转换

```java
class Solution {
    List<List<String>> res = new ArrayList<>();
    List<String> cur = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backtracking(s, 0, new StringBuilder());
        return res;
    }

    private void backtracking(String s, int startIndex, StringBuilder sb) {
        // 因为是起始位置一个一个加的，所以结束时start一定等于s.length,
        // 因为进入backtracking时一定末尾也是回文，所以cur是满足条件的
        if (startIndex == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (check(sb)) {
                cur.add(sb.toString());
                backtracking(s, i + 1, new StringBuilder());
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean check(StringBuilder sb) {
        for (int i = 0; i < sb.length() / 2; i++) {
            if (sb.charAt(i) != sb.charAt(sb.length() - 1 - i)) return false;
        }
        return true;
    }
}
```

## day 24

### [93. Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)

这题其实是分割问题，回溯 + 判断合法性

这里只有一次s被3个dot分割完，才加入res结果集，这时候第四个子区间要单独进行一下判断

```java
class Solution {
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        StringBuilder sb = new StringBuilder(s);
        backtracking(sb, 0, 0);
        return res;
    }

    private void backtracking(StringBuilder s, int startIndex, int dotCount) {
        if (dotCount == 3) {
            // 判断第四段子字符串是否合法，如果合法就放进result中
            if (isValid(s, startIndex, s.length() - 1)) {
                res.add(s.toString());
            }
            return;
        }

        // 判断 [startIndex,i] 这个区间的子串是否合法
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                // 在i的后面插入一个逗点
                s.insert(i + 1, '.');
                backtracking(s, i + 2, dotCount + 1);
                s.deleteCharAt(i + 1);
            } else {
                break;
            }
        }
    }

    private boolean isValid(StringBuilder s, int start, int end) {
        if (start > end) return false;
        if (s.charAt(start) == '0' && start != end) return false;
        int num = 0;
        for (int i = start; i <= end; i++) {
            int digit = s.charAt(i) - '0';
            num = num * 10 + digit;
            if (num > 255) return false;
        }
        return true;
    }
}
```

### [78. Subsets](https://leetcode.com/problems/subsets/)

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtracking(nums, 0);
        return res;
    }

    private void backtracking(int[] nums, int startIndex) {
        res.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }

    }
}
```

### [90. Subsets II](https://leetcode.com/problems/subsets-ii/)

相比上一题，就多了两行代码：

去重需要先对集合排序：

```
Arrays.sort(nums);
```

对树层去重：

```
if (i > startIndex && nums[i] == nums[i - 1]) continue;
```

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 去重需要先对集合排序
        Arrays.sort(nums);
        backtracking(nums, 0);
        return res;
    }

    private void backtracking(int[] nums, int startIndex) {
        res.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }

    }
}
```

## day 25

### [491. Non-decreasing Subsequences](https://leetcode.com/problems/non-decreasing-subsequences/)

剪枝方法很重要

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new LinkedList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums, 0);
        return res;
    }

    private void backtracking(int[] nums, int startIndex) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            // 假如：path最后一个数比数组下一个数要大（第一个isEmpty用来解决path为空的情况）
            // ，或者这一层出现过这个元素的值了
            if (!path.isEmpty() && path.get(path.size() - 1) > nums[i] || set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            path.add(nums[i]);
            // 这里是i
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }
}
```

### [46. Permutations](https://leetcode.com/problems/permutations/)

题目给定所有元素都是不一样的，这题主要就是判断，path中是否出现过元素，用 path.contains() 来判断

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        backtracking(nums);
        return res;
    }

    private void backtracking(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) continue;
            path.add(nums[i]);
            backtracking(nums);
            path.removeLast();
        }
    }
}
```

### [47. Permutations II](https://leetcode.com/problems/permutations-ii/)

主要是判断重复：用了一个标记数组来判断，并且

```java
// used[i - 1] == false, 加上前面的nums[i] == nums[i - 1], 用来判断是在树层上重复
// 因为已经一次for循环过了，used[i] = false; 执行过一次了
// 判断逻辑：首先得 num[i] == nums[i-1], 然后怎么判断是深度还是广度（for还是backtracking）？用used[i - 1] == false来判断
if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) continue;
```

上面这段代码直接限制了是在树层进行的剪枝

如果要对树层中前一位去重，就用`used[i - 1] == false`，如果要对树枝前一位去重用`used[i - 1] == true`，**对于排列问题，树层上去重和树枝上去重，都是可以的，但是树层上去重效率更高！**
（有点抽象，用树层处理就好）

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        Arrays.sort(nums);
        backtracking(nums, used);
        return res;
    }

    private void backtracking(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // used[i - 1] == false, 加上前面的nums[i] == nums[i - 1], 用来判断是在树层上重复
            // 因为已经一次for循环过了，used[i] = false; 执行过一次了
            // 判断逻辑：首先得 num[i] == nums[i-1], 然后怎么判断是深度还是广度（for还是backtracking）？用used[i - 1] == false来判断
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) continue;
            if (used[i] == false) {
                used[i] = true;
                path.add(nums[i]);
                backtracking(nums, used);
                path.removeLast();
                used[i] = false;    // 回溯
            }

        }
    }
}
```

## day 26

休息

## day 27

### [455. Assign Cookies](https://leetcode.com/problems/assign-cookies/)

不能先遍历饼干，再遍历小孩：假如胃口最大的小孩儿没有饼干能满足，那么饼干持续减减，最后res是0

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int j = s.length - 1;
        
        for (int i = g.length - 1; i >= 0; i--) {
            if (j >= 0 && g[i] <= s[j]) {
                res++;
                j--;
            }
        }

        return res;
    }
}
```

### [376. Wiggle Subsequence](https://leetcode.com/problems/wiggle-subsequence/)

核心点就是：

```java
				for (int i = 1; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            // 这里的=情况，考虑到了单调坡度有平坡的情况
            if (curDiff > 0 && preDiff <= 0 || curDiff < 0 && preDiff >= 0) {
                preDiff = curDiff;  // 这步很精髓
                res++;
            }
        }
```



还有细节，很重要：

1. 情况一：上下坡中有平坡
2. 情况二：数组首尾两端
3. 情况三：单调坡中有平坡

```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int curDiff = 0;
        int preDiff = 0;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            // 这里的=情况，考虑到了单调坡度有平坡的情况
            if (curDiff > 0 && preDiff <= 0 || curDiff < 0 && preDiff >= 0) {
                preDiff = curDiff;  // 这步很精髓
                res++;
            }
        }

        return res;
    }
}
```

### [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)

result 要初始化为最小负数，而不是0，不然遇到全负数的数组不好弄

```java
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            // 这一步要放if外面，因为maxSum是不管什么情况都要比较的
            maxSum = Math.max(maxSum, curSum);
            if (curSum < 0) {
                curSum = 0;
            }
        }

        return maxSum;
    }
}
```

## day 28

### [122. Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        int[] profit = new int[prices.length - 1];
        for (int i = 0; i < profit.length; i++) {
            profit[i] = prices[i + 1] - prices[i];
        }

        for (int i = 0; i < profit.length; i++) {
            res += Math.max(0, profit[i]);
        }

        return res;
    }
}
```

### [55. Jump Game](https://leetcode.com/problems/jump-game/)

```java
class Solution {
    public boolean canJump(int[] nums) {
        int max = nums[0];
        for (int i = 0; i <= max; i++) {    // 这里要小于等于，而不是小于
            max = Math.max(max, nums[i] + i);
            if (max >= nums.length - 1) {   // 这里要 -1
                return true;
            }
        }

        return false;
    }
}
```

### [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/)

```java
class Solution {
    public int jump(int[] nums) {
        int allMax = 0;
        int curMax = 0;
        int step = 0;

        // 千万注意这里是 nums.length - 1
        for (int i = 0; i < nums.length - 1; i++) {
            allMax = Math.max(allMax, i + nums[i]);

            if (i == curMax) {
                curMax = allMax;
                step++;
            }
        }

        return step;
    }
}
```

### [1005. Maximize Sum Of Array After K Negations](https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/)

绝对值转换有点麻烦，

算法思路要搞明白，这题是可以对同一个元素多次取反的

```java
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int largestSumAfterKNegations(int[] A, int K) {
        // 将 int[] 转换为 Integer[]，以便使用 Comparator
        Integer[] arr = Arrays.stream(A).boxed().toArray(Integer[]::new);

        // 按绝对值从大到小排序
        Arrays.sort(arr, (a, b) -> Math.abs(b) - Math.abs(a));

        // 第二步：从左到右，将负数取反，直到用完K或没有负数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 && K > 0) {
                arr[i] = -arr[i];
                K--;
            }
        }

        // 第三步：如果K是奇数，取绝对值最小的元素取反
        if (K % 2 == 1) {
            arr[arr.length - 1] = -arr[arr.length - 1];
        }

        // 计算和并返回结果
        int result = Arrays.stream(arr).mapToInt(Integer::intValue).sum();
        return result;
    }
}
```

## day 29

### [134. Gas Station](https://leetcode.com/problems/gas-station/)

做的时候有个误区：以为循环更换起点之后，要重新走一圈。
其实不用，从全局想，假如totalSum大于0，那一定有个起点可以是答案；反之假如totalSum小于0，那一定返回-1

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSum = 0;
        int curSum = 0;
        int start = 0;  // 从start开始遍历

        // 一圈一圈的循环，用while好一点；线性循环用for好一点
        for (int i = 0; i < gas.length; i++){
            curSum = curSum + gas[i] - cost[i];
            totalSum = totalSum + gas[i] - cost[i];
            if (curSum < 0) {
                start = (i + 1) % gas.length;
                curSum = 0;
            }
        }
        if (totalSum < 0) return -1;
        return start;
    }
}
```

### [135. Candy](https://leetcode.com/problems/candy/)

这个方法比之前做的上坡下坡方法，更易理解

本题采用了两次贪心的策略：

- 一次是从左到右遍历，只比较右边孩子评分比左边大的情况。
- 一次是从右到左遍历，只比较左边孩子评分比右边大的情况。
  这次遍历中，由于第一次遍历初始化过temp数组了，所以处理数组节点逻辑和第一次不一样。要判断大小

这样从局部最优推出了全局最优，即：相邻的孩子中，评分高的孩子获得更多的糖果。

```java
class Solution {
    public int candy(int[] ratings) {
        int[] temp = new int[ratings.length];
        temp[0] = 1;
        int res = 0;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                temp[i] = temp[i - 1] + 1;
            } else {
                temp[i] = 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                temp[i] = Math.max(temp[i], temp[i + 1] + 1);
            }
        }

        for (int i = 0; i < ratings.length; i++) {
            res += temp[i];
        }

        return res;
    }
}
```

### [860. Lemonade Change](https://leetcode.com/problems/lemonade-change/)

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        // 这个是没想到的
        int five = 0;
        int ten = 0;

        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five++;
            } else if (bills[i] == 10) {
                five--;
                ten++;
            } else if (bills[i] == 20) {
                // 这里要分情况讨论，先把10块的花出去
                if (ten > 0) {
                    five--;
                    ten--;
                } else {
                    five -= 3;
                }
                
            }

            if (five < 0 || ten < 0) return false;
        }

        return true;
    }
}
```

### [406. Queue Reconstruction by Height](https://leetcode.com/problems/queue-reconstruction-by-height/)

遇到两个维度的时候，要一个一个考虑。不要顾此失彼，分糖果那题也是

这题思想：先按照身高排序，然后调整后面的人。后面的人再怎么插入[h, k]，也不影响前面人的k

```java
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return b[0] - a[0]; // 降序排列
        });

        LinkedList<int[]> que = new LinkedList<>();

        // que 是一个 LinkedList，该列表允许在指定位置插入元素。
        // p[1] 表示 k 值，用作插入位置的索引。
        // p 表示要插入的元素，即当前人的信息数组。
        // que.add(p[1], p); 会将当前的 p 插入到 LinkedList 的索引 p[1] 位置。
        //      根据题目需求，k 值表示此人前面应有多少个身高大于或等于他的人，所以直接使用 p[1] 作为插入位置是符合要求的。
        for (int[] p : people) {
            que.add(p[1], p);
        }

        return que.toArray(new int[people.length][]);
    }
}
```

## day 30

### [452. Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/)

排序方法，和上一题不太一样

更新右边界，不能简单地 points [i] [1] = points [i-1] [1]

```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        // 使用Integer内置比较方法，不会溢出
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        // points 不为空至少需要一支箭
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i-1][1]) {
                count++;
            } else if (points[i][0] <= points[i-1][1]) {
                // 更新重叠气球最小右边界，要取两个气球右边的最小值（可能存在一个左边右边都比另一个大的情况）
                points[i][1] = Math.min(points[i-1][1], points[i][1]);
            }
        }

        return count;
    }
}
```

### [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)

这题和上一题代码很类似，只有等于号位置和最后返回值的区别

和上一题本质是一样的，细节不一样，代码又一样

因为我们用的是左端排序，排序之后的数组，两个区间的重叠部分，就是和上题一样的逻辑：

```java
intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
```

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 使用Integer内置比较方法，不会溢出
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // intervals 不为空至少有一个区间
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= intervals[i - 1][1]) {
                count++;
            } else if (intervals[i][0] < intervals[i - 1][1]) {
                // 更新重叠区间边界，要取两个边界右边的最小值（可能存在一个左边右边都比另一个大的情况）
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
            }
        }

        return intervals.length - count;
    }
}
```

### [763. Partition Labels](https://leetcode.com/problems/partition-labels/)

思路难想，用哈希表保存下表，用当前遍历中的 i 和哈希表里保存的最远下标做比较

还有就是 last 用来分割，设置为 -1，第一次见

```java
class Solution {
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int[] hash = new int[26];   // 数组实现哈希，保存字母最后出现的下标
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i;
        }

        int index = 0;  // 这个区间里面的最大下标，下面要用Math.max来对比
        int last = -1;  // 这个是为了分割用的，不理解的话仔细看题目output，要求分割出的
        for (int i = 0; i < s.length(); i++) {
            index = Math.max(index, hash[s.charAt(i) - 'a']);
            if (i == index) {
                list.add(i - last);     
                last = i;           
            }
        }

        return list;
    }
}
```

## day 31

### [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)

这题思路好想，对链表操作、格式转换要求挺大

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // 插入删除操作比较多，用linkedlist
        LinkedList<int[]> res = new LinkedList<>();
        // 按照左边界排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= res.getLast()[1]) {
                int start = res.getLast()[0];
                int end = Math.max(intervals[i][1], res.getLast()[1]);
                res.removeLast();
                res.add(new int[]{start, end});
            } else {
                res.add(intervals[i]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
```

### [738. Monotone Increasing Digits](https://leetcode.com/problems/monotone-increasing-digits/)

这题思路难想：遇到前一位比后一位大，那么前一位减一，后一位变成9

代码中，string -> char -> int 有点麻烦

```java
class Solution {
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int start = s.length();
        for (int i = s.length() - 1; i >= 1; i--) {
            if (chars[i - 1] > chars[i]) {
                chars[i - 1]--;
                start = i;
            }
        }

        for (int i = start; i < s.length(); i++) {
            chars[i] = '9';
        }

        return Integer.parseInt(String.valueOf(chars));
    }
}
```

### 还有一题，没看，二刷再看

## day32

### [509. Fibonacci Number](https://leetcode.com/problems/fibonacci-number/)

为什么简单：dp数组给了，递推公式给了，初始化cp数组给了，遍历顺序给了

```java
class Solution {
    public int fib(int n) {
        if (n < 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[dp.length - 1];
    }
}
```

### [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

第一层楼梯再跨两步就到第三层 ，第二层楼梯再跨一步就到第三层。
所以到第三层楼梯的状态可以由第二层楼梯 和 到第一层楼梯状态推导出来：
dp[3] = dp[2] + dp[1]

其实还是把dp数组写下来找规律

```java
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n];
    }
}
```

### [746. Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/)

要把length究竟是什么，在最开始定义一下

这题dp[]的长度要比cost[]大1，因为要跳到更上一层台阶上去

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }

        return dp[dp.length - 1];
    }
}
```

## day 33

休息

## day 34

### [62. Unique Paths](https://leetcode.com/problems/unique-paths/)

这次我们就要考虑如何正确的初始化了，初始化和遍历顺序其实也很重要

```java
class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // i, j要从1开始，因为第一行、第一列所有元素都初始化0过了
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
```

### [63. Unique Paths II](https://leetcode.com/problems/unique-paths-ii/)

有障碍的话，直接对dp[i]操作，不用在dp[i+1]判断dp[i]的情况

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
```

### [343. Integer Break](https://leetcode.com/problems/integer-break/)

这题我觉得说是dp的话有点牵强

```java
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i - j; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }
}
```

### 还有一题没做，二刷做

## day 35

### 01背包--二维

### 01背包--一维

### [416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/)

* 背包体积为 sum / 2
* 物品重量为元素数值，价值也为元素数值
* 如果背包正好装满，则说明找到了总和为 sum / 2 的子集

可以加上最前面的：

```java
if(nums == null || nums.length == 0) return false;
```

和最后的剪枝：

```java
//剪枝一下，每一次完成內層的for-loop，立即檢查是否dp[target] == target，優化時間複雜度（26ms -> 20ms）
            if(dp[target] == target)
                return true;
        }
```

```java
class Solution {
    public boolean canPartition(int[] nums) {
        // 计算sum，用来求target
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 如果总和为奇数，那返回false
        if (sum % 2 == 1) return false;
        
        // 计算target
        int target = sum / 2;
        
        // 这里j的最前面，有重量为0的一列，所以是target+1
        int[] dp = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {
            // j == nums[i] 的时候也要算
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[target] == target;
    }
}
```

## day 36

### [1049. Last Stone Weight II](https://leetcode.com/problems/last-stone-weight-ii/)

target 是 sum/2，是所有元素任意选取，总和最接近一半的集合

其他和上一题没啥区别，最后求左集合 (sum - dp[target]) 和右集合 (sum - 左集合) = (dp(target)) 的差：sum - 2*dp[target]

```java
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int left = sum / 2;
        
        // 关键！自己写的时候出错了
        int[] dp = new int[left + 1];

        for (int i = 0; i < stones.length; i++) {
            for (int j = left; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        return sum - 2 * dp[left];
    }
}
```

### [494. Target Sum](https://leetcode.com/problems/target-sum/)

这题递推公式有点难想，理解了挺久

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        
        if (Math.abs(target) > sum) return 0;
        if ((sum - target) % 2 != 0) return 0;

        int left = (sum + target) / 2;
        int dp[] = new int[left + 1];
        
        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[left];
    }
}
```

### [474. Ones and Zeroes](https://leetcode.com/problems/ones-and-zeroes/)

这题理解不了dp数组的含义、递推公式，但是不想再花时间了，下一题

```java
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        //dp[i][j]表示i个0和j个1时的最大子集
        int[][] dp = new int[m + 1][n + 1];
        int oneNum, zeroNum;
        for (String str : strs) {
            oneNum = 0;
            zeroNum = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            //倒序遍历
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
```

## day 37

### 完全背包基础

就是01背包

### [518. Coin Change II](https://leetcode.com/problems/coin-change-ii/)

这题是求组合，下一题是求排列

```java
class Solution {
    public int change(int amount, int[] coins) {
        int dp[] = new int[amount + 1]; // dp[j]数组含义：凑成总金额j的硬币组合数

        dp[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];  // 递推公式
            }
        }

        return dp[amount];
    }
}
```

### [377. Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/)

和上一题的不同，在于两个for遍历的顺序。这样是求排列，上一个是求组合

```java
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target + 1];

        dp[0] = 1;

        for (int j = 0; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= j) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }

        return dp[target];
    }
}
```

## day 38

### [322. Coin Change](https://leetcode.com/problems/coin-change/)

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        // 这样理解：这里 int[amount + 1] 就是压缩前二维数组的 int[j]
        int[] dp = new int[amount + 1];

        for (int j = 0; j < dp.length; j++) {
            dp[j] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
```

### [279. Perfect Squares](https://leetcode.com/problems/perfect-squares/)

```java
class Solution {
    public int numSquares(int n) {
        int dp[] = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            } 
        }

        return dp[n];
    }
}
```

### [139. Word Break](https://leetcode.com/problems/word-break/)

没搞明白

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] valid = new boolean[s.length() + 1];
        valid[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i && !valid[i]; j++) {
                if (set.contains(s.substring(j, i)) && valid[j]) {
                    valid[i] = true;
                }
            }
        }

        return valid[s.length()];
    }
}
```

## day 39

### [198. House Robber](https://leetcode.com/problems/house-robber/)

比上面的背包简单多了，搞清楚递推公式就好弄

然后注意一下一下开始的 if 判断，解决越界问题

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int dp[] = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }
}
```

### [213. House Robber II](https://leetcode.com/problems/house-robber-ii/)

环的打劫

可以进一步简化，把dp数组简化成两个变量

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        // Case 1: Rob from index 0 to nums.length - 2
        int case1 = robRange(nums, 0, nums.length - 2);
        // Case 2: Rob from index 1 to nums.length - 1
        int case2 = robRange(nums, 1, nums.length - 1);

        return Math.max(case1, case2);
    }

    private int robRange(int[] nums, int start, int end) {
        int n = end - start + 1; // 房屋的数量
        int[] dp = new int[n]; // dp 数组，dp[i] 表示抢劫前 i 个房屋能得到的最大金额

        // 初始化 dp 数组
        dp[0] = nums[start]; // 抢第一个房屋
        if (n > 1) {
            dp[1] = Math.max(nums[start], nums[start + 1]); // 比较第一个和第二个房屋的最大金额
        }

        // 动态规划填充 dp 数组
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[start + i], dp[i - 1]);
        }

        return dp[n - 1]; // 返回最后一个房屋的最大金额
    }

}
```

### [337. House Robber III](https://leetcode.com/problems/house-robber-iii/)

树的打劫，没想明白

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public int rob(TreeNode root) {
        int[] res = robAction1(root);
        return Math.max(res[0], res[1]);
    }

    int[] robAction1(TreeNode root) {
        int res[] = new int[2];
        if (root == null)
            return res;

        int[] left = robAction1(root.left);
        int[] right = robAction1(root.right);

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }
}
```











## day 48

### [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)

搞清楚这几种情况：

- 当前遍历的元素T[i]小于栈顶元素T[st.top()]的情况
  当前元素入栈
- 当前遍历的元素T[i]等于栈顶元素T[st.top()]的情况
  当前元素入栈
- 当前遍历的元素T[i]大于栈顶元素T[st.top()]的情况
  栈顶元素出栈，当前元素入栈，同时记录res数组

res数组，并不是从小到大更新的，而是根据单调栈里面的元素下标，通过计算更新哪个res元素

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int res[] = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                // 下面👇这样写是不对的：stack.pop() 每次调用都会移除并返回栈顶元素。
                // res[stack.pop()] = i - stack.pop();
                int index = stack.pop(); // 弹出栈顶元素
                res[index] = i - index; // 计算结果
            }
            stack.push(i);
        }
        return res;
    }
}
```

### [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/)

这题相比于上题，就是加了一个hashmap的映射

```java
        // Map nums1 elements to their indices for quick lookup
        for (int i = 0; i < nums1.length; i++) {
            hashMap.put(nums1[i], i);
        }
```

后面的映射：

```java
                if (hashMap.containsKey(nums2[index])) {
                    res[hashMap.get(nums2[index])] = nums2[i]; // 这里是哈希表的值存到res数组里
                }
```

一个元素值存在在nums1中，同时也存在在nums2

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> temp = new ArrayDeque<>();
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        
        // Map nums1 elements to their indices for quick lookup
        for (int i = 0; i < nums1.length; i++) {
            hashMap.put(nums1[i], i);
        }

        // Iterate over nums2 and use deque to find the next greater element
        for (int i = 0; i < nums2.length; i++) {
            while (!temp.isEmpty() && nums2[temp.peek()] < nums2[i]) {
                int index = temp.pop();
                if (hashMap.containsKey(nums2[index])) {
                    res[hashMap.get(nums2[index])] = nums2[i];
                }
            }
            temp.push(i); // Push current index to deque
        }

        return res;
    }
}
```

### [503. Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/)

就是把上面的线性改成了环形，别的没变，i 要走两遍。

判断清楚 res[x] = y 中的，x和y究竟是什么，这是很关键的

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int res[] = new int[nums.length];
        Arrays.fill(res, -1); // 默认全部初始化为-1
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < 2 * nums.length; i++) {
            while (!stack.isEmpty() && nums[i % nums.length] > nums[stack.peek() % nums.length]) {
                int index = stack.pop();
                res[index % nums.length] = nums[i % nums.length];
            }
            stack.push(i);
        }

        return res;
    }
}
```

## day 49

### [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

按照列计算

暴力法O(n^2)的时间复杂度，改进之后，用两个数组保存当前位置**（包含自身）**左右的最大高度，这样是多个for循环并列，不用嵌套，O(n)的时间复杂度

这题可以用单调栈来解，二刷的时候改进吧

```java
class Solution {
    public int trap(int[] height) {
        int res = 0;
        int length = height.length;
        if (length < 3) return 0;
        int maxLeft[] = new int[length];
        int maxRight[] = new int[length];

        // 用数组记录当前位置 左边 的最大高度（包含自己）
        maxLeft[0] = height[0];
        for (int i = 1; i < length; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }

        // 用数组记录当前位置 右边 的最大高度（包含自己）
        maxRight[length - 1] = height[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }

        for (int i = 1; i < length - 1; i++) {
            if (height[i] < maxLeft[i] || height[i] < maxRight[i]) {
                res += Math.min(maxLeft[i], maxRight[i]) - height[i];
            }
        }

        return res;
    }
}
```

### [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)

双指针做的，没搞明白

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int size = heights.length;
        int[] minLeftIndex = new int[size];
        int[] minRightIndex = new int[size];
        // 记录每个柱子左边第一个小于该柱子的下标
        minLeftIndex[0] = -1;
        for (int i = 1; i < size; i++) {
            int t = i - 1;
            while (t >= 0 && heights[t] >= heights[i]) {
                t = minLeftIndex[t];
            }
            minLeftIndex[i] = t;
        }

        // 记录每个柱子右边第一个小于该柱子的下标
        minRightIndex[size - 1] = size;
        for (int i = size - 2; i >= 0; i--) {
            int t = i + 1;
            while (t < size && heights[t] >= heights[i]) {
                t = minRightIndex[t];
            }
            minRightIndex[i] = t;
        }

        // 计算每个柱子可以扩展的最大面积
        int res = 0;
        for (int i = 0; i < size; i++) {
            int width = minRightIndex[i] - minLeftIndex[i] - 1;
            int area = heights[i] * width;
            res = Math.max(res, area);
        }

        return res;
    }
}
```

## day 50

开始图论

dfs 关键的地方就两点：

- 搜索方向，是认准一个方向搜，直到碰壁之后再换方向
- 换方向是撤销原路径，改为节点链接的下一个路径，回溯的过程。

bfs 广搜，广搜的搜索方式就适合于解决两个点之间的最短路径问题。

### [797. All Paths From Source to Target](https://leetcode.com/problems/all-paths-from-source-to-target/)

```java
public class Solution {
    // 纯模版题
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        path.add(0); // 初始路径从节点 0 开始
        dfs(graph, 0, graph.length - 1); // 从节点 0 开始搜索到目标节点
        return result;
    }

    private void dfs(int[][] graph, int current, int target) {
        if (current == target) { // 当前节点是目标节点
            result.add(new ArrayList<>(path)); // 保存路径
            return;
        }

        for (int next : graph[current]) { // 遍历当前节点连接的所有节点
            path.add(next); // 加入路径
            dfs(graph, next, target); // 递归访问下一个节点
            path.remove(path.size() - 1); // 回溯
        }
    }
}
```
acm模式
```java
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Main {
    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        
        // 鄰接矩陣
        int[][] graph = new int[n + 1][n + 1];
        
        for (int i = 0; i < m; i++) {
            int s = scanner.nextInt();  // source, 邊的起點
            int t = scanner.nextInt();  // target, 邊的終點
            // 1 表示 s 與 t 是相連的
            graph[s][t] = 1;
        }
        
        path.add(1);
        dfs(graph, 1, n);
        
        // output
        if (res.isEmpty()) System.out.println(-1);
        
        for (List<Integer> pa : res) {
            for (int i = 0; i < pa.size() - 1; i++) {
                System.out.print(pa.get(i) + " ");
            }
            // System.out.println("");
            System.out.println(pa.get(pa.size() - 1));
        }
    }
    
    public static void dfs(int[][] graph, int curr, int target) {
        if (curr == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = 1; i <= target; i++) {
            if (graph[curr][i] == 1) {
                path.add(i);
                dfs(graph, i, target);
                path.remove(path.size() - 1);
            }
        }
    }
}
```

## day 51

### [200. Number of Islands](https://leetcode.com/problems/number-of-islands/)

### DFS

版本1的方法：下一个节点是否能合法已经判断完了，传进dfs函数的就是合法节点。

```java
class Solution {
    // 定义方向数组：右、下、上、左
    public static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n]; // 记录是否访问过
        int count = 0; // 记录岛屿数量
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // !visited[i][j]说明没有遍历过，gird[i][j] == '1' 说明是陆地。&&说明是没有遍历过的陆地
                if (!visited[i][j] && grid[i][j] == '1') {
                    // 遇到新的岛屿，进行DFS
                    visited[i][j] = true;
                    count++; // 发现一个新的岛屿
                    dfs(grid, visited, i, j); // 深度优先搜索，将整个岛屿标记为已访问
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, boolean[][] visited, int x, int y) {
        for (int i = 0; i < 4; i++) {
            // 用来遍历方向，dir[][]是用来表示方向的数组
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            
            // 检查边界条件
            if (nextY < 0 || nextX < 0 || nextX >= grid.length || nextY >= grid[0].length)
                continue;
            
            // 检查是否是未访问的陆地
            // visited[][]是上面定义的二维boolean数组，用来记录是否访问过
            // 这个if语句和上面判断是否访问，是一样的
            if (!visited[nextX][nextY] && grid[nextX][nextY] == '1') {
                visited[nextX][nextY] = true; // 标记访问
                dfs(grid, visited, nextX, nextY); // 深度优先搜索
            }
        }
    }
}
```

版本二的方法：不管节点是否合法，上来就dfs，然后在终止条件的地方进行判断，不合法再return。

这个方法更熟悉一点，这就是模版写法：

```java
void dfs(){
	if(){}
	for(){}
}
```

```java
class Solution {
    // 定义四个方向：右、下、上、左
    public static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    
    // 深度优先搜索函数
    public void dfs(char[][] grid, boolean[][] visited, int x, int y) {
        // 如果超出边界，或已经访问过，或当前位置是水，则返回
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y] || grid[x][y] == '0') {
            return;
        }
        
        // 标记当前位置为已访问
        visited[x][y] = true;

        // 遍历四个方向
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            dfs(grid, visited, nextX, nextY); // 递归搜索相邻的陆地
        }
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n]; // 记录是否访问过
        int count = 0;  // 记录岛屿数量

        // 遍历整个网格
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前位置是未访问过的陆地，则启动 DFS，找到一个新的岛屿
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    dfs(grid, visited, i, j); // 调用 DFS 标记整个岛屿
                }
            }
        }

        return count; // 返回岛屿数量
    }
}
```

### BFS

```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int numsIsland = 0;  // 记录岛屿数量

        // 遍历每个格子，启动 BFS 搜索
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] == '1') { // 如果是陆地且未被访问
                    numsIsland++;
                    bfs(grid, i, j);
                }
            }
        }
        return numsIsland;
    }
    
    // BFS 函数，用于遍历整个岛屿
    public void bfs(char[][] grid, int x, int y) {
        // 定义四个方向：右、左、上、下
        int[] directionX = new int[]{0, 0, -1, 1};
        int[] directionY = new int[]{1, -1, 0, 0};
        
        // 创建队列进行广度优先搜索
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        grid[x][y] = '0'; // 将当前格子标记为水，防止重复访问

        // 当队列不为空时，继续搜索
        while (!queue.isEmpty()) {
            int[] current = queue.poll(); // 取出队列的头部节点
            int curX = current[0];
            int curY = current[1];
            
            // 遍历四个方向
            for (int i = 0; i < 4; ++i) {
                int newX = curX + directionX[i];
                int newY = curY + directionY[i];
                
                // 判断新坐标是否在边界内，且是否是陆地
                if (newX >= 0 && newY >= 0 && newX < grid.length && newY < grid[0].length && grid[newX][newY] == '1') {
                    queue.offer(new int[]{newX, newY}); // 将新的陆地格子加入队列
                    grid[newX][newY] = '0'; // 标记为水，防止重复访问
                }
            }
        }
    }
}
```

### [695. Max Area of Island](https://leetcode.com/problems/max-area-of-island/)

和上一题差不多，学习了下dfs的写法。用一个变量count，通过++和置零来记录最大面积

```java
class Solution {
    // 定义方向数组：右、下、上、左
    public static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int count; // 当前岛屿的面积

    // 深度优先搜索函数
    public void dfs(int[][] grid, boolean[][] visited, int x, int y) {
        // 如果越界、访问过或者是水（0），直接返回
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y] || grid[x][y] == 0) {
            return;
        }

        // 标记当前节点为已访问
        visited[x][y] = true;
        count++; // 增加当前岛屿的面积

        // 遍历四个方向
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            dfs(grid, visited, nextX, nextY); // 深度优先搜索
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n]; // 记录访问状态
        int maxArea = 0; // 记录最大岛屿面积

        // 遍历整个网格
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前位置是陆地且未访问，启动 DFS
                if (!visited[i][j] && grid[i][j] == 1) {
                    count = 0; // 初始化当前岛屿的面积为 0
                    dfs(grid, visited, i, j); // 搜索整个岛屿
                    maxArea = Math.max(maxArea, count); // 更新最大面积
                }
            }
        }
        return maxArea; // 返回最大岛屿面积
    }
}
```

## day 52

### [1020. Number of Enclaves](https://leetcode.com/problems/number-of-enclaves/)

dfs实现的，要说的都在注释里了

```java
public class Solution {
    private static final int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 四个方向
    private int count; // 当前孤岛面积

    // dfs() 是在遍历到一个点之后，上下左右搜索这篇土地的过程
    // 这里的dfs()做了两件事：1. 置零，防止重复计算；2. count++。对应main函数的1和2
    private void dfs(int[][] grid, int x, int y) {
        grid[x][y] = 0; // 1
        count++;    // 2
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length)
                continue;
            if (grid[nextX][nextY] == 0)
                continue;

            dfs(grid, nextX, nextY);
        }
    }

    // 1. 先处理边缘上的陆地，将其标记为水域
    // 先处理的过程中，dfs()中的count其实是无效的，后面会被覆盖
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) {
                dfs(grid, i, 0);
            }
            if (grid[i][m - 1] == 1) {
                dfs(grid, i, m - 1);
            }
        }

        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) {
                dfs(grid, 0, j);
            }
            if (grid[n - 1][j] == 1) {
                dfs(grid, n - 1, j);
            }
        }

        // 2. 遍历剩余陆地，统计孤岛面积
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    count = 0;
                    dfs(grid, i, j);
                    res += count;
                }
            }
        }

        return res;
    }
}
```

###  102. 沉没孤岛

这题没有leetcode对应

跟上一题思路类似，这题先把四周的岛屿（1）置2；接着把所有1置0；最后所有2置1

https://www.programmercarl.com/kamacoder/0102.%E6%B2%89%E6%B2%A1%E5%AD%A4%E5%B2%9B.html#_102-%E6%B2%89%E6%B2%A1%E5%AD%A4%E5%B2%9B

链接讲解里的图很清晰

```java
import java.util.Scanner;

public class Main {
    static int[][] dir = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} }; // 保存四个方向

    public static void dfs(int[][] grid, int x, int y) {
        grid[x][y] = 2;
        for (int[] d : dir) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            // 超过边界
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) continue;
            // 不符合条件，不继续遍历
            if (grid[nextX][nextY] == 0 || grid[nextX][nextY] == 2) continue;
            dfs(grid, nextX, nextY);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 步骤一：
        // 从左侧边，和右侧边 向中间遍历
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][m - 1] == 1) dfs(grid, i, m - 1);
        }

        // 从上边和下边 向中间遍历
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) dfs(grid, 0, j);
            if (grid[n - 1][j] == 1) dfs(grid, n - 1, j);
        }

        // 步骤二、步骤三
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) grid[i][j] = 0;
                if (grid[i][j] == 2) grid[i][j] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        
        scanner.close();
    }
}
```

### [417. Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)

这题要逆流而上，创两个表，两个表中都能遍历到的点，就是符合要求的点

```java
class Solution {
    private int m, n;
    private int[][] heights;
    private boolean[][] pacific;
    private boolean[][] atlantic;
    private static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        m = heights.length;
        n = heights[0].length;

        pacific = new boolean[m][n];
        atlantic = new boolean[m][n];

        // 分别从 pacific, atlantic 逆流而上
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacific); // 从 pacific 左往右
            dfs(i, n - 1, atlantic);    // 从 atlantic 右往左
        }
        for (int j = 0; j < n; j++) {
            dfs(0, j, pacific); // 从 pacific 上往下
            dfs(m - 1, j, atlantic);    // 从 atlantic 下往上
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        for (int[] d : dirs) {
            int nextX = x + d[0];
            int nextY = y + d[1];

            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                continue;
            }

            if (!visited[nextX][nextY] && heights[nextX][nextY] >= heights[x][y]) {
                dfs(nextX, nextY, visited);
            }
        }
    }
}
```

### [827. Making A Large Island](https://leetcode.com/problems/making-a-large-island/)

优化思路可以学，代码太长了，没看

## day 53

### [127. Word Ladder](https://leetcode.com/problems/word-ladder/)

bfs方法，由于本人前面只学了dfs，这题理解思路就好

```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }

        HashSet<String> set = new HashSet<>(wordList);

        Map<String, Integer> visitMap = new HashMap<>();
        visitMap.put(beginWord, 1);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            String curWord = queue.poll();
            int path = visitMap.get(curWord);

            for (int i = 0; i < curWord.length(); i++) {
                char[] ch = curWord.toCharArray();

                for (char k = 'a'; k <= 'z'; k++) {
                    ch[i] = k;
                    String newWord = new String(ch);

                    if (newWord.equals(endWord)) {
                        return path + 1;
                    }

                    if (set.contains(newWord) && !visitMap.containsKey(newWord)) {
                        visitMap.put(newWord, path + 1);
                        queue.offer(newWord);
                    }
                }
            }
        }

        return 0;
    }
}
```

### 105.有向图的完全可达性

做了leetcode133 clone graph, 但是没有弄明白，和 有向图可达性 关联也不大

这题没有leetcode对应

提醒：dfs，深搜，本质是递归。递归就有三部曲

```java
import java.util.*;

public class Main {
    public static List<List<Integer>> adjList = new ArrayList<>();

    public static void dfs(boolean[] visited, int key) {
        if (visited[key]) {
            return;
        }
        visited[key] = true;
        List<Integer> nextKeys = adjList.get(key);
        for (int nextKey : nextKeys) {
            dfs(visited, nextKey);
        }
    }

    public static void bfs(boolean[] visited, int key) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(key);
        visited[key] = true;
        while (!queue.isEmpty()) {
            int curKey = queue.poll();
            List<Integer> list = adjList.get(curKey);
            for (int nextKey : list) {
                if (!visited[nextKey]) {
                    queue.add(nextKey);
                    visited[nextKey] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices_num = sc.nextInt();
        int line_num = sc.nextInt();
        for (int i = 0; i < vertices_num; i++) {
            adjList.add(new LinkedList<>());
        }//Initialization
        for (int i = 0; i < line_num; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            adjList.get(s - 1).add(t - 1);
        }//构造邻接表
        boolean[] visited = new boolean[vertices_num];
        dfs(visited, 0);
//        bfs(visited, 0);

        for (int i = 0; i < vertices_num; i++) {
            if (!visited[i]) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(1);
    }
}
```

### [463. Island Perimeter](https://leetcode.com/problems/island-perimeter/)

这题用不到dfs, bfs。目的是避免大家惯性思维

公式自己没推导出来：

```java
return sum * 4 - cover * 2
```

```java
class Solution {
    public int islandPerimeter(int[][] grid) {
       int n = grid.length;
       int m = grid[0].length;
       int sum = 0;
       int cover = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    sum++;
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) cover++;
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) cover++;
                }
            }
        }

        return sum * 4 - cover * 2;
    }
}
```

## day 54

休息

## day 55

### 并查集理论基础

并查集主要实现了三个功能：

1. 寻找根节点，函数：find(int u)，也就是判断这个节点的祖先节点是哪个
2. 将两个节点接入到同一个集合，函数：join(int u, int v)，将两个节点连在同一个根节点上
3. 判断两个节点是否在同一个集合，函数：isSame(int u, int v)，就是判断两个节点是不是同一个根节点

### [1971. Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph/)

```java
class Solution {

    private int[] parent;   // 并查集数组

    // 初始化并查集
    private void init(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    // 查找根节点，路径压缩
    private int find(int u) {
        if (u != parent[u]) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    // 合并两个节点
    private void join(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU != rootV) {
            parent[rootV] = rootU;  // 将 v 的根连接到 u 的根
        }
    }

    // 判断两个节点是否属于同一个集合
    private boolean isSame(int u, int v) {
        return find(u) == find(v);
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        init(n);

        for (int[] edge : edges) {
            join(edge[0], edge[1]);
        }

        return isSame(source, destination);
    }
}
```



## day 56

### [684. Redundant Connection](https://leetcode.com/problems/redundant-connection/)

关键是如何把题意转化成并查集问题

```java
class Solution {

    private int[] parent;

    private void init(int n) {
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    private int find(int u) {
        if (u != parent[u]) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    // 判断 u 和 v 是否在同一个集合中
    private boolean isSame(int u, int v) {
        return find(u) == find(v);
    }

    private void join(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU != rootV) {
            parent[rootV] = rootU;
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        init(n); // 初始化并查集
        int[] lastRedundantEdge = null; // 用于存储最后一条冗余边

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (isSame(u, v)) {
                lastRedundantEdge = edge; // 更新最后一条冗余边
            } else {
                join(u, v);
            }
        }

        return lastRedundantEdge; // 返回最后一条冗余边
    }
}
```

### [685. Redundant Connection II](https://leetcode.com/problems/redundant-connection-ii/)

变成有向图了，将近100行代码，二刷再看


## day 57
prim算法核心就是三步，我称为prim三部曲，大家一定要熟悉这三步，代码相对会好些很多：

第一步，选距离生成树最近节点
第二步，最近节点加入生成树
第三步，更新非生成树节点到生成树的距离（即更新minDist数组）

