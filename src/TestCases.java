import java.util.ArrayList;

public class TestCases {


    public static void BasicTest(int boardSize, int mixMovesCount ) {
        Board testBoard= new Board(boardSize,mixMovesCount);

        SolveResultObject BFSRes =  BFSSolver.solve(testBoard);
        SolveResultObject dijkstraRes = AStarSolver.solve(testBoard,"dijkstra");
        SolveResultObject manhattanRes = AStarSolver.solve(testBoard,"manhattan");
        SolveResultObject incompatibleHeuristicRes = AStarSolver.solve(testBoard,"incompatible");

        BFSRes.printResult();
        dijkstraRes.printResult();
        manhattanRes.printResult();
        incompatibleHeuristicRes.printResult();
        System.out.println("\n---------------------------------");

    }

    public static void AverageTest(int boardSize, int mixMovesCount, int testCount){

        SolveResultObject totalBFSRes = new SolveResultObject();
        SolveResultObject totalDijkstraRes = new SolveResultObject();
        SolveResultObject totalManhattanRes = new SolveResultObject();
        SolveResultObject totalIncompatibleHeuristicRes = new SolveResultObject();

        totalBFSRes.finalizeResult(new ArrayList<>(),0);
        totalDijkstraRes.finalizeResult(new ArrayList<>(),0);
        totalManhattanRes.finalizeResult(new ArrayList<>(),0);
        totalIncompatibleHeuristicRes.finalizeResult(new ArrayList<>(),0);

        for(int testnum=0; testnum< testCount; testnum++) {
            Board testBoard= new Board(boardSize,mixMovesCount);

            SolveResultObject currBFSRes =  BFSSolver.solve(testBoard);
            SolveResultObject currDijkstraRes = AStarSolver.solve(testBoard,"dijkstra");
            SolveResultObject currManhattanRes = AStarSolver.solve(testBoard,"manhattan");
            SolveResultObject currIncompatibleHeuristicRes = AStarSolver.solve(testBoard,"incompatible");

            totalBFSRes.add(currBFSRes);
            totalDijkstraRes.add(currDijkstraRes);
            totalManhattanRes.add(currManhattanRes);
            totalIncompatibleHeuristicRes.add(currIncompatibleHeuristicRes);
        }

        System.out.println("TOTAL:");
        System.out.print("BFS:");
        totalBFSRes.printResult();
        System.out.print("dijkstra:");
        totalDijkstraRes.printResult();
        System.out.print("manhattan:");
        totalManhattanRes.printResult();
        System.out.print("incompatible:");
        totalIncompatibleHeuristicRes.printResult();

        System.out.println("*******************");

        System.out.println("AVG:");
        System.out.print("BFS:");
        totalBFSRes.printAVGResult(testCount);
        System.out.print("dijkstra:");
        totalDijkstraRes.printAVGResult(testCount);
        System.out.print("manhattan:");
        totalManhattanRes.printAVGResult(testCount);
        System.out.print("incompatible:");
        totalIncompatibleHeuristicRes.printAVGResult(testCount);

        System.out.println("*******************");
    }
}
