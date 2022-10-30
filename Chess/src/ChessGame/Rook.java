package ChessGame;

import java.util.LinkedList;

public class Rook extends Piece{
	private boolean moved = false;
	
	public Rook(int xp, int yp, boolean isWhite, PieceType type, LinkedList<Piece> ps) {
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
		
		for(int pos = 0; pos < 8; pos++) {
			if((yp == pos && this.xp == xp || xp == pos && this.yp == yp)) {
				if(!pieceInWay(xp, yp)) {
					if(checkPiece != null) {
						if(checkPiece.isWhite != isWhite)
							checkPiece.kill();
						else
							break;
					}
					changePos(xp, yp);
					canMove = true;
					if(!moved) moved = true;
				}
			}
		}
		
		if(!canMove)
			stay(this.xp, this.yp);
		else
			ChessGame.Chess.whiteTurn = !ChessGame.Chess.whiteTurn; // changing turns (white turn or black turn)
	}
	
	private boolean pieceInWay(int xp, int yp) {
		Piece checkPiece;
		// for X
		if(this.xp != xp) {
			if(this.xp < xp) {
				for(int xPos = this.xp+1; xPos < xp; xPos++) {
					checkPiece = ChessGame.Chess.getPiece(xPos*64, yp*64);
					if(checkPiece != null)
						return true;
				}
			}
			if(this.xp > xp) {
				for(int xPos = this.xp-1; xPos > xp; xPos--) {
					checkPiece = ChessGame.Chess.getPiece(xPos*64, yp*64);
					if(checkPiece != null)
						return true;
				}
			}
		}
		// for Y
		if(this.yp != yp) {
			if(this.yp < yp) {
				for(int yPos = this.yp+1; yPos < yp; yPos++) {
					checkPiece = ChessGame.Chess.getPiece(xp*64, yPos*64);
					if(checkPiece != null)
						return true;
				}
			}
			if(this.yp > yp) {
				for(int yPos = this.yp-1; yPos > yp; yPos--) {
					checkPiece = ChessGame.Chess.getPiece(xp*64, yPos*64);
					if(checkPiece != null)
						return true;
				}
			}
		}
		
		return false;
	}
	
}
