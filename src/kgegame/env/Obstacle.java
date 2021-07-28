package kgegame.env;

import com.kudodesign.entities.collision.KGCollisionEvent;
import com.kudodesign.entities.collision.KGBody2D.Body2DType;
import com.kudodesign.entities.collision.KGBody2D.Collision2DType;
import com.kudodesign.entities.e2d.sprites.Sprite2D;
import com.kudodesign.kgengine.KGE;
import com.kudodesign.math.Vector3f;

import kgegame.scenes.Instruct;
import kgegame.scenes.Lose;

public class Obstacle extends Sprite2D {

	public Obstacle(String fileName, Vector3f position, Vector3f scale) {
		super(fileName, position, scale);
	
		this.setCollision(Collision2DType.Overlap, Body2DType.stationary);
		this.addDefaultBodyPart(true);
		this.colliderTag = "obstacle";
		this.addColliderTag("player");
		//this.addColliderTag("detector");
		KGE.addSprite2D(this, "player");
	}
	
	@Override
	public void OnBeginOverlap(KGCollisionEvent e) 
	{
		KGE.setScene(Lose.class);
	}

}