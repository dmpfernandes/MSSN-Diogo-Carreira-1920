package tpc2.ex5;

import graph.SubPlot;
import processing.IProcessingApp;

// see https://en.wikipedia.org/wiki/L-system

import processing.core.PApplet;
import tpc2.LSystem;
import tpc2.Rule;
import tpc2.StocasticLSystem;



public class Exercicio5 implements IProcessingApp 
{
	
	private LSystem lsys;

	private Turtle[] turtles;
	

	private double[] window = {-1, 1, 0, 15};
	private float[] viewport = {0.1f, 0.1f, 0.6f, 0.8f};
	private SubPlot plot;
	
	@Override
	public void setup(PApplet p) 
	{
		plot = new SubPlot(window, viewport, p.width, p.height);
		Rule[] ruleset = new Rule[2];

		ruleset[0] = new Rule('F', "G[+F]-F");
		ruleset[1] = new Rule('G', "G");
		lsys = new LSystem("F", ruleset);
		turtles = new Turtle[1];
		for (int j = 0; j < turtles.length; j++) {
			
			turtles[j] = new Turtle(p, plot, 1f, PApplet.radians(20), false);
			
		}
		
	}

	@Override
	public void draw(PApplet p, float dt) 
	{
		float[] r = plot.getBoundingBox();
		p.rect(r[0], r[1], r[2], r[3]);
		for (int i = 0; i < turtles.length; i++) {
			double[] startingPos = {0,0};
			turtles[i].setPose(startingPos, (float)Math.PI/2f);
			turtles[i].render(lsys);
		}
			
	}

	@Override
	public void keyPressed(PApplet p) 
	{
	}

	@Override
	public void mousePressed(PApplet p) 
	{
		System.out.println(lsys.getSequence());
		lsys.nextGeneration();
		for (int i = 0; i < turtles.length; i++) {
			turtles[i].scaling(0.85f);
		}
		
	}
		

	
}
