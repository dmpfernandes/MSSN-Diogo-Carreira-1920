package Apps;
import Particle.Body;
import Particle.Boid;
import processing.core.PApplet;
import processing.core.PVector;

public class BoidChaseApp extends PApplet{
	public final float dimY = 100; 
	public final float dimX = 75;
	
	public final float g = 9.8f; 
	public final float mass = 80; 
	public final float radius = 10f;
	
	public float speedUp = 0.25f;
	public float timer;
	public float pixelsPerMeter;
	public float gInPixels;

	private int lastUpdate;
	private int startTime;
	
	private Boid boid;
	private PVector target, dist;
	private double distToTarget;
	
		
	public static void main(String[] args) 
	{
		
		PApplet.main(BoidChaseApp.class);
		
	}

	@Override
	public void setup() {
		lastUpdate = millis();
		startTime = millis();

		
		boid = new Boid(this, new PVector(height/2, width/2), new PVector(0,0), mass, 255, radius);
		boid.display();
	}

	@Override
	public void draw() {
		this.background(124);
		int now = millis();
		float dt = (now - lastUpdate)/1000f;

		
		
		lastUpdate = now;
		pixelsPerMeter = this.height/dimY;
		gInPixels = -g*pixelsPerMeter;
		PVector f = new PVector(0, mass*gInPixels);
		if(target != null) {
			PVector distV = new PVector(target.x - boid.pos.x, target.y - boid.pos.y);
			double currentDist = Math.sqrt(Math.pow(distV.x, 2) + Math.pow(distV.y, 2));
			
			if(distToTarget/2 >= currentDist) {
				boid.brake();
				distToTarget = currentDist;
				System.out.println("BOID: " + boid.pos.x);
			}
			boid.applyForce(boid.seek(target));
			
		}
		
		
		boid.move(dt);
		boid.display();
		
	}

	@Override
	public void mousePressed() {
		target = new PVector(mouseX, mouseY);
		dist = new PVector(target.x - boid.pos.x, target.y - boid.pos.y);
		distToTarget = Math.sqrt(Math.pow(dist.x, 2) + Math.pow(dist.y, 2));
		
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

