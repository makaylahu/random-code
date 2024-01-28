public interface Cube {
    public int getSize();

    /*
    * CUBE ROTATIONS
    * */
    public void rotate (String direction);

    public void turnRow (Face front, int row);

    public void turnCol (Face front, int col);
}
