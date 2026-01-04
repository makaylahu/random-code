public class Face {
    private EdgePiece[] edgePieces;
    private CornerPiece[] cornerPieces;

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

    }

    public void turn(Directions d) {
        switch (d) {
            case CLOCKWISE:
                break;
            case COUNTERCLOCKWISE:
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
