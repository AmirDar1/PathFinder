import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static int width;
    private static int height;
    private static int[][] maze;
    private static String[][] resultingMaze;
    private static int start_x;
    private static int start_y;
    private static int end_x;
    private static int end_y;
    private static boolean solutionFound = false;
    private static BestSolutionFinder bestSolutionFinder;

    public static void main(String[] args) throws Exception{
        // Create a scanner and read all the class fields
        Scanner scn = readInputData();

        // Read rest of file and parse in matrix maze & resulting matrix
        int i = 0;
        while (i < height) {
            String line = scn.nextLine();
            String[] elems = line.split(" ");
            for (int j = 0; j < width; ++j) {
                maze[i][j] = Integer.parseInt(elems[j]);
                if (maze[i][j] == 1) {
                    resultingMaze[i][j] = "#";
                }
                else {
                    resultingMaze[i][j] = " ";
                }
            }
            ++i;
        }

        // Call the recursive DFS function, when this obtains a result it will finish
        bestSolutionFinder = new BestSolutionFinder(height, width, maze, resultingMaze, end_x, end_y, start_x, start_y);
        bestSolutionFinder.findBestSolution();
        if (!solutionFound) {
            System.out.println("No solution found for this maze.");
        }
    }

    private static Scanner readInputData() throws FileNotFoundException {
        File f = new File("Input\\large_input.txt");
        Scanner scn = new Scanner(f);
        // Read first line
        String line = scn.nextLine();
        // Parse w/h
        width = Integer.parseInt(line.split(" ")[0]);
        height = Integer.parseInt(line.split(" ")[1]);
        // Allocate matrix size
        maze = new int[height][width];
        resultingMaze = new String[height][width];
        // Read second line & parse for start point
        line = scn.nextLine();
        start_x = Integer.parseInt(line.split(" ")[0]);
        start_y = Integer.parseInt(line.split(" ")[1]);
        // Read third line & parse for end point
        line = scn.nextLine();
        end_x = Integer.parseInt(line.split(" ")[0]);
        end_y = Integer.parseInt(line.split(" ")[1]);
        return scn;
    }

    public static void setSolutionFound(boolean solutionFound) {
        Main.solutionFound = solutionFound;
    }

    public static BestSolutionFinder getBestSolutionFinder() {
        return bestSolutionFinder;
    }
}
