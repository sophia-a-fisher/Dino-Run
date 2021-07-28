package kgegame.scenes;

import java.lang.System.Logger.Level;

import com.kudodesign.Input.InputConstants;
import com.kudodesign.Input.KGInput;
import com.kudodesign.audio.KGSound2D;
import com.kudodesign.entities.e2d.guis.GUIImage;
import com.kudodesign.entities.e2d.guis.buttons.KGButton;
import com.kudodesign.kgengine.KGE;
import com.kudodesign.kgengine.KGScene;
import com.kudodesign.kgengine.timers.KGTimerID;
import com.kudodesign.kgengine.timers.TimerListener;
import com.kudodesign.math.Vector2f;
import com.kudodesign.math.Vector3f;
import com.kudodesign.renderEngine.DisplayManager;



public class Lose extends KGScene implements InputConstants, TimerListener {

	KGButton pButton;
	KGButton qButton;
	//KGButton iButton;
	KGSound2D titlemusic;

	boolean fadingAudio = false;
	float currentGain = 1.0f;
	
	KGSound2D titleMusic;
	GUIImage GameOver;
	KGTimerID instruct, play, quit;
	

	@Override
	public void init() {

		KGE.showCursor();
		
		this.clearColor = new Vector3f(0,0,0);
		pButton = KGE.addKGButton("PlayButton", new Vector2f(-0.55f, -0.7f), new Vector2f(1f, 1f), "buttonhover.wav", "");
		qButton = KGE.addKGButton("QuitButton", new Vector2f(0.55f, -0.7f), new Vector2f(1f, 1f), "buttonhover.wav", "");
		//iButton = KGE.addKGButton("InstructButton", new Vector2f(0, -0.7f), new Vector2f(1f, 1f), "buttonhover.wav", "");
		GameOver = new GUIImage("GameOver");
		GameOver.getPosition().y = 0.2f;
		KGE.addGUIImage(GameOver);
		
		titleMusic  = KGE.createSound("titlemusic.ogg");
		titleMusic.loop();

	}

	@Override
	public void update() {
		if (KGInput.inputActivated(KEY_ESCAPE)) {

			quit = KGE.setTimer(this, 1);
			fadingAudio = true;
		}
		
		

		if (pButton.clicked()) {

			play = KGE.setTimer(this, 1);
			fadingAudio = true;

		}

		if (qButton.clicked()) {

			quit = KGE.setTimer(this, 1);
			fadingAudio = true;

		}
		
		if(fadingAudio) {
			if (currentGain > 0) {
				titleMusic.setGain(currentGain);
				currentGain -= 1 * DisplayManager.getDelta();
			}
		}
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnTimerEvent(KGTimerID ID) {
		
		

		if(ID == play) {
			KGE.setScene(LevelOne.class);
		}
		
		if(ID == quit) {
			KGE.quit();
			
		}
		
		if(ID == instruct) {
			KGE.setScene(Instruct.class);
			//go to instructions page
		}
		

	}

}
