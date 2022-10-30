package ChessGame;

import java.util.LinkedList;

public class Pawn extends Piece{
	private boolean moved = false;
	
	public Pawn(int xp, int yp, boolean isWhite, PieceType type, LinkedList<Piece> ps) {
		super(xp, yp, isWhite, type, ps);
	}

	@Override
	public void move(int x, int y) {
		int xp = x/64;
		int yp = y/64;
		boolean canMove = false;
		Piece checkPiece = ChessGame.Chess.getPiece(x, y);
		// Checks for white player if he jumps by two and there is actually black in front of him
		Piece checkPieceOverForWhite = ChessGame.Chess.getPiece(x, (yp+1)*64);
		// Checks for black player if he jumps by two and there is actually white in front of him
		Piece checkPieceOverForBlack = ChessGame.Chess.getPiece(x, (yp-1)*64);
		
		if(!isPieceOnBoard(x, y)) {
			stay(this.xp, this.yp);
			return;
		}

		// doesnt have to use stay() because its all down there in else and its still checks for that next if
		// Move by two
		if(!moved) {
			if(((this.yp)-2 == yp && isWhite || (this.yp)+2 == yp && !isWhite) && this.xp == xp) {
				if(checkPiece == null) {
					if(checkPieceOverForWhite == null && isWhite || checkPieceOverForBlack == null && !isWhite) {
						moved = true;
						changePos(xp, yp);
						canMove = true;
					}
				}
			}
		}
		
		// Move by one or move by one and kill piece
		if((this.yp)-1 == yp && isWhite|| (this.yp)+1 == yp && !isWhite) {
			if(this.xp == xp) {
				if(checkPiece == null) {
					changePos(xp, yp);
					canMove = true;
					if(!moved) moved = true;
				}
			}
			else if(((this.xp-1) == xp || (this.xp+1) == xp) && checkPiece != null) {
				if(checkPiece.isWhite != isWhite) {
					checkPiece.kill();
					changePos(xp, yp);
					canMove = true;
				}
			}
		}
		
		
		if(!canMove)
			stay(this.xp, this.yp);
		else
			ChessGame.Chess.whiteTurn = !ChessGame.Chess.whiteTurn; // changing turns (white turn or black turn)
	}
}
