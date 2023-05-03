import java.util.ArrayList;

interface Playable {
    void move(int endRow, int endCol, Board board, Piece p);

    boolean pawnPromotion(Piece p, int endRow, int endCol);

    boolean isCheck(int player);

    boolean isCheckMate(int player);

    boolean testMove(int r, int c, Piece p);

    boolean playerCanMove(int r, int c, int player);

    boolean canCastle(int endRow, int endCol, Piece p, Board board);

    ArrayList<String> getPossibleMoves(Piece p);

    String findKing(int player);

    Piece getCheckingPiece(int player);
}
