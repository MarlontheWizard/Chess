package piece;

import java.util.HashMap;
import java.util.Map;

import common.Position;
import common.File;

public final class PieceMap 
{
    private PieceMap()
    {

    }
    public static Map<Position, Piece> getPieces()
    {
        Map<Position, Piece> pieces = new HashMap<>();

        //Rook
        pieces.put(new Position(File.A, 1), new Rook(PieceColor.LIGHT));
        pieces.put(new Position(File.H, 1), new Rook(PieceColor.LIGHT));

        pieces.put(new Position(File.A, 8), new Rook(PieceColor.DARK));
        pieces.put(new Position(File.H, 8), new Rook(PieceColor.DARK));

        //Knight
        pieces.put(new Position(File.B, 1), new Knight(PieceColor.LIGHT));
        pieces.put(new Position(File.G, 1), new Knight(PieceColor.LIGHT));

        pieces.put(new Position(File.B, 8), new Knight(PieceColor.DARK));
        pieces.put(new Position(File.G, 8), new Knight(PieceColor.DARK));

        //Bishop
        pieces.put(new Position(File.C, 1), new Bishop(PieceColor.LIGHT));
        pieces.put(new Position(File.F, 1), new Bishop(PieceColor.LIGHT));

        pieces.put(new Position(File.C, 8), new Bishop(PieceColor.DARK));
        pieces.put(new Position(File.F, 8), new Bishop(PieceColor.DARK));

        //Queen
        pieces.put(new Position(File.D, 1), new Queen(PieceColor.LIGHT));

        pieces.put(new Position(File.D, 8), new Queen(PieceColor.DARK));

        //King
        pieces.put(new Position(File.E, 1), new King(PieceColor.LIGHT));

        pieces.put(new Position(File.E, 8), new King(PieceColor.DARK));

        //Pawns
        for(File file : File.values())
        {
            pieces.put(new Position(file, 2), new Pawn(PieceColor.LIGHT));
            pieces.put(new Position(file, 7), new Pawn(PieceColor.DARK));
        }

        return pieces;
    }
}
