import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

class Board implements Playable {

    public Piece[][] getBoard() {
        return board;
    }

    private final Piece[][] board;

    private Piece lastMovedPiece;

    private int lastMovedFromRow;

    private int lastMovedFromCol;

    public Board() {
        board = new Piece[8][8];
        init();
    }

    private void init() {
        IntStream.range(0, 8).forEach(col -> {
            board[1][col] = new Pawn(1, col, 1);
            board[6][col] = new Pawn(6, col, 0);
        });

        board[0][0] = new Rook(0, 0, 1);
        board[0][7] = new Rook(0, 7, 1);
        board[7][0] = new Rook(7, 0, 0);
        board[7][7] = new Rook(7, 7, 0);

        board[0][1] = new Knight(0, 1, 1);
        board[0][6] = new Knight(0, 6, 1);
        board[7][1] = new Knight(7, 1, 0);
        board[7][6] = new Knight(7, 6, 0);

        board[0][2] = new Bishop(0, 2, 1);
        board[0][5] = new Bishop(0, 5, 1);
        board[7][2] = new Bishop(7, 2, 0);
        board[7][5] = new Bishop(7, 5, 0);

        board[0][3] = new Queen(0, 3, 1);
        board[7][3] = new Queen(7, 3, 0);

        board[0][4] = new King(0, 4, 1);
        board[7][4] = new King(7, 4, 0);

        for (int row = 2; row < 6; row++) {
            Arrays.fill(board[row], null);
        }

    }

