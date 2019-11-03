package ca;

import graph.SubPlot;
import processing.core.PApplet;

public class Grid2D
{
	private int nrows, ncols;
	private int[][] cells;

	public Grid2D(int nrows, int ncols)
	{
		this.nrows = nrows;
		this.ncols = ncols;	
		cells = new int[nrows][ncols];
	}

	@Override
	public String toString()
	{
		String s = "";
		for(int i = 0; i < nrows; i++) {
			for(int j = 0; j < ncols; j++) {
				s += cells[i][j];
			}
			s += "\n";
		}
		return s;
	}
	
	public void initRandom(float prob)
	{
		for(int i = 0;i < nrows; i++) {
			for(int j = 0;j < ncols; j++) {
				cells[i][j] = (Math.random() < prob)? 1 : 0;
			}
		}
	}
	
	public void display(PApplet p, SubPlot plt)
	{
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				float[] c1 = plt.getPixelCoord(j, i);
				float[] c2 = plt.getPixelCoord(j+1, i+1);
				p.fill(255*(1-cells[i][j]));
				p.rect(c1[0], c1[1], c2[0], c2[1]);
			}
		}
	}
}