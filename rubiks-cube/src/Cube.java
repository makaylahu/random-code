public class Cube {
    private Face top; // need two faces to force the locations of other faces
    private Face front;
    private int size;

    public Cube() {
        this.top = new Face(this, "yellow");
        this.front = new Face(this, "blue");
        addFace(front, top, "UP");

    }

    /*
     * adds a face in given direction
     * can only add in (1) UP and (2) RIGHT directions
     * */
    public void addFace(Face f1, Face f2, String direction) {
        //treat f1 as main face, adding f2 above f1
        if (direction == "UP") {
            f1.getUp().setDown(f2);
            f1.setUp(f2);
            f2.getDown().setUp(f1);
            f2.setDown(f1);
            f2.setRight(f1.getRight());
            f2.setLeft(f1.getLeft());
        }
        //treating f1 as main face, adding f2 to the right of f1
        else {
            f1.getRight().setLeft(f2);
            f1.setRight(f2);
            f2.getLeft().setRight(f1);
            f2.setLeft(f1);
            f2.setUp(f1.getUp());
            f2.setDown(f1.getDown());
        }
    }

    public int getSize() {return this.size;}
}
