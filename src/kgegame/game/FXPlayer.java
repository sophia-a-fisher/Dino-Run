package kgegame.game;

import com.kudodesign.audio.KGSound2D;
import com.kudodesign.kgengine.KGE;

public class FXPlayer {
	
	
	static KGSound2D[] acornshoot = new KGSound2D[5];
	
	static int shotIndex = 0;

	
	
	public static void init() {
	
			
			acornshoot[0] = KGE.createSound("Shoot1a.wav");
			acornshoot[1] = KGE.createSound("Shoot1a.wav");
			acornshoot[2] = KGE.createSound("Shoot2a.wav");
			acornshoot[3] = KGE.createSound("Shoot3a.wav");
			acornshoot[4] = KGE.createSound("Shoot4a.wav");
		

	}
	
	public static void playShot() {
		
		
			shotIndex = (shotIndex + 1) % 5;
			acornshoot[shotIndex].play();
		
		
	}
	
	
}
