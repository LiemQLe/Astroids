package dk.sdu.cbse.asteroidsystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidsProcessor implements IEntityProcessingService {

    private static final long spawnInterval = 2000; // Spawn every 2 seconds
    private static long lastSpawnTime = System.currentTimeMillis();
    
    @Override
    public void process(GameData gameData, World world) {
        double delta = gameData.getDelta();
        long currentTime = System.currentTimeMillis();

        // Check if enough time has passed since the last spawn
        if (currentTime - lastSpawnTime >= spawnInterval) {
            createAsteroid(gameData, world);
            lastSpawnTime = currentTime;
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
 
            // Upper and lower bounds for the asteroid
            double posX = asteroid.getX();
            double posY = asteroid.getY();
            
            // Move the atroid in the direction it is facing
            double dx = Math.cos(Math.toRadians(asteroid.getRotation())) * asteroid.getSpeed();
            double dy = Math.sin(Math.toRadians(asteroid.getRotation())) * asteroid.getSpeed();
            asteroid.setX(posX + dx * delta);
            asteroid.setY(posY + dy * delta);

        }
        
    }

    public void createAsteroid(GameData gameData, World world) {
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

        world.addEntity(asteroid);
    }

}
