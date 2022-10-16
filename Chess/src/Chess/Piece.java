package Chess;

import java.util.*;

public class Piece {
	// number from 0 to 7, will be multiplied by 64 later
	int xp;
	int yp;
	// Multiply xp and yp by 64
	int x;
	int y;
	
	PieceType type;
	boolean isWhite;
	LinkedList<Piece> ps = new LinkedList();
	Piece(int xp, int yp, PieceType type, boolean isWhite, LinkedList<Piece> ps){
		this.xp = xp;
		this.yp = yp;
		x = xp*64;
		y = yp*64;
		this.isWhite = isWhite;
		this.ps = ps;
		this.type = type;
		ps.add(this);
	}
	
	public void move(int xp, int yp) {
		if(ChessGame.getPiece(xp, yp) != null) {
			if(ChessGame.getPiece(xp, yp).isWhite != isWhite) {
				ChessGame.getPiece(xp, yp).kill();;
			}
			else {
				x = this.xp*64;
				y = this.yp*64;
				return;
			}
		}
		
		this.xp = xp;
		this.yp = yp;
		x = xp*64;
		y = yp*64;
	}
	
	private void kill() {
		ps.remove(this);
	}
}
