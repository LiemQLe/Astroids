package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * Interface for the GamePluginService
 * 
 * The GamePluginService is used to add and remove entities
 * to the game when the game start or stop
 * 
 * @param gameData
 * @param world
 * @throws
 */

public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
