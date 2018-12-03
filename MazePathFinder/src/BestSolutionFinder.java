import java.util.ArrayList;
import java.util.LinkedList;

public class BestSolutionFinder {
    private MazeSolver mazeSolver;
    private MazePrinter mazePrinter;
    private int height;
    private int width;
    private int[][] maze;
    private String[][] resultingMaze;
    private int end_x;
    private int end_y;
    private int start_x;
    private int start_y;
    private static ArrayList<LinkedList<Coordinate>> allSolutions;
    private LinkedList<Coordinate> finalSolution;

    public BestSolutionFinder(int height, int width, int[][] maze, String[][] resultingMaze, int end_x, int end_y, int start_x, int start_y) {
        this.height = height;
        this.width = width;
        this.maze = maze;
        this.end_x = end_x;
        this.end_y = end_y;
        this.start_x = start_x;
        this.start_y = start_y;
        allSolutions = new ArrayList<>();
        this.resultingMaze = resultingMaze;
    }

    public void findBestSolution() {
        // Call the mazeSolver to do the recursive DFS
        mazeSolver = new MazeSolver(maze, end_x, end_y, width, height);
        mazeSolver.recursiveDFS(new LinkedList<>(), start_x, start_y);

        // Find the shortest available path
        int minSize = Integer.MAX_VALUE;
        for (LinkedList<Coordinate> possibleSolution: allSolutions) {
            int size = possibleSolution.size();
            if (size < minSize) {
                finalSolution = possibleSolution;
                minSize = size;
            }
        }

        callPrinter();
    }


    public void callPrinter() {
        mazePrinter = new MazePrinter(finalSolution, resultingMaze, height, width);
        mazePrinter.formatAndPrintMaze();
    }

    // To be called from 'outside' to add a solution
    public void addSolution(LinkedList<Coordinate> aSolution) {
        // We need to create a copy of the currentPath as it will change its components when going back in the
        // recursion, therefore causing a wrong list to be stored.
        LinkedList<Coordinate> copyToAvoidReference = new LinkedList<>();
        copyToAvoidReference.addAll(aSolution);
        allSolutions.add(copyToAvoidReference);
    }
}
