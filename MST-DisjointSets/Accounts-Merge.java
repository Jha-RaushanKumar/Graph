/*
721. Accounts Merge
https://leetcode.com/problems/accounts-merge/

Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

Example 2:
Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 
Constraints:
1 <= accounts.length <= 1000
2 <= accounts[i].length <= 10
1 <= accounts[i][j].length <= 30
accounts[i][0] consists of English letters.
accounts[i][j] (for j > 0) is a valid email.
*/

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
        int n = accounts.size();
        HashMap<String, Integer> hm = new HashMap<>();
        DisjointSet ds = new DisjointSet(n);
        for(int i = 0; i < n; i++){
            for(int j = 1; j < accounts.get(i).size(); j++){
                String mail = accounts.get(i).get(j);
                if(!hm.containsKey(mail)){
                    hm.put(mail, i);
                }
                else{
                    ds.unionByRank(i, hm.get(mail));
                }
            }
        }

        ArrayList<String> mergedMail[] = new ArrayList[n];
        for(int i = 0; i < n; i++){
            mergedMail[i] = new ArrayList<>();
        }
        for(Map.Entry<String, Integer> entry : hm.entrySet()){
            String mail = entry.getKey();
            int node = entry.getValue();
            int ulp_node = ds.findUParent(node);
            mergedMail[ulp_node].add(mail);
        }

        List<List<String>> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(mergedMail[i].size() == 0){
                continue;
            }
            Collections.sort(mergedMail[i]);
            List<String> ls = new ArrayList<>();
            ls.add(accounts.get(i).get(0));
            for(String str : mergedMail[i]){
                ls.add(str);
            }
            res.add(ls);
        }
        return res;
    }
}

class DisjointSet{
    ArrayList<Integer> parent;
    ArrayList<Integer> rank;
    ArrayList<Integer> size;
    
    public DisjointSet(int n){
        parent = new ArrayList<>();
        rank = new ArrayList<>();
        size = new ArrayList<>();
        
        for(int i = 0; i <= n; i++){
            parent.add(i);
            rank.add(0);
            size.add(1);
        }
    }
    
    public int findUParent(int node){
        if(node == parent.get(node)){
            return node;
        }
        int uParent = findUParent(parent.get(node));
        parent.set(node, uParent);
        return parent.get(node);
    }
    
    public void unionByRank(int u, int v){
        int uParent_u = findUParent(u);
        int uParent_v = findUParent(v);
        if(uParent_u == uParent_v){
            return;
        }
        if(rank.get(uParent_u) > rank.get(uParent_v)){
            parent.set(uParent_v, uParent_u);
        }
        else if(rank.get(uParent_u) < rank.get(uParent_v)){
            parent.set(uParent_u, uParent_v);
        }
        else{
            parent.set(uParent_v, uParent_u);
            int rankU = rank.get(uParent_u);
            rank.set(uParent_u, rankU + 1);
        }
    }
    
    public void unionBySize(int u, int v){
        int uParent_u = findUParent(u);
        int uParent_v = findUParent(v);
        if(uParent_u == uParent_v){
            return;
        }
        if(size.get(uParent_u) >= size.get(uParent_v)){
            parent.set(uParent_v, uParent_u);
            int sizeU = size.get(uParent_u);
            int sizeV = size.get(uParent_v);
            size.set(uParent_u, sizeU + sizeV);
        }
        else{
            parent.set(uParent_u, uParent_v);
            int sizeU = size.get(uParent_u);
            int sizeV = size.get(uParent_v);
            size.set(uParent_v, sizeU + sizeV);
        }
    }
}

/*
TC: O(N+E) + O(E logE) 
SC: O(N)
*/