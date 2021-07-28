package kgegame.scenes;

import java.util.Random;

import com.kudodesign.Input.InputConstants;
import com.kudodesign.Input.KGInput;

import com.kudodesign.kgengine.KGE;
import com.kudodesign.kgengine.KGScene;
import com.kudodesign.math.Vector2f;
import com.kudodesign.math.Vector3f;
import com.kudodesign.renderEngine.DisplayManager;

import kgegame.characters.Player;
import com.kudodesign.entities.e2d.sprites.Sprite2D;

import kgegame.env.Flag;
import kgegame.env.Obstacle;
import kgegame.env.Platform;
import com.kudodesign.kgengine.KGE.EntitySortType;

public class LevelOne extends KGScene implements InputConstants {

	
	Player player;
	Platform platform;
	Flag flag;
	Obstacle obstacle;
	Random r = new Random();
	
	
	@Override
	public void init() {
		
		
		KGE.addRenderGroup("player");
		
	
		KGE.addRenderGroup("player", EntitySortType.SortByZ);
		//camera2D.setOrthographic(-2, 2, DisplayManager.getWidth(), DisplayManager.getActualHeight());
		
		KGE.hideCursor();
		
		player = new Player("CharacterIdle", new Vector3f(0, 200, 0), new Vector3f(1f, 1f, 1),false);
		
		player.cameraController.addChild(camera2D);
			
		this.clearColor = new Vector3f(0, 0, 0);
		
		//platform = new Platform("platform", new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		//platform = new Platform("platform", new Vector3f(800, 0, 0), new Vector3f(1, 1, 1));
		//KGE.addSprite2D(platform, "player");
		
		camera2D.setPositionZ(1);
		
		//platform = new Platform("platform", new Vector3f(1000, -200, 0), new Vector3f(1, 1, 1));
		//platform.getSpeed().y = 30;
		
		
		//platform = new Platform("platform", new Vector3f(-1000, -200, 0), new Vector3f(1, 1, 1));
		//platform.getSpeed().x = -30;
		
		
		for(int i = -2; i < 40; i++) {
			
			platform = new Platform("platform", new Vector3f(800 * i, 0, 0), new Vector3f(1, 1, 1));
		
		}
		
		for(int i = -2; i < 20; i++) {
			
			platform = new Platform("Back1", new Vector3f(1600 * i, 100, -1), new Vector3f(1, 1, 1));
			platform = new Platform("Back2", new Vector3f(3200 * i, 100, -2), new Vector3f(1, 1, 1));
		
		}
		
		flag = new Flag("flag", new Vector3f(800*15, 250, 0), new Vector3f(1, 1, 1));
		
		
	
		
	}

	int timer = 0;
	@Override
	public void update() {
		if (KGInput.inputActivated(KEY_ESCAPE)) {
			KGE.setScene(MainMenu.class);
			
		}
	
		int randNum = (int)(r.nextFloat()*100);
		
		//System.out.println("Random num = " + randNum);
		//System.out.println("Player x = " + player.getWorldPosition().x);
		//System.out.println("Player y = " + player.getWorldPosition().y);
		
		if(randNum == 9 && timer > 30)
		{
			obstacle = new Obstacle("obtsacle", new Vector3f(player.getWorldPosition().x + 1200, 224, 0), new Vector3f(1, 1, 1));
			timer = 0;
		}
		
		timer++;
		
		
		
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
}
