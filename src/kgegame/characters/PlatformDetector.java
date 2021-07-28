package kgegame.characters;

import com.kudodesign.entities.collision.KGCollisionEvent;
import com.kudodesign.entities.collision.KGBody2D.Body2DType;
import com.kudodesign.entities.collision.KGBody2D.Collision2DType;
import com.kudodesign.entities.e2d.sprites.CollisionBox2D;
import kgegame.env.Platform;
import com.kudodesign.math.Vector3f;

public class PlatformDetector extends CollisionBox2D {
	Player player;
	public PlatformDetector(Player _player, Vector3f position, float desiredWidth, float desiredHeight, Collision2DType collisionType,
			Body2DType bodyType, boolean generatePhysicsEvent) {
		super(position, desiredWidth, desiredHeight, collisionType, bodyType, generatePhysicsEvent);
		
		player = _player;
		
	}
	
	  @Override
      public void OnBeginOverlap(KGCollisionEvent e) {

      	if(e.collider1.colliderTag == "platform") {
      		
      		player.currentPlatform = (Platform) e.collider1;
      	}
      	
      }
      
      public void OnEndOverlap(KGCollisionEvent e) {

      	if(e.collider1.colliderTag == "platform") {
      		
      	 player.currentPlatform = null;
      	}
      	
      }

}
