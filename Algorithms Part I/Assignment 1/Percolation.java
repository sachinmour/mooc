import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final boolean OPEN = true;
    private int limit;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException(Integer.toString(N));
        }
        limit = N;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF((N*N) + 2);
    }

    private void check(int i, int j) {
        if (i <= 0 || i > limit || j <= 0 || j > limit) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int convert(int i, int j) {
        return (limit*(i-1) + j);
    }

    public boolean isOpen(int i, int j) {
        return grid[i - 1][j - 1];
    }

    public void open(int i, int j) {
        grid[i-1][j-1] = OPEN;
        if (i == 1) {
            uf.union(0, convert(i, j));
        }
        if (i == limit) {
            uf.union(limit*limit + 1, convert(i, j));
        }
        if (j+1 <= limit && isOpen(i, j+1)) {
            uf.union(convert(i, j), convert(i, j+1));
        }
        if (j-1 >= 1 && isOpen(i, j-1)) {
            uf.union(convert(i, j), convert(i, j-1));
        }
        if (i+1 <= limit && isOpen(i+1, j)) {
            uf.union(convert(i, j), convert(i+1, j));
        }
        if (i-1 >= 1 && isOpen(i-1, j)) {
            uf.union(convert(i, j), convert(i-1, j));
        }
    }

    public boolean isFull(int i, int j) {
        check(i, j);
        return uf.connected(0, convert(i, j));
    }

    public boolean percolates() {
        return uf.connected(0, (limit*limit) + 1);
    }

}
