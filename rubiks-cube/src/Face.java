public class Face {
    //gahhh need to reorganize this code.... i don't think this will work
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

        //faces loop around (like doubly linked lists)
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
    * SET faces around this face
    * */
    public void setRight(Face f) {this.right = f;}
    public void setLeft(Face f) {this.left = f;}
    public void setUp(Face f) {this.up = f;}
    public void setDown(Face f) {this.down = f;}

    /*
    * ADDS a face in given direction (RIGHT, LEFT, UP, DOWN)
    * */
    public void addFace(Face f, String direction) {

    }

    /*
    * ROTATES cube so that this face is in the front
    * */
    public void rotateThisFront() {
        if (this.homeCube.getFront().equals(this)) {return;}
    }

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
