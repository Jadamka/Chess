package ChessGame;

import java.util.*;

public class Piece {
	// need xp, yp, x and y (x and y for changing positions while mousedragging)
	// xp and yp for simplifying position to (0-8)
	int xp;
	int yp;
	int x;
	int y;
	boolean isWhite;
	PieceType type;
	LinkedList<Piece> ps = new LinkedList<>();
	
	public Piece(int xp, int yp, boolean isWhite, PieceType type, LinkedList<Piece> ps) {
		this.xp = xp;
		this.yp = yp;
		x = xp*64;
		y = yp*64;
		this.isWhite = isWhite;
		this.type = type;
		this.ps = ps;
		ps.add(this);
	}
	
	public void move(int x, int y) {
	}
	
	protected void changePos(int xp, int yp) {
		this.xp = xp;
		this.yp = yp;
		this.x = xp*64;
		this.y = yp*64;
	}
	protected void stay(int xp, int yp) {
		this.x = xp*64;
		this.y = yp*64;
	}
	
	protected boolean isPieceOnBoard(int x, int y) {
		if(x > 512 || x < 0 || y > 512 || y < 0)
			return false;
		
		return true;
	}
	
	protected void kill() {
		ps.remove(this);
	}
}
