package Fluids;

import Particle.Mover;
import processing.core.PVector;

public class Air extends Fluid{
	
	public Air(float scaling) {
		super(scaling);
	}

	@Override
	public PVector drag(Mover m) {
		float dirX = (float) (0.65 * (m.getRadius() * Math.pow(Math.PI, 2) * m.getVel().x));
		float dirY = (float) (0.65 * (m.getRadius() * Math.pow(Math.PI, 2) * m.getVel().y));

		PVector f = new PVector(dirX, dirY);
		f.rotate((float) Math.PI);

		return f;
	}
	

}
