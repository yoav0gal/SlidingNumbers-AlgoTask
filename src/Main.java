import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a 4x4 board and shuffle it with 10 random moves
        Board board = new Board(2, 2);

        // Display the initial board
        System.out.println("Initial Board:");
        board.displayBoard();

        SlideNumbersGameAsGraph graph = new SlideNumbersGameAsGraph(board);
        List<Board> bfsPath = graph.BFSToSolved(board);

        System.out.println("Initial Board:");
        bfsPath.forEach(board1 -> {
            board1.displayBoard();
            System.out.printf("\n---------------------------------\n");
        });

    }
}