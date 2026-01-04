public class Face {
    private Colors color;
    private Colors[] neighbours; //clockwise permutation
    private EdgePiece[] edgePieces;
    private CornerPiece[] cornerPieces; //clockwise permutation

    private class EdgePiece {
        private Colors[] colors;

        EdgePiece(Colors[] colors) {
            this.colors = colors;
        }
    }

    private class CornerPiece {
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
                neighbours = new Colors[]{Colors.WHITE, Colors.BLUE, Colors.YELLOW, Colors.GREEN};
                break;
            case BLUE:
                neighbours = new Colors[]{Colors.WHITE, Colors.ORANGE, Colors.YELLOW, Colors.RED};
                break;
            case YELLOW:
                neighbours = new Colors[]{Colors.ORANGE, Colors.GREEN, Colors.RED, Colors.BLUE};
                break;
            case ORANGE:
                neighbours = new Colors[]{Colors.YELLOW, Colors.BLUE, Colors.WHITE, Colors.GREEN};
                break;
            case GREEN:
                neighbours = new Colors[]{Colors.YELLOW, Colors.ORANGE, Colors.WHITE, Colors.RED};
                break;
        }


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
    public EdgePiece[] getEdgePieces() {
        return edgePieces;
    }

    public CornerPiece[] getCornerPiece() {
        return cornerPieces;
    }
}
