// Yash Gupta
// 500125397 
import java.util.*;

class Dijkstra {
    static class Edge {
        int target, weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    static void dijkstra(List<List<Edge>> graph, int source) {
        int V = graph.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int nodeDist = current[1];

            for (Edge edge : graph.get(node)) {
                int newDist = nodeDist + edge.weight;
                if (newDist < dist[edge.target]) {
                    dist[edge.target] = newDist;
                    pq.offer(new int[]{edge.target, newDist});
                }
            }
        }

        System.out.println("Distances from source using Dijkstra's:");
        System.out.println(Arrays.toString(dist));
    }
}
class BellmanFord {
    static class Edge {
        int source, target, weight;
        Edge(int source, int target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }

    static void bellmanFord(List<Edge> edges, int V, int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Relax all edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.source] != Integer.MAX_VALUE && dist[edge.source] + edge.weight < dist[edge.target]) {
                    dist[edge.target] = dist[edge.source] + edge.weight;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edges) {
            if (dist[edge.source] != Integer.MAX_VALUE && dist[edge.source] + edge.weight < dist[edge.target]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        System.out.println("Distances from source using Bellman-Ford:");
        System.out.println(Arrays.toString(dist));
    }
}

public class Dijkstra_VS_Bellman {
    public static void main(String[] args) {
        int V = 1000;
        int E = 5000;
        Random rand = new Random();

        List<List<Dijkstra.Edge>> graph = new ArrayList<>();
        List<BellmanFord.Edge> edges = new ArrayList<>();

        // Initialize graph
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Generate random edges
        for (int i = 0; i < E; i++) {
            int u = rand.nextInt(V);
            int v = rand.nextInt(V);
            int weight = rand.nextInt(100) + 1;
            graph.get(u).add(new Dijkstra.Edge(v, weight));
            edges.add(new BellmanFord.Edge(u, v, weight));
        }

        int source = 0;

        // Measure time for Dijkstra's Algorithm
        long startDijkstra = System.nanoTime();
        Dijkstra.dijkstra(graph, source);
        long endDijkstra = System.nanoTime();

        // Measure time for Bellman-Ford Algorithm
        long startBellmanFord = System.nanoTime();
        BellmanFord.bellmanFord(edges, V, source);
        long endBellmanFord = System.nanoTime();

        System.out.println("Dijkstra's Algorithm Time: " + (endDijkstra - startDijkstra) / 1e6 + " ms");
        System.out.println("Bellman-Ford Algorithm Time: " + (endBellmanFord - startBellmanFord) / 1e6 + " ms");
    }
}
