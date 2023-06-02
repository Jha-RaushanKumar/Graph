/*
https://practice.geeksforgeeks.org/problems/word-ladder-ii/1
Word Ladder II

Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. Find all shortest transformation sequence(s) from startWord to targetWord. You can return them in any order possible. Keep the following conditions in mind:
A word can only consist of lowercase characters.
Only one letter can be changed in each transformation.
Each transformed word must exist in the wordList including the targetWord.
startWord may or may not be part of the wordList.
Return an empty list if there is no such transformation sequence.
The first part of this problem can be found here.

Example 1:
Input:
startWord = "der", targetWord = "dfs",
wordList = {"des","der","dfr","dgt","dfs"}
Output:
der dfr dfs
der des dfs
Explanation: The length of the smallest transformation is 3. And the following are the only two ways to get to targetWord:-
"der" -> "des" -> "dfs".
"der" -> "dfr" -> "dfs".

Example 2:
Input:
startWord = "gedk", targetWord = "geek", 
wordList = {"geek", "gefk"}
Output:
"gedk" -> "geek"

Your Task:
You don't need to read or print anything, Your task is to complete the function findSequences() which takes startWord, targetWord and wordList as input parameter and returns a list of list of strings of the shortest transformation sequence from startWord to targetWord.
Note: You don't have to return -1 in case of no possible sequence. Just return the Empty List.

Expected Time Compelxity: O(N*(logN * M * 26))
Expected Auxiliary Space: O(N * M) where N = length of wordList and M = |wordListi|
Constraints:
1 ≤ N ≤ 100
1 ≤ M ≤ 10
*/

class Solution
{
    public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList)
    {
        // Code here
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        
        Set<String> set = new HashSet<>();
        for(String word : wordList){
            set.add(word);
        }
        
        Queue<ArrayList<String>> q = new LinkedList<>();
        ArrayList<String> list = new ArrayList<>();
        list.add(startWord);
        q.add(list);
        int level = 1;
        ArrayList<String> usedOnLevel = new ArrayList<>();
        
        
        while(!q.isEmpty()){
            ArrayList<String> ls = q.peek();
            q.remove();
            if(ls.size() > level){
                level++;
                for(String str : usedOnLevel){
                    set.remove(str);
                }
            }
            
            String word = ls.get(ls.size() - 1);
            if(word.equals(targetWord)){
                if(ans.size() == 0){
                    ans.add(ls);
                }
                else{
                    if(ans.get(0).size() == ls.size()){
                        ans.add(ls);
                    }
                }
            }
            
            for(int i = 0; i < word.length(); i++){
                for(char ch = 'a'; ch <= 'z'; ch++){
                    char arr[] = word.toCharArray();
                    arr[i] = ch;
                    String newString = String.valueOf(arr);

                    if(set.contains(newString)){
                        ArrayList<String> temp = new ArrayList<>(ls);
                        temp.add(newString);
                        q.add(temp);
                        usedOnLevel.add(newString);
                    }
                }
            }
        }
        return ans;
    }
}

/*
TC: O(N*word.length*26) [N*M for dfs done 4 directionally]
SC: O(N) [For lists and queue]
*/