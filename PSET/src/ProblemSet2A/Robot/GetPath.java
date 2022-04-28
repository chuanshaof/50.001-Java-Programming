package ProblemSet2A.Robot;


import java.util.ArrayList;

public class GetPath {

    //Fill in your answer for this method
    // r = y of destination
    // c = x of destination
    // path = input ArrayList, should be edited to be some an array list of [start] -> [end]
    // start = (0,0), is a Point
    public static boolean getPath (int r, int c, ArrayList<Point> path, final int [][] grid) {
        int pathFailCount = 0;
        // Base case for r == 0, c == 0
        if (r == 0 && c == 0) {
            path.add(0, new Point(r, c));
            return true;
        }

        if (grid[r][c] == 1) {
            return false;
        }


        if (r != 0 && getPath(r - 1, c, path, grid)) {
            path.add(new Point(r,c));
            if (grid[r - 1][c] == 0) {
                return true;
            } else {
                return false;
            }
        } else if (c != 0 && getPath(r, c - 1, path, grid)) {
            if (grid[r][c - 1] == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            path.clear();
            return false;
        }


//        if (pathFailCount != 1 && r != 0 && grid[r - 1][c] == 0) {
//            path.add(0, new Point(r, c));
//            return true && getPath(r - 1, c, path, grid);
//        } else if (pathFailCount != 2 && c != 0 && grid[r][c - 1] == 0) {
//            path.add(0, new Point(r, c));
//            return true && getPath(r, c - 1, path, grid);
//        } else {
//            try {
//                Point previous = path.remove(0);
//                if (previous.y > r) {
//                    pathFailCount = 1;
//                    return getPath(previous.y, previous.x, path, grid);
//                } else if (previous.x > c) {
//                    pathFailCount = 2;
//                    Point previous2 = path.remove(0);
//                    return getPath(previous2.y, previous2.x, path, grid);
//                }
//            } catch (NullPointerException e) {
//                return false;
//            }
//        }
//        return false;
    }
}

//You do not need to change any code below ---------
class Point {
    int x;
    int y;

    Point (int x, int y) {
        this.x=x;
        this.y=y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
//--------------------------------------------------

/* HINT:
Your solution ought to work with a grid that is not square.
Here is the grid for Test Case 8:

            final int[][] grid = {
                    {0,0,0,1},
                    {0,1,0,0},
                    {0,1,1,1},
                    {0,0,0,1},
                    {1,1,0,0},
                    {1,1,1,0}
            };

If the destination is r = 5, c = 3, then getPath() returns true.
If the destination is r = 2, c = 3, then getPath() returns false.

*/

