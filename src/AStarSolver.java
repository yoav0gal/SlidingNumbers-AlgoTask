import java.util.*;

public class AStarSolver {


    private static class Node implements Comparable<Node> {
        Board board;
        int moves;
        Node previous;
        int priority; // This is 'f' in A*

        public Node(Board board, String heuristicType , int moves, Node previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.priority = moves + heuristic(board, heuristicType);
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    public static SolveResultObject solve(Board initial, String heuristicType) {
        SolveResultObject result = new SolveResultObject();
        int checkedNodesCount = 0;

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Board> closedSet = new HashSet<>();
        openSet.add(new Node(initial, heuristicType, 0, null));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.board.isSolved()) {
                result.finalizeResult( constructPath(current),checkedNodesCount);
                return result;
            }

            closedSet.add(current.board);
            checkedNodesCount ++;

            for (Board neighbor : current.board.generateNeighbors()) {
                if (closedSet.contains(neighbor)) continue;
                openSet.add(new Node(neighbor, heuristicType,current.moves + 1, current));
            }
        }
        result.finalizeResult( Collections.emptyList(),checkedNodesCount);
        return result; // Return an empty list if no solution is found
    }

    private static int heuristic(Board board, String type) {
        return switch (type) {
            case "manhattan" -> manhattanDistance(board);
            case "dijkstra" -> 0; // Dijkstra's algorithm doesn't use a heuristic, so return 0
            case "incompatible" -> incompatibleHeuristic(board);
            default -> throw new IllegalArgumentException("Unknown heuristic type: " + type);
        };
    }

    private static int manhattanDistance(Board board) {
        int distance = 0;
        int size = board.getSize();
        int[][] gameState = board.getGameState();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = gameState[i][j];
                if (value != 0) {
                    int targetX = (value - 1) / size; // Expected x-coordinate (row)
                    int targetY = (value - 1) % size; // Expected y-coordinate (column)
                    distance += Math.abs(i - targetX) + Math.abs(j - targetY);
                }
            }
        }
        return distance;
    }

    private static int incompatibleHeuristic(Board board) {
        // Generate an incompatible heuristic. For example, a random number.
        // Note: This is just a placeholder and should not be used in real scenarios.
        return manhattanDistance(board) + new Random().nextInt(5);
    }

    private static List<Board> constructPath(Node node) {
        LinkedList<Board> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node.board);
            node = node.previous;
        }
        return path;
    }
}
