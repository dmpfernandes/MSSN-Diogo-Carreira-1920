package Fluids;
import Particle.Mover;
import processing.core.PApplet;
import processing.core.PVector;

public class Water extends Fluid{
	private static float density= 1000;
	private static float drag = 1;
	private float h;
	private PApplet p;
	private float scaling;
	
	public Water(PApplet p, float waterHeight, float scaling) {
		super(density);
		this.h = waterHeight;
		this.p = p;
		this.scaling = scaling;
	}
	
	public boolean isInside(Mover m) {
		return m.pos.y > h ? true : false;
	}
	
	public void display() {
		p.fill(p.color(30,144,255));
		p.rect(0, p.height-h, p.width, h);
	}

	@Override
	public PVector drag(Mover m) {
		float dirX = (float) (0.5 * drag * density * (m.getRadius() * Math.pow(Math.PI, 2) * m.getVel().x));
		float dirY = (float) (0.5 * drag * density * (m.getRadius() * Math.pow(Math.PI, 2) * m.getVel().y));

		PVector f = new PVector(dirX, dirY).mult(scaling);
		f.rotate((float) Math.PI);

		return f;
	}
}
