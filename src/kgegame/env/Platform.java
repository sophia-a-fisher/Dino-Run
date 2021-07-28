package kgegame.env;

import com.kudodesign.entities.collision.KGBody2D.Body2DType;
import com.kudodesign.entities.collision.KGBody2D.Collision2DType;
import com.kudodesign.entities.e2d.sprites.Sprite2D;
import com.kudodesign.kgengine.KGE;
import com.kudodesign.math.Vector3f;

public class Platform extends Sprite2D {

	public Platform(String fileName, Vector3f position, Vector3f scale) {
		super(fileName, position, scale);
	
		this.setCollision(Collision2DType.Solid, Body2DType.stationary);
		this.addDefaultBodyPart(true);
		this.colliderTag = "floor";
		this.addColliderTag("player");
		this.addColliderTag("detector");
		KGE.addSprite2D(this, "player");
	}

}
