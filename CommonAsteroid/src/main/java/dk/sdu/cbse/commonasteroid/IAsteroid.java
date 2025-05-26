package dk.sdu.cbse.commonasteroid;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IAsteroid {
    /**
     * Gets the size of the asteroid.
     *
     * @return the size of the asteroid
     */
    int getSize();

    /**
     * Sets the size of the asteroid.
     *
     * @param size the new size of the asteroid
     */
    void setSize(int size);

    /**
     * Splits the asteroid into smaller pieces.
     *
     * @param gameData the game data containing information about the game state
     * @param world the world in which the asteroid exists
     * @param e the asteroid entity to be split
     */
    void splitAsteroid(GameData gameData, World world, Entity e);
}
