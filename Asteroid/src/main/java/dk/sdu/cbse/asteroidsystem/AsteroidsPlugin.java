package dk.sdu.cbse.asteroidsystem;

import java.util.Arrays;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class AsteroidsPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        Entity asteroid = createAsteroid(gameData, world);
        world.addEntity(asteroid);

    }

    @Override
    public void stop(GameData gameData, World world) {
        // TODO Auto-generated method stub
        System.out.println("AsteroidsPlugin stopped");
    }

    public Entity createAsteroid(GameData gameData, World world) {
        Asteroid asteroid = new Asteroid();
        asteroid.setPolygonCoordination(
                90, 0,
                45, 75,
                -45, 75,
                -90, 0,
                -45, -75,
                45, -75);

        // Decide spawn point for asteroid randomly
        int rand = (int) (Math.random() * 4);
        switch (rand) {
            case 1:
                asteroid.setX(0);
                asteroid.setY((double) (Math.random() * gameData.getDisplayHeight()));
                break;
            case 2:
                asteroid.setX(gameData.getDisplayWidth());
                asteroid.setY((double) (Math.random() * gameData.getDisplayHeight()));
                break;
            case 3:
                asteroid.setX((double) (Math.random() * gameData.getDisplayWidth()));
                asteroid.setY(0);
                break;
            default:
                asteroid.setX((double) (Math.random() * gameData.getDisplayWidth()));
                asteroid.setY(gameData.getDisplayHeight());
                break;
        }
        asteroid.setRadius(asteroid.calcRadius());
        asteroid.setSpeed(50);
        asteroid.setDmg(10);
        asteroid.setSize(3);

        // Set the rotation of the asteroid towards the center of the screen
        double centerX = gameData.getDisplayWidth() / 2;
        double centerY = gameData.getDisplayHeight() / 2;
        double angle = Math.atan2(centerY - asteroid.getY(), centerX - asteroid.getX());
        asteroid.setRotation(Math.toDegrees(angle));

        return asteroid;
    }
}
