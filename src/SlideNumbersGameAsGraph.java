
import java.util.*;

public class SlideNumbersGameAsGraph {
    private Map<Board, List<Board>> gameGraph;

    public Map<Board, List<Board>> getGraph() {
        return gameGraph;
    }
    public SlideNumbersGameAsGraph(Board initialBoard) {
        this.gameGraph = new HashMap<>();

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

            for (Board neighbor : current.generateNeighbors()) {
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
