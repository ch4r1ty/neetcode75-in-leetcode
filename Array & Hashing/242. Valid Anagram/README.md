这题用数组来实现哈希表会很简单：
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int hash[] = new int[26];
        int length = s.length();

        for (int i = 0; i < length; i++) {
            hash[s.charAt(i) - 'a']++;
            hash[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != 0) return false;
        }

        return true;
    }
}

```

主要是要体会下 HashMap 这种结构怎么用
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> hashS = new HashMap<>();
        HashMap<Character, Integer> hashT = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            hashS.put(s.charAt(i),
                hashS.getOrDefault(s.charAt(i), 0) + 1);
            hashT.put(t.charAt(i),
                hashT.getOrDefault(t.charAt(i), 0) + 1);
        }

        return hashS.equals(hashT);
    }
}

```

在 put 中，第一个参数是 key，第二个参数是 value；

在 getOrDefault 中，第一个参数是 key，第二个参数是默认值；
```md
HashMap<Character, Integer> hashS = new HashMap<>();

hashS.put(s.charAt(i), hashS.getOrDefault(s.charAt(i), 0) + 1);
```