    public void printBoard() {
        StringBuilder result = new StringBuilder("\n");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String pieceStr;
                if (board[i][j] == null) {
                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            pieceStr = "   ";
                        } else {
                            pieceStr = "## ";
                        }
                    } else {
                        if (j % 2 == 0) {
                            pieceStr = "## ";
                        } else {
                            pieceStr = "   ";
                        }
                    }
                } else {
                    pieceStr = board[i][j].toString() + " ";
                }
                result.append(pieceStr);
            }
            result.append(8 - i).append("\n");
        }
        result.append(" a  b  c  d  e  f  g  h \n");
        System.out.println(result);
    }

    public Piece getPiece(int r, int c) {
        if (r < 0 || r > 7 || c < 0 || c > 7) {
            return null;
        }
        return board[r][c];
    }

    public int getLastRow() {
        return lastMovedFromRow;
    }

    public int getLastCol() {
        return lastMovedFromCol;
    }

    public void removePiece(Piece p) {
        board[p.getRow()][p.getCol()] = null;
    }

    public void insertPiece(int r, int c, Piece p) {
        board[r][c] = p;
    }

    public Piece getLastPiece() {
        return lastMovedPiece;
    }

    @Override
    public void move(int endRow, int endCol, Board board, Piece p) {
        if (p instanceof King && !p.getHasMoved()) {
            if (p.getPlayer() == 0) {
                if ((endRow == 7 && endCol == 6) && board.getPiece(7, 7) != null && !board.getPiece(7, 7).getHasMoved()) {
                    if (board.getPiece(7, 5) == null && board.getPiece(7, 6) == null) {
                        Piece rookTemp = board.getPiece(7, 7);
                        removePiece(p);
                        removePiece(board.getPiece(7, 7));
                        insertPiece(7, 6, p);
                        insertPiece(7, 5, rookTemp);
                        p.setRow(7);
                        p.setCol(6);
                        p.setHasMoved(true);
                        rookTemp.setHasMoved(true);
                        rookTemp.setRow(7);
                        rookTemp.setCol(5);
                    }
                }
                if ((endRow == 7 && endCol == 2) && board.getPiece(7, 0) != null && !board.getPiece(7, 0).getHasMoved()) {
                    if (board.getPiece(7, 1) == null && board.getPiece(7, 2) == null && board.getPiece(7, 3) == null) {
                        Piece rookTemp = board.getPiece(7, 0);
                        removePiece(p);
                        removePiece(board.getPiece(7, 0));
                        insertPiece(7, 2, p);
                        insertPiece(7, 3, rookTemp);
                        p.setRow(7);
                        p.setCol(2);
                        p.setHasMoved(true);
                        rookTemp.setHasMoved(true);
                        rookTemp.setRow(7);
                        rookTemp.setCol(3);
                    }
                }
            } else {
                if ((endRow == 0 && endCol == 6) && board.getPiece(0, 7) != null && !board.getPiece(0, 7).getHasMoved()) {
                    if (board.getPiece(0, 5) == null && board.getPiece(0, 6) == null) {
                        Piece rookTemp = board.getPiece(0, 7);
                        removePiece(p);
                        removePiece(board.getPiece(0, 7));
                        insertPiece(0, 6, p);
                        insertPiece(0, 5, rookTemp);
                        p.setRow(0);
                        p.setCol(6);
                        p.setHasMoved(true);
                        rookTemp.setHasMoved(true);
                        rookTemp.setRow(0);
                        rookTemp.setCol(5);
                    }
                }
                if ((endRow == 0 && endCol == 2) && board.getPiece(0, 0) != null && !board.getPiece(0, 0).getHasMoved()) {
                    if (board.getPiece(0, 1) == null && board.getPiece(0, 2) == null && board.getPiece(0, 3) == null) {
                        Piece rookTemp = board.getPiece(0, 0);
                        removePiece(p);
                        removePiece(board.getPiece(0, 0));
                        insertPiece(0, 2, p);
                        insertPiece(0, 3, rookTemp);
                        p.setRow(0);
                        p.setCol(2);
                        p.setHasMoved(true);
                        rookTemp.setHasMoved(true);
                        rookTemp.setRow(0);
                        rookTemp.setCol(3);
                    }
                }
            }
        }
        if (p.isValidMove(endRow, endCol, board)) {
            if (p instanceof Pawn && p.enPassant && getLastPiece() != null) {
                board.removePiece(board.getLastPiece());
            }

            lastMovedFromRow = p.getRow();
            lastMovedFromCol = p.getCol();
            lastMovedPiece = p;

            removePiece(p);


            if (board.getPiece(endRow, endCol) != null) {
                board.removePiece(board.getPiece(endRow, endCol));
            }

            p.setRow(endRow);
            p.setCol(endCol);
            insertPiece(endRow, endCol, p);
            p.setHasMoved(true);
        }
    }

    @Override
    public boolean canCastle(int endRow, int endCol, Piece p, Board board) {
        if (p instanceof King && !p.getHasMoved()) {
            if (p.getPlayer() == 0) {
                if ((endRow == 7 && endCol == 6) && board.getPiece(7, 7) != null && !board.getPiece(7, 7).getHasMoved()) {
                    if (board.getPiece(7, 5) == null && board.getPiece(7, 6) == null) {
                        return true;
                    }
                }
                if ((endRow == 7 && endCol == 2) && board.getPiece(7, 0) != null && !board.getPiece(7, 0).getHasMoved()) {
                    return board.getPiece(7, 1) == null && board.getPiece(7, 2) == null && board.getPiece(7, 3) == null;
                }
            } else {
                if ((endRow == 0 && endCol == 6) && board.getPiece(0, 7) != null && !board.getPiece(0, 7).getHasMoved()) {
                    if (board.getPiece(0, 5) == null && board.getPiece(0, 6) == null) {
                        return true;
                    }
                }
                if ((endRow == 0 && endCol == 2) && board.getPiece(0, 0) != null && !board.getPiece(0, 0).getHasMoved()) {
                    return board.getPiece(0, 1) == null && board.getPiece(0, 2) == null && board.getPiece(0, 3) == null;
                }
            }
        }
        return false;

    }

    @Override
    public boolean pawnPromotion(Piece p, int endRow, int endCol) {
        if (p instanceof Pawn) {
            if (p.getPlayer() == 0) {
                if (endRow == 0) {
                    return p.isValidMove(endRow, endCol, this);
                }
            } else {
                if (endRow == 7) {
                    return p.isValidMove(endRow, endCol, this);
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getPossibleMoves(Piece p) {

        ArrayList<String> moves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (p.isValidMove(i, j, this)) {
                    moves.add(i + String.valueOf(j));
                }
            }
        }

        return moves;
    }

    @Override
    public String findKing(int player) {
        Optional<Piece> king = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(piece -> piece instanceof King && piece.getPlayer() == player)
                .findFirst();
        if (king.isPresent()) {
            int row = king.get().getRow();
            int col = king.get().getCol();
            return row + String.valueOf(col);
        } else {
            return "";
        }
    }

    @Override
    public boolean isCheck(int player) {
        String location = findKing(player);
        int KingX = Integer.parseInt(location.substring(0, 1));
        int KingY = Integer.parseInt(location.substring(1));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getPlayer() != player) {
                    if (board[i][j].isValidMove(KingX, KingY, this)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isCheckMate(int player) {

        String location = findKing(player);
        int KingX = Integer.parseInt(location.substring(0, 1));
        int KingY = Integer.parseInt(location.substring(1));
        Piece king = getPiece(KingX, KingY);

        if (isCheck(player)) {
            ArrayList<String> moves = getPossibleMoves(king);
            boolean[] threatening = new boolean[moves.size()];
            for (int i = 0; i < moves.size(); i++) {
                for (int a = 0; a < 8; a++) {
                    for (int b = 0; b < 8; b++) {
                        if (this.getPiece(a, b) != null && this.getPiece(a, b).getPlayer() != player) {
                            ArrayList<String> threateningMoves = getPossibleMoves(this.getPiece(a, b));
                            for (int x = 0; x < moves.size(); x++) {
                                for (String threateningMove : threateningMoves) {
                                    if (moves.get(x).equals(threateningMove)) {
                                        threatening[x] = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            boolean kingCanMove = false;
            for (boolean b : threatening) {
                if (!b) {
                    kingCanMove = true;
                    break;
                }
            }

            if (!kingCanMove) {
                boolean finalCheckMate = true;
                Piece threat = getCheckingPiece(player);

                int rowDistance = Math.abs(KingX - threat.getRow());
                int colDistance = Math.abs(KingY - threat.getCol());
                if (threat instanceof Rook || threat instanceof Queen) {
                    int startRow = threat.getRow();
                    int startCol = threat.getCol();
                    int rowDirection = Integer.signum(KingX - startRow);
                    int colDirection = Integer.signum(KingY - startCol);
                    for (int i = 1; i < Math.max(rowDistance, colDistance); i++) {
                        int row = startRow + i * rowDirection;
                        int col = startCol + i * colDirection;
                        if (playerCanMove(row, col, player)) {
                            finalCheckMate = false;
                        }
                    }
                }

                if (threat instanceof Bishop) {
                    int startRow = threat.getRow();
                    int startCol = threat.getCol();
                    int rowDirection = Integer.signum(KingX - startRow);
                    int colDirection = Integer.signum(KingY - startCol);
                    for (int i = 1; i < rowDistance; i++) {
                        int row = startRow + i * rowDirection;
                        int col = startCol + i * colDirection;
                        if (playerCanMove(row, col, player)) {
                            finalCheckMate = false;
                        }
                    }
                }

                if (playerCanMove(threat.getRow(), threat.getCol(), player)) {
                    finalCheckMate = false;
                }
                return finalCheckMate;

            }

        }
        return false;
    }

    @Override
    public Piece getCheckingPiece(int player) {
        String position = findKing(player);
        int kingX = Integer.parseInt(position.substring(0, 1));
        int kingY = Integer.parseInt(position.substring(1));

        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(p -> p != null && p.getPlayer() != player && p.isValidMove(kingX, kingY, this))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean playerCanMove(int r, int c, int player) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getPlayer() == player) {
                    if (board[i][j].isValidMove(r, c, this)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean testMove(int r, int c, Piece p) {
        int startRow = p.getRow();
        int startCol = p.getCol();
        boolean isCheck;
        Piece temp = null;

        if (getPiece(r, c) != null && this.getPiece(r, c).getPlayer() != p.getPlayer()) {
            temp = getPiece(r, c);
        }

        insertPiece(startRow, startCol, null);
        insertPiece(r, c, p);
        p.setRow(r);
        p.setCol(c);
        isCheck = isCheck(p.getPlayer());

        if (isCheck) {
            p.setRow(startRow);
            p.setCol(startCol);
            insertPiece(r, c, null);
            insertPiece(startRow, startCol, p);
            insertPiece(r, c, temp);
            return false;
        }

        p.setRow(startRow);
        p.setCol(startCol);
        insertPiece(r, c, null);
        insertPiece(startRow, startCol, p);
        insertPiece(r, c, temp);
        return true;
    }
}
