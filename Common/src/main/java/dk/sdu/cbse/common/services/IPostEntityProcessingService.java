package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 *
 * Handles logic when the entity is done processing such as collision detection, removing dead entities etc.
 */
public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
