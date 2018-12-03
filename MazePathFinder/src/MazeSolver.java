import java.util.LinkedList;

public class MazeSolver {

    private static int[][] maze;
    private static Coordinate[][] coordinateMaze;
    private static int end_x;
    private static int end_y;
    private static int width;
    private static int height;

    public MazeSolver(int[][] maze, int end_x, int end_y, int width, int height) {
        MazeSolver.end_x = end_x;
        MazeSolver.end_y = end_y;
        MazeSolver.maze = maze;
        MazeSolver.width = width;
        MazeSolver.height = height;
        coordinateMaze = new Coordinate[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                coordinateMaze[i][j] = new Coordinate(j, i);
            }
        }
    }

    public void recursiveDFS(LinkedList<Coordinate> currentPath, int current_x, int current_y) {

        Coordinate current = coordinateMaze[current_y][current_x];

        if (checkIfVisited(currentPath, current)) return ; // Already visited this one

        if (maze[current.getY()][current.getX()] == 1) { // Wall hit, so nothing to do here
            return ;
        }

        if (current.getX() == end_x && current.getY() == end_y) { // End / destination
            currentPath.add(current);
            Main.setSolutionFound(true);
            // Add solution to the best solution finder's list
            Main.getBestSolutionFinder().addSolution(currentPath);
            return ;
        }

        // Add note to path & mark visited
        current.setVisited(true);
        currentPath.add(current);

        // Get all neighbours and call DFS on them
        LinkedList<Coordinate> elemsToVisit = addNeighbours(maze, current.getX(), current.getY());
        for (Coordinate coord: elemsToVisit) {
            if (!checkIfVisited(currentPath, coord)) {
                recursiveDFS(currentPath, coord.getX(), coord.getY());
            }
        }

        // Remove the element if none of the neighbours was successful, meaning we're not part of a good path & return to
        // parent node to try one of my neighbours
        currentPath.remove(currentPath.size() - 1);
        return ;
    }

    // Checks if we already visited the node in the current path
    private static boolean checkIfVisited(LinkedList<Coordinate> currentPath, Coordinate current) {
        for (Coordinate coordinate: currentPath) {
            if (coordinate.getX() == current.getX() && coordinate.getY() == current.getY() && coordinate.isVisited()) {
                return true;
            }
        }
        return false;
    }

    // Checks all the neighbours 'roundly'
    private static LinkedList<Coordinate> addNeighbours(int[][] maze, int x, int y) {
        LinkedList<Coordinate> elemsToVisit = new LinkedList<>();

        if ((x == 0) && (maze[y][width - 1] == 0)) { // Left wrap
            elemsToVisit.add(new Coordinate((x - 1 + width) % width, y));
        }
        if ((x == (width - 1)) && (maze[y][0] == 0)) { // Right wrap
            elemsToVisit.add(new Coordinate((x + 1 + width) % width, y));
        }
        if ((x != 0) && maze[y][x - 1] == 0) { // Left
            elemsToVisit.add(new Coordinate(x - 1, y));
        }
        if ((x != (width - 1)) && maze[y][x + 1] == 0) {  // Right
            elemsToVisit.add(new Coordinate(x + 1, y));
        }

        if ((y == 0) && (maze[height - 1][x] == 0)) { // Below wrap
            elemsToVisit.add(new Coordinate(x,(y - 1 + height) % height));
        }
        if ((y == (height - 1)) && (maze[0][x] == 0)) { // Above wrap
            elemsToVisit.add(new Coordinate(x,(y + 1 + height) % height));
        }
        if ((y != 0) && (maze[y - 1][x] == 0)) { // Above
            elemsToVisit.add(new Coordinate(x, y - 1));
        }
        if ((y != (height - 1)) && (maze[y + 1][x] == 0)) { // Below
            elemsToVisit.add(new Coordinate(x, y + 1));
        }

        return elemsToVisit;
    }

}
