package ChessGame;

import java.util.LinkedList;

public class Queen extends Piece{
	
	public Queen(int xp, int yp, boolean isWhite, PieceType type, LinkedList<Piece> ps) {
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
		
		// Horizontally and Vertically
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
				}
			}
		}
		// Diagonally
		for(int pos = 0; pos < 8; pos++) {
			if((this.xp+pos) == xp && (this.yp+pos) == yp || (this.xp+pos) == xp && (this.yp-pos) == yp || 
					(this.xp-pos) == xp && (this.yp+pos) == yp || (this.xp-pos) == xp && (this.yp-pos) == yp) {
				if(!pieceInWay(xp, yp)) {
					if(checkPiece != null) {
						if(checkPiece.isWhite != isWhite)
							checkPiece.kill();
						else
							break;
					}
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
	
	private boolean pieceInWay(int xp, int yp) {
		Piece checkPiece;
		int index = 1;
		
		// for X
		if(this.xp != xp && this.yp == yp) {
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
		if(this.yp != yp && this.xp == xp) {
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
		// Diagonally
		// right up
		if(this.xp < xp && this.yp > yp) {
			for(int xPos = this.xp+1; xPos < xp; xPos++) {
				int yPos = this.yp-index++;
				checkPiece = ChessGame.Chess.getPiece(xPos*64, yPos*64);
				if(checkPiece != null)
					return true;
			}
		}
		// right down
		if(this.xp < xp && this.yp < yp) {
			for(int xPos = this.xp+1; xPos < xp; xPos++) {
				int yPos = this.yp+index++;
				checkPiece = ChessGame.Chess.getPiece(xPos*64, yPos*64);
				if(checkPiece != null)
					return true;
			}
		}
		// left up
		if(this.xp > xp && this.yp > yp) {
			for(int xPos = this.xp-1; xPos > xp; xPos--) {
				int yPos = this.yp-index++;
				checkPiece = ChessGame.Chess.getPiece(xPos*64, yPos*64);
				if(checkPiece != null)
					return true;
			}
		}
		// left down
		if(this.xp > xp && this.yp < yp) {
			for(int xPos = this.xp-1; xPos > xp; xPos--) {
				int yPos = this.yp+index++;
				checkPiece = ChessGame.Chess.getPiece(xPos*64, yPos*64);
				if(checkPiece != null)
					return true;
			}
		}
		
		return false;
	}
}
