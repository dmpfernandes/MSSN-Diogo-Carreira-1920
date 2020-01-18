package ca;

import graph.SubPlot;
import processing.IProcessingApp;
import processing.core.PApplet;
import processing.core.PConstants;

public class TestGrid2D implements IProcessingApp 
{
	private int nrows = 25;
	private int ncols = 35;
	private double[] window = {0, ncols, 0, nrows};
	private float[] viewport = {0.2f, 0.2f, 0.6f, 0.6f};
	private SubPlot plt;
	private Grid2D grid2d;
	
	@Override
	public void setup(PApplet p) 
	{
		plt = new SubPlot(window, viewport, p.width, p.height);
		grid2d = new Grid2D(nrows, ncols);
		grid2d.initRandom(0.2f);
		System.out.println(grid2d);
		p.rectMode(PConstants.CORNERS);
		p.frameRate(2);
	}

	@Override
	public void draw(PApplet p, float dt) 
	{	
		grid2d.display(p, plt);
		grid2d.initRandom(0.2f);		
	}

	@Override
	public void mousePressed(PApplet p) 
	{		
	}

	@Override
	public void keyPressed(PApplet p) 
	{		
	}

	@Override
	public void mouseReleased(PApplet p) 
	{		
	}
}
