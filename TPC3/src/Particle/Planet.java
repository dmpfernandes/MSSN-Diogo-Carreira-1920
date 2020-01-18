package Particle;

import processing.core.PApplet;
import processing.core.PVector;

public class Planet extends Mover{

	public PVector pos;
	private PVector vel;
	private float radius;
	private int color;
	private PApplet p;
	public final float PLANET_SPEED = 10;
	public Planet(PApplet p,PVector pos, PVector vel, float mass, int color, float radius) {
		super(pos, vel, mass, radius);
		this.pos = pos;
		this.vel = vel;
		this.color = color;
		this.radius = radius;
		this.p = p;
	}
	
	//Math.sqrt(Math.pow(distV.x, 2) + Math.pow(distV.y, 2)) Normalizar um pvector
	public PVector orbit(Star star) {
		vel = new PVector(0,0);
		float g = (float) (6.67430 * Math.pow(10, -11));
		System.out.println(g);
		PVector distToStar = PVector.sub(pos, star.pos);
		System.out.println(distToStar.x + "  " + distToStar.y);
		float mImJ = mass * star.mass;
		float rSquared = (float) Math.sqrt(Math.pow(distToStar.x, 2) + Math.pow(distToStar.y, 2));
		vel = distToStar.mult(g * (mImJ / rSquared)).normalize().rotate((float) Math.PI);
		
		return vel;
	}
	
	@Override
	public void applyForce(PVector f) {
		super.applyForce(f);
	}
	
	public void display() {
		p.fill(color);
		p.ellipse(pos.x, pos.y, radius, radius);
	}
}
