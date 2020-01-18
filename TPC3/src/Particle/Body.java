package Particle;
import processing.core.PApplet;
import processing.core.PVector;

public class Body extends Mover{
	private int color;
	private PApplet p;
	
	public Body(PApplet p, PVector pos, PVector vel, float mass, float radius, int color) {
		super(pos, vel, mass, radius);
		this.color = color;
		this.p = p;
	}
	
	public void display() {
		p.fill(color);
		p.ellipse(pos.x, pos.y, radius, radius);
	}

}
