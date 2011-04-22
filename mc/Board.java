package mc;
import java.util.*;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import java.io.IOException;


public class Board implements Drawable {

	//ArrayList of all the board squares
	ArrayList<Square> squares;

	JPanel gameBoard;
	Icon image;
	
	public Board() {
		//empty constructor
		//initialize every square here
	}
	
	public Square getSquare(int position) {
		return squares.get(position);
	}
	
	private void addSquare(Square square) {
		squares.add(square);
	}
	
	public JPanel init() {
	
		gameBoard = new JPanel();
		image = new ImageIcon("images/mboard.png");
		JLabel bg = new JLabel(image);
		//bg.setLayout(new BorderLayout());
		gameBoard.add(bg);
		return gameBoard;
	}
	
	public void update() {
		
	}
	
}
