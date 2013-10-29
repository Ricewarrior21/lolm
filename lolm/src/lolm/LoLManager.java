package lolm;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class LoLManager extends BasicGame {
	// This class holds the Map, AStarPathFinder, Lane Nodes for minion pathing,
	// Towers, Minions, Jungle Creeps, Champions
	Game game; 
	
	// Use this class to update target, position, attacking 
	// (all entities updated here to calculate interactions between multiple
	// entities such as attacking towers, other minions, champions)
	//EntityManager em; 
	
	// Pass game information to this class to draw what's happening
	// The information is taken from the EntityManager in order to update 
	// only when all interactions and movement is calculated
	Renderer rm;
	
	// Handles input by player
	InputChecker im;
	
	public LoLManager() {
		super("LoL Manager");
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new LoLManager());
			app.setDisplayMode(1024,768,false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		rm = new Renderer(container.getGraphics());
		im = new InputChecker(container.getInput());
		game = new Game(rm, im);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		game.update();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.clear();
		game.render();
	}
}
