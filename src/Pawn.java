class Pawn extends Piece {

    public Pawn(int row, int col, int player) {
        super(row, col, player);
    }

    @Override
    public boolean isValidMove(int r, int c, Board board) {
        enPassant = false;

        if (r < 0 || r > 7 || c < 0 || c > 7) {
            return false;
        }

        if (r == this.getRow() && c == this.getCol()) {
            return false;
        }

        if (Math.abs(r - this.getRow()) > 2) {

            return false;
        }

        if (this.getHasMoved() && Math.abs(r - this.getRow()) >= 2) {
            return false;
        }

        if (board.getPiece(r, c) != null && board.getPiece(r, c).getPlayer() == this.getPlayer()) {
            return false;
        }


        int rowDistance = r - this.getRow();
        int colDistance = Math.abs(c - this.getCol());

        if (this.getPlayer() == 0) {
            if (rowDistance >= 0 || colDistance > 1) {
                return false;
            }
        } else {
            if (rowDistance <= 0 || colDistance > 1) {
                return false;
            }
        }

        boolean test = board.testMove(r, c, this);

        if (board.getLastPiece() != null && board.getLastPiece() instanceof Pawn && this.getRow() == board.getLastPiece().getRow() && Math.abs(this.getCol() - board.getLastPiece().getCol()) == 1 && Math.abs(board.getLastPiece().getRow() - board.getLastRow()) == 2 && c == board.getLastPiece().getCol()) {
            enPassant = true;
        }

        if (enPassant) {
            if (Math.abs(r - board.getLastPiece().getRow()) == 1 && (c == board.getLastPiece().getCol())) {
                if (board.getPiece(r, c) == null) {
                    return test;
                } else if (board.getPiece(r, c).getPlayer() != this.getPlayer()) {
                    return test;
                } else {
                    return false;
                }
            }
        }

        if (Math.abs(this.getRow() - r) == 1 && c == this.getCol() && board.getPiece(r, c) == null) {
            return test;
        }

        if (colDistance > 0) {
            if (Math.abs(this.getRow() - r) == 1 && Math.abs(c - this.getCol()) == 1 && board.getPiece(r, c) != null && board.getPiece(r, c).getPlayer() != this.getPlayer()) {
                return test;
            } else {
                return false;
            }
        }

        if (!this.getHasMoved()) {
            if (this.getPlayer() == 0) {
                if (board.getPiece(this.getRow() - 1, c) != null || board.getPiece(this.getRow() - 2, c) != null) {
                    return false;
                } else {
                    return test;
                }
            } else {
                if (board.getPiece(this.getRow() + 1, c) != null || board.getPiece(this.getRow() + 2, c) != null) {
                    return false;
                } else {
                    return test;
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        String result = "";
        if (this.getPlayer() == 0) {
            result = "wP";

        } else {
            result = "bP";
        }
        return result;

    }
}
