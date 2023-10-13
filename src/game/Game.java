package game;

import board.Board;
import common.File;
import common.Position;
import piece.Movable;
import piece.Piece;
import piece.Pawn;
import piece.Knight;
import piece.*;
import piece.PieceColor;
import boxes.Box;


import java.util.List;
import java.util.Map;
import java.util.Scanner;


/** 
 * @author Marlon Dominguez
 * @author Ernie Oscar
 */

/**
 * Game class contains our main method and
 * manages the control flow of our chess game handling 
 * player one and player two with while loops. 
 */


public class Game 
{
    public static void main(String[] args)
    {
    	
    	
    	/**
    	 * The board object, of the Board type, has
    	 * @param NONE
    	 */
        Board board = new Board();
 
        
        //@return 8x8 updated chessboard in command prompt
        board.printBoard();
        
        
        boolean whiteTurn = true;
        boolean blackTurn = false;
        boolean gameActive = true;
        boolean initiateDraw = false;
        boolean gameDraw = false;
        
        
        /**Scanner must be closed after each use. If not, will will overfill our heap.
         * @param input stream class
         * @returns scanner object to process input/output
         */
        
        Scanner scanner = new Scanner(System.in);
        
        //
        while(gameActive)
        {   
        	
        	/**
        	 * The while loop here handles and processes the requests made by 
        	 * the player who is in control of white pieces. 
        	 * @param whiteTurn establishes that it is Player X's turn. 
        	 */
            while(whiteTurn)
            {
            	
                do{
                   
                    System.out.println("White Turn");

                    //User input 
                    String line = scanner.nextLine();
                    
                    String[] fromTo = line.split (" ");
                    
                    //draw check
                    
                    if(initiateDraw == true)
                    {
                        /**if: player accepts draw then game ends
                         * else: the game does not end, draw is not valid. 
                         */
                        if(fromTo[0].equalsIgnoreCase("draw"))
                        {
                            gameDraw = true;
                            System.out.println("Draw accepted. Game ended in a draw");
                            whiteTurn = false;
                            blackTurn = false;
                            break;
                        }
                        //else game contines
                        else
                        {
                            gameDraw = false;
                            initiateDraw = false;
                            System.out.println("Draw not Accepted. Game continues");
                        }
                    }

                    /**
                     * @param Character
                     * @returns 
                     */
                    File fromFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
                    
                    /**
                     * @param String
                     * @returns rank
                     */
                    int fromRank = Integer.parseInt(String.valueOf(fromTo[0].charAt(1)));
                    
                    
                    File toFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
                    int toRank = Integer.parseInt(String.valueOf(fromTo[1].charAt(1)));


                    int inputLength = fromTo.length;
                    if(inputLength == 3)
                    {
                        String drawPrompt = fromTo[2];
                        //Check to see if current player offers a draw
                        if(drawPrompt != null)
                        {
                            if(drawPrompt.equalsIgnoreCase("draw?"))
                            {
                                initiateDraw = true;
                            }
                            else
                            {
                                drawPrompt = null;
                            }
                        }
                        
                    }
                    
                    
                    
                 

                    /**
                     * fromBox represents the cell containing information about our current piece
                     * toBox represents the cell containing information about our current target piece
                     */
                    Box fromBox = board.getPositionBoxMap().get(new Position(fromFile, fromRank)); //current box
                    Box toBox = board.getPositionBoxMap().get(new Position(toFile,toRank)); //target box

                  
                   
                    /**
                     * To obtain current piece, use the method getCurrentPiece() to retrieve piece at current cell
                     */
                    
                    Piece currentPiece = fromBox.getCurrentPiece(); //current piece
                    String pieceType = currentPiece.getName();  //current piece's name 
                    
                    
                    /**we must  now validate and process chosen move.
                     * 
                     * A Switch statement is used to efficiently control the logic flow in an easy manner.
                     * Each case in our Switch statement will create a new piece object, ready to be placed
                     * Into our chessboard.
                     * @param variable to store piece object created in selected case
                     */
     
                    switch(pieceType)
                    {
                        case "Pawn":
                            System.out.println("Pawn Selected");
                            Pawn pawn = new Pawn(currentPiece.getPieceColor());
                            currentPiece = pawn;
                            break;
                        case "NKnight":
                            System.out.println("Knight Selected");
                            Knight knight = new Knight(currentPiece.getPieceColor());
                            currentPiece = knight;
                            break;
                        case "Rook":
                            System.out.println("Rook Selected");
                            Rook rook = new Rook(currentPiece.getPieceColor());
                            currentPiece = rook;
                            break;
                        case "Bishop":
                            System.out.println("Bishop Selected");
                            Bishop bishop = new Bishop(currentPiece.getPieceColor());
                            currentPiece = bishop;
                            break;
                        case "King":
                            System.out.println("King Selected");
                            King king = new King(currentPiece.getPieceColor());
                            currentPiece = king;
                            break;
                        case "Queen":
                            System.out.println("Queen Selected");
                            Queen queen = new Queen(currentPiece.getPieceColor());
                            currentPiece = queen;
                            break;
                    }
                     
                   /* The validate method iterates through an Arraylist of all possible moves that a particular piece can
                    * justify. If the targeted location inputted by the user is contained inside of this Arraylist, then 
                    * the move was valid. 
                    */
                    if(!Validate(board, fromBox, toBox)){
                    	 board.printBoard();
                    	 System.out.println("Illegal Move, try again");
                    	 
                         continue;	
                    }
                    
                    /**Check if selected piece is White
                     * @param color of the piece, which also indicates the player (Light or Dark)
                     */
                    if(currentPiece.getPieceColor().equals(PieceColor.LIGHT))
                    {
                        
                   
                        /*Check if target position is occupied
                         *Remember, our toBox object represents information about target piece.
                         */
                        if(toBox.isOccupied())
                        {
                        	/**If target position is occupied then check if piece in position is of the same color
                        	 * Edge case that handles when a potential location is occupied with the user or enemy's piece.
                        	 * @param piece color
                        	 */
                        	if(toBox.getCurrentPiece().getPieceColor() == PieceColor.LIGHT) {
                        		 board.printBoard();
                        		 System.out.println("Illegal Move, try again");
                                 continue;	
                        	}
             

                            /**Else: Capture the piece located at target location
                             * @param toBox
                             */
                            else
                            {
                            	//Validate moves --> Check, and 
                                fromBox.getCurrentPiece().capture(toBox);
                                if(isCheck(board, fromBox)) {
                                	System.out.println("Check");
                                }
                                
                                /** reprint the board
                                 *  @param none
                                 */
                                board.printBoard();
                                break;
                            }
                        }
                        
                        /**Else: A capture is uneccessary. Since our target location is not occupied, a simple move -> makeMove()
                         * 		 will suffice. 
                         */
                        
                        else
                        {
                          
                            fromBox.getCurrentPiece().makeMove(toBox);
                            board.printBoard();
                            if(isCheck(board, fromBox)) {
                            	System.out.println("Check");
                            }
                            
                            break;
                        }
                    }
                    else
                    {
                        board.printBoard();
                      
                        System.out.println("Invalid Move");
                    }
                }while(whiteTurn);
                whiteTurn = false;
                blackTurn = true;
            }
            
            
            /**While loop to handle and process player two's requests. 
             * The code for the Player controlling the Dark pieces may seem redundant to player one's 
             * implementation. 
             * @param piece color, which identifies user.
             */
            while(blackTurn)
            {
                do{
                    System.out.println("Black Turn");
                    String line = scanner.nextLine();
                    String[] fromTo = line.split (" ");
                    
                    //draw check
                    if(initiateDraw == true)
                    {
                        //if player accepts draw then game ends
                        if(fromTo[0].equalsIgnoreCase("draw"))
                        {
                            gameDraw = true;
                            System.out.println("Draw accepted. Game ended in a draw");
                            whiteTurn = false;
                            blackTurn = false;
                            break;
                        }
                        //else game contines
                        else
                        {
                            gameDraw = false;
                            initiateDraw = false;
                            System.out.println("Draw not Accepted. Game continues");
                        }
                    }


                    File fromFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
                    int fromRank = Integer.parseInt(String.valueOf(fromTo[0].charAt(1)));
                    File toFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
                    int toRank = Integer.parseInt(String.valueOf(fromTo[1].charAt(1)));


                    int inputLength = fromTo.length;
                    
                    
                    
                    if(inputLength == 3)
                    {
                        String drawPrompt = fromTo[2];
                        //Check to see if current player offers a draw
                        if(drawPrompt != null)
                        {
                            if(drawPrompt.equalsIgnoreCase("draw?"))
                            {
                                initiateDraw = true;
                            }
                            else
                            {
                                drawPrompt = null;
                            }
                        }
                        
                    }
                    
                    
                    
              

                    Box fromBox = board.getPositionBoxMap().get(new Position(fromFile, fromRank));
                    Box toBox = board.getPositionBoxMap().get(new Position(toFile,toRank));
                    
                   
                 
                   
                   
                    if(!Validate(board, fromBox, toBox)){
                    	board.printBoard();
                   	    System.out.println("Illegal Move, try again");
                   	   
                        continue;	
                    }
                    
                    if(fromBox.getCurrentPiece()!= null && fromBox.getCurrentPiece().getPieceColor().equals(PieceColor.DARK) )
                    {
                        fromBox.getCurrentPiece().makeMove(toBox);
                        board.printBoard();
                        if(isCheck(board, fromBox)) {
                        	System.out.println("Check");
                        }
                        break;
                    }
                    else
                    {
                        board.printBoard();
                        System.out.println("Illegal Move, try again");
                        continue;
                    }
                }while(blackTurn);
                blackTurn = false;
                whiteTurn = true;
            }




        }
        /**King has been captured, game is over.
         * CHECKMATE!
         */
        System.out.println("Game Over");


    }

