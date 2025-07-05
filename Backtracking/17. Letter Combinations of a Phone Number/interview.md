# Java中null與空集合的區別

## 核心區別

```java
List<String> nullList = null;           // null引用
List<String> emptyList = new ArrayList<>();  // 空集合

System.out.println(nullList);    // 輸出: null
System.out.println(emptyList);   // 輸出: []
```

## 關鍵差異

| 比較項目 | null | 空集合 |
|---------|------|--------|
| 值 | `null` | `[]` |
| 調用方法 | 拋出 `NullPointerException` | 正常執行 |
| 判斷 | `== null` 為 `true` | `== null` 為 `false` |

## 實際問題

### 錯誤寫法
```java
List<String> res = new ArrayList<>();

if (digits.length() == 0) 
    return null;  // ❌ 返回null
```

### 正確寫法
```java
List<String> res = new ArrayList<>();

if (digits.length() == 0) 
    return res;   // ✅ 返回空集合[]
```

## 黃金法則

**集合類型的方法應該返回空集合，而不是null**

這樣調用者就不需要檢查null，避免 `NullPointerException`。