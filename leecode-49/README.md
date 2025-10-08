# 49.字母异位词分组

## 解决方法：哈希表

## 思路一：排序后作为 key（最常用 ✅）

```
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 特判
        if (strs == null || strs.length == 0) return new ArrayList<>();

        // 哈希表：key 为排序后的字符串，value 为对应的单词列表
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // 将字符串转换为字符数组并排序
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // 将单词加入到对应分组
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        // 返回所有分组
        return new ArrayList<>(map.values());
    }
}

```

**利用哈希表根据排序后的字符串对字母异位词进行分组**。

### 1️⃣ 健壮性处理

首先要进行判空处理：
如果字符串数组为空或长度为 0，就直接返回一个空集合 `new ArrayList<>()`。
这属于代码健壮性（防止空指针异常）。

------

### 2️⃣ 使用哈希表存储分组关系

我们使用一个哈希表 `Map<String, List<String>> map`，
其中：

- **key**：是每个字符串排序后的结果；
- **value**：是一个 `List<String>`，存储与该 key 对应的所有字母异位词。

------

### 3️⃣ 遍历输入数组并构建映射关系

对于数组中的每个字符串 `s`：

1. 先将它转换为字符数组 `char[] chars = s.toCharArray()`；
2. 调用 `Arrays.sort(chars)` 对字符数组进行排序；
3. 再把排序后的字符数组转换回字符串 `String key = new String(chars)`；
4. 接着判断 map 中是否已经包含该 key：
    - 如果 **不包含**，说明这是第一次遇到这种字母组合 → `map.put(key, new ArrayList<>())`
    - 如果 **已包含**，则直接在对应的 value（也就是 list）中追加当前字符串；
5. 最后通过 `map.get(key).add(s)` 把当前字符串加入对应的组。

------

### 4️⃣ 构建返回结果

循环结束后，map 中的所有 `value` 就是题目要求的分组结果。
因为 `map.values()` 返回的是一个 `Collection<List<String>>`，
我们可以通过 `new ArrayList<>(map.values())` 来把它转成符合返回类型的 `List<List<String>>`。

------

### 5️⃣ 关于返回格式说明

在 Java 中：

- `List` 和 `Set` 是单列集合，打印时使用 `[]`
- `Map` 是双列集合，打印时使用 `{}`
  所以返回结果打印出来类似：

```
[[bat], [nat, tan], [ate, eat, tea]]
```

------

### ✅ 最终总结版思路

> **通过排序使字母异位词具有相同 key，用哈希表将相同 key 的字符串分组。**

首先代码健壮性判断，如果数组为空，那么直接返回一个空数组（也就是一个空集合），然后我们的思想就是将这些字母异位词排序，因为排序后的字符串都是一样的，所以我们就可以设置一个哈希表，key就是这些字母异位词排序后的字符串，value就来存储这些字母异位词，所以就是一个数组；首先初始化这个map集合，接着遍历题目要求的数组，先将每次遍历的字符串转成字符串数组后可以排序处理，然后将排序后的字符串数组转成字符串，然后接着判断该map集合中的key如果不包含这个key（反着判断代码更少更可观），那么就在这map中添加这个key，以及value是一个集合，判断之后就是存在这个key，所以我们通过map.get(key)得到这个key对应的value对象，在这个value对象的基础上add添加这个字母异位词，对于返回的结果有规定，List和Set（单列集合）都使用 []，Map（双列集合）使用 {}，最后循环结束，返回这些values

## 思路二：计数法作为 key（更高效，面试扩展）

```
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder keyBuilder = new StringBuilder();
            for (int num : count) {
                keyBuilder.append(num).append('#');
            }
            String key = keyBuilder.toString();

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}

```



## （计数法解法）

这道题由于字母异位词全部由 **小写字母** 组成，因此我们可以通过一个 **长度为 26 的计数数组** 来表示每个字符串中每个字母出现的次数，从而构造出每个字符串的唯一“特征值”（key）。

------

### 1️⃣ 思想核心

我们让 `'a'` 对应计数数组的 0 索引，`'b'` 对应 1 索引，以此类推。
这样每个字符串都能通过统计字母出现次数得到一组长度为 26 的计数信息。
由于字母异位词具有相同的字母组成和字母频率，因此它们的计数数组完全相同。
这样我们就不需要排序，直接根据计数数组来生成哈希表的 key。

------

### 2️⃣ 实现过程

1. **初始化哈希表**

   ```
   Map<String, List<String>> map = new HashMap<>();
   ```

2. **遍历输入字符串数组**
   对每个字符串 `s`：

    - 新建一个长度为 26 的整型数组 `int[] count = new int[26];`
    - 遍历该字符串的每个字符 `c`，通过 `count[c - 'a']++` 统计出现次数。
      这里利用 `'a'` 作为基准，`'c' - 'a'` 得到该字符在 26 个字母表中的索引。

3. **构造 key**
   为了防止数字直接拼接时出现歧义（例如 `[11, 1]` 和 `[1, 11]` 拼接后都是 `"111"`），
   我们使用 `StringBuilder` 来拼接，并在每个数字之间加上一个特殊分隔符（如 `#`）：

   ```
   StringBuilder sb = new StringBuilder();
   for (int num : count) {
       sb.append(num).append('#');
   }
   String key = sb.toString();
   ```

4. **存入哈希表**

   ```
   map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
   ```

   这行代码的含义是：

    - 如果 key 不存在，就新建一个空的列表；
    - 如果 key 已存在，则直接在该 key 对应的 value（List）中追加字符串；
    - 因此，这里已经**隐式实现了健壮性判断**，不需要再额外判断 key 是否存在。

5. **返回结果**
   遍历结束后，哈希表中所有的 `value` 就是字母异位词的分组结果：

   ```
   return new ArrayList<>(map.values());
   ```

------

### ✅ 总结

> 通过 26 个字母的计数数组生成唯一 key（加分隔符防止冲突），
> 使用 `map.computeIfAbsent` 自动完成 key 初始化与分组，
> 实现了无需排序、无需健壮性判断的高效字母异位词分组。

首先由于我们的字母异位词都是小写字母，我们就可以通过这些小写字母在26个字母的计数数组来作为key，然后每个字母对应这个计数数组的索引，a对应0索引，这样就不需要将字符串遍历后排序结果作为key，而是遍历时候通过计数在对应索引来实现字母异位词的相同key；首先初始化map集合，然后遍历题目的数组，接着需要初始化一个长度为26的字符数组来作为计数数组，然后将遍历到的每一个字符串转成字符数组遍历每一个字母，通过减去字符a能得到在一个长度为26的字符数组的对应索引并在其下面+1；接着我们需要通过计数数组设计key，但是拼接会可能出现相同key，所以我们需要new一个StringBuilder来使每个计数索引下的值之间通过任意一个特殊字符，然后转化为字符串作为key；通过map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);，表示如果key存在就执行key对应下的value值然后add，如果不存在，就new一个ArrayList，最后返回values，这个实现中不需要健壮性判断，因为就是map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);这里实现了