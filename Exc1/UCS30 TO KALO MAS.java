import java.util.*;

public class UcsSearch {

    // Define the Node class
    static class Node {
        char name;                   // Represents the name or identifier of the node
        List<Edge> adjacencies;      // Represents the list of edges (connections to other nodes) from this node
        int pathCost;                // Represents the cost of the path from the start node to this node
        Node parent;                 // Represents the parent node in the path from the start node to this node

        // Constructor to initialize a node with its name
        Node(char name) {
            this.name = name;
            this.adjacencies = new ArrayList<>();
        }
    }

    // Define the Edge class
    static class Edge {
        Node target;                 // Represents the target node of the edge
        int weight;                  // Represents the weight or cost of traversing this edge

        // Constructor to initialize an edge with its target node and weight
        Edge(Node target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Define the SearchResult class to hold the result of the search
    static class SearchResult {
        List<Node> path;             // Represents the path from the start node to the goal node
        int cost;                    // Represents the total cost of the path

        // Constructor to initialize a search result with a path and its cost
        SearchResult(List<Node> path, int cost) {
            this.path = path;
            this.cost = cost;
        }
    }

    // Define the UCS (Uniform Cost Search) algorithm
    static SearchResult ucs(Node start, Node goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.pathCost));
        Set<Node> explored = new HashSet<>();
        start.pathCost = 0;
        frontier.add(start);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current == goal) {
                List<Node> path = new ArrayList<>();
                Node node = goal;
                while (node != null) {
                    path.add(node);
                    node = node.parent;
                }
                Collections.reverse(path);
                return new SearchResult(path, goal.pathCost);
            }
            explored.add(current);
            for (Edge edge : current.adjacencies) {
                Node neighbor = edge.target;
                int newCost = current.pathCost + edge.weight;
                if (!explored.contains(neighbor) && (!frontier.contains(neighbor) || newCost < neighbor.pathCost)) {
                    neighbor.pathCost = newCost;
                    neighbor.parent = current;
                    if (frontier.contains(neighbor)) {
                        frontier.remove(neighbor);
                    }
                    frontier.add(neighbor);
                }
            }
        }
        return null;
    }

    // Main method to test the UCS algorithm
    public static void main(String[] args) {
        // Create nodes representing the graph
        Node n0 = new Node('0');
        Node n1 = new Node('1');
        Node n2 = new Node('2');
        Node n3 = new Node('3');
        Node n4 = new Node('4');
        Node n5 = new Node('5');
        Node n6 = new Node('6');
        Node n7 = new Node('7');
        Node n8 = new Node('8');

        // Add edges between nodes to create the graph
        n0.adjacencies.add(new Edge(n1, 1));
        n0.adjacencies.add(new Edge(n4, 1));
		n0.adjacencies.add(new Edge(n3, 1));
		
        n1.adjacencies.add(new Edge(n0, 1));
		n1.adjacencies.add(new Edge(n3, 1));
		n1.adjacencies.add(new Edge(n4, 1));
        n1.adjacencies.add(new Edge(n5, 1));
		n1.adjacencies.add(new Edge(n2, 1));
		
        n2.adjacencies.add(new Edge(n1, 7));
        n2.adjacencies.add(new Edge(n4, 4));
        n2.adjacencies.add(new Edge(n5, 2));
		
        n3.adjacencies.add(new Edge(n0, 9));
        n3.adjacencies.add(new Edge(n1, 14));
		n3.adjacencies.add(new Edge(n4, 9));
        n3.adjacencies.add(new Edge(n7, 9));
        n3.adjacencies.add(new Edge(n6, 9));

        n4.adjacencies.add(new Edge(n5, 10));
        n5.adjacencies.add(new Edge(n6, 2));
        n6.adjacencies.add(new Edge(n7, 1));
        n6.adjacencies.add(new Edge(n8, 6));
        n7.adjacencies.add(new Edge(n8, 7));

        // Perform UCS search from node n0 to node n4
        SearchResult result = ucs(n0, n4);

        // Print the result
        if (result != null) {
            System.out.println("Path: " + result.path);
            System.out.println("Cost: " + result.cost);
        } else {
            System.out.println("No path found.");
        }
    }
}


