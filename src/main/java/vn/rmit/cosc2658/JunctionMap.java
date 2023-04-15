package vn.rmit.cosc2658;

public class JunctionMap {
    public static class LinkedListQueue {
        static class Node {
            Integer data;
            Node next;

            public Node(Integer data) {
                this.data = data;
                this.next = null;
            }
        }

        private int size;
        private Node head;

        public LinkedListQueue() {
            size = 0;
            head = null;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean enQueue(Integer item) {
            Node n = new Node(item);
            n.next = head;
            head = n;
            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            if (size == 1) {
                head = null;
                size = 0;
                return true;
            }
            Node prev = head;
            Node current = prev.next;
            while (current.next != null) {
                prev = current;
                current = current.next;
            }
            prev.next = null;
            size--;
            return true;
        }

        public Integer peekFront() {
            if (isEmpty()) {
                return null;
            }
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            return node.data;
        }
    }


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


        boolean[] visited = new boolean[edges.length];
        for (int i = 0; i < visited.length; i++) visited[i] = false;

        int[] prior = new int[edges.length];  // used to backtrack and construct the path
        for (int i = 0; i < prior.length; i++) prior[i] = -1;  // defaults to no prior junction

        LinkedListQueue visitQueue = new LinkedListQueue();


        visitQueue.enQueue(source);
        visited[source] = true;
        while (!visitQueue.isEmpty()) {
            int currentJunction = visitQueue.peekFront();
            visitQueue.deQueue();
            int[] adjacency = edges[currentJunction];

            for (int junction = 0; junction < adjacency.length; junction++) {
                if (adjacency[junction] > 0 && !visited[junction]) {
                    visited[junction] = true;
                    visitQueue.enQueue(junction);
                    prior[junction] = currentJunction;
                }
            }
        }

        if (!visited[destination]) return "";


        final String SEP = " -> ";
        StringBuilder result = new StringBuilder();

        int currentJunction = destination;
        result.insert(0, currentJunction);
        while (prior[currentJunction] != -1) {
            currentJunction = prior[currentJunction];
            result.insert(0, SEP).insert(0, currentJunction);
        }

        return result.toString();
    }
}
