package dk.sdu.cbse.commonbullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

/**
 * The BulletSpawnerService is used to create bullet entities in the game.
 * It defines a method for creating bullets based on the provided entity and game data.
 */
public interface BulletSpawnerService {
    
    Entity createBullet(Entity e, GameData gameData);
}
