package Particle;
import processing.core.PVector;

public class Mover {
	public PVector pos;
	protected PVector vel;
	protected PVector acc;
	
	protected float mass;
	protected float radius;
	
	public Mover(PVector pos, PVector vel, float mass, float radius) {
		this.acc = new PVector();
		this.pos = pos;
		this.vel = vel;
		this.mass = mass;
		this.radius = radius;
		
	}
	
	public void applyForce(PVector f) {
		acc.add(PVector.div(f, mass));
	}
	
	public void move(float dt) {
		vel.add(acc.mult(dt));
		pos.add(PVector.mult(vel,dt));
		acc.mult(0);
	}

	public PVector getVel() {
		return vel;
	}

	public void setVel(PVector vel) {
		this.vel = vel;
	}

	public PVector getPos() {
		return pos;
	}

	public float getMass() {
		return mass;
	}

	public float getRadius() {
		return radius;
	}
	
	
}
