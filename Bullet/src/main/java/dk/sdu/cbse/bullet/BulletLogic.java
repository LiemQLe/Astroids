package dk.sdu.cbse.bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.commonbullet.BulletSpawnerService;

public class BulletLogic implements IEntityProcessingService, BulletSpawnerService {

   @Override
public void process(GameData gameData, World world) {
    double delta = gameData.getDelta();

    for (Entity bullet : world.getEntities(Bullet.class)) {
        
        //move the bullet in the direction it is facing
        double dx = Math.cos(Math.toRadians(bullet.getRotation())) * bullet.getSpeed();
        double dy = Math.sin(Math.toRadians(bullet.getRotation())) * bullet.getSpeed();
        bullet.setX(bullet.getX() + dx * delta );
        bullet.setY(bullet.getY() + dy * delta );

        if (bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth() ||
            bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight()) {
            world.removeEntity(bullet);
        }
    }
}


    @Override
    public Entity createBullet(Entity e, GameData gameData) {
        Bullet bullet = new Bullet();

        // Used to make sure the bullets won't hurt the shooter itself
        bullet.setOwner(e);
        bullet.setSpeed(300f);
        bullet.setPosition(e.getX() , e.getY());
        bullet.setRotation(e.getRotation());
        bullet.setPolygonCoordination(2, -2, 2, 2, -2, 2, -2, -2);
        bullet.setRadius(bullet.calcRadius());
        bullet.setDmg(1);

        return bullet;

    }
    
}
