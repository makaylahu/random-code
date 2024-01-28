public class Face {

    private String[][] colorGrid;
    /* ------------------
    * | 0,0 | 0,1 | 0,2 |
    * -------------------
    * | 1,0 | 1,1 | 1,2 |
    * -------------------
    * | 2,0 | 2,1 | 2,2 |
    * -------------------  */
    private Cube homeCube;
    private Face right;
    private Face left;
    private Face up;
    private Face down;

    public Face(Cube cube, String color) {
        homeCube = cube;

        for (int row = 0; row < homeCube.getSize(); row++) {
            for (int col = 0; col < homeCube.getSize(); col++) {
                colorGrid[row][col] = color;
            }
        }

        //faces loop around
        right = this; left = this; up = this; down = this;
    }

    /*
    * RETRIEVE faces around
    * */
    public Face getRight() {return this.right;}
    public Face getLeft() {return this.left;}
    public Face getUp() {return this.up;}
    public Face getDown() {return this.down;}

    /*
    * MOVES
    * (1) TURNS face clockwise or counterclockwise
    * (2) a column/row in the face is turned
    * */
    public void clockwise() {}

    public void counterclockwise() {}

    public void turnRowClockwise(int row) {
        if (row == 0) {
            this.up.clockwise();
        } else if (row == homeCube.getSize()) {
            this.down.clockwise();
        } else {

        }
    }

}
