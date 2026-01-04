public class Cube {
    private Face[] faces;

    public Cube () {
        faces = new Face[6];
        int index = 0;

        for (Colors c : Colors.values()) {
            faces[index] = new Face(c);
        }
    }
}
