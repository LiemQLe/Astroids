package dk.sdu.cbse.collisionsystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.shape.Shape;



public class CollisionProcessor implements IEntityProcessingService, IGamePluginService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entityA : world.getEntities()) {
            for (Entity entityB : world.getEntities()) {
                if (entityA != entityB && checkCollision(entityA, entityB)) {
                    System.out.println("Collision detected between " + entityA.getClass() + " and " + entityB.getClass());
                }
            }
        }
    }

    private boolean checkCollision(Entity entityA, Entity entityB) {
        if(Shape.intersect(entityA.getTransformedPolygon(), entityB.getTransformedPolygon()).getBoundsInLocal().getWidth() > 0) {
            return true;
        }
        return false; 
    }

    private void handleCollision(Entity entityA, Entity entityB) {
        // Implement collision handling logic here
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
