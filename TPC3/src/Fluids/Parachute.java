package Fluids;
import Particle.Mover;
import processing.core.PApplet;
import processing.core.PVector;

public class Parachute extends Fluid{
	private static float density= 1;
	private static float drag = 1;
	private float h;
	private PApplet p;
	private float scaling;
	
	public Parachute(PApplet p, float deployHeight, float scaling) {
		super(density);
		this.h = deployHeight;
		this.p = p;
		this.scaling = scaling;
	}
	
	public boolean isInside(Mover m) {
		return m.pos.y > h ? true : false;
	}
	
	public void display(Mover m) {
		p.fill(p.color(120,45,255));
		p.arc(m.pos.x, m.pos.y, 100 , 100 , (float) (5 * Math.PI/4), (float)(7 * Math.PI)/4);
		
	}

	@Override
	public PVector drag(Mover m) {
		float totalArea = (float) (m.getRadius() * Math.pow(Math.PI, 2) + (50 * Math.pow(Math.PI, 2))/4);
		float dirX = (float) (0.5 * drag * density * totalArea * m.getVel().x);
		float dirY = (float) (0.5 * drag * density * totalArea * m.getVel().y);

		PVector f = new PVector(dirX, dirY).mult(scaling);
		f.rotate((float) Math.PI);

		return f;
	}
}
