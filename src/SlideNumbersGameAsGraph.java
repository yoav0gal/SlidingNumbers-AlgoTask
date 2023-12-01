
import java.util.*;

public class SlideNumbersGameAsGraph {
    private Map<Integer, List<Board>> gameGraph;

    public Map<Integer, List<Board>> getGraph() {
        return gameGraph;
    }
    public SlideNumbersGameAsGraph(Board initialBoard) {
        this.gameGraph = new HashMap<>();
        buildGraph(initialBoard);
    }

    private void buildGraph(Board initialBoard) {
        Queue<Board> queue = new LinkedList<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        queue.add(initialBoard);
        visited.put(initialBoard.getId(), true);

        while (!queue.isEmpty()) {
            Board current = queue.poll();

            List<Board> neighbors = current.generateNeighbors();
            gameGraph.put(current.getId(), neighbors);

            for (Board neighbor : neighbors) {
                if (!visited.containsKey(neighbor.getId())) {
                    queue.add(neighbor);
                    visited.put(neighbor.getId(), true);
                }
            }
        }
    }


    public List<Board> BFSToSolved(Board startBoard) {
        if (startBoard.isSolved()) {
            return Collections.singletonList(startBoard);
        }

        Queue<Board> queue = new LinkedList<>();
        Map<Board, Board> predecessors = new HashMap<>();
        queue.add(startBoard);
        predecessors.put(startBoard, null);

        while (!queue.isEmpty()) {
            Board current = queue.poll();

            if (current.isSolved()) {
                return buildPath(predecessors, current);
            }

            for (Board neighbor : gameGraph.getOrDefault(current, new ArrayList<>())) {
                if (!predecessors.containsKey(neighbor)) {
                    queue.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }

        return new ArrayList<>(); // No solution found
    }

    private List<Board> buildPath(Map<Board, Board> predecessors, Board end) {
        LinkedList<Board> path = new LinkedList<>();
        for (Board at = end; at != null; at = predecessors.get(at)) {
            path.addFirst(at);
        }
        return path;
    }


    // You can add methods to access and interact with the graph here.
}
