import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class ChessUtil {
    public static void saveGameBoard(Piece[][] board) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("file.dat")) {
            byte[] bytes = new byte[64];
            int index = 0;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (index > 63) break;
                    Piece piece = board[row][col];
                    if (piece == null) {
                        bytes[index++] = 0;
                        continue;
                    }
                    int type = getTypeCode(piece);
                    int color = piece.getPlayer();
                    byte b = (byte) ((type << 5) | (col << 1) | color);
                    bytes[index++] = b;
                    bytes[index++] = (byte) (row << 4);

                }
            }
            fos.write(bytes);
        }
    }

    private static int getTypeCode(Piece piece) {
        if (piece instanceof King) {
            return 1;
        } else if (piece instanceof Queen) {
            return 2;
        } else if (piece instanceof Rook) {
            return 3;
        } else if (piece instanceof Bishop) {
            return 4;
        } else if (piece instanceof Knight) {
            return 5;
        } else {
            return 0;
        }
    }

    public static void loadGameBoard(Piece[][] board) throws IOException {
        try (FileInputStream fis = new FileInputStream("file.dat")) {
            byte[] bytes = fis.readAllBytes();
            int index = 0;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (index > 63) break;
                    byte b1 = bytes[index++];
                    byte b2 = bytes[index++];
                    if (b1 == 0) {
                        board[row][col] = null;
                        continue;
                    }
                    int type = (b1 & 0b11100000) >> 5;
                    int x = (b1 & 0b00011110) >> 1;
                    int y = (b2 & 0b11110000) >> 4;
                    int color = b1 & 0b00000001;
                    Piece piece = createPiece(type, x, y, color);
                    board[row][col] = piece;
                }
            }
        }
    }

    private static Piece createPiece(int type, int x, int y, int color) {
        return switch (type) {
            case 1 -> new King(y, x, color);
            case 2 -> new Queen(y, x, color);
            case 3 -> new Rook(y, x, color);
            case 4 -> new Bishop(y, x, color);
            case 5 -> new Knight(y, x, color);
            case 0 -> new Pawn(y, x, color);
            default -> null;
        };
    }
}
