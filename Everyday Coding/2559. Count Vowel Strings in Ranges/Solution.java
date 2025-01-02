class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int leng = words.length;
        boolean val[] = new boolean[leng];
        for (int i = 0; i < leng; i++) {
            val[i] = valid(words[i]);
        }

        int res[] = new int[queries.length];
        int preSum[] = new int[leng];

        preSum[0] = val[0] ? 1 : 0;
        // System.out.println(preSum[0]);

        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + (val[i] ? 1 : 0);
            // System.out.println(preSum[i]);
        }

        for (int i = 0; i < res.length; i++) {
            if (queries[i][0] == 0) {
                res[i] = preSum[queries[i][1]];
            } else {
                res[i] = preSum[queries[i][1]] - preSum[queries[i][0] - 1];
            }
        }

        return res;
    }

    private boolean valid(String word) {
        boolean start = false;
        boolean end = false;
        if (word.charAt(0) == 'a' ||
                word.charAt(0) == 'e' ||
                word.charAt(0) == 'i' ||
                word.charAt(0) == 'o' ||
                word.charAt(0) == 'u') {
            start = true;
        }

        if (word.charAt(word.length() - 1) == 'a' ||
                word.charAt(word.length() - 1) == 'e' ||
                word.charAt(word.length() - 1) == 'i' ||
                word.charAt(word.length() - 1) == 'o' ||
                word.charAt(word.length() - 1) == 'u') {
            end = true;
        }

        return start && end;
    }
}