public class ThreeByThreeCube implements Cube {

    private Face top; //forces direction of other faces
    private static final int size = 3;

    public ThreeByThreeCube() {
        top = new Face(this, "white");
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void rotate(String direction) {}
}
