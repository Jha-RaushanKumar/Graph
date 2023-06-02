/*
127. Word Ladder
https://leetcode.com/problems/word-ladder/

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 
Constraints:
1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        Set<String> set = new HashSet<>();
        for(String word : wordList){
            set.add(word);
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));
        set.remove(beginWord);

        while(!q.isEmpty()){
            String word = q.peek().first;
            int level = q.peek().second;
            q.remove();
            if(word.equals(endWord)){
                return level;
            }

            for(int i = 0; i < word.length(); i++){
                for(char ch = 'a'; ch <= 'z'; ch++){
                    char arr[] = word.toCharArray();
                    arr[i] = ch;
                    String newString = String.valueOf(arr);

                    if(set.contains(newString)){
                        q.add(new Pair(newString, level + 1));
                        set.remove(newString);
                    }
                }
            }
        }
        return 0;
    }

    class Pair{
        String first;
        int second;

        public Pair(String _first, int _second){
            this.first = _first;
            this.second = _second;
        }
    }
}

/*
TC: O(N*word.length*26) [N*M for dfs done 4 directionally]
SC: O(N) [For Stack space and Visited array]
*/