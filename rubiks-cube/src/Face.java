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
    }

    /*
    * MOVES
    * - turns face clockwise or counterclockwise
    * */
    public void clockwise() {

    }

    public void counterclockwise() {}
}
