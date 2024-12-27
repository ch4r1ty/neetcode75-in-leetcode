public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs.isEmpty())
            return "";

        StringBuilder res = new StringBuilder();
        // 定义一个动态数组
        List<Integer> sizes = new ArrayList<>();
        // 动态数组 存放的是 list 中，每个单词的长度
        for (String str : strs) {
            sizes.add(str.length());
        }
        // System.out.println(sizes.toString());

        for (int size : sizes) {
            res.append(size).append(','); // int + ','
        }
        // System.out.println(res.toString());

        res.append('#'); // 分隔符
        // System.out.println(res.toString());

        for (String str : strs) {
            res.append(str); // 拼接所有字符串
        }
        System.out.println(res.toString());
        return res.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String str) {
        if (str.length() == 0) {
            System.out.println("Input string is empty. Returning empty list.");
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        int i = 0;

        // Step 1: Parse sizes from the encoded string
        System.out.println("Parsing sizes...");
        while (str.charAt(i) != '#') {
            StringBuilder cur = new StringBuilder();
            while (str.charAt(i) != ',') {
                cur.append(str.charAt(i));
                i++;
            }
            int size = Integer.parseInt(cur.toString());
            sizes.add(size);
            System.out.println("Found size: " + size);
            i++; // Skip the comma
        }
        System.out.println("Sizes parsed: " + sizes);
        i++; // Skip the '#'

        // Step 2: Extract strings using sizes
        // System.out.println("Extracting strings...");
        for (int sz : sizes) {
            // String substring(int beginIndex, int endIndex)
            String extracted = str.substring(i, i + sz);
            res.add(extracted);
            System.out.println("Extracted string: " + extracted);
            i += sz;
        }
        System.out.println("Decoded strings: " + res);
        return res;
    }

}

//Input
//dummy_input =
//["Hello","World"]
//Stdout
//5,5,#HelloWorld
//Parsing sizes...
//Found size: 5
//Found size: 5
//Sizes parsed: [5, 5]
//Extracted string: Hello
//Extracted string: World
//Decoded strings: [Hello, World]
//Output
//["Hello","World"]