package game;
import processing.core.PApplet;

public class Board {
	
	
	private int x;
	private int y;

	
	private Cell[][] board;
	private PApplet p;
	private int cellWidth;
	private int cellHeight;
	private int generation;
	private static final int RADIUS = 1;

	public Board(int x, int y, int cellWidth, int cellHeight) {
		this.x = x;
		this.y = y;
		this.board = new Cell[x][y];
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.generation=0;
	}



	public void fillInitialBoard(PApplet p) {
		this.p = p;
		for (int posX = 0; posX < x; posX++) {
			for (int posY = 0; posY < y; posY++) {
				if(posX == 0 || posY == 0 || posX==x-1 || posY==y-1) {
					p.fill(0);
					board[posX][posY] = new Cell(posX, posY, 1);
				} else {
					p.fill(255);
					board[posX][posY] = new Cell(posX, posY, 0);
				}
				p.stroke(0);
				p.rect(posX * cellWidth, posY * cellHeight, cellWidth, cellHeight);
			}
		}
	}

	public Cell[][] generate() {
		Cell[][] nextGeneration = board.clone();
		
		for (int i = 1; i < nextGeneration.length-1; i++) {
			for (int j = 1; j < nextGeneration.length-1; j++) {
				int neighbours = checkNeighbourhood(nextGeneration[i][j]);
				neighbours -= board[i][j].getState();
				//Check for loneliness first.
				if((board[i][j].getState() == 1 && (neighbours < 2))) {
					nextGeneration[i][j] = new Cell(i, j, 0);
				}else if(board[i][j].getState() == 1 && (neighbours >= 4)) {
					nextGeneration[i][j] = new Cell(i, j, 0);
				}else if(board[i][j].getState() == 0 && (neighbours == 3)) {
					nextGeneration[i][j] = new Cell(i, j, 1);
				}
			}
		}
				
		return nextGeneration;
	}
	
	public void fillBoard(Cell[][] newBoard) {
		for (int i = 0; i < newBoard.length; i++) {
			for (int j = 0; j < newBoard[i].length; j++) {
				
			}
		}
	}

	
	


	private int checkNeighbourhood(Cell c) {
		int neighbours = 0;
		for (int x = -RADIUS; x < RADIUS; x++) {
			for (int y = -RADIUS; y < RADIUS; y++) {
				neighbours += board[c.getPositionX() + x][c.getPositionY() + y].getState();
			}
		}
		return neighbours;
	}

}
