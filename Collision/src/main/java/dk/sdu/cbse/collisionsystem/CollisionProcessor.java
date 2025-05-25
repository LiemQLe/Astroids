package dk.sdu.cbse.collisionsystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.shape.Shape;
import dk.sdu.cbse.commonbullet.BulletMarker;

public class CollisionProcessor implements IEntityProcessingService, IGamePluginService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entityA : world.getEntities()) {
            for (Entity entityB : world.getEntities()) {
                if (entityA != entityB && checkCollision(entityA, entityB)) {
                    handleCollision(entityA, entityB, world);
                }
            }
        }
    }

    private boolean checkCollision(Entity entityA, Entity entityB) {
        double radiusSum = entityA.getRadius() + entityB.getRadius();
        double dist = Math.sqrt(Math.pow(entityA.getX() - entityB.getX(), 2) + Math.pow(entityA.getY() - entityB.getY(), 2));
        if( dist < radiusSum) {
            
            return true;
        }
        return false;
    }

    private void handleCollision(Entity entityA, Entity entityB, World world) {
       
        // Bullet collision
        if (entityA instanceof BulletMarker){
            BulletMarker bulletA = (BulletMarker) entityA;
            if(bulletA.getOwner().getID().equals(entityB.getID())){
                // In case the bullet come from shooter and hit shooter do nothing
                return;
            }
            // Remove bullet from world and hit entity
            world.removeEntity((Entity) bulletA);
            world.removeEntity((Entity) entityB);

        }else if (entityB instanceof BulletMarker){
            BulletMarker bulletB = (BulletMarker) entityB;
            if(bulletB.getOwner().getID().equals(entityA.getID())){
                //In case the bullet come from shooter and hit shooter do nothing
                return;
            }
            // Remove bullet from world and hit entity
            world.removeEntity((Entity) bulletB);
            world.removeEntity((Entity) entityA);
        }


    }

    @Override
    public void start(GameData gameData, World world) {
        // Initialization logic if needed
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Cleanup logic if needed
    }

}
