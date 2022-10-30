package ChessGame;

import java.util.LinkedList;

public class Knight extends Piece{
	
	public Knight(int xp, int yp, boolean isWhite, PieceType type, LinkedList<Piece> ps) {
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
		
		if((this.xp-1 == xp || this.xp+1 == xp) && (this.yp+2 == yp || this.yp-2 == yp) || 
				(this.yp-1 == yp || this.yp+1 == yp) && (this.xp+2 == xp || this.xp-2 == xp)) {
			if(checkPiece != null && checkPiece.isWhite != isWhite) {
					checkPiece.kill();
					changePos(xp, yp);
					canMove = true;
			}
			if(checkPiece == null) {
				changePos(xp, yp);
				canMove = true;
			}
		}
		
		if(!canMove)
			stay(this.xp, this.yp);
		else
			ChessGame.Chess.whiteTurn = !ChessGame.Chess.whiteTurn; // changing turns (white turn or black turn)
	}
}
