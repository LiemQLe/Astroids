package dk.sdu.cbse.collisionsystem;

import java.util.ArrayList;
import java.util.List;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.commonasteroid.IAsteroid;
import dk.sdu.cbse.commonbullet.IBullet;
import dk.sdu.cbse.commonplayer.IPlayer;
import org.springframework.web.client.RestTemplate;

public class CollisionProcessor implements IEntityProcessingService, IGamePluginService {

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> entities = new ArrayList<>(world.getEntities());

        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity entityA = entities.get(i);
                Entity entityB = entities.get(j);

                if (checkCollision(entityA, entityB)) {
                    handleCollision(entityA, entityB, gameData, world);
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

    private void handleCollision(Entity entityA, Entity entityB, GameData gameData, World world) {
        
        // Only allow bullets to handle collision with asteroids, not the other way around
        if (entityA instanceof IBullet) {
            handleBulletCollision(entityA, entityB, world);
        } else if (entityB instanceof IBullet) {
            handleBulletCollision(entityB, entityA, world);
        }
    
        // Asteroid-asteroid or asteroid-player collisions only
        if ((entityA instanceof IAsteroid) || (entityB instanceof IAsteroid)) {
            handleAsteroidCollision(entityA, entityB, gameData, world);
        }
    
        // Remove entities if health is 0 or less
        if (entityA.getHealth() <= 0) {
            world.removeEntity(entityA);
        }
        if (entityB.getHealth() <= 0) {
            world.removeEntity(entityB);
        }
    }
    

    public void handleBulletCollision(Entity bullet, Entity target, World world) {
        if (bullet instanceof IBullet) {
            RestTemplate restTemplate = new RestTemplate();

            IBullet bulletEntity = (IBullet) bullet;
            if (bulletEntity.getOwner().getID().equals(target.getID())) {
                // In case the bullet come from shooter and hit shooter do nothing
                return;
            }
            target.setHealth(target.getHealth() - bullet.getDmg());
            world.removeEntity(bullet);
            if (bulletEntity.getOwner() instanceof IPlayer) {
                // If the bullet is from a player, increase the score by 10 points
                restTemplate.postForObject("http://localhost:8080/score/player?points=10", null, Void.class);
                System.out.println("Player hit an asteroid, score increased by 10 points.");
            } else {
                // If the bullet is from an enemy, increase the enemy score by 10 points
                restTemplate.postForObject("http://localhost:8080/score/enemy?points=10", null, Void.class);
            }
        }
    }

    public void handleAsteroidCollision(Entity asteroid, Entity target, GameData gameData, World world) {
        if (asteroid instanceof IAsteroid && target instanceof IAsteroid) {
            ((IAsteroid) asteroid).splitAsteroid(gameData, world, asteroid);
            ((IAsteroid) target).splitAsteroid(gameData, world, target);
        } else if (asteroid instanceof IAsteroid) {
            ((IAsteroid) asteroid).splitAsteroid(gameData, world, asteroid);
            target.setHealth(target.getHealth() - asteroid.getDmg());
        } else if (target instanceof IAsteroid) {
            ((IAsteroid) target).splitAsteroid(gameData, world, target);
            asteroid.setHealth(asteroid.getHealth() - target.getDmg());
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
