package Apps;
import Particle.Body;
import Particle.Boid;
import Particle.Planet;
import Particle.Star;
import processing.core.PApplet;
import processing.core.PVector;

public class StarSystemApp extends PApplet{
	public final float dimY = 100; 
	public final float dimX = 75;
	
	public final float g = 9.8f;
	public final float mass = 8000;
	public final float massStar = mass * 100000000; 
	public final float radius = 10f;
	public final float radiusStar = 100f;

	public float speedUp = 0.25f;
	public float timer;
	public float pixelsPerMeter;
	public float gInPixels;

	private int lastUpdate;
	private int startTime;
	
	private Star star;
	Planet planet;
	private PVector target, dist;
	private double distToTarget;
	
		
	public static void main(String[] args) 
	{
		
		PApplet.main(StarSystemApp.class);
		
	}

	@Override
	public void setup() {
		lastUpdate = millis();
		startTime = millis();

		
		star = new Star(this, new PVector(height/2, width/2), new PVector(0,0), massStar, color(255, 204, 0), radiusStar);
		planet = new Planet(this, new PVector(height/2-200, width/2), new PVector(0,0), mass, color(0, 0, 255), radius);
		star.display();
	}

	@Override
	public void draw() {
		this.background(124);
		int now = millis();
		float dt = (now - lastUpdate)/1000f;
		
		planet.applyForce(planet.orbit(star));

		planet.move(dt);
		planet.display();
		star.display();
		
	}

	@Override
	public void mousePressed() {
		
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

