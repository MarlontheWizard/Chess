package board;

import common.File;
import common.Position;
import piece.Piece;

import piece.PieceColor;
import piece.PieceMap;
import boxes.Box;
import boxes.BoxColor;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** 
 * @author Marlon Dominguez
 * @author Ernie Oscar
 */

public class Board {

    private static final Integer BOARD_LENGTH = 8;
    private final Map<Position, Box> positionBoxMap;
  
    Box[][] boardBoxes = new Box[BOARD_LENGTH][BOARD_LENGTH];
  
    private final List<Piece> whitePieces = new ArrayList<>();
    private final List<Piece> blackPieces = new ArrayList<>();
  
    public Board() {
      positionBoxMap = new HashMap<>();
      Map<Position, Piece> pieces = PieceMap.getPieces();
      for(int i = 0; i < boardBoxes.length; i++) {
        int column = 0;
        BoxColor currentColor = (i % 2 == 0) ? BoxColor.LIGHT : BoxColor.DARK;
        for(File file : File.values()) {
          Box newBox = new Box(currentColor, new Position(file,  BOARD_LENGTH - i));
          if (pieces.containsKey(newBox.getPosition())) {
            Piece piece = pieces.get(newBox.getPosition());
            newBox.setCurrentPiece(piece);
            newBox.setOccupied(true);
            piece.setCurrentBox(newBox);
            if (piece.getPieceColor().equals(PieceColor.DARK)) {
              blackPieces.add(piece);
            } else {
              whitePieces.add(piece);
            }
          }
          boardBoxes[i][column] = newBox;
          positionBoxMap.put(newBox.getPosition(), newBox);
          currentColor = (currentColor == BoxColor.DARK) ? BoxColor.LIGHT : BoxColor.DARK;
          column++;
        }
      }
    }
  
    public Map<Position, Box> getPositionBoxMap() {
      return positionBoxMap;
    }
  
    public List<Piece> getWhitePieces() {
      return whitePieces;
    }
  
    public List<Piece> getBlackPieces() {
      return blackPieces;
    }
  
    public void printBoard() {
      for(int i = 0; i < boardBoxes.length; i++) {
        System.out.print(BOARD_LENGTH - i + " ");
        for(int j = 0; j < boardBoxes[i].length; j++) {
          if (boardBoxes[i][j].isOccupied()) {
            Piece piece = boardBoxes[i][j].getCurrentPiece();
            if(piece.getPieceColor().equals(PieceColor.LIGHT))
            {
              System.out.print("w" + piece.getName().charAt(0));
            }
            else
            {
              System.out.print("b" + piece.getName().charAt(0));
            }
          } else {
            // empty square
            System.out.print("- ");
          }
        }
        System.out.println();
      }
      System.out.print("  ");
      for(File file : File.values()) {
        System.out.print(file.name() + " ");
      }
      System.out.println();
    }
  
  }
	