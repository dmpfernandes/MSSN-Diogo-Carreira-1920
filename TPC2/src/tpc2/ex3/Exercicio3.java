package tpc2.ex3;

import graph.SubPlot;
import processing.IProcessingApp;

// see https://en.wikipedia.org/wiki/L-system

import processing.core.PApplet;
import tpc2.ex2.LSystem;
import tpc2.ex2.Rule;
import tpc2.ex2.StocasticLSystem;
import tpc2.ex2.Turtle;

public class Exercicio3 implements IProcessingApp 
{
	private LSystem lsys;
//	private StocasticLSystem lsys;

	private Turtle turtle;
	private double[] startingPos = {0, 0};

	private double[] window = {-1, 1, 0, 15};
	private float[] viewport = {0.1f, 0.1f, 0.6f, 0.8f};
	private SubPlot plot;
	
	@Override
	public void setup(PApplet p) 
	{
		plot = new SubPlot(window, viewport, p.width, p.height);
		Rule[] kochCurve = new Rule[1];

//		primerio exemplo 
		kochCurve[0] = new Rule('F', "F+F--F+F");
		lsys = new LSystem("F", kochCurve);
		
//		koch snowflake 
//		kochCurve[0] = new Rule('F', "F+F--F+F");
//		lsys = new LSystem("+F--F--F", kochCurve);
		
//		stocastic koch snowflake
//		kochCurve[0] = new Rule('F', "F+F--F+F");
//		lsys = new StocasticLSystem("+F--F--F", kochCurve, 0.5f);

		turtle = new Turtle(p, plot, 0.05f, PApplet.radians(60), false);
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
