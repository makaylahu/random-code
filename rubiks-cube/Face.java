import java.util.ArrayList;

public class Face {
    private Colors color;
    private Colors[] neighbours; //clockwise permutation
    private ArrayList<EdgePiece> edgePieces;
    private ArrayList<CornerPiece> cornerPieces; //clockwise permutation

    private static class EdgePiece {
        private Colors[] colors;

        EdgePiece(Colors[] colors) {
            this.colors = colors;
        }
    }

    private static class CornerPiece {
        private Colors[] colors;

        CornerPiece(Colors[] colors) {
            this.colors = colors;
        }
    }

    public Face (Colors c) {
        this.color = c;
        switch (c) {
            case WHITE:
                neighbours = new Colors[]{Colors.RED, Colors.GREEN, Colors.ORANGE, Colors.BLUE};
                break;
            case RED:
                neighbours = new Colors[]{Colors.BLUE, Colors.YELLOW, Colors.GREEN, Colors.WHITE};
                break;
            case BLUE:
                neighbours = new Colors[]{Colors.WHITE, Colors.ORANGE, Colors.YELLOW, Colors.RED};
                break;
            case YELLOW:
                neighbours = new Colors[]{Colors.GREEN, Colors.RED, Colors.BLUE, Colors.ORANGE};
                break;
            case GREEN:
                neighbours = new Colors[]{Colors.ORANGE, Colors.WHITE, Colors.RED, Colors.YELLOW};
                break;
            case ORANGE:
                neighbours = new Colors[]{Colors.YELLOW, Colors.BLUE, Colors.WHITE, Colors.GREEN};
                break;
        }

        for (int index = 0; index < neighbours.length; index++) {
            Colors[] edgeColors = new Colors[]{c, neighbours[index]};
            edgePieces.add (new EdgePiece (edgeColors));
        }

        for (int index = 0; index < neighbours.length - 1; index++) {
            Colors[] cornerColors = new Colors[]{c, neighbours[index], neighbours[index + 1]};
            cornerPieces.add (new CornerPiece (cornerColors));
        }
        cornerPieces.add (new CornerPiece(new Colors[]{c, neighbours[3], neighbours[0]}));
    }

    public void turn(Directions d) {
        switch (d) {
            case CLOCKWISE:
                // add mechanism
                break;
            case COUNTERCLOCKWISE:
                // add mechanism
                break;
        }
    }
    public ArrayList<EdgePiece> getEdgePieces() {
        return edgePieces;
    }

    public ArrayList<CornerPiece> getCornerPiece() {
        return cornerPieces;
    }
}
