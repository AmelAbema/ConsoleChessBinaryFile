import java.io.IOException;
import java.util.Scanner;

class Game {

    private final Board board;

    char[] fileRank = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    int[] positionsNum = {0, 1, 2, 3, 4, 5, 6, 7};

    int player = 0;

    boolean invalidMove = false;

    boolean resign = false;

    boolean draw = false;

    boolean castleCheck = false;

    boolean pawnPromotionCheck = false;

    public Game() throws IOException {
        this.board = new Board();
        start();
    }

    public void start() throws IOException {

        Scanner scan = new Scanner(System.in);

        boolean isCheckMateWhite = board.isCheckMate(0);
        boolean isCheckMateBlack = board.isCheckMate(1);

        while (!isCheckMateWhite && !isCheckMateBlack && !resign) {
            board.printBoard();

            if (board.isCheck(0) || board.isCheck(1)) {
                System.out.println("Check");
            }
            if (invalidMove) {
                System.out.println("Illegal move, try again.");
            }
            invalidMove = false;
            castleCheck = false;
            pawnPromotionCheck = false;


            if (player == 0) {
                System.out.print("White's Move: ");
            }
            if (player == 1) {
                System.out.print("Black's Move: ");
            }

            String input = scan.nextLine();

            if (draw) {
                break;
            }

            String[] arrPositions = input.split(" ");

            if (arrPositions.length == 1 && arrPositions[0].equalsIgnoreCase("resign")) {
                if (player == 0) {
                    System.out.println("Black Wins!");
                } else {
                    System.out.println("White Wins!");
                }
                resign = true;
            }

            if (arrPositions.length == 2 || (arrPositions.length == 3 && !arrPositions[2].equalsIgnoreCase("draw?"))) {
                char startRank = arrPositions[0].charAt(0);
                char endRank = arrPositions[1].charAt(0);

                int startRow = 8 - Integer.parseInt(arrPositions[0].substring(1));
                int endRow = 8 - Integer.parseInt(arrPositions[1].substring(1));
                int startCol = 0;
                int endCol = 0;
                for (int i = 0; i < 8; i++) {
                    if (startRank == fileRank[i]) {
                        startCol = positionsNum[i];
                    }
                    if (endRank == fileRank[i]) {
                        endCol = positionsNum[i];
                    }
                }

                if (board.getPiece(startRow, startCol) == null || board.getPiece(startRow, startCol).getPlayer() != player) {
                    invalidMove = true;
                }

                if (!invalidMove) {
                    Piece p = board.getPiece(startRow, startCol);
                    boolean castle = board.canCastle(endRow, endCol, p, board);
                    if (board.pawnPromotion(p, endRow, endCol) && arrPositions.length == 2) {
                        board.move(endRow, endCol, board, p);
                        Piece queen = new Queen(p.getRow(), p.getCol(), p.getPlayer());
                        queen.setHasMoved(p.getHasMoved());
                        board.insertPiece(p.getRow(), p.getCol(), queen);
                        pawnPromotionCheck = true;
                    } else if (arrPositions.length == 3 && board.pawnPromotion(p, endRow, endCol)) {
                        char upgrade = arrPositions[2].charAt(0);
                        if (upgrade == 'N' || upgrade == 'n') {
                            board.move(endRow, endCol, board, p);
                            Piece knight = new Knight(p.getRow(), p.getCol(), p.getPlayer());
                            knight.setHasMoved(p.getHasMoved());
                            board.insertPiece(p.getRow(), p.getCol(), knight);
                            pawnPromotionCheck = true;
                        }
                        if (upgrade == 'B' || upgrade == 'b') {
                            board.move(endRow, endCol, board, p);
                            Piece bishop = new Bishop(p.getRow(), p.getCol(), p.getPlayer());
                            bishop.setHasMoved(p.getHasMoved());
                            board.insertPiece(p.getRow(), p.getCol(), bishop);
                            pawnPromotionCheck = true;

                        }
                        if (upgrade == 'Q' || upgrade == 'q') {
                            board.move(endRow, endCol, board, p);
                            Piece queen = new Queen(p.getRow(), p.getCol(), p.getPlayer());
                            queen.setHasMoved(p.getHasMoved());
                            board.insertPiece(p.getRow(), p.getCol(), queen);
                            pawnPromotionCheck = true;

                        }
                        if (upgrade == 'R' || upgrade == 'r') {
                            board.move(endRow, endCol, board, p);
                            Piece rook = new Rook(p.getRow(), p.getCol(), p.getPlayer());
                            rook.setHasMoved(p.getHasMoved());
                            board.insertPiece(p.getRow(), p.getCol(), rook);
                            pawnPromotionCheck = true;
                        }
                    } else if (castle) {
                        board.move(endRow, endCol, board, p);
                        castleCheck = true;
                    } else if (p.isValidMove(endRow, endCol, board)) {
                        board.move(endRow, endCol, board, p);
                    } else {
                        invalidMove = true;
                    }
                }
                if (!invalidMove || castleCheck || pawnPromotionCheck) {
                    if (player == 0) {
                        player = 1;
                    } else {
                        player = 0;
                    }
                }
            }

            if (arrPositions.length == 3) {
                if (arrPositions[2].equalsIgnoreCase("draw?")) {
                    draw = true;
                    if (player == 0) {
                        player = 1;
                    } else {
                        player = 0;
                    }
                }
            }
            isCheckMateWhite = board.isCheckMate(0);
            isCheckMateBlack = board.isCheckMate(1);

            System.out.println("For save/load press s/l: \n");
            char sl = scan.nextLine().trim().toLowerCase().charAt(0);
            if (sl == 's') ChessUtil.saveGameBoard(this.board.getBoard());
            if (sl == 'l') ChessUtil.loadGameBoard(this.board.getBoard());
        }

        if (draw) {
            board.printBoard();
            System.out.println("Draw");
        }
        if (isCheckMateWhite) {
            board.printBoard();
            System.out.println("Checkmate \nBlack Wins!");
        }
        if (isCheckMateBlack) {
            board.printBoard();
            System.out.println("Checkmate \nWhite Wins!");
        }

        scan.close();
    }

}
