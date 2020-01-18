package tpc2;
import processing.IProcessingApp;
import processing.core.PApplet;
import tpc2.ex1.Exercicio1;
import tpc2.ex2.Exercicio2;
import tpc2.ex3.Exercicio3;

public class ProcessingSetup extends PApplet
{
	private static IProcessingApp app;
	private int lastUpdate;
	
	@Override
	public void settings()
	{
		size(1500, 1000);
	}
	
	@Override
	public void setup()
	{		
		app.setup(this);
		lastUpdate = millis();
	}
	
	@Override
	public void draw()
	{	
		int now = millis();
		float dt = (now - lastUpdate)/1000f;
		lastUpdate = now;
		app.draw(this, dt);
	}

	@Override
	public void mousePressed()
	{
		app.mousePressed(this);
	}
	
	@Override
	public void keyPressed()
	{
		app.keyPressed(this);
	}
		
	public static void main(String[] args) 
	{
//		app = new LSystemApp();
//		app = new Exercicio1();
//		app = new Exercicio2();
		app = new Exercicio3();
		PApplet.main(ProcessingSetup.class);
	}
}
