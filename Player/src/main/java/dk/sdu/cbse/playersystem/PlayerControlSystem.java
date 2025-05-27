package dk.sdu.cbse.playersystem;

import java.util.Collection;
import java.util.ServiceLoader;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.commonbullet.BulletSpawnerService;

import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            double delta = gameData.getDelta();
            
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 5);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 5);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {

                double dx = Math.cos(Math.toRadians(player.getRotation()))* delta * player.getSpeed();
                double dy = Math.sin(Math.toRadians(player.getRotation()))* delta * player.getSpeed();

                player.setX(player.getX() + dx);
                player.setY(player.getY() + dy);
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                // Byllet cooldown logic
                Player p = (Player) player;
                if (p.getBulletCooldown() <= 0) {
                    getBulletSPI().stream().findFirst().ifPresent(
                            spi -> {
                                world.addEntity(spi.createBullet(player, gameData));
                            });
                    p.setBulletCooldown(0.5); // Set cooldown to 0.5 second
                } else {
                    p.setBulletCooldown(p.getBulletCooldown() - gameData.getDelta());
                }
                    
            }

            if (player.getX() < 0) {
                player.setX(1);
            }

            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(gameData.getDisplayWidth() - 1);
            }

            if (player.getY() < 0) {
                player.setY(1);
            }

            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(gameData.getDisplayHeight() - 1);
            }

        }
    }

    // Service loader for bullet spawner
    private Collection<? extends BulletSpawnerService> getBulletSPI() {
        return ServiceLoader.load(BulletSpawnerService.class).stream().map(ServiceLoader.Provider::get)
                .collect(toList());
    }
}
