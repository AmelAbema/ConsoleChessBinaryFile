interface Movable {
    boolean isValidMove(int X, int Y, Board board);

    boolean getHasMoved();

    void setHasMoved(boolean hasMoved);
}
