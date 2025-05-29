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
    private long lastMoveTime = 0;
    private static long rotateIntierval = 1000; // Rotate every 1 seconds

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            // time elapsed
            double delta = gameData.getDelta();
            long currentTime = System.currentTimeMillis();

            double dx = Math.cos(Math.toRadians(enemy.getRotation())) * delta * enemy.getSpeed();
            double dy = Math.sin(Math.toRadians(enemy.getRotation())) * delta * enemy.getSpeed();
            enemy.setX(enemy.getX() + dx);
            enemy.setY(enemy.getY() + dy);

            if (currentTime - lastMoveTime >= rotateIntierval) {
                lastMoveTime = currentTime;
                enemy.setRotation(Math.random() * 360);

            }

            // Bullet cooldown logic
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
