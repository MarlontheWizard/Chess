package piece;

import board.Board;
import boxes.Box;
import common.Position;

import java.util.List;
/** 
 * @author Marlon Dominguez
 * @author Ernie Oscar
 */
public interface Movable 
{
    List<Position> getValidMoves(Board board);
    List<Position> getValidMoves(Board board, Box box);
    
    void capture(Box toBox);
    void makeMove(Box tobox);
}
