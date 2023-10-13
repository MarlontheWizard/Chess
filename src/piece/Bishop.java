package piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import board.Board;
import common.Position;
import common.PositionMap;
import boxes.Box;

/** 
 * @author Marlon Dominguez
 * @author Ernie Oscar
 */

public class Bishop extends Piece
{
    public Bishop(PieceColor pieceColor)
    {
        super(pieceColor);
        this.name = "Bishop";
    }

    @Override
    public List<Position> getValidMoves(Board board)
    {
        List<Position> moveCandidates = new ArrayList<>();
        Map<Position, Box> boxMap = board.getPositionBoxMap();
        Position current = this.getCurrentBox().getPosition();
        getMoves(moveCandidates, boxMap, current, 1, 1);
        getMoves(moveCandidates, boxMap, current, 1, -1);
        getMoves(moveCandidates, boxMap, current, -1, -1);
        getMoves(moveCandidates, boxMap, current, -1, 1);
        return moveCandidates;
    }

    private void getMoves(List<Position> candidates,Map<Position, Box> boxMap,Position current,int rankOffset,int fileOffset) 
    {
        try {
            Position next = PositionMap.build(current, fileOffset, rankOffset);
            while (boxMap.containsKey(next)) 
            {
                if (boxMap.get(next).isOccupied()) 
                {
                    if (boxMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)) break;
                    candidates.add(next);
                    break;
                }
                candidates.add(next);
                next = PositionMap.build(next, fileOffset, rankOffset);
            }
    } catch (Exception e) 
    { 

    }
  }

  @Override
  public List<Position> getValidMoves(Board board, Box box) {
    List<Position> moveCandidates = new ArrayList<>();
    Map<Position, Box> boxMap = board.getPositionBoxMap();
    Position current = box.getPosition();
    getMoves(moveCandidates, boxMap, current, 1, 1);
    getMoves(moveCandidates, boxMap, current, 1, -1);
    getMoves(moveCandidates, boxMap, current, -1, -1);
    getMoves(moveCandidates, boxMap, current, -1, 1);
    return moveCandidates;
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
