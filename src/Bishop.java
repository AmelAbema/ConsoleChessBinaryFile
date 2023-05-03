class Bishop extends Piece {
    public Bishop(int row, int col, int player) {
        super(row, col, player);
    }

    @Override
    public boolean isValidMove(int r, int c, Board board) {

        if (r < 0 || r > 7 || c < 0 || c > 7) {
            return false;
        }

        int rowDistance = Math.abs(r - this.getRow());
        int colDistance = Math.abs(c - this.getCol());
        if (rowDistance != colDistance) {
            return false;
        }

        if (board.getPiece(r, c) != null && board.getPiece(r, c).getPlayer() == this.getPlayer()) {
            return false;
        }

        int startRow = this.getRow();
        int startCol = this.getCol();
        int rowDirection = Integer.signum(r - startRow);
        int colDirection = Integer.signum(c - startCol);
        for (int i = 1; i < rowDistance; i++) {
            int row = startRow + i * rowDirection;
            int col = startCol + i * colDirection;
            if (board.getPiece(row, col) != null) {
                return false;
            }
        }

        Piece destPiece = board.getPiece(r, c);
        if (destPiece == null || destPiece.getPlayer() != this.getPlayer()) {
            return board.testMove(r, c, this);
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        if (this.getPlayer() == 0) {
            result = "wB";
        } else {
            result = "bB";
        }
        return result;
    }
}
