package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/** Percolation model */
public class Percolation {

    private WeightedQuickUnionUF DJSforP;
    private WeightedQuickUnionUF DJSforF;
    private int size;  //size of the grid
    private int topID = size * size + 1;
    private int bottomID = size * size + 2;
    private int openedSites = 0;
    private int[] opened;  //record the open status, 0 is unopened, 1 is opened
    /** Create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if(N <= 0) {
            throw new IllegalArgumentException("N should be a positive integer");
        }
        size = N;
        DJSforP = new WeightedQuickUnionUF(N * N + 2);
        DJSforF = new WeightedQuickUnionUF(N * N + 1);
        opened = new int[N * N + 2];
        //set all of the block to unopened
        for (int i = 0; i < N * N + 2; i++) {
            opened[i] = 0;
        }
    }

    /** Translate 2D coordinates into number */
    private int xyInto1D(int row, int col) {
        if(row >= size || col >= size) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        return size * row + col;
    }

    /** Open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if(row >= size || col >= size) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        int thisID = xyInto1D(row, col);
        if(!isOpen(row, col)) {
            opened[thisID] = 1;
            openedSites += 1;
            if(row == 0) {
                DJSforP.union(thisID, topID);
                DJSforF.union(thisID, topID);
            }
            if(row == size - 1) {
                DJSforP.union(thisID, bottomID);
            }
        }

        if(col - 1 >= 0) {
            if(isOpen(row, col - 1)) {
                int leftID = xyInto1D(row, col - 1);
                DJSforP.union(thisID, leftID);
                DJSforF.union(thisID, leftID);
            }
        }
        if(col + 1 < size) {
            if(isOpen(row, col + 1)) {
                int rightID = xyInto1D(row, col + 1);
                DJSforP.union(thisID, rightID);
                DJSforF.union(thisID, rightID);
            }
        }

        if(row - 1 >= 0) {
            if(isOpen(row - 1, col)) {
                int upID = xyInto1D(row - 1, col);
                DJSforP.union(thisID, upID);
                DJSforF.union(thisID, upID);
            }
        }

        if(row + 1 < size) {
            if(isOpen(row + 1, col)) {
                int downID = xyInto1D(row + 1, col);
                DJSforP.union(thisID, downID);
                DJSforF.union(thisID, downID);
            }
        }
    }

    /** Is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if(row >= size || col >= size) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        if(this.opened[xyInto1D(row, col)] == 1) {
            return true;
        }
        return false;
    }

    /** Is the site (row, col) full? */
    public boolean isFull(int row, int col){
        int thisID = xyInto1D(row, col);
        if(DJSforF.connected(topID, thisID)) {
            return true;
        }
        return false;
    }

    /** Number of open sites */
    public int numberOfOpenSites() {
        return openedSites;
    }

    /** Does the system percolate? */
    public boolean percolates() {
        if(DJSforP.connected(topID, bottomID)) {
            return true;
        }
        return false;
    }
}
