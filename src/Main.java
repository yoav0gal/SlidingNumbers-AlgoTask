import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void printPath(List<Board> path){
        System.out.println("Initial Board:");
        path.forEach(board -> {
            board.displayBoard();
            System.out.println("\n---------------------------------");
        });
    }


    public static void main(String[] args) {
        // Create a 4x4 board and shuffle it with 10 random moves
        Board board = new Board(5, 20);

        // Display the initial board


        BFSSolver graph = new BFSSolver();
        List<Board> bfsPath = graph.solve(board);
        printPath(bfsPath);

        AStarSolver astarGraph = new AStarSolver();
        List<Board> astarPath = astarGraph.solve(board, "manhattan");
        printPath(astarPath);


    }
}