# Happy Number - Interview Script

"Let me walk you through my solution for this problem:

First, let's understand what a happy number is. A number is happy if we can reach 1 by repeatedly replacing the number with the sum of the squares of its digits. For example, 19 is a happy number because:
1. 1² + 9² = 82
2. 8² + 2² = 68
3. 6² + 8² = 100
4. 1² + 0² + 0² = 1

The key insight here is that if a number is not happy, it will eventually enter a cycle. For example, 4 is not happy because:
1. 4² = 16
2. 1² + 6² = 37
3. 3² + 7² = 58
4. 5² + 8² = 89
5. 8² + 9² = 145
6. 1² + 4² + 5² = 42
7. 4² + 2² = 20
8. 2² + 0² = 4 (we're back to 4, entering a cycle)

So our solution needs to:
1. Keep track of numbers we've seen to detect cycles
2. Calculate the next number by summing the squares of digits
3. Return true if we reach 1, false if we detect a cycle

Let me explain the code line by line:

```java
public boolean isHappy(int n) {
    Set<Integer> set = new HashSet<>();
```
We use a HashSet to store numbers we've seen. This helps us detect cycles.

```java
    while (!set.contains(n)) {
```
We keep going as long as we haven't seen this number before.

```java
        set.add(n);
        n = getNext(n);
```
First, we add the current number to our set, then calculate the next number. This order is important because:
- If we see the same number again, it means we're in a cycle
- We need to add the number before transforming it

```java
        if (n == 1) {
            return true;
        }
    }
    return false;
```
If we reach 1, the number is happy. If we detect a cycle (by seeing a number we've seen before), it's not happy.

```java
private int getNext(int n) {
    int sum = 0;
    while (n > 0) {
        sum += (n % 10) * (n % 10);
        n /= 10;
    }
    return sum;
}
```
This helper function:
1. Takes a number and returns the sum of squares of its digits
2. Uses modulo (%) to get the last digit
3. Uses division (/) to remove the last digit
4. Continues until we've processed all digits

Time Complexity: O(log n)
- The number of digits in a number is log n
- Each digit is processed once
- The cycle detection is also O(log n) as we'll see at most log n numbers

Space Complexity: O(log n)
- We store at most log n numbers in our set

Edge cases to consider:
- Single digit numbers
- Numbers that quickly reach 1
- Numbers that enter long cycles
- Very large numbers"
