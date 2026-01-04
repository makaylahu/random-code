import java.util.HashMap;

public class Cube {
    private HashMap<Colors, Face> faces;

    public Cube () {
        for (Colors c : Colors.values()) {
            faces.put(c, new Face(c));
        }
    }
}
