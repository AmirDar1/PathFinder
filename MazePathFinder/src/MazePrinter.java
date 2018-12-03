import java.util.LinkedList;

public class MazePrinter {
    private LinkedList<Coordinate> resultingPath;
    private String[][] resultingMaze;
    private int height;
    private int width;

    public MazePrinter(LinkedList<Coordinate> resultingPath, String[][] resultingMaze, int height, int width) {
        this.resultingMaze = resultingMaze;
        this.resultingPath = resultingPath;
        this.height = height;
        this.width = width;
    }

    public void formatAndPrintMaze() {
        for (Coordinate c: resultingPath) {
            resultingMaze[c.getY()][c.getX()] = "X";
        }
        Coordinate first = resultingPath.getFirst();
        resultingMaze[first.getY()][first.getX()] = "S";
        Coordinate last = resultingPath.getLast();
        resultingMaze[last.getY()][last.getX()] = "E";

        Main.setSolutionFound(true);

        printMaze();
    }

    private void printMaze() {
        for (int k = 0; k < height; ++k) {
            for (int l = 0; l < width; ++l) {
                System.out.print(resultingMaze[k][l]);
            }
            System.out.println();
        }
    }
}
