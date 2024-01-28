public class GeneralCube implements Cube {
    private int size;

    public GeneralCube(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void rotate(String direction) {

    }

    @Override
    public void turnRow(Face front, int row) {

    }

    @Override
    public void turnCol(Face front, int col) {

    }
}
