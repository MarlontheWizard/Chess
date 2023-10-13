package piece;

import board.Board;
import common.Position;
import boxes.Box;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

/** 
 * @author Marlon Dominguez
 * @author Ernie Oscar
 */
public class King extends Piece
{
    private final Movable rook;
    private final Movable bishop;
    boolean checkStatus = false;

    public King(PieceColor pieceColor, Movable bishop, Movable rook)
    {
        super(pieceColor);
        this.name = "King";
        this.bishop = bishop;
        this.rook = rook;
    }

    public King(PieceColor pieceColor)
    {
        this(pieceColor, new Bishop(pieceColor), new Rook(pieceColor));
    }

    @Override
    public List<Position> getValidMoves(Board board)
    {
        List<Position> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(rook.getValidMoves(board, this.getCurrentBox()));
        moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrentBox()));
        Position current = this.getCurrentBox().getPosition();
        return moveCandidates.stream()
        .filter(candidate -> (
            Math.abs(candidate.getFile().ordinal() - current.getFile().ordinal()) == 1 &&
                Math.abs(candidate.getRank() - current.getRank()) == 1))
        .collect(Collectors.toList());
    }

    @Override
    public List<Position> getValidMoves(Board board, Box box)
    {
        return null;
    }
    
    @Override
    public void capture(Box targetBox)
    {
    	 this.currentBox.setOccupied(false);
         
         this.setCurrentBox(targetBox);
         targetBox.setCurrentPiece(this);
    }
    
    @Override
    public void makeMove(Box box)
    {
        System.out.println(this.getName() + "-> makeMove()");
        
    }

    
    
    public boolean isCheck()
    {
        return this.checkStatus;
    }

}
