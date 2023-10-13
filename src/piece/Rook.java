package piece;

import board.Board;

import common.Position;
import common.PositionMap;
import boxes.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 
 * @author Marlon Dominguez
 * @author Ernie Oscar
 */

public class Rook extends Piece
{
    public Rook(PieceColor pieceColor)
    {
        super(pieceColor);
        this.name = "Rook";
    }    

    @Override
    public List<Position> getValidMoves(Board board)
    {
        List<Position> moveCandidates = new ArrayList<>();
        Map<Position, Box> boxMap = board.getPositionBoxMap();
        Position current = this.getCurrentBox().getPosition();
        getFileCandidates(moveCandidates, boxMap, current, -1);
        getFileCandidates(moveCandidates, boxMap, current, 1);
        getRankCandidates(moveCandidates, boxMap, current, -1);
        getRankCandidates(moveCandidates, boxMap, current, 1);

        return moveCandidates;
    }

    private void getRankCandidates(List<Position> moveCandidates, Map<Position, Box> boxMap, Position current, int offset)
    {
        try
        {
            Position next = PositionMap.build(current, current.getFile().ordinal(), offset);
            while(boxMap.containsKey(next))
            {
                if(boxMap.get(next).isOccupied())
                {
                    if(boxMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor))
                    {
                        break;
                    }
                    moveCandidates.add(next);
                    break;
                }
                moveCandidates.add(next);
                next = PositionMap.build(next, next.getFile().ordinal(), offset);
            }
        } catch(Exception e)
        {

        }
    } 

    private void getFileCandidates(List<Position> moveCandidates, Map<Position, Box> boxMap, Position current, int offset)
    {
        try
        {
            Position next = PositionMap.build(current, offset, 0);
            while(boxMap.containsKey(next))
            {
                if(boxMap.get(next).isOccupied())
                {
                    if(boxMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor))
                    {
                        break;
                    }
                    moveCandidates.add(next);
                    break;
                }
                moveCandidates.add(next);
                next = PositionMap.build(next, offset, 0);
            }
        } catch(Exception e)
        {

        }
    }

    @Override
    public List<Position> getValidMoves(Board board, Box box)
    {
        List<Position> moveCandidates = new ArrayList<>();
        Map<Position, Box> boxMap = board.getPositionBoxMap();
        Position current = box.getPosition();
        getFileCandidates(moveCandidates, boxMap, current, -1);
        getFileCandidates(moveCandidates, boxMap, current, 1);
        getRankCandidates(moveCandidates, boxMap, current, -1);
        getRankCandidates(moveCandidates, boxMap, current, 1);
        return moveCandidates;

    }

   
    @Override
    public void capture(Box targetBox)
    {
    	 Piece targetPiece = targetBox.getCurrentPiece();
    	 if(targetPiece.getName().equals("King")) {
    		 System.out.println("Checkmate");
    		 if(targetPiece.getPieceColor() == PieceColor.LIGHT) {
    			 System.out.println("Black wins");
    			 
    		 }
    		 else {System.out.println("White wins");}
    		 
    		 return;
    	 }
    	 this.currentBox.setOccupied(false);
         
         this.setCurrentBox(targetBox);
         targetBox.setCurrentPiece(this);
    }
    
    @Override
    public void makeMove(Box box)
    {
        this.currentBox.setOccupied(false);
        this.setCurrentBox(box);
        box.setCurrentPiece(this);
        box.setOccupied(true);
    }
}
