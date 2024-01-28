public class ThreeByThreeCube implements Cube {

    private Face top; // need two faces to force the locations of other faces
    private Face front;
    private static final int size = 3;

    public ThreeByThreeCube() {
        top = new Face(this, "yellow");
        front = new Face(this, "blue");
    }

    @Override
    public int getSize() {return size;}

    @Override
    public void rotate(String direction) {}
}
