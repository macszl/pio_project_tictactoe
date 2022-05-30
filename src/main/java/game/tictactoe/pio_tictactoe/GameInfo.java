package game.tictactoe.pio_tictactoe;

public class GameInfo {
    static int placedSize=50;
    static final int gridSize=280;
    static final int squareSize=90;

    static Board gameBoard;
    static PlayerType currentPlayer;

    public static final int SECTOR_UNRESTRICTED = -1;
    static int currentSector = SECTOR_UNRESTRICTED;

    public static int getCurrentSector() {
        return currentSector;
    }

    public static void setCurrentSector( int _newSector)
    {
        if(_newSector >= 0 && _newSector < 9)
            currentSector = _newSector;
    }

    static public int getGridSize()
    {
        return gridSize;
    }
    static public int getSquareSize()
    {
        return squareSize;
    }
}
