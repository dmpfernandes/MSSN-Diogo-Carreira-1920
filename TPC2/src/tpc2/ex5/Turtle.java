package tpc2.ex5;

import graph.SubPlot;
import processing.core.PApplet;
import tpc2.LSystem;
import tpc2.StocasticLSystem;

public class Turtle 
{
	private PApplet p;
	private SubPlot plot;
	private float len;
	private float theta;

	public Turtle(PApplet p, SubPlot plot, float step, 
			float theta, boolean horizontal)
	{
		this.p = p;		
		this.plot = plot;
		double[] window = plot.getWindow();
		float[] viewport = plot.getViewport();
		if (horizontal)
			len = step/(float)(window[1]-window[0])*p.width*viewport[2];
		else
			len = step/(float)(window[3]-window[2])*p.height*viewport[3];
		this.theta = theta;				
	} 

	public void setPose(double[] position, float orientation)
	{
		float[] c = plot.getPixelCoord(position);
		p.translate(c[0], c[1]);
		p.rotate(-orientation);
	}
	
	public void scaling(float s)
	{
		len *= s;
	}
	
	public void render(LSystem lsystem) {
		p.stroke(0); 
		for (int i = 0; i < lsystem.getSequence().length(); i++) {
			char c = lsystem.getSequence().charAt(i);

			if (c == 'G') {
				p.line(0, 0, len*.5f, 0);
				p.translate(len*.5f, 0);
			}
			else if (c == 'F') {
				p.ellipse(0, 0, len*.5f, len*.5f);
//				p.translate(len*.5f, 0);
			}
			else if (c == ' ') p.translate(len*.5f, 0);
			else if (c == '+') p.rotate(theta);
			else if (c == '-') p.rotate(-theta);
			else if (c == '[') p.pushMatrix();
			else if (c == ']') p.popMatrix();
		} 
	}
	
	public void render(StocasticLSystem lsystem) {
		p.stroke(0); 
		for (int i = 0; i < lsystem.getSequence().length(); i++) {
			char c = lsystem.getSequence().charAt(i);

			if (c == 'F') {
				p.line(0, 0, len*.5f, 0);
				p.translate(len*.5f, 0);
			}
			else if (c == 'G') {
				p.ellipse(0, 0, len*0.2f, len*0.2f);
			}
			else if (c == '+') p.rotate(theta);
			else if (c == '-') p.rotate(-theta);
			else if (c == '[') p.pushMatrix();
			else if (c == ']') p.popMatrix();
		} 
	}
}