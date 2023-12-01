import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a 4x4 board and shuffle it with 10 random moves
        Board board = new Board(5, 20);

        // Display the initial board


        BFSSolver graph = new BFSSolver();
        List<Board> bfsPath = graph.solve(board);

        AStarSolver astarGraph = new AStarSolver();
        List<Board> astarPath = astarGraph.solve(board, "manhattan");

        System.out.println("Initial Board:");
        astarPath.forEach(board1 -> {
            board1.displayBoard();
            System.out.printf("\n---------------------------------\n");
        });

    }
}