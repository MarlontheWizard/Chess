package piece;
import boxes.Box;

/** 
 * @author Marlon Dominguez
 * @author Ernie Oscar
 */


public abstract class Piece implements Movable
{
    protected String name;
    protected PieceColor pieceColor;
    protected Box currentBox;

   
    public Piece(PieceColor pieceColor)
    {
        this.pieceColor = pieceColor;
    }
    public String getName()
    {
        return name;
    }
    public PieceColor getPieceColor()
    {
        return pieceColor;
    }

    public Box getCurrentBox()
    {
        return currentBox;
    }
    public void setCurrentBox(Box currentBox)
    {
        this.currentBox = currentBox;
    }
    
 
    
    @Override
    public String toString()
    {
        return "Piece{" +
        "name='" + name + '\'' +
        ", pieceColor=" + pieceColor +
        '}';
    }

}
