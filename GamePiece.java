package arimaa;

public class GamePiece {
	int rank;
	
	//0 is gold, 1 is silver
	int side;
	//"Dummy" is for a dummy
	String label;
	
	int column;
	int row;
	
	int[] turnsAgoColumn;
	
	int[] turnsAgoRows;
	
	
	boolean isAlive;
	
	public GamePiece(int newColumn, int newRow, int newSide, int newRank, String newLabel) {
		column = newColumn;
		row = newRow;
		label = newLabel;
		isAlive = true;
		
		side = newSide;
		rank = newRank;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int[] getTurnsAgoColumn() {
		return turnsAgoColumn;
	}

	public void setTurnsAgoColumn(int[] turnsAgoColumn) {
		this.turnsAgoColumn = turnsAgoColumn;
	}

	public int[] getTurnsAgoRows() {
		return turnsAgoRows;
	}

	public void setTurnsAgoRows(int[] turnsAgoRows) {
		this.turnsAgoRows = turnsAgoRows;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}
