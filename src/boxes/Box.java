package boxes;
import piece.Piece;

/** 
 * @author Marlon Dominguez
 * @author Ernie Oscar
 */


/**Box class
 * -Responsible for representing each cell of the chessboard as a polymorphic object. 
 */
import common.Position;
public class Box 
{

    private final BoxColor boxColor;
    private final Position position;
    private boolean isOccupied;
    private Piece currentPiece;
    
    
    public Box(BoxColor boxColor, Position position)
    {
        this.boxColor = boxColor;
        this.position = position;
        this.isOccupied = false;
    }

    public void reset()
    {
        this.isOccupied = false;
        this.currentPiece = null;
    }

    public Piece getCurrentPiece()
    {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece)
    {
        this.currentPiece = currentPiece;
        setOccupied(true);
    }

    public boolean isOccupied()
    {
        return isOccupied;
    }

    public void setOccupied(boolean occupied)
    {
        isOccupied = occupied;
    }

    public BoxColor getBoxColor()
    {
        return boxColor;
    }

    public Position getPosition()
    {
        return this.position;
    }

    @Override
    public String toString()
    {
        return "Box{" + "boxColor=" + boxColor + ", postion=" + position + ", isOccupied=" + isOccupied + ", currentPiece=" + currentPiece + '}';
    }
}
