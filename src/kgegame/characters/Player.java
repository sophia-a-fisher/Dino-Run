package kgegame.characters;

import java.util.Random;

import com.kudodesign.Input.InputConstants;
import com.kudodesign.Input.KGInput;
import com.kudodesign.entities.KGLight;
import com.kudodesign.entities.collision.KGBody2D.Body2DType;
import com.kudodesign.entities.collision.KGBody2D.Collision2DType;
import com.kudodesign.entities.collision.KGCollisionEvent;
import com.kudodesign.entities.e2d.guis.GUIImage;
import com.kudodesign.entities.e2d.sprites.CollisionBox2D;
import com.kudodesign.entities.e2d.sprites.Sprite2D;
import com.kudodesign.entities.e2d.sprites.animations.Animation2D;
import kgegame.env.Platform;
import com.kudodesign.kgengine.KGE;
import com.kudodesign.kgengine.timers.KGTimerID;
import com.kudodesign.kgengine.timers.TimerListener;
import com.kudodesign.math.Vector2f;
import com.kudodesign.math.Vector3f;
import com.kudodesign.renderEngine.DisplayManager;
import com.kudodesign.toolbox.Maths;

public class Player extends Sprite2D implements InputConstants, TimerListener {

	PlatformDetector platformdetect;
	
	public Platform currentPlatform = null;

	boolean canFire = true;

	boolean firstPlack = false;

	// was 560
	float moveSpeed = 560;
	Animation2D idleAnim;
	Animation2D runAnim;
	Animation2D jumpAnim;

	KGTimerID fireTimer;

	Vector2f tmpPos = new Vector2f(0, 0);

	GUIImage HeartBar;

	Random r = new Random();

	public boolean enabled = true;

	float playerOffset = 48;
	// float vertCorrection = -0.0027435f;

	public int projLevel = 0;

	float lastMinY;

	GUIImage crossHair;
	public boolean onSlope = false;

	boolean onGround = false;
	boolean noMove;

	int jumpCount = 2;
	int currentJump = 0;
	int heartCount = 6;
	GUIImage equipped;
	public KGLight playerLight;

	boolean started = false;
	KGTimerID start;

	GUIImage plack1, plack2, plack3, plack4, plack5;

	GUIImage black;

	public Sprite2D cameraController;
	
	CollisionBox2D floordetect;

	public Player(String fileName, Vector3f position, Vector3f scale, boolean noMove) {

		super(fileName, position, scale);

		//getTexture().setFrameSheetCols(2);
		this.noMove = noMove;
		idleAnim = new Animation2D(fileName, 2, 1, 2, 0.2f);
		runAnim = new Animation2D("CharacterWalk", 6, 1, 6, 0.05f);
		jumpAnim = new Animation2D("CharacterJump", 2, 1, 2, 0.3f);
		addAnimation(idleAnim);
		addAnimation(runAnim);
		addAnimation(jumpAnim);
		setCurrentAnimation(idleAnim);
		this.setUseLighting(false);
		KGE.addSprite2D(this, "player");
		playerLight = new KGLight(new Vector3f(0, 0, 0.6f), new Vector3f(2f, 2f, 2f), new Vector3f(1, 0.1f, 1.6f));
		KGE.addKGLight(playerLight);
		this.addChild(playerLight);
		this.setCollision(Collision2DType.Solid, Body2DType.dynamic);
		this.addDefaultBodyPart(true);
		this.colliderTag = "player";
		this.addColliderTag("floor");
		this.addColliderTag("obstacle");
		this.addColliderTag("flag");

		cameraController = new Sprite2D("camController", getWorldPosition(), new Vector3f(1, 1, 1));
		cameraController.setVisible(false);
		
		floordetect = new CollisionBox2D(new Vector3f(0, 0, 0), 20, 20, Collision2DType.Overlap, Body2DType.dynamic, false);
		floordetect.setWorldPositionY(-40);
		floordetect.colliderTag = "detector";
		floordetect.addColliderTag("floor");
		this.addChild(floordetect);
		KGE.addCollisionBox2D(floordetect);

		//crossHair = new GUIImage("CrossHair");
		//KGE.addGUIImage(crossHair);

		start = KGE.setTimer(this, 0.5);

		black = KGE.addGUIImage("fadeInPixel");
		
		black.setScale2D(new Vector2f(DisplayManager.screenWidth, DisplayManager.screenHeight));
		
		gravity = -3000;
		
	//	System.out.println(th);
	//	System.out.println(this.width);
		
		
		/*platformdetect =  new PlatformDetector(this, new Vector3f(0, 0, 0), width, height/5, Collision2DType.Overlap, Body2DType.dynamic, true);
		
        
        platformdetect.getSpeed().y = -100;
		//platformdetect = new CollisionBox2D(new Vector3f(0, 0, 0), this.width, this.height/5, Collision2DType.Overlap, Body2DType.stationary, true);
		platformdetect.setWorldPositionY(-50);
		platformdetect.setWorldPositionX(0);
		
		platformdetect.colliderTag = "detector";
		platformdetect.addColliderTag("platform");
		
		KGE.addCollisionBox2D(platformdetect);
		addChild(platformdetect);*/

	}

