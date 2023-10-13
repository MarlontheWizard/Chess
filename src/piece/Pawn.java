package piece;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import board.Board;
import common.Position;
import common.PositionMap;
import boxes.Box;

public class Pawn extends Piece
{
    private boolean isFirstMove = true;

    public Pawn(PieceColor pieceColor)
    {
        super(pieceColor);
        this.name = "Pawn";
    }

    @Override
    public List<Position> getValidMoves(Board board)
    {
        List<Position> moveCandidates = new ArrayList<>();
        Position current = this.getCurrentBox().getPosition();
        int sign = (pieceColor.equals(PieceColor.DARK)) ? - 1 : 1;
        moveCandidates.add(PositionMap.build(current, 0, sign));

        if(isFirstMove)
        {
            moveCandidates.add(PositionMap.build(current, 0, 2 * sign));
            return moveCandidates;
        }

        moveCandidates.add(PositionMap.build(current, 1, sign));
        moveCandidates.add(PositionMap.build(current, -1, sign));
        Map<Position, Box> boxMap = board.getPositionBoxMap();
        List<Position> validMoves = moveCandidates.stream().filter(boxMap :: containsKey).collect(Collectors.toList());

        return validMoves.stream().filter((candidate) -> {
            // occupied
            if(candidate.getFile().equals(this.getCurrentBox().getPosition().getFile()) &&
                boxMap.get(candidate).isOccupied()) {
              return false;
            }
      
            // occupied in front.
            if (boxMap.get(candidate).isOccupied() &&
                candidate.getFile().equals(this.getCurrentBox().getPosition().getFile())) {
              return false;
            }
      
            // occupied on diagonal with opposite color
            if (boxMap.get(candidate).isOccupied() &&
                boxMap.get(candidate).getCurrentPiece().getPieceColor().equals(this.getPieceColor()) &&
                candidate.getFile().equals(this.getCurrentBox().getPosition().getFile())
            ) {
              return false;
            }
      
            if (!boxMap.get(candidate).isOccupied() &&
                !candidate.getFile().equals(this.getCurrentBox().getPosition().getFile())) {
              return false;
            }
      
            return true;
          }).collect(Collectors.toList());
    }

    @Override
    public List<Position> getValidMoves(Board board, Box box)
    {
        return null;
    }

    @Override
    public void capture(Box targetBox)
    {
    	if(isFirstMove)
        {
            isFirstMove = false;
        }
        this.currentBox.setOccupied(false);
       
        this.setCurrentBox(targetBox);
        targetBox.setCurrentPiece(this);
        
    }
    
    
    @Override
    public void makeMove(Box target_box)
    {
        if(isFirstMove)
        {
            isFirstMove = false;
        }
        this.currentBox.setOccupied(false);
        this.setCurrentBox(target_box);
        target_box.setCurrentPiece(this);
        target_box.setOccupied(true);
    }


}