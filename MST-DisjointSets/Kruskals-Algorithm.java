/*
https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1
Minimum Spanning Tree

Given a weighted, undirected and connected graph of V vertices and E edges. The task is to find the sum of weights of the edges of the Minimum Spanning Tree.
 
Example 1:
Input:
3 3
0 1 5
1 2 3
0 2 1
Output:4
Explanation: The Spanning Tree resulting in a weight of 4 is shown above.

Example 2:
Input:
2 1
0 1 5
Output:5
Explanation: Only one Spanning Tree is possible which has a weight of 5.

Your task:
Since this is a functional problem you don't have to worry about input, you just have to complete the function  spanningTree() which takes number of vertices V and an adjacency matrix adj as input parameters and returns an integer denoting the sum of weights of the edges of the Minimum Spanning Tree. Here adj[i] contains a list of lists containing two integers where the first integer a[i][0] denotes that there is an edge between i and a[i][0][0] and second integer a[i][0][1] denotes that the distance between edge i and a[i][0][0] is a[i][0][1].
In other words , adj[i][j] is of form  { u , wt } . So,this denotes that i th node is connected to u th node with  edge weight equal to wt.

Expected Time Complexity: O(ElogV).
Expected Auxiliary Space: O(V2).
 
Constraints:
2 ≤ V ≤ 1000
V-1 ≤ E ≤ (V*(V-1))/2
1 ≤ w ≤ 1000
Graph is connected and doesn't contain self loops & multiple edges.
*/

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
            size.add(i);
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

class Edge implements Comparable<Edge>{
    int src;
    int dst;
    int ew;
    
    public Edge(int src, int dst, int ew){
        this. src = src;
        this. dst = dst;
        this. ew = ew;
    }
    
    public int compareTo(Edge comparableEdge){
        return this.ew - comparableEdge.ew;
    }
}

class Solution{
	static int spanningTree(int V, int E, int edges[][]){
	    // Code Here. 
	    
	    ArrayList<Edge> edgeList = new ArrayList<>();
	    for(int i = 0; i < edges.length; i++){
	        int u = edges[i][0];
	        int v = edges[i][1];
	        int w = edges[i][2];
	        
	        Edge edge1 = new Edge(u, v, w);
	        edgeList.add(edge1);
	        Edge edge2 = new Edge(v, u, w);
	        edgeList.add(edge2);
	    }
	    
	    Collections.sort(edgeList);
	    DisjointSet ds = new DisjointSet(V);
	    
	    int mst = 0;
	    for(int i = 0; i < edgeList.size(); i++){
	        int u = edgeList.get(i).src;
	        int v = edgeList.get(i).dst;
	        int wt = edgeList.get(i).ew;
	        
	        if(ds.findUParent(u) != ds.findUParent(v)){
	            mst += wt;
	            ds.unionBySize(u, v);
	        }
	    }
	    return mst;
	}
}

/*
TC: O(N+E) + O(E logE) 
SC: O(N)
*/