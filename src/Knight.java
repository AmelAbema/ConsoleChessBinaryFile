class Knight extends Piece {
    public Knight(int row, int col, int player) {
        super(row, col, player);
    }

    @Override
    public boolean isValidMove(int r, int c, Board board) {
        if (r < 0 || r > 7 || c < 0 || c > 7) {
            return false;
        }

        if (board.getPiece(r, c) != null && board.getPiece(r, c).getPlayer() == this.getPlayer()) {
            return false;
        }

        int rowDistance = Math.abs(r - this.getRow());
        int colDistance = Math.abs(c - this.getCol());

        if (!((rowDistance == 1 && colDistance == 2) || (rowDistance == 2 && colDistance == 1))) {
            return false;
        }

        return board.testMove(r, c, this);
    }

    @Override
    public String toString() {
        String result = "";
        if (this.getPlayer() == 0) {
            result = "wN";

        } else {
            result = "bN";
        }
        return result;

    }

}
