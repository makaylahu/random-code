public class Cube {
    private Face top; // need two faces to force the locations of other faces
    private Face front;
    private int size;

    public Cube() {
        this.top = new Face(this, "yellow");
        this.front = new Face(this, "blue");
        addFace(front, top, "UP");
        addFace(front, new Face(this, "red"), "RIGHT");
    }

    /*
     * adds a face in given direction
     * can only add in (1) UP and (2) RIGHT directions
     *
     * thoughts - do not need to specify what main face is? --> front face, or else rotations wont work
     * move this to face.java - rotations still wouldn't work
     * I DONT WANNA WRITE THIS CODE 4 TIMES
     * */
    public void addFace(Face f1, Face f2, String direction) {
        int numRotations = 0;
        try {
            if (direction == "UP") {numRotations = 0;}
            else if (direction == "RIGHT") {numRotations = 1;}
            else if (direction == "DOWN") {numRotations = 2;}
            else if (direction == "LEFT") {numRotations = 3;}
        } catch (Exception e) {
            System.out.println("Can only add Face in UP, RIGHT, DOWN, or LEFT direction");
        }
        for (int rep = 0; rep < numRotations; rep++) {
            rotateCube("x","counterclockwise");
        }

        f1.getUp().setDown(f2);
        f1.setUp(f2);
        f2.getDown().setUp(f1);
        f2.setDown(f1);

        f2.getRight().setLeft(f1.getRight());
        f2.setRight(f1.getRight());
        f1.getRight().getUp().setDown(f2);
        f1.getRight().setUp(f2);

        f2.getLeft().setRight(f1.getLeft());
        f2.setLeft(f1.getLeft());
        f1.getLeft().getUp().setDown(f2);
        f1.getLeft().setUp(f2);
    }

    /*
    * ROTATES cube with given axis as AXIS OF SYMMETRY (X, Y, Z)
    *  - X pointing FRONT, Y pointing RIGHT, Z pointing UP
    * also needs a direction (POS, NEG) - follows polar angles on plane where axis=0
    * */
    public void rotateCube(String axis, String direction) {

    }

    public int getSize() {return this.size;}
    public Face getFront() {return this.front;}
    public Face getTop() {return this.top;}
}
