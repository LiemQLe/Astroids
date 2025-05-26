package dk.sdu.cbse.commonasteroid;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
/**
 * The AsteroidSpawnerInterface is used to create asteroid entities in the game.
 * It defines a method for creating asteroids based on the provided entity and game data.
 */
public interface AsteroidSpawnerInterface {
    /**
     * Creates an asteroid entity based on the provided entity and game data.
     *
     * @param e the entity that will be used to create the asteroid
     * @param gameData the game data containing information about the current game state
     * @return a new asteroid entity
     */
    Entity createAsteroid(Entity e, GameData gameData);
}
