package game.grid;

import java.time.Instant;

import processing.core.PApplet;

public class Cell
{
	private int row, col;
	private int state;
	private int w, h;
	private int[] colors;
	private PApplet p;	
	private Instant timeBirth;

	public Cell(PApplet p, int row, int col, int w, int h)
	{
		this.p = p;
		this.row = row;
		this.col = col;
		this.w = w;
		this.h = h;
		this.colors = new int[3];
		
		// empty - saddlebrown
		colors[0] = p.color(139,69,19);
		// fertile - darkgreen 
		colors[2] = p.color(0,100,0);
		// food - lightsalmon
		colors[1] = p.color(255,160,122);
	}


	public void resetState() {
		state = 0;
	}
	
	public void setFertil() {
		state = 1;
		timeBirth = Instant.now();
	}
		
	public void setFood() {
		state = 2;
	}	
	
	public int getState()
	{
		return state;
	}
	
	public Instant getBirth() {
		return timeBirth;
	}

	public void display()
	{
		p.noStroke();
		p.fill(colors[state]);
		p.rect(col*w, row*h, w, h);
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
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


	public int[] getColors() {
		return colors;
	}


	public void setColors(int[] colors) {
		this.colors = colors;
	}


	public PApplet getP() {
		return p;
	}


	public void setP(PApplet p) {
		this.p = p;
	}


	public Instant getTimeBirth() {
		return timeBirth;
	}


	public void setTimeBirth(Instant timeBirth) {
		this.timeBirth = timeBirth;
	}


	public void setState(int state) {
		this.state = state;
	}
}
