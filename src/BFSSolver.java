
import java.util.*;

public class BFSSolver {
    public static SolveResultObject solve(Board startBoard) {
        SolveResultObject result = new SolveResultObject();
        int checkedNodesCount = 0;

        if (startBoard.isSolved()) {
            result.finalizeResult(Collections.singletonList(startBoard),checkedNodesCount);
            return result;
        }

        Queue<Board> queue = new LinkedList<>();
        Map<Board, Board> predecessors = new HashMap<>();
        queue.add(startBoard);
        predecessors.put(startBoard, null);

        while (!queue.isEmpty()) {
            Board current = queue.poll();
            checkedNodesCount++;
            if (current.isSolved()) {
                result.finalizeResult(buildPath(predecessors, current),checkedNodesCount);
                return result;
            }

            for (Board neighbor : current.generateNeighbors()) {
                if (!predecessors.containsKey(neighbor)) {
                    queue.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }

        result.finalizeResult(new ArrayList<>(),checkedNodesCount);
        return result;
    }

    public static List<Board> buildPath(Map<Board, Board> predecessors, Board end) {
        LinkedList<Board> path = new LinkedList<>();
        for (Board at = end; at != null; at = predecessors.get(at)) {
            path.addFirst(at);
        }
        return path;
    }

}