    public static void printPiece(Movable piece)
    {
        piece.getValidMoves(null);
    }
    
    public static Position getKingBox(Board board) {
    	
    
    	
    	
    	Map<Position, Box> positionMap = board.getPositionBoxMap();
    	for(Map.Entry<Position, Box> positions : positionMap.entrySet()) {
    		Box cell = positions.getValue();
    		Piece piece = cell.getCurrentPiece();
    		
    		
    		if(piece != null && piece.getName().equals("King")){
    			
    			Position kingCoordinate = cell.getPosition();
    			return kingCoordinate;
    			
    		}
    	}
    	//this return should never happen
    	return null;    	
    }
    
    
    public static boolean Validate(Board board, Box box, Box targetBox) {
    	
    	Piece piece = box.getCurrentPiece();
    	
    	Position target_position = targetBox.getPosition();
    	List<Position> possible_positions = piece.getValidMoves(board);
    	boolean valid = false;
    	for(Position position: possible_positions){
    		
    		
    		if(target_position.compareTo(position) == 1){
                 valid = true;
                 return valid;
    		}
    		
    	}
    	return valid;
    }
    
    
    
    
    public static boolean isCheck(Board board, Box currentBox){
    	
    	Piece piece = currentBox.getCurrentPiece();
    
    	Position kingCoordinate = getKingBox(board);
    
    	
    	
    	List<Position> possible_positions = piece.getValidMoves(board);
    	
    	boolean check = false;
    	for(Position position: possible_positions){
    	   
    		if(kingCoordinate.compareTo(position) == 1){
                 check = true;
    		}
    		
    	}
    	return check;
    
    }
    
    
}
