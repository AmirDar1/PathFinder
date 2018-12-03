public class Coordinate {
    private int x;
    private int y;
    private boolean visited;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}