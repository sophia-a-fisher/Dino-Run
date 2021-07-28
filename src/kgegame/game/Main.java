package kgegame.game;



import com.kudodesign.kgengine.KGE;

import kgegame.scenes.Instruct;
import kgegame.scenes.LevelOne;
import kgegame.scenes.Lose;
import kgegame.scenes.MainMenu;
import kgegame.scenes.Splash;
import kgegame.scenes.Win;



public class Main {

	public static void main(String[] args) {
		
		
		//KGE.init(800, 480);
		//KGE.initWindowToScreenSize();
		
		KGE.setShowDebug(false);
		//KGE.set_showFPS(true);
		KGE.addScene(LevelOne.class);
		KGE.addScene(MainMenu.class);
		KGE.addScene(Instruct.class);
		KGE.addScene(Lose.class);
		KGE.addScene(Win.class);
		KGE.addScene(Splash.class);
		KGE.setUseFullScreen(true);
		KGE.setScene(Splash.class);
	
		KGE.setGameData(new GD());
		
		KGE.run();

	}

}