/*
743. Network Delay Time
https://leetcode.com/problems/network-delay-time/

You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

Example 1:
Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

Example 2:
Input: times = [[1,2,1]], n = 2, k = 1
Output: 1

Example 3:
Input: times = [[1,2,1]], n = 2, k = 2
Output: -1

Constraints:
1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
*/

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<Pair>());
        }
        for(int i = 0; i < times.length; i++){
            adj.get(times[i][0]).add(new Pair(times[i][1], times[i][2]));
        }

        PriorityQueue<Pair> q = new PriorityQueue<>((a,b) -> a.second - b.second);
        q.add(new Pair(k, 0));

        int dist[] = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        while(!q.isEmpty()){
            int node = q.peek().first;
            int cost = q.peek().second;
            q.remove();

            for(Pair it : adj.get(node)){
                int adjNode = it.first;
                int c = it.second;

                if(cost + c < dist[adjNode]){
                    dist[adjNode] = cost + c;
                    q.add(new Pair(adjNode, cost + c));
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < dist.length; i++){
            if(dist[i] == Integer.MAX_VALUE){
                return -1;
            }
            max = Math.max(max, dist[i]);
        }
        return max;

    }

    class Pair{
        int first;
        int second;

        Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
}

/*
TC: O(N+V) 
SC: O(N) [For dis]
*/