//package assign3;

public class Percolation {

    private int n;
    private boolean[][] grid;
    private int[] path;
    private int[] size;
    private int totalOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        int cnt = 0;
        this.n = n;
        grid = new boolean[n][n];
        path = new int[n * n];
        size = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
                path[cnt] = cnt;
                size[cnt] = 1;
                cnt++;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    //ToDo: Whenever a site is to be opened it needs to connect to the nearby open sites
    public void open(int row, int col) {
        validateRange(row, col);
        grid[row][col] = true;
        totalOpenSites++;
        connectOpenSites(row, col);
    }

    private void validateRange(int row, int col) {
        if (row < 0 || row > n-1 || col < 0 || col > n-1) {
            throw new IllegalArgumentException(String.format("Row %d or Col %d out of range %d", row, col, n));
        }
    }

    private void connectOpenSites(int row, int col) {
        int[] nearbyOpenSites = nearbyOpenSites(row, col);
        int p = index(row, col);
        for (int nearbyOpenSite : nearbyOpenSites) {
            if (nearbyOpenSite != -1) {
                union(p, nearbyOpenSite);
            }
        }
    }

    private int[] nearbyOpenSites(int row, int col) {
        int[] nearbySites = {-1, -1, -1, -1};
        int idx = 0;
        if (isOpen(row - 1, col)) {
            nearbySites[idx++] = index(row - 1, col);
        }
        if (isOpen(row + 1, col)) {
            nearbySites[idx++] = index(row + 1, col);
        }
        if (isOpen(row, col - 1)) {
            nearbySites[idx++] = index(row, col - 1);
        }
        if (isOpen(row, col + 1)) {
            nearbySites[idx] = index(row, col + 1);
        }
        return nearbySites;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRange(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRange(row, col);
        return !isOpen(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return totalOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        boolean percolates = false;
        for (int firstRowCol = 0; firstRowCol < n; firstRowCol++) {
            for (int secondRowCol = 0; secondRowCol < n; secondRowCol++) {
                if (isConnected(index(0, firstRowCol), index(n - 1, secondRowCol))) {
                    percolates = true;
                    break;
                }
            }
        }
        return percolates;
    }

    private boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int i) {
        while (path[i] != i) {
            path[i] = path[path[i]];
            i = path[i];
        }
        return i;
    }

    private void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) return;
        if (size[p] < size[q]) {
            path[rootP] = rootQ;
            size[q] += size[p];
        } else {
            path[rootQ] = rootP;
            size[p] += size[q];
        }
    }

    private int index(int i, int j) {
        return i * (n) + j;
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
