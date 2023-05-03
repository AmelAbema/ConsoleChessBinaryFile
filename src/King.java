class King extends Piece {

    public King(int row, int col, int player) {
        super(row, col, player);
    }

    @Override
    public boolean isValidMove(int r, int c, Board board) {
        if (r < 0 || r > 8 || c < 0 || c > 8) {
            return false;
        }

        if (board.getPiece(r, c) != null && board.getPiece(r, c).getPlayer() == this.getPlayer()) {
            return false;
        }

        int rowDistance = Math.abs(r - this.getRow());
        int colDistance = Math.abs(c - this.getCol());

        if (rowDistance > 1 || colDistance > 1) {
            return false;
        }

        return board.testMove(r, c, this);
    }

    @Override
    public String toString() {
        String result = "";
        if (this.getPlayer() == 0) {
            result = "wK";

        } else {
            result = "bK";
        }
        return result;
    }

}
