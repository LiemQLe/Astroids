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
                    handleCollision(entityA, entityB);
                }
            }
        }
    }

    private boolean checkCollision(Entity entityA, Entity entityB) {
        if (Shape.intersect(entityA.getTransformedPolygon(), entityB.getTransformedPolygon()).getBoundsInLocal()
                .getWidth() > 0) {
            return true;
        }
        return false;
    }

    private void handleCollision(Entity entityA, Entity entityB) {
        //System.out.println("Collision Detected between " + entityA.getClass().getSimpleName() + " and " + entityB.getClass().getSimpleName());
        // Bullet collision
        if (entityA instanceof BulletMarker){
            BulletMarker bulletA = (BulletMarker) entityA;
            System.out.println(bulletA.getOwner().getID() + " ----------------- " + entityB.getID());
            if(bulletA.getOwner().getID().equals(entityB.getID())){
                //In case the bullet come from shooter and hit shooter do nothing
                return;
            }
        }else if (entityB instanceof BulletMarker){
            BulletMarker bulletB = (BulletMarker) entityB;
            if(bulletB.getOwner().getID().equals(entityA.getID())){
                //In case the bullet come from shooter and hit shooter do nothing
                return;
            }
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
