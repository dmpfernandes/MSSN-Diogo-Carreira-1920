package Particle;

import processing.core.PApplet;
import processing.core.PVector;

public class Boid extends Mover{

	public PVector pos;
	private PVector vel;
	private float radius;
	private int color;
	private PApplet p;
	private BoidDNA dna;
	
	public Boid(PApplet p,PVector pos, PVector vel, float mass, int color, float radius) {
		super(pos, vel, mass, radius);
		this.pos = pos;
		this.vel = vel;
		this.color = color;
		this.radius = radius;
		this.p = p;
		dna = new BoidDNA(p);
		
	}

	public PVector flee(PVector target) {
		return seek(target).mult(-1);
	}
	
	public PVector seek(PVector target) {
		PVector f = PVector.sub(target, pos);
		f.normalize().mult(dna.maxSpeed);
		return PVector.sub(f, vel);
	}
	
	public PVector brake() {
		return vel.mult(0.5f);
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
