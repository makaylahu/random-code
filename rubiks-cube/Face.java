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
}
