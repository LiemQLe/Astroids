package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.commonbullet.BulletSpawnerService;
import dk.sdu.cbse.common.data.World;

import java.util.Collection;
import static java.util.stream.Collectors.toList;
import java.util.ServiceLoader;

import dk.sdu.cbse.common.data.Entity;

public class EnemyControlSystem implements IEntityProcessingService {
    private long currentTime = System.currentTimeMillis();
    private long lastMoveTime = 0;
    private static long moveIntierval = 2000; // Move every 2 seconds
    private static int moveCount = 0;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            //time elapsed
            double delta = gameData.getDelta();
           
            if(currentTime - lastMoveTime >= moveIntierval) {
                double dx = Math.cos(Math.toRadians(enemy.getRotation())) * delta * enemy.getSpeed();
                double dy = Math.sin(Math.toRadians(enemy.getRotation())) * delta * enemy.getSpeed();
                enemy.setX(enemy.getX() + dx);
                enemy.setY(enemy.getY() + dy);
                lastMoveTime = currentTime;
                moveCount++;
                // Every 20 moves, set a random rotation for the enemy should be changed to the player position
                if(moveCount >= 20) {
                    // Set random rotation for enemy
                    enemy.setRotation(Math.random() * 360);
                    moveCount = 0;
                }
            }


            // Byllet cooldown logic
            Enemy e = (Enemy) enemy;
            if (e.getBulletCooldown() <= 0) {
                getBulletSPI().stream().findFirst().ifPresent(
                        spi -> {
                            world.addEntity(spi.createBullet(enemy, gameData));
                        });
                e.setBulletCooldown(0.5); // Set cooldown to 0.5 second
            } else {
                e.setBulletCooldown(e.getBulletCooldown() - gameData.getDelta());
            }

            // Keep enemy within game boundaries
            if (enemy.getX() < 0) {
                enemy.setX(1);
            }
            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(gameData.getDisplayWidth() - 1);
            }
            if (enemy.getY() < 0) {
                enemy.setY(1);
            }
            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(gameData.getDisplayHeight() - 1);
            }
            
        }
    }

    // Service loader for bullet spawner
    private Collection<? extends BulletSpawnerService> getBulletSPI() {
        return ServiceLoader.load(BulletSpawnerService.class).stream().map(ServiceLoader.Provider::get)
                .collect(toList());
    }

}
