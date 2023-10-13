package piece;

import board.Board;
import common.Position;
import boxes.Box;

import java.util.ArrayList;

import java.util.List;

public class Queen extends Piece 
{
    private Movable bishop;
    private Movable rook;

    public Queen(PieceColor pieceColor)
    {
        this(pieceColor, new Bishop(pieceColor), new Rook(pieceColor));
    }
    public Queen(PieceColor pieceColor, Movable bishop, Movable rook) 
    {
        super(pieceColor);
        this.name = "Queen";
        this.bishop = bishop;
        this.rook = rook;
    }

    @Override
    public List<Position> getValidMoves(Board board) {
      List<Position> moveCandidates = new ArrayList<>();
      moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrentBox()));
      moveCandidates.addAll(rook.getValidMoves(board, this.getCurrentBox()));
      return moveCandidates;
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
        Box current = this.getCurrentBox();
        this.setCurrentBox(box);
        current.reset();
    }
  
}
