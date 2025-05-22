package dk.sdu.cbse.commonbullet;
import dk.sdu.cbse.common.data.Entity;


/**
 * The Bullet class represents a bullet entity in the game.
 * It extends the Entity class and serves as a marker for bullet entities.
 * This class can be used to identify bullet entities in the game world.
 */
public interface BulletMarker {
    /**
     * Sets the owner of the bullet.
     *
     * @param owner The entity that owns the bullet.
     */
    Entity getOwner();
} 
