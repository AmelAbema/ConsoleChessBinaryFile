abstract class Piece implements Movable {
    private int row;
    private int col;
    private int player;
    private boolean hasMoved;
    public boolean enPassant;

    public Piece(int row, int col, int player) {
        this.row = row;
        this.col = col;
        this.player = player;
        this.hasMoved = false;
    }


    public int getRow() {
        return row;
    }

    @Override
    public boolean getHasMoved() {
        return hasMoved;
    }

    public int getCol() {
        return col;
    }

    public int getPlayer() {
        return player;
    }

    public void setRow(int r) {
        row = r;
    }

    public void setCol(int c) {
        col = c;
    }

    public void setPlayer(int p) {
        player = p;
    }

    @Override
    public void setHasMoved(boolean val) {
        hasMoved = val;
    }

    public abstract String toString();

}
