package Apps;
import java.util.ArrayList;
import java.util.List;

import Particle.Body;
import processing.core.PApplet;
import processing.core.PVector;

public class ParticleSystemApp extends PApplet{
	private static final int NUMBER_OF_PARTICLES = 100;
	
	public final float dimY = 100; 
	public final float dimX = 75;
	
	public final float g = 9.8f; 
	public final float mass = 80; 
	public final float radius = 10f;
	
	public float speedUp = 0.25f;
	public float timer;
	public float pixelsPerMeter;

	private int lastUpdate;
	private int startTime;
	
	
	private List<Body> particleSystem;
	
		
	public static void main(String[] args) 
	{
		
		PApplet.main(ParticleSystemApp.class);
		
	}

	@Override
	public void setup() {
		lastUpdate = millis();
		startTime = millis();
		
		particleSystem = new ArrayList<Body>();
		for (int i = 0; i < NUMBER_OF_PARTICLES; i++) {
			Body particle = new Body(this, new PVector(this.height/2, this.width/2), new PVector(0,0), mass, radius, (int) (Math.random() * 255));
			particle.display();
			particleSystem.add(particle);
		}
	}

	@Override
	public void draw() {
		this.background(124);
		int now = millis();
		float dt = (now - lastUpdate)/1000f;
		lastUpdate = now;
		pixelsPerMeter = this.height/dimY;
		
		particleSystem.stream().forEach(p -> {
			PVector f = new PVector((float) (Math.random()*1000), (float) (Math.random()*1000));
			p.applyForce(f.rotate((float) (Math.random()* (2 * Math.PI))));
			p.move(dt);
			p.display();
		});
	}
	

	@Override
	public void mousePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void settings()
	{
		size(1000, 750);
	}
	
}
