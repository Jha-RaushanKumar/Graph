/*
Alien Dictionary
https://practice.geeksforgeeks.org/problems/alien-dictionary/1

Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary. Find the order of characters in the alien language.
Note: Many orders may be possible for a particular test case, thus you may return any valid order and output will be 1 if the order of string returned by the function is correct else 0 denoting incorrect string returned.
 
Example 1:
Input: N = 5, K = 4 dict = {"baa","abcd","abca","cab","cad"}
Output: 1
Explanation:
Here order of characters is 'b', 'd', 'a', 'c' Note that words are sorted and in the given language "baa" comes before "abcd", therefore 'b' is before 'a' in output.Similarly we can find other orders.

Example 2:
Input: N = 3, K = 3 dict = {"caa","aaa","aab"}
Output: 1
Explanation:
Here order of characters is'c', 'a', 'b' Note that words are sorted and in the given language "caa" comes before "aaa", therefore 'c' is before 'a' in output. Similarly we can find other orders.
 
Your Task:
You don't need to read or print anything. Your task is to complete the function findOrder() which takes  the string array dict[], its size N and the integer K as input parameter and returns a string denoting the order of characters in the alien language.

Expected Time Complexity: O(N * |S| + K) , where |S| denotes maximum length.
Expected Space Compelxity: O(K)

Constraints:
1 ≤ N, M ≤ 300
1 ≤ K ≤ 26
1 ≤ Length of words ≤ 50
*/

class Solution
{
    public String findOrder(String [] dict, int N, int K)
    {
        // Write your code here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < K; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < dict.length - 1; i++){
            String str1 = dict[i];
            String str2 = dict[i + 1];
            
            int len = Math.min(str1.length(), str2.length());
            for(int j = 0; j < len;j++){
                if(str1.charAt(j) != str2.charAt(j)){
                    adj.get(str1.charAt(j) - 'a').add(str2.charAt(j) - 'a');
                    break;
                }
            }
        }
        
        int topo[] = topoSort(K, adj);
        String ans = "";
        
        for(int it : topo){
            ans += (char)(it + 'a');
        }
        return ans;
    }
    
    public int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj){
        
        int indegree[] = new int[V];
        for(int i = 0; i < V; i++){
            for(int it : adj.get(i)){
                indegree[it]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < V; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        
        int topo[] = new int[V];
        int i = 0;
        while(!q.isEmpty()){
            int node = q.remove();
            topo[i++] = node;
            
            for(int it : adj.get(node)){
                indegree[it]--;
                if(indegree[it] == 0){
                    q.add(it);
                }
            }
        }
        return topo;
    }
}

/*
TC: O(N) + O(2E) + O(dict.length) [N is total nodes(V), 2E is for total degrees as we traverse all adjacent nodes]
SC: O(N) [For Queue, ans and indegree array]
*/