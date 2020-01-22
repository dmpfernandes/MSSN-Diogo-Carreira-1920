package game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.flock.Flock;
import game.grid.Board;
import processing.IProcessingApp;
import processing.core.PApplet;
import processing.core.PVector;

public class GameOfLife implements IProcessingApp {

	private static final int BOARD_X = 70;
	private static final int BOARD_Y = 70;

	private Board board;
	private Flock rabbits;
	private Flock eagles;
	private Flock mice;
	private Flock foxes;

	private List<Flock> eaglePreys;
	private List<Flock> foxPreys;
	private List<Flock> foxPredators;

	private List<Flock> rabbitAndMousePredators;
	private PVector target;

	@Override
	public void setup(PApplet p) {
		board = new Board(BOARD_X, BOARD_Y, p);
		board.initFertilTerrain(0.1f, 0.1f);
		
		rabbits = new Flock(p, DB.N_RABBITS, "rabbit", board, p.color(0,0,255), 15);
		mice = new Flock(p, DB.N_MICE, "mouse", board, p.color(0,255,255), 15);			
		eagles = new Flock(p, DB.N_EAGLES, "eagle", board, p.color(255,0,0), 15);
		foxes = new Flock(p, DB.N_FOXES, "fox", board, p.color(124,124,124), 15);

		target = new PVector();
		p.frameRate(20);
		eaglePreys = new ArrayList<Flock>();
		eaglePreys.add(mice);
		eaglePreys.add(rabbits);
		eaglePreys.add(foxes);
		rabbitAndMousePredators = new ArrayList<>();
		rabbitAndMousePredators.add(eagles);
		rabbitAndMousePredators.add(foxes);
		foxPreys = new ArrayList<Flock>();
		foxPreys.add(mice);
		foxPreys.add(rabbits);
		foxPredators = new ArrayList<Flock>();
		foxPredators.add(eagles);
		
		rabbits.setPredators(rabbitAndMousePredators);
		mice.setPredators(rabbitAndMousePredators);
		
		eagles.setPreys(eaglePreys);
		foxes.setPreys(foxPreys);
		foxes.setPredators(foxPredators);

	}

	@Override
	public void draw(PApplet p, float dt) {
		board.run();
		board.display();
		eagles.applyForces();
		eagles.move(dt);
		eagles.display(p);
		foxes.applyForces();
		foxes.move(dt);
		foxes.display(p);
		rabbits.applyForces();
		rabbits.move(dt);
		rabbits.display(p);
		mice.applyForces();
		mice.move(dt);
		mice.display(p);
	}
	
	@Override
	public void mousePressed(PApplet p) {

	}

	@Override
	public void keyPressed(PApplet p) {
		
	}

	@Override
	public void mouseReleased(PApplet p) {
		// TODO Auto-generated method stub
		
	}

}
