package dk.sdu.cbse.collisionsystem;

import java.util.ArrayList;
import java.util.List;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.commonbullet.BulletMarker;
import dk.sdu.cbse.commonasteroid.AsteroidMarker;

public class CollisionProcessor implements IEntityProcessingService, IGamePluginService {

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> entities = new ArrayList<>(world.getEntities());

        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity entityA = entities.get(i);
                Entity entityB = entities.get(j);

                if (checkCollision(entityA, entityB)) {
                    handleCollision(entityA, entityB, world);
                }
            }
        }

    }

    private boolean checkCollision(Entity entityA, Entity entityB) {
        double radiusSum = entityA.getRadius() + entityB.getRadius();
        double dist = Math
                .sqrt(Math.pow(entityA.getX() - entityB.getX(), 2) + Math.pow(entityA.getY() - entityB.getY(), 2));
        if (dist < radiusSum) {

            return true;
        }
        return false;
    }

    private void handleCollision(Entity entityA, Entity entityB, World world) {

        
        // Bullet collision
        if (entityA instanceof BulletMarker) {
            BulletMarker bulletA = (BulletMarker) entityA;
            if (bulletA.getOwner().getID().equals(entityB.getID())) {
                // In case the bullet come from shooter and hit shooter do nothing
                return;
            }
            entityA.setHealth(entityA.getHealth() - 1);
            entityB.setHealth(entityB.getHealth() - 1);

        } else if (entityB instanceof BulletMarker) {
            BulletMarker bulletB = (BulletMarker) entityB;
            if (bulletB.getOwner().getID().equals(entityA.getID())) {
                // In case the bullet come from shooter and hit shooter do nothing
                return;
            }
            // Remove bullet from world and hit entity
            entityB.setHealth(entityB.getHealth() - 1);
            entityA.setHealth(entityA.getHealth() - 1);
        }
        // Astroid collision on Player, Enemy or Asteroid
        boolean aIsAsteroid = entityA instanceof AsteroidMarker;
        boolean bIsAsteroid = entityB instanceof AsteroidMarker;

        // Substract Asteroid dmg from health
        if (aIsAsteroid && bIsAsteroid) {
            entityA.setHealth(entityA.getHealth() - entityA.getDmg());
            entityB.setHealth(entityB.getHealth() - entityB.getDmg());
        } else if (aIsAsteroid) {
            entityB.setHealth(entityB.getHealth() - entityA.getDmg());
        } else if (bIsAsteroid) {
            entityA.setHealth(entityA.getHealth() - entityB.getDmg());
        }

        if (entityA.getHealth() <= 0) {
            world.removeEntity(entityA);
        }
        if (entityB.getHealth() <= 0) {
            world.removeEntity(entityB);
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
