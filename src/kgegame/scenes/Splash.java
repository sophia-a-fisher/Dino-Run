package kgegame.scenes;

import com.kudodesign.Input.InputConstants;
import com.kudodesign.Input.KGInput;
import com.kudodesign.audio.KGSound2D;
import com.kudodesign.entities.e2d.guis.GUIImage;
import com.kudodesign.kgengine.KGE;
import com.kudodesign.kgengine.KGScene;
import com.kudodesign.kgengine.timers.KGTimerID;
import com.kudodesign.kgengine.timers.TimerListener;
import com.kudodesign.math.Vector3f;

public class Splash extends KGScene implements InputConstants, TimerListener {

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		GUIImage g;
		g = KGE.addGUIImage("kudo_logo_black");
		
		//add a 1 second fade from black
		g.addColorFade(0, 1, new Vector3f(0, 0, 0), new Vector3f(1,1,1), false).start();
		
		//and a half second fade back out, starting after 1.5 seconds
		g.addColorFade(1.75f, 0.25f, new Vector3f(1, 1, 1), new Vector3f(0,0,0), false).start();
		
		KGE.setTimer(this, 4.0);
		//SoundBuffer buffer = new SoundBuffer("logo3a.wav");
		KGSound2D logoSound = KGE.createSound("logo3a.wav");
		logoSound.play();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (KGInput.inputActive(KEY_ESCAPE)) {
			KGE.setScene(MainMenu.class);
		}
		
	}

	@Override
	public void OnTimerEvent(KGTimerID id) {
		// TODO Auto-generated method stub
		KGE.setScene(MainMenu.class);
	}


}
