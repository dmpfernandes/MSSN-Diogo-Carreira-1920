package game.grid;
import java.time.Duration;
import java.time.Instant;

import processing.core.PApplet;

public class Board {
	private Cell[][] board;
	private PApplet p;
	private int nrows, ncols;
	private int w;
	private int h;
	

	public Board(int nrows, int ncols, PApplet p) {
		this.p = p;
		this.nrows = nrows;
		this.ncols = ncols;
		this.board = new Cell[nrows][ncols];
		createCells();		
	}
	
	private void createCells() 
	{
		w = p.width / ncols;
		h = p.height / nrows;		
		for(int i=0; i<nrows; i++) {
			for(int j=0; j<ncols; j++) {
				Cell c = new Cell(p, i, j, w, h);
				board[i][j] = c;
			}
		}
	}
	
	public Cell getCell(int x, int y) 
	{
		int row = y/h;
		int col = x/w;
		if (row >= nrows) row = nrows - 1;
		if (col >= ncols) col = ncols - 1;
		return board[row][col];
	}
	
	public void clearGrid() 
	{
		for(int i=0; i<nrows; i++) {
			for(int j=0; j<ncols; j++) {
				board[i][j].resetState();
			}
		}
	}
	
	public void initFertilTerrain(float probFertil, float probFood) 
	{
		clearGrid();
		for(int i=0; i<nrows; i++) {
			for(int j=0; j<ncols; j++) {
				if(p.random(1f) < probFertil) board[i][j].setFertil();
			}
		}
		for(int i=0; i<nrows; i++) {
			for(int j=0; j<ncols; j++) {
				if(p.random(1f) < probFood) board[i][j].setFood();
			}
		}	
	}

	public void checkFertil() 
	{
		for(int i=0; i<nrows; i++) {
			for(int j=0; j<ncols; j++) {
				Instant growRate = board[i][j].getBirth();
				Instant now = Instant.now();
				if(board[i][j].getState() == 1 && Duration.between(growRate, now).toMillis() >= 10000) board[i][j].setFood();
			}
		}
	}

	public void run() 
	{
		checkFertil();
	}
	
	public void display() 
	{
		for(int i=0; i<nrows; i++) {
			for(int j=0; j<ncols; j++) {
				board[i][j].display();
			}
		}
	}

	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	}

	public int getNrows() {
		return nrows;
	}

	public void setNrows(int nrows) {
		this.nrows = nrows;
	}

	public int getNcols() {
		return ncols;
	}

	public void setNcols(int ncols) {
		this.ncols = ncols;
	}

	public PApplet getP() {
		return p;
	}

	public void setP(PApplet p) {
		this.p = p;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

}