	boolean firstTimeSpace = true;
	boolean charMoving = false;
	boolean charMovingX = false;
	public void update() {
		super.update();
		
		if (!started) {
			return;
		}
		
		if(firstTimeSpace)
		{
			charMoving = charMovingX = false;
		}

		//crossHair.getPosition().x = (KGInput.getMouseXWindow());
		//crossHair.getPosition().y = -(KGInput.getMouseYWindow());

		if (!enabled) {

			getSpeed().x = 0;
			onGround = false;
			if (this.getCurrentAnimation() != idleAnim) {
				this.setCurrentAnimation(idleAnim);
			}

		}

	
		

		if (!enabled) {
			super.update();
			return;
		}

		
		boolean charMovingY = false;
		//this.setSpeed(0, getSpeed().y);

		/*if (KGInput.inputActive(KEY_A)) {
			this.setSpeed(-moveSpeed, getSpeed().y);
			charMoving = true;
			charMovingX = true;
		}/*/
	
		if (KGInput.inputActivated(KEY_SPACE) && firstTimeSpace&&!noMove) {
			this.setSpeed(moveSpeed, getSpeed().y);
			charMoving = true;
			charMovingX = true;
			firstTimeSpace= false;
		}
		
		
		/*if (KGInput.inputActive(KEY_S)) {
			this.setSpeed(getSpeed().x, -90);
			charMoving = true;
			charMovingX = true;
		}*/

		if (!onGround) {

			// apply gravity
			this.getSpeed().y += gravity * DisplayManager.getMinDelta();

		}
		if (charMoving) {

			setFlipped(getSpeed().x < 0);

			setCurrentAnimation(runAnim);
		} else {

			if (this.getCurrentAnimation() != idleAnim) {

				setCurrentAnimation(idleAnim);
			}

		}

		if (KGInput.inputActivated(KEY_SPACE) && floordetect.overlappers.size() > 0) {

			//if (currentJump < jumpCount) {
				//if ((currentJump == 0 && onGround) || currentJump > 0) {
					this.getSpeed().y = 700;
					onGround = false;
					currentJump++;

				//}
			//}

		}

		Vector3f pos = Maths.lerp(cameraController.getWorldPosition(), getWorldPosition(),
				DisplayManager.getDelta() * 10);

		cameraController.setPosition(pos);

		cameraController.updateChildPositions();

		

		cameraController.update();

		if (KGInput.LMouseActivated && canFire) {

		}
		
		//and if on a platform, add its x speed to ours
		
		if(currentPlatform != null) {
			
			getSpeed().x += currentPlatform.getSpeed().x;
		
		}

	}


	public void decHeartCount() {

		heartCount--;
		updateHearts();

	}

	public void updateHearts() {

		HeartBar.getTexture().setFrameIndex(6 - heartCount);
	}

	@Override
	public void OnTimerEvent(KGTimerID ID) {
		if (ID == fireTimer) {
			canFire = true;
		}
		if (ID == start) {
			started = true;
			setVisible(true);
		}
		black.setVisible(false);

	}
	

}
