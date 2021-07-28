package kgegame.scenes;

import com.kudodesign.Input.InputConstants;
import com.kudodesign.Input.KGInput;
import com.kudodesign.entities.e2d.guis.GUIImage;
import com.kudodesign.kgengine.KGE;
import com.kudodesign.kgengine.KGScene;
import com.kudodesign.kgengine.KGE.EntitySortType;
import com.kudodesign.math.Vector3f;

import kgegame.characters.Player;
import kgegame.env.Platform;

public class Instruct extends KGScene implements InputConstants {

	GUIImage space;
	GUIImage toJump;
	GUIImage escPrompt;
	Player player;
	Platform platform;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		space = new GUIImage("Space");
		space.getPosition().y = 0.2f;
		KGE.addGUIImage(space);
		
		toJump = new GUIImage("toJump");
		toJump.getPosition().y = 0.0f;
		KGE.addGUIImage(toJump);
		
		escPrompt = new GUIImage("escPrompt");
		escPrompt.getPosition().y = 0.85f;
		escPrompt.getPosition().x = -0.75f;
		KGE.addGUIImage(escPrompt);
		
		
		KGE.addRenderGroup("player");
		camera2D.setPositionZ(1);
		
		KGE.addRenderGroup("player", EntitySortType.SortByZ);
		//camera2D.setOrthographic(-2, 2, DisplayManager.getWidth(), DisplayManager.getActualHeight());
		
		//KGE.hideCursor();
		
		player = new Player("CharacterIdle", new Vector3f(0, 200, 0), new Vector3f(1f, 1f, 1), true);
		
		//player.cameraController.addChild(camera2D);
		
		platform = new Platform("platform", new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		
		KGE.hideCursor();
		this.clearColor = new Vector3f(1, 1, 1);
	}

	@Override
	public void update() {
		
		if (KGInput.inputActivated(KEY_ESCAPE)) {
			KGE.setScene(MainMenu.class);
			
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

}
