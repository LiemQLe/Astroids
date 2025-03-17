package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.GameData;

public interface IEntityProcessingService {
    
    /**
     *
     * Update the entity based on the game data and world
     * Can also say it updates based on frame
     *
     * @param gameData
     * @param world
     * @throws
     */
    void process(GameData gameData, World world);

}
