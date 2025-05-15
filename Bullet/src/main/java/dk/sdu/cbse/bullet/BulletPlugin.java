package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public class BulletPlugin implements IGamePluginService{
    
    // Register the plugin
    @Override
    public void start(GameData gameData, World world) {
        
    }

    // Remove all the bullets from the world
    @Override
    public void stop(GameData gameData, World world) {
        for(Entity e : world.getEntities(Bullet.class)){
            world.removeEntity(e);
        }
    }
}
