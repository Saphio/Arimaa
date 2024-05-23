package arimaa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameControl implements ActionListener, MouseListener {
	
	/////// SETUP ///////
	// organization
	JFrame frame = new JFrame("Arimaa");
	Board board = new Board();
	Container east = new Container();
	Container south = new Container();
	Container north = new Container();

	int headerHeight = frame.getInsets().top; // the height of the little header at the top
	// that's throwing off the image-adding coordinates
	
	// buttons
	JButton rabbit = new JButton("Rabbit");
	JButton cat = new JButton("Cat");
	JButton dog = new JButton("Dog");
	JButton horse = new JButton("Horse");
	JButton camel = new JButton("Camel");
	JButton elephant = new JButton("Elephant");
	ArrayList<JButton> pieceButtons = new ArrayList<JButton> () {
		{
			add(rabbit);
			add(cat);
			add(dog);
			add(horse);
			add(camel);
			add(elephant);
		}
	};
	JButton push = new JButton("Push");
	JButton pull = new JButton("Pull");
	JButton endTurn = new JButton("End turn");
	JButton newGame = new JButton("New game");
	
	// labels
	JLabel curPlayerLbl = new JLabel("Gold's turn", SwingConstants.CENTER);
	JLabel movesLeftLbl = new JLabel("Setup", SwingConstants.CENTER);
	JLabel addPiecesLbl = new JLabel("Add pieces");
	
	// setup
	double width;
	double height;
	public static final Color VERY_LIGHT_RED = new Color(255,102,102);
	public static final Color PIECE_BTN_SELECTED = new Color(78, 237, 115);
	
	// board
	ArrayList<ArrayList<GamePiece>> boardArray = new ArrayList<ArrayList<GamePiece>>();
	
	
	/////// STATE VARIABLES ///////
	boolean currentlyPlaying = false;
	int currentPlayer = 0;
	int movesLeft = 4;
	
	// setup variables
	String side = "";
	String setupPiece = "";
	int[] pieceCounts = new int[6]; // pieceCounts[0] -> #rabbits, 1 -> #cats, etc. up the ranks
	// reset pieceCounts to blank array when gold is done setting up
	int[] pieceLimits = new int[] {8, 2, 2, 2, 1, 1};
	String[] pieces = new String[] {"rabbit", "cat", "dog", "horse", "camel", "elephant"};
	
	
	/////// CONSTRUCTOR ///////
	public GameControl () {
		// frame
		frame.setSize(1000, 900);
		frame.setLayout(new BorderLayout());
		
		// center
		board.addMouseListener(this);
		board.createBoard();
		frame.add(board, BorderLayout.CENTER);
		
		// east container
		east.setLayout(new GridLayout(7, 1));
		east.add(addPiecesLbl);
		east.add(elephant);
		east.add(camel);
		east.add(horse);
		east.add(dog);
		east.add(cat);
		east.add(rabbit);
		elephant.addActionListener(this);
		camel.addActionListener(this);
		horse.addActionListener(this);
		dog.addActionListener(this);
		cat.addActionListener(this);
		rabbit.addActionListener(this);
		frame.add(east, BorderLayout.EAST);
		
		// south container
		south.setLayout(new GridLayout(1, 2));
		south.add(push);
		south.add(pull);
		push.addActionListener(this);
		pull.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		push.setEnabled(false);
		pull.setEnabled(false);
		
		// north container
		north.setLayout(new GridLayout(2, 2));
		north.add(endTurn);
		north.add(newGame);
		endTurn.addActionListener(this);
		newGame.addActionListener(this);
		endTurn.setEnabled(false);
		newGame.setEnabled(false);
		north.add(curPlayerLbl);
		north.add(movesLeftLbl);
		frame.add(north, BorderLayout.NORTH);
		
		// frame functions
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
	}
	

	/////// MAIN ///////
	public static void main (String[] args) {
		new GameControl();
	}

	// a helper function to change the coloring of the button of a piece when selected
	private void selectPieceButton (String piece, JButton button) {
		if (!setupPiece.equals("") && !setupPiece.equals(piece)) {
			for (int i = 0; i < pieceButtons.size(); i++) {
				pieceButtons.get(i).setBackground(null);
			}
			setupPiece = "";
		}
		if (button.getBackground().equals(PIECE_BTN_SELECTED)) {
			button.setBackground(null);
			setupPiece = "";
		}
		else {
			button.setBackground(PIECE_BTN_SELECTED);
			setupPiece = piece;
		}
	}
	
	// action listener - buttons
	public void actionPerformed(ActionEvent e) {
		// setup
		if (!currentlyPlaying) {
			if (currentPlayer == 1) { side = "silver_"; }
			else { side = "gold_"; }
			
			if (e.getSource().equals(rabbit)) {
				selectPieceButton("rabbit", rabbit);
			}
			if (e.getSource().equals(cat)) {
				selectPieceButton("cat", cat);
			}	
			if (e.getSource().equals(dog)) {
				selectPieceButton("dog", dog);
			}
			if (e.getSource().equals(horse)) {
				selectPieceButton("horse", horse);
			}
			if (e.getSource().equals(camel)) {
				selectPieceButton("camel", camel);
			}
			if (e.getSource().equals(elephant)) {
				selectPieceButton("elephant", elephant);
			}			
		}	
		
	}
	
	// updates the counts of each piece after a change is made to the setup of the board
	// adding is true if you are adding a piece, false if removing
	private void updatePieceCounts (boolean adding, String piece) {
		int i = 0;
		if (piece.equals("rabbit")) { i = 0; }
		if (piece.equals("cat")) { i = 1; }
		if (piece.equals("dog")) { i = 2; }
		if (piece.equals("horse")) { i = 3; }
		if (piece.equals("camel")) { i = 4; }
		if (piece.equals("elephant")) { i = 5; }
			
		if (adding) {
			pieceCounts[i] ++;
		}
		else if (!piece.equals("Dummy")){
			pieceCounts[i] --;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {

		double width = (double)(board.getWidth()) / 8;
		double height = ((double)((board.getHeight())) / 8);

		int column = Math.min(7, (int)((event.getX()) / width)); 
		int row =  Math.min(7, (int)((event.getY() - headerHeight) / height)); 
		
		
		// setting up
		if (!currentlyPlaying) {
			
			//boardArray.get(row).set(column, new GamePiece(column, row, 0, 1, "gold_rabbit"));
			//When we add buttons, the string at the end can be determined based on the button that has been selected and if they still actually have those pieces
			//to put in. Actually adding it to the board is done by Board
			//change "dummy" in this section to be equals and doesn't equal the string name of the pieces that you've added
		
			// add a piece to that loReport this adcation
			List tempPieces = Arrays.asList(pieces);
			if (board.boardArray.get(row).get(column).label.equals("Dummy") && !setupPiece.equals("")) {
				board.addPiece(column, row, currentPlayer, 1, side + setupPiece);
				updatePieceCounts (true, setupPiece);
			}
			// remove a piece from that location
			else {
				String tempPiece = board.boardArray.get(row).get(column).getLabel();
				int i_u = tempPiece.indexOf("_");
				tempPiece = tempPiece.substring(i_u + 1);
				board.addPiece(column, row, -1, -1, "Dummy");
				updatePieceCounts (false, tempPiece);
			}
			// check if more of that piece can be added
			for (int i = 0; i < pieceButtons.size(); i++) {
				if (pieceCounts[i] >= pieceLimits[i]) {
					pieceButtons.get(i).setBackground(null);
					pieceButtons.get(i).setSelected(false);
					pieceButtons.get(i).setEnabled(false);
					if (setupPiece.equals(pieces[i])) {
						setupPiece = "";
					}
				}
				else if (!pieceButtons.get(i).isEnabled()){
					pieceButtons.get(i).setEnabled(true);
				}
			}
			
			// Switch players/game modes 
			// If all the piece buttons are disabled, either one or both players are done
			// setting up
			boolean allDisabled = true;
			for (int i = 0; i < pieceButtons.size(); i++) {
				if (pieceButtons.get(i).isEnabled()) {
					allDisabled = false;
				}
			}
			if (allDisabled) {
				if (currentPlayer == 0) {
					// Silver's turn to set up
					currentPlayer = 1;
					pieceCounts = new int[6];
					setupPiece = "";
					curPlayerLbl.setText("Silver's turn");
					for (int i = 0; i < pieceButtons.size(); i++) {
						pieceButtons.get(i).setBackground(null);
						pieceButtons.get(i).setSelected(false);
						pieceButtons.get(i).setEnabled(true);
					}
				}
				else {
					// Done setting up; time to start game
					if (board.isValidSetup()) {
						System.out.println("Valid");
					}
					else {
						System.out.println("Invalid");
					}
					currentPlayer = 1;
					currentlyPlaying = true;
					curPlayerLbl.setText("Gold's turn");
					movesLeftLbl.setText(movesLeft + " moves left");
					push.setEnabled(true);
					pull.setEnabled(true);
					newGame.setEnabled(true);
				}
			}
		
		}
		
		board.repaint();
		
	}
	
	
	/////// UNUSED DEFAULT METHODS ///////
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
