package Apps;
import Fluids.Air;
import Fluids.Fluid;
import Fluids.Water;
import Particle.Body;
import processing.core.PApplet;
import processing.core.PVector;

public class FallingBodyInWater extends PApplet{
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
	
	private Body ball;
	private Water water;
	private Fluid air;
	
		
	public static void main(String[] args) 
	{
		
		PApplet.main(FallingBodyInWater.class);
		
	}

	@Override
	public void setup() {
		lastUpdate = millis();
		startTime = millis();
		water = new Water(this, this.height/2, 0.01f);
		air = new Air(1);
		
		water.display();
		ball = new Body(this, new PVector(this.width/2, 0), new PVector(0,0), mass, radius, 255);
		ball.display();
		
	}

	@Override
	public void draw() {
		this.background(124);
		water.display();
		int now = millis();
		float dt = (now - lastUpdate)/1000f;
		lastUpdate = now;
		pixelsPerMeter = this.height/dimY;
		gInPixels = -g*pixelsPerMeter;
		PVector f = new PVector(0, mass*gInPixels);
		ball.applyForce(f.rotate((float) Math.PI));
		
		if (water.isInside(ball))
			f = water.drag(ball);
		else f = air.drag(ball);
		ball.applyForce(f);
		
		ball.move(dt);
		ball.display();
		
		if(ball.pos.y > height) {
			this.text("A bola demorou " + (millis() - startTime)/1000f + "segundos a cair.", dimX/2, dimY/2);
			this.noLoop();
		}
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
