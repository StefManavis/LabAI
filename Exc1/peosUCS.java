import java.util.*;

public class peosUCS {

    public static void printList(char[] list) {
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.println(list[i] + "|" + list[i + 1] + "|" + list[i + 2]);
        }
    }

    public static char[] swapPositions(char[] list, int pos1, int pos2) {
        char[] newList = Arrays.copyOf(list, list.length);
        char temp = newList[pos1];
        newList[pos1] = newList[pos2];
        newList[pos2] = temp;
        return newList;
    }

    public static List<char[]> getNeighbors(char[] currentState) {
        List<char[]> neighbors = new ArrayList<>();
        int emptyPos = -1;
        for (int i = 0; i < currentState.length; i++) {
            if (currentState[i] == ' ') {
                emptyPos = i;
                break;
            }
        }

        // Movement: UP, DOWN, LEFT, RIGHT, UP-RIGHT, UP-LEFT, DOWN-RIGHT, DOWN-LEFT
        int[] moveOffsets = {-3, 3, -1, 1, -2, -4, 4, 2};
        for (int offset : moveOffsets) {
            int newPos = emptyPos + offset;
            if (isValidMove(emptyPos, newPos, offset)) {
                neighbors.add(swapPositions(currentState, emptyPos, newPos));
            }
        }

        return neighbors;
    }

    public static boolean isValidMove(int emptyPos, int newPos, int offset) {
        // Check if newPos is within bounds
        if (newPos < 0 || newPos >= 9) {
            return false;
        }
        // Check if move is within the same row for LEFT and RIGHT moves
        if (offset == -1 || offset == 1) {
            return (emptyPos / 3 == newPos / 3);
        }
        // Check if move is valid for diagonal moves
        if (offset == -2 || offset == 2) { // Up-right or down-left
            return (emptyPos % 3 < 2);
        }
        if (offset == -4 || offset == 4) { // Up-left or down-right
            return (emptyPos % 3 > 0);
        }
        return true;
    }

    static class Node {
        char[] state;
        int cost;
        Node parent;

        Node(char[] state, int cost, Node parent) {
            this.state = state;
            this.cost = cost;
            this.parent = parent;
        }
    }

    static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node node1, Node node2) {
            return Integer.compare(node1.cost, node2.cost);
        }
    }

    public static void ucsSearch(char[] startState) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
        Set<String> explored = new HashSet<>();
        char[] goalState = {'6', '5', '4', '7', ' ', '3', '8', '1', '2'};

        frontier.add(new Node(startState, 0, null));

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            printList(currentNode.state);

            if (Arrays.equals(currentNode.state, goalState)) {
                System.out.println("This is the solution!");
                printSolution(currentNode);
                return;
            }

            String currentNodeStr = new String(currentNode.state);
            if (!explored.contains(currentNodeStr)) {
                explored.add(currentNodeStr);

                for (char[] neighbor : getNeighbors(currentNode.state)) {
                    String neighborStr = new String(neighbor);
                    if (!explored.contains(neighborStr)) {
                        frontier.add(new Node(neighbor, currentNode.cost + 1, currentNode));
                    }
                }
            }
        }

        System.out.println("No solution found.");
    }

    public static void printSolution(Node node) {
        if (node == null) {
            return;
        }
        printSolution(node.parent);
        printList(node.state);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give me the desired starting state (e.g., 12345678 ):");
        String input = scanner.nextLine();
        char[] startState = input.toCharArray();
        ucsSearch(startState);
    }
}
