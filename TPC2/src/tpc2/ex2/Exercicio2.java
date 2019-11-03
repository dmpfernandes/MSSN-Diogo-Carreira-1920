package tpc2.ex2;

import graph.SubPlot;
import processing.IProcessingApp;

// see https://en.wikipedia.org/wiki/L-system

import processing.core.PApplet;

public class Exercicio2 implements IProcessingApp 
{
	private LSystem lsys;
	private Turtle turtle;
	private double[] startingPos = {0, 0};

	private double[] window = {-1, 1, 0, 15};
	private float[] viewport = {0.1f, 0.1f, 0.6f, 0.8f};
	private SubPlot plot;
	
	@Override
	public void setup(PApplet p) 
	{
		plot = new SubPlot(window, viewport, p.width, p.height);
		Rule[] ruleset = new Rule[2];
//		ruleset[0] = new Rule('F', "FF+[+F-F-F]-[-F+F+F]");
//		ruleset[0] = new Rule('F', "F[[+F]-F]");
		//primerio exemplo
//		ruleset[0] = new Rule('F', "F[[-FF]+F+F]");
		//segundo exemplo
		ruleset[0] = new Rule('F', "GGGGGG");
		ruleset[1] = new Rule('G', "GG");
		lsys = new LSystem("FG", ruleset);
		turtle = new Turtle(p, plot, 2.4f, PApplet.radians(10), false);
	}

	@Override
	public void draw(PApplet p, float dt) 
	{
		float[] r = plot.getBoundingBox();
		p.rect(r[0], r[1], r[2], r[3]);
		turtle.setPose(startingPos, (float)Math.PI/2f);
		turtle.render(lsys);	
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
		turtle.scaling(0.85f);
	}
}
