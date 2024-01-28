public class ThreeByThreeCube implements Cube {

    private Face top; //forces direction of other faces
    private int size;

    public ThreeByThreeCube() {
        top = new Face(this, "white");
        size = 3;
    }

    public int getSize() {
        return size;
    }

    /*
    * CUBE ROTATIONS
    * - can turn cube along any axis
    * - top face changes
    * */


}
