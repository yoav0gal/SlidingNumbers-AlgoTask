import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create a 4x4 board and shuffle it with 10 random moves
        Board board = new Board(4, 10);

        // Display the initial board
        System.out.println("Initial Board:");
        board.displayBoard();

        // Generate and display neighbors
        ArrayList<Board> neighbors = board.generateNeighbors();
        System.out.println("\nNeighbors:");
        for (int i = 0; i < neighbors.size(); i++) {
            System.out.println("Neighbor " + (i + 1) + ":");
            neighbors.get(i).displayBoard();
            System.out.println();
        }
    }
}