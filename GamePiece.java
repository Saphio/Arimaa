package arimaa;
/**
 * The Game Piece class for Arimaa. 
 */

public class GamePiece {

	////// ATTRIBUTES ///////
	private int ID;
	private int side;
	private String label;
	private int posX, posY;
	private boolean alive = true;
	private int[] turnsAgoPosX = new int[4];
	private int[] turnsAgoPosY = new int[4];
	
	/////// CONSTRUCTOR ///////
	public GamePiece (int id, int s, String lbl, int px, int py) {
		ID = id;
		side = s;
		label  = lbl;
		posX = px;
		posY = py;
	}
	
	/////// METHODS //////
	// gets ID of the piece
	public int getID () { return ID; }
	// gets the side of the piece
	public int getSide () { return side; }
	// gets label of the piece
	public String getLabel () { return label; }
	// gets position of the piece as an integer array [x, y]
	public int[] getPos() { return new int[] {posX, posY}; }
	// returns if the piece is in play
	public boolean isAlive () { return alive; }

	// shifts the turnsAgoPosX and current position based on a move
	public void movePiece (int newX, int newY) {
		for (int i = 2; i >= 0; i--) { 
			// shifts old moves - 2 shifts to 3, 1 shifts to 2, etc.
			turnsAgoPosX[i + 1] = turnsAgoPosX[i];
			turnsAgoPosY[i + 1] = turnsAgoPosY[i];
		}
		// updates current position to new position
		turnsAgoPosX[0] = posX;
		turnsAgoPosY[0] = posY;
		posX = newX;
		posY = newY;
	}
}
