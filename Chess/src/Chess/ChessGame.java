package Chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class ChessGame {
	static String enterPathForImage() {
		return JOptionPane.showInputDialog(null, "Enter Path");
	}
	
	static LinkedList<Piece> ps = new LinkedList<>();
	static Piece selectedPiece = null;
	public static void main(String[] args) throws IOException {
		new ChessGame();
		BufferedImage all = ImageIO.read(new File(enterPathForImage()));
		Image imgs[] = new Image[12];
		int index = 0;
		for(int y = 0; y < 400; y+=200) {
			for(int x = 0; x < 1200; x+=200) {
				imgs[index++] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
			}
		}
		// White
		Piece wRook = new Piece(0, 7, PieceType.rook, true, ps);
		Piece wKnight = new Piece(1, 7, PieceType.knight, true, ps);
		Piece wBishop = new Piece(2, 7, PieceType.bishop, true, ps);
		Piece wQueen = new Piece(3, 7, PieceType.queen, true, ps);
		Piece wKing = new Piece(4, 7, PieceType.king, true, ps);
		Piece wBishop2 = new Piece(5, 7, PieceType.bishop, true, ps);
		Piece wKnight2 = new Piece(6, 7, PieceType.knight, true, ps);
		Piece wRook2 = new Piece(7, 7, PieceType.rook, true, ps);
		Piece wPawn1 = new Piece(0, 6, PieceType.pawn, true, ps);
		Piece wPawn2 = new Piece(1, 6, PieceType.pawn, true, ps);
		Piece wPawn3 = new Piece(2, 6, PieceType.pawn, true, ps);
		Piece wPawn4 = new Piece(3, 6, PieceType.pawn, true, ps);
		Piece wPawn5 = new Piece(4, 6, PieceType.pawn, true, ps);
		Piece wPawn6 = new Piece(5, 6, PieceType.pawn, true, ps);
		Piece wPawn7 = new Piece(6, 6, PieceType.pawn, true, ps);
		Piece wPawn8 = new Piece(7, 6, PieceType.pawn, true, ps);
		// Black
		Piece bRook = new Piece(0, 0, PieceType.rook, false, ps);
		Piece bKnight = new Piece(1, 0, PieceType.knight, false, ps);
		Piece bBishop = new Piece(2, 0, PieceType.bishop, false, ps);
		Piece bQueen = new Piece(3, 0, PieceType.queen, false, ps);
		Piece bKing = new Piece(4, 0, PieceType.king, false, ps);
		Piece bBishop2 = new Piece(5, 0, PieceType.bishop, false, ps);
		Piece bKnight2 = new Piece(6, 0, PieceType.knight, false, ps);
		Piece bRook2 = new Piece(7, 0, PieceType.rook, false, ps);
		Piece bPawn1 = new Piece(0, 1, PieceType.pawn, false, ps);
		Piece bPawn2 = new Piece(1, 1, PieceType.pawn, false, ps);
		Piece bPawn3 = new Piece(2, 1, PieceType.pawn, false, ps);
		Piece bPawn4 = new Piece(3, 1, PieceType.pawn, false, ps);
		Piece bPawn5 = new Piece(4, 1, PieceType.pawn, false, ps);
		Piece bPawn6 = new Piece(5, 1, PieceType.pawn, false, ps);
		Piece bPawn7 = new Piece(6, 1, PieceType.pawn, false, ps);
		Piece bPawn8 = new Piece(7, 1, PieceType.pawn, false, ps);
		
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(512, 512);
		JPanel pn = new JPanel() {
			@Override
			public void paint(Graphics g) {
				boolean white = true;
				for(int y = 0; y < 8; y++) {
					for(int x = 0; x < 8; x++) {
						if(white)
							g.setColor(new Color(238, 238, 210));
						else
							g.setColor(new Color(118, 150, 86));
						g.fillRect(x*64, y*64, 64, 64);
						white = !white;
					}
					white = !white;
				}
				
				for(Piece p: ps) {
					int index = 0;
					if(p.type.equals(PieceType.bishop))
						index = 2;
					if(p.type.equals(PieceType.king))
						index = 0;
					if(p.type.equals(PieceType.knight))
						index = 3;
					if(p.type.equals(PieceType.pawn))
						index = 5;
					if(p.type.equals(PieceType.queen))
						index = 1;
					if(p.type.equals(PieceType.rook))
						index = 4;
					if(!p.isWhite)
						index += 6;
					
					g.drawImage(imgs[index], p.x, p.y, this);
				}
			}
		};
		frame.add(pn);
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					frame.dispose();
					System.exit(0);
				}
			}
		});
		frame.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(selectedPiece != null) {
					selectedPiece.x = e.getX()-32;
					selectedPiece.y = e.getY()-32;
					frame.repaint();
				}
			}
			@Override
			public void mouseMoved(MouseEvent e) {
			}
			
		});
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectedPiece = getPiece(e.getX()/64, e.getY()/64);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(selectedPiece != null) {
					selectedPiece.move(e.getX()/64, e.getY()/64);
					frame.repaint();
				}
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	static Piece getPiece(int xp, int yp) {
		for(Piece p: ps) {
			if(p.xp == xp && p.yp == yp)
				return p;
		}
		
		return null;
	}
}
