//TO DO: Combine the buttons, set up a string to change whenever the button is pressed, and do "if does not equal string, add piece, if does equal string, remove piece"
//then, after each piece added, run a check to see how many of each piece is on the board and show pieces remaining.

package arimaa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel{
	
	//Holds the width and height of each square.
	double width;
	double height;
	//color for capture squares
	public static final Color VERY_LIGHT_RED = new Color(255,102,102);
	public Board() {
		super();
	}
	//Not sure if we should have this list be here, the main class, or both. Might be better in the main file as more stuff will happen there.
	//ArrayList<ArrayList<GamePiece>> boardArray = new ArrayList<ArrayList<GamePiece>>();
	
	ArrayList<ArrayList<GamePiece>> boardArray = new ArrayList<ArrayList<GamePiece>>();
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//width/height of rectangles
		width = (double)this.getWidth() / 8;
		height = (double)this.getHeight() / 8;
		
		g.setColor(Color.LIGHT_GRAY);
		
		//Draws color for background, and then makes the capture squares red.
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(VERY_LIGHT_RED);
		g.fillRect((int)Math.round(width * 2), (int)Math.round(height * 2), (int)width, (int)height);
		g.fillRect((int)Math.round(width * 5), (int)Math.round(height * 2), (int)width, (int)height);
		g.fillRect((int)Math.round(width * 2), (int)Math.round(height * 5), (int)width, (int)height);
		g.fillRect((int)Math.round(width * 5), (int)Math.round(height * 5), (int)width, (int)height);
		
		g.setColor(Color.BLACK);
		
		//Draws the lines for the board based on the width.
		for (int x = 0; x < 8 + 1; x++) {
			//Math.round ensures that the lines are accurate.
			g.drawLine((int)Math.round(x*width), 0, (int)Math.round(x*width), this.getHeight());
		} 
		for (int y = 0; y < 8 + 1; y++) {
			g.drawLine(0, (int)Math.round(y*height), this.getWidth(), (int)Math.round(y*height));
		} 
		/*
		for (int i = 0; i < boardArray.size(); i++) {
			if (!boardArray.get(i).equals(null)) {
				//find the image based on the piece's label
				Image pieceImage = null;
				GamePiece currentPiece = boardArray.get(i);
				try {
					pieceImage = ImageIO.read(new File(currentPiece.getLabel() + ".png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				g.drawImage(pieceImage, (int)((currentPiece.getColumn()) * width), (int)((currentPiece.getRow()) * height), null);
			}
		}
		*/
		
		for (int row = 0; row < boardArray.size(); row++) {
			for (int column = 0; column < boardArray.size(); column++) {
				if (!boardArray.get(row).get(column).label.equals("Dummy")) {
					//find the image based on the piece's label
					Image pieceImage = null;
					try {
						pieceImage = ImageIO.read(new File("/home/saphio/" + boardArray.get(row).get(column).getLabel() + ".png"));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					g.drawImage(pieceImage, (int)((column) * width), (int)((row) * height), null);
				}
			}
		}
	}
	
	public void addPiece (int column, int row, int side, int rank, String label) {
		/*
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i ==row && j == column) {
					boardArray.get(row).set(column, new GamePiece(column, row, side, rank, label));
				}
				else {
					boardArray.get(i).add(null);
				}
			}
		}
		*/
		//boardArray.add(new GamePiece(column, row, side, rank, label));
		boardArray.get(row).set(column, new GamePiece(column, row, side, rank, label));
	}
	
	public void createBoard() {
		for (int row = 0; row < 8; row++) {
			ArrayList<GamePiece> thisRow = new ArrayList<GamePiece>();
			for (int column = 0; column < 8; column++) {
				thisRow.add(new GamePiece(column, row, -1, -1, "Dummy"));
			}
			boardArray.add(thisRow);
		}
	}
	
}
