package game.particle;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.DB;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

public class Boid extends Mover {
	protected PShape s;
	protected float phiWander;
	protected int color;
	protected PApplet p;
	private float maxSpeed;
	private float maxForce = 200;
	float visionDistance;
	private float visionAngle = (float) Math.PI;
	private Instant birthRate;
	public int energy;
	private String type;
	private PImage img;


	public Boid(PApplet p, PVector pos, float mass, int color, float radius, String type) 
	{
		super(pos, new PVector(), mass,0);
		this.p = p;
		this.color = color;
		this.radius = radius;
		this.type = type;
		img = getImageByType(type);
		
		maxSpeed = p.random(150,300);
		visionDistance= p.random(50, 150);
		setShape(color, radius);
		birthRate = Instant.now();
		energy = DB.STARTING_ENERGY;
	}

	private PImage getImageByType(String type) {
		File imgFile = new File(type + ".png");
		return p.loadImage(imgFile.getAbsolutePath());
		
	}

	public void setShape(int color, float radius)
	{
		s = p.createShape();
		s.beginShape();
		s.noStroke();
		s.fill(color);
		s.vertex(-radius, radius/2);
		s.vertex(radius, 0);
		s.vertex(-radius, -radius/2);
		s.vertex(-radius/2, 0);
		s.endShape(PConstants.CLOSE);
		
	}

	@Override
	public void applyForce(PVector f)
	{
		super.applyForce(f.limit(maxForce));
	}

	@Override
	public void move(float dt)
	{
		super.move(dt);
		pos.x = (pos.x + p.width) % p.width;
		pos.y = (pos.y + p.height) % p.height;
	}

	public PVector seek(PVector target)
	{	
		PVector vd = PVector.sub(target, pos);
		vd.normalize().mult(maxSpeed);
		return PVector.sub(vd, vel);
	}

	public PVector flee(Boid b)
	{
		PVector f = new PVector();
		float d = PVector.dist(pos, b.pos);
		if( d > 0 && d < 2*visionDistance) {
			f = seek(b.pos);
		}
		return f.mult(-1);
	}

	public PVector pursuit(Boid b)
	{
		float deltaTPursuit = 0.8f;
		PVector d = PVector.mult(b.vel, deltaTPursuit);
		PVector target = PVector.add(b.pos, d);
		return seek(target);
	}

	public ArrayList<Boid> inCone(ArrayList<Boid> allBoids)
	{
		ArrayList<Boid> boidsInSight = new ArrayList<Boid>();
		for(Boid b: allBoids) {
			if(inSight(b.pos, visionDistance)) boidsInSight.add(b);
		}
		return boidsInSight;	
	}

	public boolean inSight(PVector t, float visionReach)
	{
		PVector r = PVector.sub(t, pos);
		float d = r.mag();
		float angle = PVector.angleBetween(vel,r);
		if ((d > 0) && (d < visionReach) && (angle < visionAngle))return true;
		return false;
	}


	public PVector separate(ArrayList<Boid> boids)
	{
		PVector vd = new PVector();
		for(Boid b : boids) {
			PVector r = PVector.sub(pos, b.pos);
			float d = r.mag();
			r.div(d*d);
			vd.add(r);
		}
		vd.normalize().mult(maxSpeed);
		return PVector.sub(vd, vel);
	}

	public PVector cohesion(ArrayList<Boid> boids)
	{
		PVector target = pos.copy();
		for(Boid b : boids) target.add(b.pos);
		target.div(boids.size()+1);
		return seek(target);
	}

	public PVector align(ArrayList<Boid> boids)
	{
		PVector vd = this.vel.copy();
		for(Boid b : boids) vd.add(b.vel);
		vd.normalize().mult(maxSpeed);
		return PVector.sub(vd, vel);
	}

	public void decrementEnergy() {
		Instant now = Instant.now();
		if(Duration.between(birthRate, now).toMillis() >= 1000) {
			birthRate = Instant.now();
			energy--;
		}
	}
	
	public boolean deathByStarvation() {
		decrementEnergy();
		if(energy<=0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void display(PApplet p)
	{
		p.pushMatrix();
		
		p.translate(pos.x, pos.y);
		p.rotate(vel.heading());
		p.shape(s);
//		p.imageMode(p.CENTER);
//		p.image(img, pos.x, pos.y);
		p.popMatrix();
		
		
		// energy counter
		p.textSize(10);
		p.fill(0);
		p.text(energy, pos.x, pos.y);
	}

	public String getType() {
		return type;
	}

}
