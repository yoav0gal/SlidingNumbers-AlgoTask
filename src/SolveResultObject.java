import java.util.List;

public class SolveResultObject {

    private final long startTime;

    public long executionTime;
    public int checkedNodesCount;
    public List<Board> solution;

    public int solutionLength;


    public SolveResultObject () {
        this.startTime = System.currentTimeMillis();
        this.checkedNodesCount = 0;
        this.solution = null;
    }

    public void finalizeResult(List<Board> solution, int checkedNodeCount) {
        long endTime = System.currentTimeMillis();
        this.executionTime = endTime - this.startTime;
        this.checkedNodesCount = checkedNodeCount;
        this.solution = solution;
        this.solutionLength = this.solution.size();
    }

    public void add(SolveResultObject toAdd) {
        this.solutionLength += toAdd.solutionLength;
        this.executionTime += toAdd.executionTime;
        this.checkedNodesCount += toAdd.checkedNodesCount;
    }

    public void printResult() {
        String toPrint =" `{\"checkedNodesCount\":" + this.checkedNodesCount + ", \"executionTime\":" +this.executionTime + ", \"solutionLength\":" + this.solutionLength + "}`";
        System.out.println(toPrint);
    }
    public void printAVGResult(int resultsAmount) {
        float AVGSolutionLength = (float) this.solutionLength /resultsAmount;
        float AVGExecutionTime = (float) this.executionTime /resultsAmount;
        float AVGCheckedNodesCount = (float) this.checkedNodesCount/ resultsAmount;

        String toPrint =" `{\"checkedNodesCount\":" + AVGCheckedNodesCount + ", \"executionTime\":" + AVGExecutionTime + ", \"solutionLength\":" + AVGSolutionLength + "}`";
        System.out.println(toPrint);
    }


    public void printPath(){
        System.out.println("Initial Board:");
        this.solution.forEach(board -> {
            board.displayBoard();
            System.out.println("\n---------------------------------");
        });
    }
    public void printEmptySlot(){
        System.out.println("First empty index");
        this.solution.forEach(board -> {
            System.out.print(board.getEmptySlot());
        });
        System.out.print("*******************");
    }

}
