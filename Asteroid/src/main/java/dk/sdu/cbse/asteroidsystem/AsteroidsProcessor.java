package dk.sdu.cbse.asteroidsystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidsProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        double delta = gameData.getDelta();
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
}
