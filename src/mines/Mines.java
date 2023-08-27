package mines;

import java.util.Random;

public class Mines {

	private Random rand = new Random();
	private Field[][] board;
	private int height, width; 
	private boolean showAll = false;
	
	public Mines(int height, int width, int numMines) {
		this.height = height;
		this.width = width;
		board = new Field[height][width];; // the board of the game 
		for(int i = 0; i < height; i++) { 
			for(int j = 0; j < width; j++) {
				board[i][j] = new Field();
			}
		}
		int i=numMines;
		while( i > 0) { //put mines in random fields on the board 
			addMine(rand.nextInt(height), rand.nextInt(width)); //put mines in random fields on the board 
			i--; 
		}
	}

	public boolean addMine(int i, int j) { // add mine in the given location
		if(board[i][j].mine) { 
			return false; // if this location is already a mine return false
		}
		board[i][j].mine = true; // put mine
		for (int x = i - 1; x <= i + 1; x++) {
			for (int y = j - 1; y <= j + 1; y++) {
				if (x>= 0 && x < height && y >= 0 && y< width) {
					if(!( x == i && y==j)) {
						if (board[x][y].mine == false) {
							board[x][y].neighborNum++; // count the neighbors that are NOT mines 
						}
					}	
				}
			}
		}
		return true;
	}

	public class Field {
		private boolean open = false; //sign if open
		private boolean flag = false;//sign if flag
		private boolean mine = false; //sign if its mine
		private int neighborNum = 0; // amount of neighbors that are not mines
	
	}

	public boolean open(int i, int j) {
		if(board[i][j].flag) 
			return false;
		if(board[i][j].open ) // if open already return true
			return true;
		if(board[i][j].mine) 
			return false;
		
		board[i][j].open = true; 
		if(!board[i][j].mine ) {// if this isn't a mine 
			if(board[i][j].neighborNum == 0) { //if the amount of neighbors that not with mine is, open all
				for(int x = i - 1; x <= i + 1; x++) {	
					for(int y = j - 1; y <= j + 1; y++) {	
						if(x>= 0 && x < height && y >= 0 && y < width) {	 // if the neighbor in the board
							if(!(x == i && y == j)) { // don't count our current location
									open(x, y); 
							}
						}
					}
				}
			}
			return true;// return true if this isn't a mine
		}
		return false; 
	}

	public void toggleFlag(int x, int y) {
		if (board[x][y].flag) {
			board[x][y].flag=false;
		}else {
		board[x][y].flag=true;
		}
		
	}
	
	public boolean isDone() {  //Returns true if all non-mine locations are open
		for(int i = 0; i < height; i++) { 
			for(int j = 0; j < width; j++) { 
				if(!board[i][j].mine  && !board[i][j].open ) {
					return false;
				}	
			}
		}
		return true;
	}
	public String get(int i, int j) {
		if(showAll == false) { 
			if (!board[i][j].open) {
				if (board[i][j].flag) {
					return "F"; //If the position is closed and has a flag on it will return "F"
				}
				else if(!board[i][j].flag) {
					return "."; //If the position is closed and there is no flag on it it will return "F"
				}
			}
			if (board[i][j].open) { //If the position is open, it returns "X" if it is a mine
				if(board[i][j].mine) { 
					return "X";
				} //If the place is open, it will return the number of mines if it is not a mine
				if(board[i][j].neighborNum==0)
					return " ";// if the number of mines is equal to 0 it will return " "
				return board[i][j].neighborNum+"";
					
			}
		}
		// if showAll is true so all open
		if(board[i][j].mine) { 
			return "X";
		}
		if(board[i][j].neighborNum==0)
			return " ";
		return board[i][j].neighborNum+""; // If true - the return value is as if all the cells are open
	}
		
	public void setShowAll(boolean showAll) { // determine the value of showAll
		this.showAll = showAll;
	}

	public String toString() {
		String s ="";
		for(int i = 0; i < height; i++) { 
			for(int j = 0; j < width; j++) { 
				s+=(get(i, j));
			}
			s+="\n";
		}
		return s;
	}
	 
		
		public boolean getFlag(int i,int j) { // returns if there is a flag 
			return board[i][j].flag;
		}

		
		public boolean getOpen(int i, int j) { // returns true  if  open 
			return board[i][j].open || showAll;
		}
		
		
		public boolean getMine(int i, int j) { // returns if there is a mine 
			return board[i][j].mine;
		}
		
		
		public boolean getEmpty(int i,int j) { // returns if the field is empty 
			return get(i,j).equals(" ");
		}

}

