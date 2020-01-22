package game.flock;

import java.util.ArrayList;
import java.util.List;

import game.DB;
import game.grid.Board;
import game.particle.Boid;
import processing.core.PApplet;
import processing.core.PVector;

public class Flock {
	protected ArrayList<Boid> boids;
	protected int nboids;
	private String type;
	private Board grid;
	private PApplet p;
	private int color;
	protected float radius;
	private List<Flock> preys;
	private List<Flock> predators;

	public Flock(PApplet p, int nboids, String type, Board grid, int color, float radius) {
		this.nboids = nboids;
		this.type = type;
		this.grid = grid;
		this.p = p;
		this.color = color;
		this.radius = radius;
		boids = new ArrayList<Boid>();
		createFlock(p);

	}

	protected void createFlock(PApplet p) {
		for (int i = 0; i < nboids; i++) {
			PVector pos = new PVector(p.random(p.width), p.random(p.height));
			Boid b = new Boid(p, pos, 1, color, radius, type);
			addBoid(b);
		}
	}

	public void addBoid(Boid b) {
		boids.add(b);
	}

	public Boid getBoid(int n) {
		return boids.get(n);
	}

	public void move(float dt) {
		for (Boid b : boids)
			b.move(dt);
	}

	public void applyForces() {
		for (Boid b : boids) {
			ArrayList<Boid> inSight = b.inCone(boids);
			PVector fs = b.separate(inSight);
			PVector fa = b.align(inSight);
			PVector fc = b.cohesion(inSight);
			b.applyForce(fs.add(fa).add(fc));
			if(inSight.isEmpty()) {
				b.applyForce(PVector.random2D().mult(0.1f));
			}
			switch (type) {
			case "rabbit":
				predators.stream().forEach(p -> {
					ArrayList<Boid> predatorsInSight = b.inCone(p.boids);
					if (!predatorsInSight.isEmpty() && (predatorsInSight.get(0).getType().equals("eagle") || predatorsInSight.get(0).getType().equals("fox"))) {
						PVector ff = b.flee(predatorsInSight.get(0));
						b.applyForce(ff); 
					}
				});
				
				break;
			case "mouse":
				predators.stream().forEach(p -> {
					ArrayList<Boid> predatorsInSight = b.inCone(p.boids);
					if (!predatorsInSight.isEmpty() && (predatorsInSight.get(0).getType().equals("eagle") || predatorsInSight.get(0).getType().equals("fox"))) {
						PVector ff = b.flee(predatorsInSight.get(0));
						b.applyForce(ff); 
					}
				});
				
				break;
			case "eagle":
				preys.stream().forEach(p -> {
					ArrayList<Boid> preysInSight = b.inCone(p.boids);
					if (!preysInSight.isEmpty()) {
						PVector fp = b.pursuit(preysInSight.get(0));
						b.applyForce(fp); 
					}
				});
				break;
			case "fox":
				predators.stream().forEach(p -> {
					ArrayList<Boid> predatorsInSight = b.inCone(p.boids);
					if (!predatorsInSight.isEmpty() && (predatorsInSight.get(0).getType().equals("eagle"))) {
						PVector ff = b.flee(predatorsInSight.get(0));
						b.applyForce(ff); 
					}
				});
				preys.stream().forEach(p -> {
					ArrayList<Boid> preysInSight = b.inCone(p.boids);
					if (!preysInSight.isEmpty() && (preysInSight.get(0).getType().equals("rabbit") || preysInSight.get(0).getType().equals("mouse"))) {
						PVector fp = b.pursuit(preysInSight.get(0));
						b.applyForce(fp); 
					}
				});
				break;
			default:
				break;
			}
		}
		checkEatenFood();
		checkEatenBoids();
		checkNewBorns();
	}

	public void checkEatenFood() {
		if (type.equals("rabbit")) {
			checkEatenFoodByType(DB.GRASS_TO_RABBIT_NUTRITIVE_VALUE);
		} else if(type.equals("mouse")) {
			checkEatenFoodByType(DB.GRASS_TO_MOUSE_NUTRITIVE_VALUE);
		}
	}

	private void checkEatenFoodByType(int foodEnergy) {
		for (Boid b : boids) {
			if (!b.deathByStarvation()) {
				for (int i = 0; i < grid.getNrows(); i++) {
					for (int j = 0; j < grid.getNcols(); j++) {
						if (grid.getBoard()[i][j].getState() == 2 && b.pos.y > grid.getH() * i
								&& b.pos.y < grid.getH() * i + grid.getH() && b.pos.x > grid.getW() * j
								&& b.pos.x < grid.getW() * j + grid.getW()) {
							b.energy = b.energy + foodEnergy;
							grid.getBoard()[i][j].setFertil();
						}
					}
				}
			}
		}
	}

	public void checkEatenBoids() {
		switch (type) {
		case "eagle":
			preys.forEach(p -> {
				if(p.type.equals("rabbit")) {
					checkEatenBoidsByType(p, DB.RABBIT_TO_EAGLE_NUTRITIVE_VALUE);
				} else if(p.type.equals("mouse")) {
					checkEatenBoidsByType(p, DB.RABBIT_TO_FOX_NUTRITIVE_VALUE);
				}
				else if(p.type.equals("fox")) {
					checkEatenBoidsByType(p, DB.FOX_TO_EAGLE_NUTRITIVE_VALUE);
				}
			});
			break;
		case "fox":
			preys.forEach(p -> {
				if(p.type.equals("rabbit")) {
					checkEatenBoidsByType(p, DB.MOUSE_TO_EAGLE_NUTRITIVE_VALUE);
				} else if(p.type.equals("mouse")) {
					checkEatenBoidsByType(p, DB.MOUSE_TO_FOX_NUTRITIVE_VALUE);
				}
			});
			break;
		default:
			break;
		}
	}

	private void checkEatenBoidsByType(Flock preys, int foodEnergy) {
		for (Boid predator : boids) {
			if (!predator.deathByStarvation()) {
				for (Boid presa : preys.boids) {
					if (!presa.deathByStarvation() && predator.pos.x > presa.pos.x - 10
							&& predator.pos.x < presa.pos.x + 10 && predator.pos.y > presa.pos.y - 10
							&& predator.pos.y < presa.pos.y + 10) {
						presa.energy = 0;
						predator.energy = predator.energy + foodEnergy;
					}

				}

			}
		}
	}

	public void checkNewBorns() {
		ArrayList<Boid> newBorns = new ArrayList<Boid>();
		for (Boid b : boids) {
			// set the energy needed to breed
			if (b.energy >= DB.REPRODUCTION_ENERGY) {
				b.energy = b.energy / 2;
				PVector pos = new PVector(b.pos.x, b.pos.y);
				Boid newBorn = new Boid(p, pos, 1, color, radius, type);
				newBorn.energy = b.energy;
				newBorns.add(newBorn);
			}
		}
		for (Boid newBorn : newBorns) {
			addBoid(newBorn);
		}
	}

	public void display(PApplet P) {
		for (Boid b : boids)
			if (!b.deathByStarvation())
				b.display(p);
	}
	
	public List<Flock> getPreys() {
		return preys;
	}
	public List<Flock> getPredators() {
		return predators;
	}

	public void setPredators(List<Flock> predators) {
		this.predators = predators;
	}

	public void setPreys(List<Flock> preys) {
		this.preys = preys;
	}
	
	public ArrayList<Boid> getBoids() {
		return boids;
	}
}
