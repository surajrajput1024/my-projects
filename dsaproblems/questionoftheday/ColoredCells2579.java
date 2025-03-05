package dsaproblems.questionoftheday;

public class ColoredCells2579 {
    /**
     * Given a grid of size n x n, each cell of the grid is colored with some color. The color of the cell (i, j) is represented by two numbers x and y. The grid is said to be colored if all cells have the same color. The task is to find the number of ways to color the grid such that the grid is colored and the number of colors used is exactly n.
     * 1 + 4 + 8 + 16...
     * 1 + 4 ( 1+ 2+ 3+ ... n-1)
     * 1+ 4 * (n-1) * n/2
     * @param n
     * @return
     */
    public long coloredCells(int n) {
      return 1 + 4L * (n-1) * n/2;
    }

    public static void main(String[] args) {
        System.out.println(new ColoredCells2579().coloredCells(100000));
    }
}
