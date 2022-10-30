package ChessGame;

import java.util.LinkedList;

public class King extends Piece{
	boolean isAlive = true;
	
	public King(int xp, int yp, boolean isWhite, PieceType type, LinkedList<Piece> ps) {
		super(xp, yp, isWhite, type, ps);
	}
	
	@Override
	public void move(int x, int y) {
		int xp = x/64;
		int yp = y/64;
		boolean canMove = false;
		Piece checkPiece = ChessGame.Chess.getPiece(x, y);
		
		if(!isPieceOnBoard(x, y)) {
			stay(this.xp, this.yp);
			return;
		}
		
		// Up and down
		if((this.yp-1 == yp || this.yp+1 == yp) && this.xp == xp) {
			canMove = canMakeMove(checkPiece, xp, yp);
		}
		// Left and right
		if((this.xp-1 == xp || this.xp+1 == xp) && this.yp == yp) {
			canMove = canMakeMove(checkPiece, xp, yp);
		}
		// Diagonally
		if((this.xp-1 == xp || this.xp+1 == xp) && (this.yp-1 == yp || this.yp+1 == yp)) {
			canMove = canMakeMove(checkPiece, xp, yp);
		}
		
		if(!canMove)
			stay(this.xp, this.yp);
		else
			ChessGame.Chess.whiteTurn = !ChessGame.Chess.whiteTurn; // changing turns (white turn or black turn)
	}
	
	public boolean canMakeMove(Piece checkPiece, int xp, int yp) {
		if(checkPiece != null && checkPiece.isWhite != isWhite) {
			checkPiece.kill();
			changePos(xp, yp);
			return true;
		}
		if(checkPiece == null) {
			changePos(xp, yp);
			return true;
		}
		return false;
	}
}
