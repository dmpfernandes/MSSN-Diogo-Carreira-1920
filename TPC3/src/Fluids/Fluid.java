package Fluids;
import Particle.Mover;
import processing.core.PVector;

public abstract class Fluid {
	private float density;
	
	protected Fluid(float density) {
		this.density = density;
		
	}
	
	public abstract PVector drag(Mover m);
}
