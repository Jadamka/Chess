package ChessGame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Chess {

	static JFrame frame;
	static JPanel pn;
	static Image imgs[];
	static LinkedList<Piece> ps = new LinkedList<>();
	static Piece selectedPiece = null;
	static boolean whiteTurn = true;
	
	static void frameSetup() {
		frame = new JFrame();
		frame.setSize(512, 512);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		try {
			loadPieces();
		}
		catch(Exception e) {}
		createPieces();
		panelSetup();
		frame.add(pn);
		frameEvents();
		frame.setVisible(true);
	}
	
	static void frameEvents() {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(0);
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
				if(e.getButton() == MouseEvent.BUTTON1)
					selectedPiece = getPiece(e.getX(), e.getY());
				if(selectedPiece != null)
					if(selectedPiece.isWhite != whiteTurn) selectedPiece = null;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(selectedPiece != null) {
					selectedPiece.move(e.getX(), e.getY());
					frame.repaint();
				}
			}
		});
	}
	
	static void panelSetup() {
		pn = new JPanel() {
			@Override
			public void paint(Graphics g) {
				drawBoard(g);
				drawPieces(g);
			}
		};
	}

	static void drawBoard(Graphics g) {
		boolean changeColor = true;
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(changeColor)
					g.setColor(new Color(238, 235, 216));
				else
					g.setColor(new Color(75, 111, 143));
				g.fillRect(x*64, y*64, 64, 64);
				changeColor = !changeColor;
			}
			changeColor = !changeColor;
		}
	}
	
	static void loadPieces() throws IOException {
		BufferedImage all = ImageIO.read(new File("C:\\Users\\adamk\\Desktop\\Java\\Chess\\chess.png"));
		imgs = new Image[12];
		int index = 0;
		for(int y = 0; y < 400; y += 200) {
			for(int x = 0; x < 1200; x += 200) {
				imgs[index++] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
			}
		}
	}
	
	static void createPieces() {
		// WHITE
		Piece wRook1 = new Rook(0, 7, true, PieceType.rook, ps);
		Piece wKnight1 = new Knight(1, 7, true, PieceType.knight, ps);
		Piece wBishop1 = new Bishop(2, 7, true, PieceType.bishop, ps);
		Piece wQueen = new Queen(3, 7, true, PieceType.queen, ps);
		Piece wKing = new King(4, 7, true, PieceType.king, ps);
		Piece wBishop2 = new Bishop(5, 7, true, PieceType.bishop, ps);
		Piece wKnight2 = new Knight(6, 7, true, PieceType.knight, ps);
		Piece wRook2 = new Rook(7, 7, true, PieceType.rook, ps);
		Piece wPawn1 = new Pawn(0, 6, true, PieceType.pawn, ps);
		Piece wPawn2 = new Pawn(1, 6, true, PieceType.pawn, ps);
		Piece wPawn3 = new Pawn(2, 6, true, PieceType.pawn, ps);
		Piece wPawn4 = new Pawn(3, 6, true, PieceType.pawn, ps);
		Piece wPawn5 = new Pawn(4, 6, true, PieceType.pawn, ps);
		Piece wPawn6 = new Pawn(5, 6, true, PieceType.pawn, ps);
		Piece wPawn7 = new Pawn(6, 6, true, PieceType.pawn, ps);
		Piece wPawn8 = new Pawn(7, 6, true, PieceType.pawn, ps);
		// BLACK
		Piece bRook1 = new Rook(0, 0, false, PieceType.rook, ps);
		Piece bKnight1 = new Knight(1, 0, false, PieceType.knight, ps);
		Piece bBishop1 = new Bishop(2, 0, false, PieceType.bishop, ps);
		Piece bQueen = new Queen(3, 0, false, PieceType.queen, ps);
		Piece bKing = new King(4, 0, false, PieceType.king, ps);
		Piece bBishop2 = new Bishop(5, 0, false, PieceType.bishop, ps);
		Piece bKnight2 = new Knight(6, 0, false, PieceType.knight, ps);
		Piece bRook2 = new Rook(7, 0, false, PieceType.rook, ps);
		Piece bPawn1 = new Pawn(0, 1, false, PieceType.pawn, ps);
		Piece bPawn2 = new Pawn(1, 1, false, PieceType.pawn, ps);
		Piece bPawn3 = new Pawn(2, 1, false, PieceType.pawn, ps);
		Piece bPawn4 = new Pawn(3, 1, false, PieceType.pawn, ps);
		Piece bPawn5 = new Pawn(4, 1, false, PieceType.pawn, ps);
		Piece bPawn6 = new Pawn(5, 1, false, PieceType.pawn, ps);
		Piece bPawn7 = new Pawn(6, 1, false, PieceType.pawn, ps);
		Piece bPawn8 = new Pawn(7, 1, false, PieceType.pawn, ps);
	}
	
	static void setupPieces() {
		try {
			loadPieces();
		}
		catch(Exception e) {}
		createPieces();
	}
	
	static void drawPieces(Graphics g) {
		for(Piece p: ps) {
			int index = 0;
			if(p.type == PieceType.king)
				index = 0;
			if(p.type == PieceType.queen)
				index = 1;
			if(p.type == PieceType.bishop)
				index = 2;
			if(p.type == PieceType.knight)
				index = 3;
			if(p.type == PieceType.rook)
				index = 4;
			if(p.type == PieceType.pawn)
				index = 5;
			if(!p.isWhite)
				index += 6;
			
			g.drawImage(imgs[index], p.x, p.y, frame);
		}
	}
	
	static Piece getPiece(int x, int y) {
		int xp = x/64;
		int yp = y/64;
		for(Piece p: ps) {
			if(p.xp == xp && p.yp == yp)
				return p;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		frameSetup();
	}

}
