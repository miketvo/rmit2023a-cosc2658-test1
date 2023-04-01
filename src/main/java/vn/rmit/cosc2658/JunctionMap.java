package vn.rmit.cosc2658;

public class JunctionMap {
    int[][] edges;


    public JunctionMap(int[][] config) {
        edges = config;
    }


    // edgesSymmetric() complexity = O(N^2)
    private boolean edgesSymmetric() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                if (edges[i][j] != edges[j][i]) return false;
            }
        }
        return true;
    }

    // hasOneWayStreet() complexity = edgesSymmetric() complexity = O(N^2)
    public boolean hasOneWayStreet() {
        return !edgesSymmetric();  // directed graph cannot have symmetric adjacency matrix
    }


    // sum() complexity = O(N) with N is the number of elements in the array
    private int sum(int[] array) {  // assuming array is not empty
        int sum = array[0];
        for (int j : array) {
            sum += j;
        }
        return sum;
    }

    // hasDeadEnd() complexity = O(N^2)
    public boolean hasDeadEnd() {
        if (!hasOneWayStreet()) return false;  // undirected graph never has dead-end.

        for (int[] edge : edges) {
            if (sum(edge) == 0) return true;
        }
        return false;
    }


    // getShortestPath() complexity = O(N^2)
    public String getShortestPath(int source, int destination) {
        if (source == destination) throw new IllegalArgumentException("Source cannot be the same as Destination!");

        // TODO: Implement

        return "";
    }
}
