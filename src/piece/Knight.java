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

public class Knight extends Piece
{
    public Knight(PieceColor pieceColor)
    {
        super(pieceColor);
        this.name = "NKnight";
    }

    @Override
    public List<Position> getValidMoves(Board board)
    {
        List<Position> moveCandidates = new ArrayList<>();
        Map<Position, Box> boxMap = board.getPositionBoxMap();
        Position current = this.getCurrentBox().getPosition();
        getMoves(moveCandidates, boxMap, current, 2, 1);
        getMoves(moveCandidates, boxMap, current, 2, -1);
        getMoves(moveCandidates, boxMap, current, -2, 1);
        getMoves(moveCandidates, boxMap, current, -2, -1);
        getMoves(moveCandidates, boxMap, current, 1, 2);
        getMoves(moveCandidates, boxMap, current, -1, -2);
        getMoves(moveCandidates, boxMap, current, 1, -2);
        getMoves(moveCandidates, boxMap, current, -1, 2);
        return moveCandidates;
    }

    private void getMoves(List<Position> candidates, Map<Position, Box> boxMap, Position current, int rankOffset, int fileOffset)
    {
        try
        {
            Position next = PositionMap.build(current, fileOffset, rankOffset);
            if(boxMap.containsKey(next))
            {
                if(boxMap.get(next).isOccupied())
                {
                    if(boxMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor))
                    {
                        return;
                    }
                    candidates.add(next);
                    return;
                }
                candidates.add(next);
            }
        }catch(Exception e)
        {

        }
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
        this.currentBox.setOccupied(false);
        this.setCurrentBox(box);
        box.setCurrentPiece(this);
        box.setOccupied(true);
    }
}
